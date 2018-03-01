package com.app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.IOUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.app.entity.Alipay;
import com.app.entity.Mobpay;
import com.app.entity.ServiceOrder;
import com.app.entity.Wxpay;
import com.app.service.MobpayService;
import com.app.service.ServiceOrderService;
import com.app.util.Application;
import com.app.util.mob.HttpsResult;
import com.app.util.mob.RequestHttpsUtils;
import com.xiaoleilu.hutool.json.JSONUtil;

/**
 * 支付
 * @author taoxiangfei
 *
 */
@Controller
@RequestMapping("mobpay")
public class MobpayController {
	
	@Resource(name="serviceOrderService")
	private ServiceOrderService serviceOrderService;
	
	@Resource(name="mobpayService")
	private MobpayService mobpayService;
	
	private Logger logger = LoggerFactory.getLogger(MobpayController.class);

	/**
	 * 创建支付订单
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/preparepay.do", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String createOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");//支付渠道
		String orderId = request.getParameter("orderId");//订单号
		String plat = request.getParameter("plat");//平台类型
		System.out.println(type+orderId+plat);
		//根据订单号获取订单信息
		ServiceOrder serviceOrder = serviceOrderService.getServiceOrderByOrderId(orderId);
		//创建交易map
		Map<String,String> payMap = createMap(serviceOrder,"1",plat);
		//创建支付实体类
		Mobpay mobpay = createMobpay(serviceOrder,"1",plat);
		mobpay.setPayChannel(Integer.parseInt(type));
		payMap.put("pay_channel", type);
		System.out.println(payMap.toString());
		HttpsResult httpsResult = RequestHttpsUtils.send(Application.mobpreparepay, payMap);
        String replyMsg = httpsResult.getReplyMsg();
        System.out.println("返回的数据："+replyMsg);
        com.xiaoleilu.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(replyMsg);// 解析返回的数据
        System.out.println(jsonObject.toString());
        mobpay.setCode(jsonObject.get("code").toString());
        mobpay.setMsg(jsonObject.get("msg").toString());
        mobpay.setTicketId(jsonObject.get("ticket_id").toString());
        mobpay.setNonceStrBack(jsonObject.get("nonce_str").toString());
        mobpay.setSignBack(jsonObject.get("sign").toString());
        mobpayService.save(mobpay);
		return jsonObject.toString();
	}
	/**
	 * 支付通知
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/notifyPay.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String notifyPay(@RequestParam("ticket_id") String ticket_id,@RequestParam("pay_at") String pay_at,@RequestParam("result") String result,
						@RequestParam("trade_msg") String trade_msg,@RequestParam("order_id") String order_id,@RequestParam("amount") String amount,
						@RequestParam("pay_channel") String pay_channel,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(ticket_id+","+pay_at+","+result+","+trade_msg+","+order_id+","+amount+","+pay_channel);
		return null;
	}
	
	/**
	 * 支付通知
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/payBackUrl.do", produces = "text/html;charset=UTF-8")
	public void payBackUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//读取request内容为字符串
        String reqcontent = readRequest(request);
        String jsonstr = "{";
		String[] arraystr = reqcontent.split("&");
		for (int i = 0; i < arraystr.length; i++) {
			String[] arraystra = arraystr[i].split("=");
			for(int j=0;j<arraystra.length;j++) {
				jsonstr+="\""+arraystra[j]+"\":";
			}
			jsonstr = jsonstr.substring(0, jsonstr.length()-1)+",";
		}
		jsonstr = jsonstr.substring(0, jsonstr.length()-1)+"}";
		System.out.println(jsonstr);
		JSONObject obj = JSON.parseObject(jsonstr);
		System.out.println(obj.toJSONString());
		//保存通知结果
		saveResult(obj);
	}
	/**
	 * 保存通知结果
	 * @param obj
	 */
	private void saveResult(JSONObject obj) {
		String sign = obj.getString("sign");
		String amount = obj.getString("amount");
		String result = obj.getString("result");
		String ticketId = obj.getString("ticketId");
		String appkey = obj.getString("appkey");
		String payAt = obj.getString("payAt");
		String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(Long.parseLong(payAt)));
		String nonce_str = obj.getString("nonce_str");
		String msg = obj.getString("msg");
		String channel = obj.getString("channel");
		String orderId = obj.getString("orderId");
		ServiceOrder serviceOrder = new ServiceOrder();
		serviceOrder.setSign(sign);
		serviceOrder.setAmount(amount);
		serviceOrder.setResult(result);
		serviceOrder.setTicketId(ticketId);
		serviceOrder.setAppkey(appkey);
		serviceOrder.setPayAt(date);
		serviceOrder.setNonce_str(nonce_str);
		serviceOrder.setMsg(msg);
		serviceOrder.setChannel(channel);
		if(result != null && "2".equals(result)) {
			if(orderId.endsWith("W")) {
				//尾款
				serviceOrder.setState("1000");
				serviceOrder.setOrderId(orderId.substring(0, orderId.length()-1));
				serviceOrder.setOrderIdW(orderId);
				serviceOrderService.savePayBackW(serviceOrder);
			}else {
				//新订单
				serviceOrder.setState("1002");
				serviceOrder.setOrderId(orderId);
				serviceOrderService.savePayBack(serviceOrder);
				
			}
		}
		
	}
	/**
	 * 读取request内容为字符串
	 * @param request
	 * @return
	 * @throws IOException
	 */
	private String readRequest(HttpServletRequest request) throws IOException {
		// 请求消息实体的总长度(请求消息中除消息头之外的数据长度)
        int totalbytes = request.getContentLength();
        System.out.println("接收到的长度："+totalbytes);
        // 容纳请求消息实体的字节数组
        byte[] dataOrigin = new byte[totalbytes];
        ServletInputStream ris = request.getInputStream();
        StringBuilder content = new StringBuilder();
        byte[] b = new byte[1024];
        int lens = -1;
        while ((lens = ris.read(b)) > 0) {
        	System.out.println(b);
            content.append(new String(b, 0, lens));
        }
        String reqcontent = content.toString();
        // 关闭数据流
        ris.close();
        System.out.println("接收到的字符串："+reqcontent);
		return reqcontent;
	}
	/**
	 * 创建传输map
	 * @param serviceOrder	服务订单
	 * @param string	订单类型
	 * @param String 平台类型
	 * @return
	 */
	private Map<String, String> createMap(ServiceOrder serviceOrder, String orderType,String plat) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("appkey", Application.mobappkey);
		String orderIdSe = "";
		if("1".equals(orderType)) {
			orderIdSe = serviceOrder.getOrderId()+"01";
		}else {
			orderIdSe = serviceOrder.getOrderId()+"02";
		}
		map.put("order_id", orderIdSe);
		map.put("app_user_id", serviceOrder.getCustomerId());
		System.out.println("money:"+serviceOrder.getMoney());
		map.put("amount", String.valueOf(new BigDecimal(serviceOrder.getMoney()).multiply(new BigDecimal(100)).intValue()));
		map.put("subject", "家享精服——"+serviceOrder.getServiceName());
		map.put("client_ip", "127.0.0.1");
		map.put("plat", plat);
		return map;
	}
	/**
	 * 创建支付对象
	 * @param serviceOrder
	 * @return
	 */
	private Mobpay createMobpay(ServiceOrder serviceOrder,String orderType,String plat) {
		String orderIdSe = "";
		String ordertype = "";
		if("1".equals(orderType)) {
			orderIdSe = serviceOrder.getOrderId()+"01";
			ordertype = "1";
		}else {
			orderIdSe = serviceOrder.getOrderId()+"02";
			ordertype = "2";
		}
		Mobpay mobpay = new Mobpay();
		mobpay.setAppkey(Application.mobappkey);
		mobpay.setOrderId(serviceOrder.getOrderId());
		mobpay.setOrderIdSe(orderIdSe);
		mobpay.setOrderType(ordertype);
		mobpay.setAppUserId("");
		mobpay.setAppUserNickname("");
		mobpay.setAmount(new BigDecimal(serviceOrder.getMoney()).multiply(new BigDecimal(100)).intValue());
		mobpay.setSubject("家享精服——"+serviceOrder.getServiceName());
		return mobpay;
	}
}

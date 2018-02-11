package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.app.entity.Alipay;
import com.app.entity.ServiceOrder;
import com.app.service.AlipayService;
import com.app.service.ServiceOrderService;
import com.app.util.Application;
/**
 * 支付宝支付
 * @author taoxiangfei
 *
 */
@Controller
@RequestMapping("alipay")
public class AlipayController {
	@Resource(name="serviceOrderService")
	private ServiceOrderService serviceOrderService;
	
	@Resource(name="alipayService")
	private AlipayService alipayService;
	public Logger logger = LoggerFactory.getLogger("alipay");
	private String appid = Application.aliAppid;
	private String notifyUrl = Application.aliNotifyUrl;
	private String privatekey = Application.aliPrivateKey;
	private String publickey = Application.aliPublicKey;
	private String tradeUrl = Application.aliPayTradeUrl;
	
	/**
	 * 创建订单
	 * @param serviceOrder
	 * @return
	 */
	public String createOrder(ServiceOrder serviceOrder) {
		// 实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(tradeUrl, appid,privatekey, "json", "utf-8", publickey, "RSA2");
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setSubject(serviceOrder.getServiceName());
		model.setOutTradeNo(serviceOrder.getOrderId());
		model.setTimeoutExpress("30m");
		model.setTotalAmount("0.01");
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		try {
//			logger.trace("创建订单："+request.toString());
			// 这里和普通的接口调用不同，使用的是sdkExecute
			System.out.println(logger);
			logger.trace("支付宝创建订单，请求内容：OutTradeNo："+model.getOutTradeNo()+",subject:"+model.getSubject()+",getTimeoutExpress:"+model.getTimeoutExpress()+
						",getTotalAmount:"+model.getTotalAmount()+",getProductCode:"+model.getProductCode());
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			logger.trace("支付宝创建订单，返回内容："+response.getBody());
			System.out.println(response.getBody());// 就是orderString 可以直接给客户端请求，无需再做处理。
//			logger.trace("创建订单返回："+response.getBody());
			return response.getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
			logger.error("支付宝创建订单，请求失败，请求内容：OutTradeNo："+model.getOutTradeNo()+",subject:"+model.getSubject()+",getTimeoutExpress:"+model.getTimeoutExpress()+
						",getTotalAmount:"+model.getTotalAmount()+",getProductCode:"+model.getProductCode());
			return null;
		}

	}
	/**
	 * 查询支付宝订单
	 * @param ordernum
	 * @return
	 */
	@RequestMapping(value = "/queryOrder.do", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String QueryOrder(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		AlipayClient alipayClient = new DefaultAlipayClient(tradeUrl, appid,privatekey, "json", "utf-8", publickey,"RSA2");
		AlipayTradeQueryRequest alirequest = new AlipayTradeQueryRequest();
		alirequest.setBizContent("{" +
		"\"out_trade_no\":\""+orderId+"\"" +
		"}");
		AlipayTradeQueryResponse aliresponse;
		try {
			aliresponse = alipayClient.execute(alirequest);
			if(aliresponse.isSuccess()){
				System.out.println("调用成功");
			} else {
				System.out.println("调用失败");
			}
			System.out.println(aliresponse.getBody());
			return aliresponse.getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 订单退款
	 * @return
	 */
	@RequestMapping(value = "/refundOrder", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String RefundOrder(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		String refundAmount = request.getParameter("refundAmount");
		AlipayClient alipayClient = new DefaultAlipayClient(tradeUrl, appid,privatekey, "json", "utf-8", publickey,"RSA2");
		AlipayTradeRefundRequest alirequest = new AlipayTradeRefundRequest();
		int a = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;
		alirequest.setBizContent("{" + "    \"out_trade_no\":\"" + orderId + "\"," + "    \"refund_amount\":\""
				+ refundAmount + "\"," + "    \"refund_reason\":\"正常退款\"," + "    \"out_request_no\":\""
				+ Integer.toString(a) + "\"" + "  }");
		System.out.print(alirequest.getBizContent());
		try {
			AlipayTradeRefundResponse aliresponse = alipayClient.execute(alirequest);
			System.out.println(aliresponse.getBody());
			return aliresponse.getBody();

		} catch (AlipayApiException e) {
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * 订单退款查询
	 * @return
	 */
	@RequestMapping(value = "/refundQuery", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String refundQuery(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		AlipayClient alipayClient = new DefaultAlipayClient(tradeUrl,appid,privatekey,"json","utf-8",publickey,"RSA2");
		AlipayTradeFastpayRefundQueryRequest alirequest = new AlipayTradeFastpayRefundQueryRequest();
		alirequest.setBizContent("{" +
		"\"out_trade_no\":\"2014112611001004680073956707\"," +
		"\"out_request_no\":\"2014112611001004680073956707\"" +
		"}");
		AlipayTradeFastpayRefundQueryResponse aliresponse;
		try {
			aliresponse = alipayClient.execute(alirequest);
			if(aliresponse.isSuccess()){
				System.out.println("调用成功");
			} else {
				System.out.println("调用失败");
			}
			return aliresponse.getBody();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 支付宝支付回调
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value = "/aliCreateOrderBackUrl.do", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	public void aliBackUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			request.setCharacterEncoding("UTF-8");
	        int totalbytes = request.getContentLength();
	        System.out.println(totalbytes);
	        byte[] dataOrigin = new byte[totalbytes];
	        ServletInputStream ris = request.getInputStream();
	        StringBuilder content = new StringBuilder();
	        byte[] b = new byte[1024];
	        int lens = -1;
	        while ((lens = ris.read(b)) > 0) {
	            content.append(new String(b, 0, lens));
	        }
	        String reqcontent = content.toString();
	        
	        ris.close();
	        System.out.println("reqcontent:"+reqcontent);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i = 0; i < values.length; i++) {
		        valueStr = (i == values.length - 1) ? valueStr + values[i]
		                    : valueStr + values[i] + ",";
		  	}
		  //乱码解决，这段代码在出现乱码时使用。
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
		try {
			boolean flag = AlipaySignature.rsaCheckV1(params, Application.aliPublicKey, "utf-8","RSA2");
			if(flag) {
				Alipay alipay = new Alipay();
				alipay.setNotifyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(params.get("notify_time")));
				alipay.setSign(params.get("sign"));
				alipay.setTradeno(params.get("trade_no"));
				alipay.setOuttradeno(params.get("out_trade_no"));
				alipay.setBuyerUserId(params.get("buyer_id"));
				alipay.setBuyerLogonId(params.get("buyer_logon_id"));
				alipay.setTradeStatus(params.get("trade_status"));
				alipay.setTotalAmount(params.get("total_amount"));
				alipay.setSubject(params.get("subject"));
				alipay.setGmtPayment(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(params.get("gmt_payment")));
				alipay.setMoneyType("start");
				alipayService.updateNotify(alipay);
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

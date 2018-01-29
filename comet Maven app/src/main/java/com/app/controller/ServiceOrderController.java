package com.app.controller;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.app.common.BaseResult;
import com.app.entity.Alipay;
import com.app.entity.ServiceOrder;
import com.app.service.AlipayService;
import com.app.service.ServiceOrderService;
import com.app.util.Application;
import com.app.util.pay.bean.PayOrder;
import com.app.util.wxpay.api.WxPayConfigStorage;
import com.app.util.wxpay.api.WxPayService;
import com.app.util.wxpay.bean.WxTransactionType;



@Controller
@RequestMapping("serviceOrder")
public class ServiceOrderController {

	@Resource(name="serviceOrderService")
	private ServiceOrderService serviceOrderService;
	
	@Resource(name="alipayService")
	private AlipayService alipayService;
	
	/**
	 * 创建支付订单
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/createOrder.action", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String createOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		String orderId = request.getParameter("orderId");
		ServiceOrder serviceOrder = serviceOrderService.getServiceOrderByOrderId(orderId);
		if(type != null && "0".equals(type)) {
//			WxConnectMpay wxConnectMpay = new WxConnectMpay();
//			String resp = wxConnectMpay.createWXOrder(serviceOrder);
//			System.out.println("resp:"+resp);
			
			WxPayConfigStorage wxPayConfigStorage = new WxPayConfigStorage();
	        wxPayConfigStorage.setMchId(Application.wxmchid);
	        wxPayConfigStorage.setAppid(Application.wxAPPID);
	        wxPayConfigStorage.setKeyPublic(Application.wxpayKey);
	        wxPayConfigStorage.setKeyPrivate(Application.wxpayKey);
	        wxPayConfigStorage.setNotifyUrl(Application.wxBackUrl);
	        wxPayConfigStorage.setSignType("MD5");
	        wxPayConfigStorage.setInputCharset("utf-8");

	        WxPayService wxpayservice =  new WxPayService(wxPayConfigStorage);
	        PayOrder payOrder = new PayOrder(serviceOrder.getServiceName(), "",  new BigDecimal(0.01) , serviceOrder.getOrderId());
	        payOrder.setTransactionType(WxTransactionType.APP);
	        JSONObject jsonobj = wxpayservice.unifiedOrder(payOrder);
	        System.out.println(jsonobj);
		}
		if(type != null && "1".equals(type)) {
			//支付宝支付
//			AlipayConnectMpay alipayConnectMpay = new AlipayConnectMpay();
			AlipayController alipayController = new AlipayController();
			String resp = alipayController.createOrder(serviceOrder);
			if(resp != null) {
				try {
					String orderStr = URLDecoder.decode(resp,"UTF-8");
					System.out.println("orderStr"+orderStr);
					String[] a = orderStr.split("&");
					Map<String, String> m = new HashMap<String, String>();
					for(String s:a){
						String[] ms = s.split("=");
						m.put(ms[0], ms[1]);
					}
					String bizContent = m.get("biz_content");
					JSONObject jsonObj = JSONObject.parseObject(bizContent);
					Alipay alipay = new Alipay();
					alipay.setConsumerid(serviceOrder.getCustomerId());
					alipay.setInsertdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(m.get("timestamp")));
					alipay.setOuttradeno(jsonObj.getString("out_trade_no"));
					alipay.setTotalAmount(jsonObj.getString("total_amount"));
					alipay.setSubject(jsonObj.getString("subject"));
					alipay.setTimeoutExpress(jsonObj.getString("timeout_express"));
					alipay.setMoneyType("start");
					alipayService.createOrder(alipay);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return resp;
			}
			
		}
		
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("all")
	@ResponseBody
	public List<ServiceOrder> getServiceOrderAll() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String toyear = formatter.format(new Date());
		ServiceOrder serviceOrder=new ServiceOrder();
		serviceOrder.setToyear(toyear);
		List<ServiceOrder> serviceOrderList=serviceOrderService.getServiceOrderAll(serviceOrder);
		return serviceOrderList;
	}

	/**
	 * 根据条件查询订单
	 * @param city
	 * @param time
	 * @param state
	 * @param sc
	 * @return
	 */
	@RequestMapping(value="findByCondition", method = RequestMethod.POST)
	@ResponseBody
	public List<ServiceOrder> getServiceOrderByCondition(String city,String time,
			String state,Integer sc) {
		//实例化订单类
		ServiceOrder serviceOrder=new ServiceOrder();
		serviceOrder.setCity(city);
		if ("已完成".equals(state)) { 
			serviceOrder.setState("1000");
		}else if("待支付".equals(state)){
			serviceOrder.setState("1001");
		}else if ("待服务".equals(state)) {
			serviceOrder.setState("1002");
		}
		System.out.println("sc=="+sc);
		serviceOrder.setSc(sc);
		//判断如果没有选择时间就不筛选
		if (null==time) {
			return serviceOrderService.getServiceOrderByCondition(serviceOrder);
		}else {
			List<ServiceOrder> serviceOrderList=serviceOrderService.getServiceOrderByCondition(serviceOrder);
			//存放返回的数据
			List<ServiceOrder> serviceOrders=new ArrayList<ServiceOrder>();
			//格式化时间
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			for (int i = 0; i < serviceOrderList.size(); i++) {
				//判断给出的时间和查询的时间是否相同
				if (time.equals(formatter.format(serviceOrderList.get(i).getCreatetime()))) {
					serviceOrders.add(serviceOrderList.get(i));
				}
			}
			return serviceOrders;
		}
		
	}

	/**
	 * 取消支付,根据用户的id,返回的服务订单,这条数据是该用户最新的数据
	 * @param customerId
	 * @return
	 */
	@RequestMapping("findByCancel.action")
	@ResponseBody
	public ServiceOrder getServiceOrderByCancel(String customerId) {
		List<ServiceOrder> serviceOrderList=serviceOrderService.getServiceOrderByCancel(customerId);
		if (null!=serviceOrderList&&serviceOrderList.size()>=0) {
			return serviceOrderList.get(0);
		}
		return null;
	}

	/**
	 * 根据用户id查询所有订单，APP端用
	 * @param customerId
	 * @return
	 */
	@RequestMapping("findByCancelAll.action")
	@ResponseBody
	public List<ServiceOrder> getServiceOrderByCancelAll(String customerId) {
		return serviceOrderService.getServiceOrderByCancel(customerId);
		
	}
	
	
	
	/**
	 * 查询订单中的时间
	 * @return
	 */
	@RequestMapping("findTime")
	@ResponseBody
	public Set<String> getServiceOrderTime() {
		//获取所有时间
		List<String> timeList=serviceOrderService.getServiceOrderTime();
		//返回的数据
		Set<String> setTime=new HashSet<String>();
		for (int i = 0; i < timeList.size(); i++) {
			String date=timeList.get(i).substring(0, 4);
			setTime.add(date);
		}
		return setTime;
	}

	/**
	 * 更新状态
	 * @param state
	 * @param id
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public BaseResult updateServiceOrderByState(String state, Integer id) {
		ServiceOrder serviceOrder=new ServiceOrder();
		if (null==id||null==serviceOrderService.getServiceOrderById(id)) {
			return BaseResult.build(500, "更新失败");
		}
		serviceOrder.setId(id);
		if ("已完成".equals(state)) {
			serviceOrder.setState("1000");
			serviceOrderService.updateServiceOrderByState(serviceOrder);
			return BaseResult.ok();
		}else if("待支付".equals(state)) {
			serviceOrder.setState("1001");
			serviceOrderService.updateServiceOrderByState(serviceOrder);
			return BaseResult.ok();
		}else if ("待服务".equals(state)) {
			serviceOrder.setState("1002");
			serviceOrderService.updateServiceOrderByState(serviceOrder);
			return BaseResult.ok();
		}
		return BaseResult.build(500, "更新失败");
	}
	/**
	 * 根据id返回订单
	 * @param id
	 * @return
	 */
	@RequestMapping("findByIdPc")
	@ResponseBody
	public ServiceOrder getServiceOrderPcById(int id) {
		
		return serviceOrderService.getServiceOrderById(id);
	}
	/**
	 * 结算
	 * @param ids
	 * @return
	 */
	@RequestMapping("settlement")
	@ResponseBody
	public BaseResult updateServiceOrderSettlement(String ids) {
		ServiceOrder serviceOrder=new ServiceOrder();
		serviceOrder.setState("1000");
		if (null==ids||"".equals(ids)) {
			return BaseResult.build(500, "结算失败");
		}else if (!ids.contains(",")) {
			if (null!=serviceOrderService.getServiceOrderById(Integer.parseInt(ids))) {
				serviceOrder.setId(Integer.parseInt(ids));
				serviceOrderService.updateServiceOrderByState(serviceOrder);
				return BaseResult.ok();
			}
			return BaseResult.build(500, "结算失败");
		}else {
			String[] postcode = ids.split(",");
			for (int i = 0; i < postcode.length; i++) {
				int id=Integer.parseInt(postcode[i]);
				if (null==serviceOrderService.getServiceOrderById(id)) {
					return BaseResult.build(500, "结算失败");
				}
			}
			for (int i = 0; i < postcode.length; i++) {
				int id=Integer.parseInt(postcode[i]);
				serviceOrder.setId(id);
				serviceOrderService.updateServiceOrderByState(serviceOrder);
			}
			return BaseResult.ok();
		}
		
	}

	
	//------------------------前台订单接口--------------------------------------------------//
	/**
	 * 查询用户的单次订单
	 * @param customerId
	 * @return
	 */
	@RequestMapping("once.action")
	@ResponseBody
	public List<ServiceOrder> frontOrderOnce(String customerId) {
		List<ServiceOrder> fList = serviceOrderService.getServiceOrderByCancel(customerId);
		//ServiceOrder serviceOrder=new ServiceOrder();
		List<ServiceOrder> serviceOrders=new ArrayList<ServiceOrder>();
		for (int i = 0; i < fList.size(); i++) {
			if (fList.get(i).getSc()==1) {
				serviceOrders.add(fList.get(i));
			}
		}
		return serviceOrders;
	}
	/**
	 * 查询用户的多次订单
	 * @param customerId
	 * @return
	 */
	@RequestMapping("more.action")
	@ResponseBody
	public List<ServiceOrder> frontOrderMore(String customerId) {
		List<ServiceOrder> fList = serviceOrderService.getServiceOrderByCancel(customerId);
		//ServiceOrder serviceOrder=new ServiceOrder();
		List<ServiceOrder> serviceOrders=new ArrayList<ServiceOrder>();
		for (int i = 0; i < fList.size(); i++) {
			if (fList.get(i).getSc()>1) {
				serviceOrders.add(fList.get(i));
			}
		}
		return serviceOrders;
	}
	/**
	 * 根据id返回订单
	 * @param id
	 * @return
	 */
	@RequestMapping("findById.action")
	@ResponseBody
	public ServiceOrder getServiceOrderById(int id) {
		
		return serviceOrderService.getServiceOrderById(id);
	}
}

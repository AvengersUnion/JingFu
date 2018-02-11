package com.app.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
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
import com.app.service.WxpayService;
import com.app.util.Application;
import com.app.util.pay.api.PayService;
import com.app.util.pay.bean.PayMessage;
import com.app.util.pay.bean.PayOrder;
import com.app.util.pay.bean.PayOutMessage;
import com.app.util.wxpay.api.WxPayConfigStorage;
import com.app.util.wxpay.api.WxPayService;
import com.app.util.wxpay.bean.WxTransactionType;
/**
 * 支付宝支付
 * @author taoxiangfei
 *
 */
@Controller
@RequestMapping("wxpay")
public class WxpayController {
	@Resource(name="serviceOrderService")
	private ServiceOrderService serviceOrderService;
	
	@Resource(name="wxpayService")
	private WxpayService wxpayService;
	
	private String appid = Application.wxAPPID;
	private String notifyUrl = Application.wxBackUrl;
	private String mchid = Application.wxmchid;
	private String key = Application.wxpayKey;
	
	/**
	 * 创建订单
	 * @param serviceOrder
	 * @return
	 */
	public Map<String,Object> createOrder(ServiceOrder serviceOrder) {
		//封装微信支付参数
		WxPayConfigStorage wxPayConfigStorage = new WxPayConfigStorage();
        wxPayConfigStorage.setMchId(mchid);
        wxPayConfigStorage.setAppid(appid);
        wxPayConfigStorage.setKeyPublic(key);
        wxPayConfigStorage.setKeyPrivate(key);
        wxPayConfigStorage.setNotifyUrl(notifyUrl);
        wxPayConfigStorage.setSignType("MD5");
        wxPayConfigStorage.setInputCharset("utf-8");
        //生成微信支付订单
        PayService payservice =  new WxPayService(wxPayConfigStorage);
        PayOrder payOrder = new PayOrder(serviceOrder.getServiceName(), "",  new BigDecimal(0.01) , serviceOrder.getOrderId());
        payOrder.setTransactionType(WxTransactionType.APP);
        Map<String,Object> jsonMap = payservice.orderInfo(payOrder);
        return jsonMap;

	}
	/**
	 * 查询微信订单
	 * @return
	 */
	@RequestMapping(value = "/queryOrder.do", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String QueryOrder(HttpServletRequest request, HttpServletResponse response) {
		return null;
		
	}
	/**
	 * 订单退款
	 * @return
	 */
	@RequestMapping(value = "/refundOrder", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String refundOrder(HttpServletRequest request, HttpServletResponse response) {
		return null;

	}
	/**
	 * 订单退款查询
	 * @return
	 */
	@RequestMapping(value = "/refundQuery", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String refundQuery(HttpServletRequest request, HttpServletResponse response) {
		return null;
		
	}
	/**
	 * 微信支付回调
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value = "/wxCreateOrderBackUrl.do", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	public void aliBackUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {

//	    //支付账户配置
//	    PayConfigStorage storage = payService.getPayConfigStorage();
//	    // 获取支付回调返回的所有参数
//	    Map<String, Object> params = payService.getParameter2Map(request.getParameterMap(), request.getInputStream());
//	    //创建支付消息
//	    PayMessage message = new PayMessage(params, storage.getPayType(), storage.getMsgType().name());
//
//	    PayOutMessage outMessage = router.route(message);
//	    
//	    response.getWriter().write(outMessage.toMessage();
		
	}
}

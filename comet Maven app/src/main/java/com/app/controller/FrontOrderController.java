package com.app.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.app.entity.FrontOrder;
import com.app.entity.ServiceOrder;
import com.app.service.FrontOrderService;

/**
 * 前台的服务订单
 * 
 * @author 李洋
 * 
 */
@Controller
@RequestMapping("/frontOrder/")
public class FrontOrderController {
	@Resource(name = "frontOrderService")
	private FrontOrderService frontOrderService;

	/**
	 * 查询用户的单次订单
	 * 
	 * @return
	 */
	@RequestMapping("once.action")
	@ResponseBody
	public List frontOrderOnce() {
		List<FrontOrder> fList = frontOrderService.getFrontOrdersOnce();
		return fList;
	}

	/**
	 * 查询用户的多次订单
	 * 
	 * @return
	 */
	@RequestMapping("more.action")
	@ResponseBody
	public List frontOrderMore() {
		List<FrontOrder> fList = frontOrderService.getFrontOrdersMore();
		return fList;
	}
	/**
	 * 功能描述：获取订单详情
	 *
	 *
	 */
	@RequestMapping(value = "/getOrderInfo.action", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String getOrderInfo(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		JSONObject obj = new JSONObject();
		//订单id
		String orderId = request.getParameter("orderId");
		Map<String,String> map = frontOrderService.getOrderInfo(orderId);
		return JSON.toJSON(map).toString();
	}
}

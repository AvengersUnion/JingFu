package com.app.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.entity.FrontOrder;
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
	@RequestMapping("once")
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
	@RequestMapping("more")
	@ResponseBody
	public List frontOrderMore() {
		List<FrontOrder> fList = frontOrderService.getFrontOrdersMore();
		return fList;
	}
}

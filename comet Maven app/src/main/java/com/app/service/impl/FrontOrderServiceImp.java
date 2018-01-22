package com.app.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.FrontOrderDao;
import com.app.entity.FrontOrder;
import com.app.entity.ServiceOrder;
import com.app.service.FrontOrderService;
@Service("frontOrderService")
public class FrontOrderServiceImp implements FrontOrderService{
	@Resource
	FrontOrderDao frontOrderDao;
	public List<FrontOrder> getFrontOrdersOnce() {
		// TODO Auto-generated method stub
		
		return frontOrderDao.getFrontOrdersOnce();
	}
	public List<FrontOrder> getFrontOrdersMore() {
		// TODO Auto-generated method stub
		return frontOrderDao.getFrontOrdersMore();
	}
	public Map getOrderByOrderId(String orderId) {
		return frontOrderDao.getOrderByOrderId(orderId);
	}
	/**
	 * 获取订单详情
	 */
	public Map<String,String> getOrderInfo(String orderId) {
		return frontOrderDao.getOrderInfo(orderId);
	}

}

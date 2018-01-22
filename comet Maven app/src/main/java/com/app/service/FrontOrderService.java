package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.entity.FrontOrder;
import com.app.entity.ServiceOrder;

public interface FrontOrderService {
	List<FrontOrder> getFrontOrdersOnce();
	List<FrontOrder> getFrontOrdersMore();
	Map getOrderByOrderId(String orderId);
	/**
	 * 获取订单详情
	 * @param orderId
	 * @return
	 */
	Map<String,String> getOrderInfo(String orderId);
}

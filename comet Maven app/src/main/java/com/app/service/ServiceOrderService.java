package com.app.service;

import java.util.List;

import com.app.entity.ServiceOrder;

public interface ServiceOrderService {
	//查询所有的服务订单
	List<ServiceOrder> getServiceOrderAll(ServiceOrder serviceOrder);
	//根据条件查询
	List<ServiceOrder> getServiceOrderByCondition(ServiceOrder serviceOrder);
	//取消支付,根据用户的id,返回的服务订单,这条数据是该用户最新的数据
	List<ServiceOrder> getServiceOrderByCancel(String customerId);
	//根据id返回订单
	ServiceOrder getServiceOrderById(int id);
	//查询订单中的时间
	List<String> getServiceOrderTime();
	//根据id修改订单的状态
	void updateServiceOrderByState(ServiceOrder serviceOrder);
	//根据订单编号查询订单
	ServiceOrder getServiceOrderByOrderId(String orderId);
}

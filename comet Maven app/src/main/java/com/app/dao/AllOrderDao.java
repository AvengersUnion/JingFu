package com.app.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.entity.Address;
import com.app.entity.Dict;
import com.app.entity.ServiceOrder;

@Component
public interface AllOrderDao {
	
	List<ServiceOrder> getAllOrdersAll();//查询所有的服务订单
	List<ServiceOrder> getServiceOrder(String id);
	//List<AllOrder> getUserOrders(String id);
	List<Address> getAddressesAll();//获取地址
	int getServiceCount();//获取服务订单条数
	List<Dict> getServiceType();//获取字典表数据
	List<ServiceOrder> getServiceTime();//获取订单时间
	//List<AllOrder> getServiceOrders(AllOrder allOrder);
	void updateOrderState(ServiceOrder id);//更新订单状态
	ServiceOrder getServiceOrderById(String id);//查询单条服务订单数据
	void updateOrderById(ServiceOrder serviceOrder);//修改订单状态
	//void updateOrderUserById(AllOrder allOrder);
	void deleteOrderById(String id);//删除订单
}

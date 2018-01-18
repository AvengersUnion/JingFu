package com.app.service;

import java.util.List;

import com.app.entity.Address;
import com.app.entity.Dict;
import com.app.entity.ServiceOrder;

public interface AllOrderService {
	List<ServiceOrder> getAllOrdersAll();
	List<ServiceOrder> getServiceOrder(String id);
	List<Address> getAddressesAll();
	int getServiceCount();
	List<Dict> getServiceType();
	List<ServiceOrder> getServiceTime();
	List<ServiceOrder> getServiceOrders(ServiceOrder serviceOrder);
	void updateOrderState(ServiceOrder id);
	ServiceOrder getServiceOrderById(String id);
	void updateOrderById(ServiceOrder serviceOrder);
	void updateOrderUserById(ServiceOrder serviceOrder);
	void deleteOrderById(String id);
	//List<AllOrder> getUserOrders(String id);
}

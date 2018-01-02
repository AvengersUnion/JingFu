package com.app.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.entity.Address;
import com.app.entity.AllOrder;
import com.app.entity.Dict;
import com.app.entity.ServiceOrder;

@Component
public interface AllOrderDao {
	List<AllOrder> getAllOrdersAll();
	List<AllOrder> getServiceOrder(String id);
	//List<AllOrder> getUserOrders(String id);
	List<Address> getAddressesAll();
	int getServiceCount();
	List<Dict> getServiceType();
	List<ServiceOrder> getServiceTime();
	List<AllOrder> getServiceOrders(AllOrder allOrder);
	void updateOrderState(AllOrder id);
	AllOrder getServiceOrderById(String id);
	void updateOrderById(AllOrder allOrder);
	void updateOrderUserById(AllOrder allOrder);
	void deleteOrderById(String id);
}

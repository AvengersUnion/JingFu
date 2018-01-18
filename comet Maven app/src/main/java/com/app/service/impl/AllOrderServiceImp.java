package com.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.AllOrderDao;
import com.app.entity.Address;
import com.app.entity.AllOrder;
import com.app.entity.Dict;
import com.app.entity.ServiceOrder;
import com.app.service.AllOrderService;

@Service("allOrderService")
public class AllOrderServiceImp implements AllOrderService {

	@Resource
	private AllOrderDao allOrderDao;
	public List<ServiceOrder> getAllOrdersAll() {
		// TODO Auto-generated method stub
		return allOrderDao.getAllOrdersAll();
	}
	public List<ServiceOrder> getServiceOrder(String id) {
		// TODO Auto-generated method stub
		return allOrderDao.getServiceOrder(id);
	}
	public List<Address> getAddressesAll() {
		// TODO Auto-generated method stub
		return allOrderDao.getAddressesAll();
	}
	public int getServiceCount() {
		// TODO Auto-generated method stub
		return allOrderDao.getServiceCount();
	}
	public List<Dict> getServiceType() {
		// TODO Auto-generated method stub
		return allOrderDao.getServiceType();
	}
	public List<ServiceOrder> getServiceTime() {
		// TODO Auto-generated method stub
		return allOrderDao.getServiceTime();
	}
	/*
	public List<ServiceOrder> getServiceOrders(ServiceOrder serviceOrder) {
		// TODO Auto-generated method stub
		return allOrderDao.getServiceOrders(serviceOrder);
	}
	*/
	public void updateOrderState(ServiceOrder id) {
		// TODO Auto-generated method stub
		allOrderDao.updateOrderState(id);
	}
	public ServiceOrder getServiceOrderById(String id) {
		// TODO Auto-generated method stub
		return allOrderDao.getServiceOrderById(id);
	}
	public void updateOrderById(ServiceOrder allOrder) {
		// TODO Auto-generated method stub
		allOrderDao.updateOrderById(allOrder);
	}
	/*
	public void updateOrderUserById(ServiceOrder allOrder) {
		// TODO Auto-generated method stub
		allOrderDao.updateOrderUserById(allOrder);
	}
	*/
	public void deleteOrderById(String id) {
		// TODO Auto-generated method stub
		allOrderDao.deleteOrderById(id);
	}
	public List<ServiceOrder> getServiceOrders(ServiceOrder serviceOrder) {
		// TODO Auto-generated method stub
		return null;
	}
	public void updateOrderUserById(ServiceOrder serviceOrder) {
		// TODO Auto-generated method stub
		
	}

}

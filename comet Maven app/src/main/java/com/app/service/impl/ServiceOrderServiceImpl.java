package com.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.ServiceOrderDao;
import com.app.entity.ServiceOrder;
import com.app.service.ServiceOrderService;

@Service("serviceOrderService")
public class ServiceOrderServiceImpl implements ServiceOrderService{

	@Resource
	private ServiceOrderDao serviceOrderDao;
	//查询所有本年的订单
	public List<ServiceOrder> getServiceOrderAll(ServiceOrder serviceOrder) {
		// TODO Auto-generated method stub
		
		return serviceOrderDao.getServiceOrderAll(serviceOrder);
	}

	//根据条件查询订单
	public List<ServiceOrder> getServiceOrderByCondition(
			ServiceOrder serviceOrder) {
		// TODO Auto-generated method stub
		return serviceOrderDao.getServiceOrderByCondition(serviceOrder);
		/*
		if (null==serviceOrder.getSc()) {
			
			
		}else if (serviceOrder.getSc()==1){
			List<ServiceOrder> serviceOrderList=serviceOrderDao.getServiceOrderByCondition(serviceOrder);
			List<ServiceOrder> serviceOrders=new ArrayList<ServiceOrder>();
			for (int i = 0; i < serviceOrderList.size(); i++) {
				if (serviceOrderList.get(i).getSc()==1) {
					System.out.println("sc=="+1);
					serviceOrders.add(serviceOrderList.get(i));
				}
			}
			return serviceOrders;
		}else {
			List<ServiceOrder> serviceOrderList=serviceOrderDao.getServiceOrderByCondition(serviceOrder);
			List<ServiceOrder> serviceOrders=new ArrayList<ServiceOrder>();
			for (int i = 0; i < serviceOrderList.size(); i++) {
				if (serviceOrderList.get(i).getSc()>1) {
					serviceOrders.add(serviceOrderList.get(i));
				}
			}
			return serviceOrders;
		}
		*/
	}

	//取消支付,根据用户的id,返回的服务订单,这条数据是该用户最新的数据
	public List<ServiceOrder> getServiceOrderByCancel(String customerId) {
		// TODO Auto-generated method stub
		
		return serviceOrderDao.getServiceOrderByCancel(customerId);
	}

	//根据id返回订单
	public ServiceOrder getServiceOrderById(int id) {
		// TODO Auto-generated method stub
		return serviceOrderDao.getServiceOrderById(id);
	}

	//查询订单中的时间
	public List<String> getServiceOrderTime() {
		// TODO Auto-generated method stub
		return serviceOrderDao.getServiceOrderTime();
	}

	public void updateServiceOrderByState(ServiceOrder serviceOrder) {
		// TODO Auto-generated method stub
		serviceOrderDao.updateServiceOrderByState(serviceOrder);
	}
	/**
	 * 根据订单编号获取订单
	 */
	public ServiceOrder getServiceOrderByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return serviceOrderDao.getServiceOrderByOrderId(orderId);
	}

	@Override
	public void savePayBackW(ServiceOrder serviceOrder) {
		serviceOrderDao.savePayBackW(serviceOrder);
		
	}

	@Override
	public void savePayBack(ServiceOrder serviceOrder) {
		serviceOrderDao.savePayBack(serviceOrder);
		
	}

}

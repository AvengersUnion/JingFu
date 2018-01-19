package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.common.BaseResult;
import com.app.entity.FrontOrder;
import com.app.entity.ServiceOrder;
import com.app.service.ServiceOrderService;

@Controller
@RequestMapping("serviceOrder")
public class ServiceOrderController {

	@Resource(name="serviceOrderService")
	private ServiceOrderService serviceOrderService;
	/**
	 * 查询所有本年的订单
	 * @return
	 */
	@RequestMapping("all")
	@ResponseBody
	public List<ServiceOrder> getServiceOrderAll() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String toyear = formatter.format(new Date());
		ServiceOrder serviceOrder=new ServiceOrder();
		serviceOrder.setToyear(toyear);
		List<ServiceOrder> serviceOrderList=serviceOrderService.getServiceOrderAll(serviceOrder);
		return serviceOrderList;
	}

	/**
	 * 根据条件查询订单
	 * @param city
	 * @param time
	 * @param state
	 * @param sc
	 * @return
	 */
	@RequestMapping(value="findByCondition", method = RequestMethod.POST)
	@ResponseBody
	public List<ServiceOrder> getServiceOrderByCondition(String city,String time,
			String state,Integer sc) {
		//实例化订单类
		ServiceOrder serviceOrder=new ServiceOrder();
		serviceOrder.setCity(city);
		if ("已完成".equals(state)) {
			serviceOrder.setState("1000");
		}else if("待支付".equals(state)){
			serviceOrder.setState("1001");
		}else if ("待服务".equals(state)) {
			serviceOrder.setState("1002");
		}
		System.out.println("sc=="+sc);
		serviceOrder.setSc(sc);
		//判断如果没有选择时间就不筛选
		if (null==time) {
			return serviceOrderService.getServiceOrderByCondition(serviceOrder);
		}else {
			List<ServiceOrder> serviceOrderList=serviceOrderService.getServiceOrderByCondition(serviceOrder);
			//存放返回的数据
			List<ServiceOrder> serviceOrders=new ArrayList<ServiceOrder>();
			//格式化时间
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			for (int i = 0; i < serviceOrderList.size(); i++) {
				//判断给出的时间和查询的时间是否相同
				if (time.equals(formatter.format(serviceOrderList.get(i).getCreatetime()))) {
					serviceOrders.add(serviceOrderList.get(i));
				}
			}
			return serviceOrders;
		}
		
	}

	/**
	 * 取消支付,根据用户的id,返回的服务订单,这条数据是该用户最新的数据
	 * @param customerId
	 * @return
	 */
	@RequestMapping("findByCancel.action")
	@ResponseBody
	public ServiceOrder getServiceOrderByCancel(String customerId) {
		List<ServiceOrder> serviceOrderList=serviceOrderService.getServiceOrderByCancel(customerId);
		if (null!=serviceOrderList&&serviceOrderList.size()>=0) {
			return serviceOrderList.get(0);
		}
		return null;
	}

	/**
	 * 根据用户id查询所有订单，APP端用
	 * @param customerId
	 * @return
	 */
	@RequestMapping("findByCancelAll.action")
	@ResponseBody
	public List<ServiceOrder> getServiceOrderByCancelAll(String customerId) {
		return serviceOrderService.getServiceOrderByCancel(customerId);
		
	}
	
	
	
	/**
	 * 查询订单中的时间
	 * @return
	 */
	@RequestMapping("findTime")
	@ResponseBody
	public Set<String> getServiceOrderTime() {
		//获取所有时间
		List<String> timeList=serviceOrderService.getServiceOrderTime();
		//返回的数据
		Set<String> setTime=new HashSet<String>();
		for (int i = 0; i < timeList.size(); i++) {
			String date=timeList.get(i).substring(0, 4);
			setTime.add(date);
		}
		return setTime;
	}

	/**
	 * 更新状态
	 * @param state
	 * @param id
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public BaseResult updateServiceOrderByState(String state, Integer id) {
		ServiceOrder serviceOrder=new ServiceOrder();
		if (null==id||null==serviceOrderService.getServiceOrderById(id)) {
			return BaseResult.build(500, "更新失败");
		}
		serviceOrder.setId(id);
		if ("已完成".equals(state)) {
			serviceOrder.setState("1000");
			serviceOrderService.updateServiceOrderByState(serviceOrder);
			return BaseResult.ok();
		}else if("待支付".equals(state)) {
			serviceOrder.setState("1001");
			serviceOrderService.updateServiceOrderByState(serviceOrder);
			return BaseResult.ok();
		}else if ("待服务".equals(state)) {
			serviceOrder.setState("1002");
			serviceOrderService.updateServiceOrderByState(serviceOrder);
			return BaseResult.ok();
		}
		return BaseResult.build(500, "更新失败");
	}
	/**
	 * 根据id返回订单
	 * @param id
	 * @return
	 */
	@RequestMapping("findByIdPc")
	@ResponseBody
	public ServiceOrder getServiceOrderPcById(int id) {
		
		return serviceOrderService.getServiceOrderById(id);
	}
	/**
	 * 结算
	 * @param ids
	 * @return
	 */
	@RequestMapping("settlement")
	@ResponseBody
	public BaseResult updateServiceOrderSettlement(String ids) {
		ServiceOrder serviceOrder=new ServiceOrder();
		serviceOrder.setState("1000");
		if (null==ids||"".equals(ids)) {
			return BaseResult.build(500, "结算失败");
		}else if (!ids.contains(",")) {
			if (null!=serviceOrderService.getServiceOrderById(Integer.parseInt(ids))) {
				serviceOrder.setId(Integer.parseInt(ids));
				serviceOrderService.updateServiceOrderByState(serviceOrder);
				return BaseResult.ok();
			}
			return BaseResult.build(500, "结算失败");
		}else {
			String[] postcode = ids.split(",");
			for (int i = 0; i < postcode.length; i++) {
				int id=Integer.parseInt(postcode[i]);
				if (null==serviceOrderService.getServiceOrderById(id)) {
					return BaseResult.build(500, "结算失败");
				}
			}
			for (int i = 0; i < postcode.length; i++) {
				int id=Integer.parseInt(postcode[i]);
				serviceOrder.setId(id);
				serviceOrderService.updateServiceOrderByState(serviceOrder);
			}
			return BaseResult.ok();
		}
		
	}

	
	//------------------------前台订单接口--------------------------------------------------//
	/**
	 * 查询用户的单次订单
	 * @param customerId
	 * @return
	 */
	@RequestMapping("once.action")
	@ResponseBody
	public List<ServiceOrder> frontOrderOnce(String customerId) {
		List<ServiceOrder> fList = serviceOrderService.getServiceOrderByCancel(customerId);
		//ServiceOrder serviceOrder=new ServiceOrder();
		List<ServiceOrder> serviceOrders=new ArrayList<ServiceOrder>();
		for (int i = 0; i < fList.size(); i++) {
			if (fList.get(i).getSc()==1) {
				serviceOrders.add(fList.get(i));
			}
		}
		return serviceOrders;
	}
	/**
	 * 查询用户的多次订单
	 * @param customerId
	 * @return
	 */
	@RequestMapping("more.action")
	@ResponseBody
	public List<ServiceOrder> frontOrderMore(String customerId) {
		List<ServiceOrder> fList = serviceOrderService.getServiceOrderByCancel(customerId);
		//ServiceOrder serviceOrder=new ServiceOrder();
		List<ServiceOrder> serviceOrders=new ArrayList<ServiceOrder>();
		for (int i = 0; i < fList.size(); i++) {
			if (fList.get(i).getSc()>1) {
				serviceOrders.add(fList.get(i));
			}
		}
		return serviceOrders;
	}
	/**
	 * 根据id返回订单
	 * @param id
	 * @return
	 */
	@RequestMapping("findById.action")
	@ResponseBody
	public ServiceOrder getServiceOrderById(int id) {
		
		return serviceOrderService.getServiceOrderById(id);
	}
}

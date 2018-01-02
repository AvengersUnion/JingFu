package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.entity.Address;
import com.app.entity.AllOrder;
import com.app.entity.Dict;
import com.app.entity.ServiceOrder;
import com.app.service.AllOrderService;
/**
 * 服务订单
 * @author 李洋
 *
 */
@Controller
@RequestMapping("/order/")
public class AllOrderController {
	@Resource(name="allOrderService")
	private AllOrderService allOrderService;
	/**
	 * 查询本年度所有的订单
	 * @return
	 */
	@RequestMapping("all")
    @ResponseBody
	public List getAllOrderList() {
		//SimpleDateFormat sdf = new SimpleDateFormat(yy-MM-dd);
		//获取现在的时间(年)
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatMonth=new SimpleDateFormat("mm");
		String date=formatter.format(new Date());
		
		List<Object> allOrderlist=new ArrayList<Object>();
		List<AllOrder> list=allOrderService.getAllOrdersAll();
		AllOrder allOrder=null;
		for (int i = 0; i < list.size(); i++) {
			allOrder=list.get(i);
			if (allOrder.getOrderTime().substring(0,4).equals(date)) {
				//allOrder.setMonth(Integer.parseInt(formatMonth.format(allOrder.getOrderTime())));
				Integer month=Integer.parseInt(allOrder.getOrderTime().substring(5, 7));
				allOrderlist.add(month);
				allOrderlist.add(allOrder);
			}
			
		}
		
		return allOrderlist;
		
	}
	/**
	 * 获取用户的服务订单
	 * @return
	 */
	@RequestMapping("serviceUser")
    @ResponseBody
	public List getServiceOrder(String id) {
		List<AllOrder> serviceOrders=allOrderService.getServiceOrder(id);
		return serviceOrders;
	}
	/**
	 * 获取用户的地址、订单时间
	 * @return
	 */
	@RequestMapping("addressList")
	@ResponseBody
	public List getAddressList() {
		List<List> list=new ArrayList<List>();
		//地点
		List<Address> addressesList=allOrderService.getAddressesAll();
		//订单时间
		Set<String> setTime=new HashSet<String>();
		List<ServiceOrder> serviceOrders=allOrderService.getServiceTime();
		for (int i = 0; i < serviceOrders.size(); i++) {
			String timeString=serviceOrders.get(i).getCT().substring(0,4);
			setTime.add(timeString);
		}
		List<String> orderTime=new ArrayList<String>();
		Iterator<String> it = setTime.iterator();  
		while (it.hasNext()) {  
		  String str = it.next();  
		  orderTime.add(str);
		}  
		//服务类型
		//List<Dict> dictList=allOrderService.getServiceType();
		
		list.add(addressesList);
		list.add(orderTime);
		//list.add(dictList);
		return list;
	}
	/**
	 * 获取服务次数
	 * @return
	 */
	@RequestMapping("serviceCount")
	@ResponseBody
	public List  getServiceCount() {
		int i=allOrderService.getServiceCount();
		List<String> list=new ArrayList<String>();
		if (i==1&&i>0) {
			list.add("单次");
		}else if(i>1){
			list.add("多次");
		}
		return list;
	}
	/**
	 * 获取服务类型
	 * @return
	 */
	@RequestMapping("serviceType")
	@ResponseBody
	public List getServiceType(){
		List<Dict> dictList=allOrderService.getServiceType();
		return dictList;
	}
	/**
	 * 获取服务时间
	 * @return
	 */
	@RequestMapping("orderTime")
	@ResponseBody
	public Set getServiceTime() {
		Set<String> setTime=new HashSet<String>();
		List<ServiceOrder> serviceOrders=allOrderService.getServiceTime();
		for (int i = 0; i < serviceOrders.size(); i++) {
			String timeString=serviceOrders.get(i).getCT().substring(0,4);
			setTime.add(timeString);
		}
		return setTime;
	}
	/**
	 * 根据条件获取订单数据
	 * @param cityAddress
	 * @param orderType
	 * @param orderTime
	 * @param serviceType
	 * @return
	 */
	@RequestMapping("serviceOrders")
	@ResponseBody
	public List getServiceOrders(String cityAddress,String orderType,
			String orderTime,String payType ) {
		
		AllOrder allOrder=new AllOrder();
		SimpleDateFormat newTime = new SimpleDateFormat("yyyy");
		//判断城市
		if (cityAddress.equals("请选择")||null==cityAddress||cityAddress=="") {
			cityAddress=null;
		}
		allOrder.setCity(cityAddress);
		//判断支付类型
		if (payType.equals("请选择")||null==payType||cityAddress=="") {
			payType=null;
		}
		allOrder.setServiceStatus(payType);
		//判断订单类型(单次，多次)
		if ("0"==orderType) {
			allOrder.setOrderType(0);
		}else {
			allOrder.setOrderType(Integer.parseInt(orderType));
		}
		if (null==orderTime) {
			//获取现在的时间
			orderTime=newTime.format(new Date());
		}
		System.out.println(allOrder.getCity());
		List<AllOrder> allOrders=allOrderService.getServiceOrders(allOrder);
		List<AllOrder> list=new ArrayList<AllOrder>();
		
		for (int i = 0; i < allOrders.size(); i++) {
			SimpleDateFormat formatter = new SimpleDateFormat("MM");
			SimpleDateFormat formatYear= new SimpleDateFormat("yyyy");
			//String date=formatter.format(new Date());
			if (allOrders.get(i).getOrderTime().substring(0,4).equals(orderTime)) {
				//System.out.println(formatYear.format(allOrders.get(i).getOrderTime()));
				allOrder.setMonth(Integer.parseInt(allOrders.get(i).getOrderTime().substring(5,7)));
				list.add(allOrders.get(i));
			}else {
				//allOrder.setMonth(Integer.parseInt(formatter.format(allOrders.get(i).getOrderTime())));
				allOrder.setMonth(Integer.parseInt(allOrders.get(i).getOrderTime().substring(5,7)));
				//list.add(allOrders.get(i));
			}
		}
		return list;
	}
	/**
	 * 更改服务订单的状态
	 * @param list
	 */
	@RequestMapping("serviceUpdate")
	@ResponseBody
	public String updateServiceState(String ids) {
		String[] strArray=null;
		AllOrder allOrder=new AllOrder();
		if (ids==null) {
			return "0";
		}
		if (!ids.contains(",")) {
			allOrder.setId(ids);
			allOrder.setStt("COS_ZF");
			allOrderService.updateOrderState(allOrder);
		}else {
			strArray=ids.split(",");
			for (int i = 0; i <strArray.length; i++) {
				if (strArray[i]!=null&&strArray[i]!="") {
					allOrder.setId(strArray[i]);
					allOrder.setStt("COS_ZF");
					allOrderService.updateOrderState(allOrder);
					System.out.println(strArray[i]);
				}
			}
		}
		return "1";
	}
	
	/**
	 * 查询单条数据
	 * @param id
	 * @return
	 */
	@RequestMapping("getOrder")
	@ResponseBody
	public AllOrder getServiceOrderById(String id) {
		AllOrder allOrder=null;
		if (id!=null&&id!="") {
			allOrder = allOrderService.getServiceOrderById(id);
		}
		return allOrder;
	}
	
	/**
	 * 修改订单
	 * @param orderJson
	 * @return
	 */
	@RequestMapping("updateOrderById")
	@ResponseBody
	public String updateOrderById(String id,String payType) {
		AllOrder allOrder=new AllOrder();
		//时间的格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Date date = sdf.parse("2016-06-06 16:24:50");
		//System.out.println(sdf.format(date));
		//AllOrder allOrder=new AllOrder();
		/*
		allOrder.setServiceAddress(serviceAddress);
		if(serviceTime!=null){
			allOrder.setServiceTime(serviceTime);
		}else {
			allOrder.setServiceTime(null);
		}
		*/
		if (payType.equals("已支付")) {
			allOrder.setServiceStatus(payType);
			allOrder.setStt("COS_ZF");
			//return "0";
		}else if (payType.equals("未支付")) {
			allOrder.setServiceStatus(payType);
			allOrder.setStt("SOS_ZF");
		}else {
			return "0";
		}
		allOrder.setId(id);
		/*
		allOrder.setUserName(userName);
		allOrder.setUserIphone(userIphone);
		*/
		System.out.println(allOrder);
		if (null==allOrder||allOrder.getId()==null) {
			return "0";
		}
		//更新订单信息
		allOrderService.updateOrderById(allOrder);
		//更新用户信息
		//allOrderService.updateOrderUserById(allOrder);
		return "1";
	}
	
	/**
	 * 
	 * @param id
	 * @return 
	 */
	@RequestMapping("deleteOrderById")
	@ResponseBody
	public String deleteOrderById(String id) {
		if(null==id&&id==""){
			return "0";
		}else {
			if (null==allOrderService.getServiceOrderById(id)) {
				return "0";
			}
			allOrderService.deleteOrderById(id);
		}
		return "1";
	}
	
}

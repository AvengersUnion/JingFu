package com.app.entity;

import java.util.Date;

/**
 * 服务订单
 */
public class ServiceOrder {


    //服务订单 id
    private Integer id;
    //订单id
    private String orderId;
    // 服务id
    private String serviceId;
    //下单用户编码
    private String customerId;
    // 订单种类
    private String addressId;
    //服务数量SERVICE_COUNT
    private String sc;
    //满一定额度后总体折扣比例
    private String discount;
    //订单创建时间
    private Date createtime;
    //订单状态
    private String state;
    //支付类型
    private Integer flag;
    //支付金额
    private Double money;
    //服务时间
    private Date serviceTime;
    //用户备注
    private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(Date serviceTime) {
		this.serviceTime = serviceTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "ServiceOrder [id=" + id + ", orderId=" + orderId + ", serviceId=" + serviceId + ", customerId="
				+ customerId + ", addressId=" + addressId + ", sc=" + sc + ", discount=" + discount + ", createtime="
				+ createtime + ", state=" + state + ", flag=" + flag + ", money=" + money + ", serviceTime="
				+ serviceTime + ", remark=" + remark + "]";
	}
	
    
    
}

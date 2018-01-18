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
    //用户名
    private String uName;
    //电话
    private String uIphone;
    // 服务id
    private String serviceId;
    //今年的年份
    private String toyear;
    //服务类型(父服务)
    private String serviceType;
    //地址
    private String community;
    //门牌号
    private String houseNumber;
    //城市
    private String city;
    //下单用户编码
    private String customerId;
    // 订单种类
    private String addressId;
    //服务数量SERVICE_COUNT
    private Integer sc;
    //满一定额度后总体折扣比例
    private String discount;
    //服务名字
    private String serviceName;
    //价格
    private double price;
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
	
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
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
	/**
	 * @return the uName
	 */
	public String getuName() {
		return uName;
	}
	/**
	 * @param uName the uName to set
	 */
	public void setuName(String uName) {
		this.uName = uName;
	}
	/**
	 * @return the uIphone
	 */
	public String getuIphone() {
		return uIphone;
	}
	/**
	 * @param uIphone the uIphone to set
	 */
	public void setuIphone(String uIphone) {
		this.uIphone = uIphone;
	}
	/**
	 * @return the serviceType
	 */
	public String getServiceType() {
		return serviceType;
	}
	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the sc
	 */
	public Integer getSc() {
		return sc;
	}
	/**
	 * @param sc the sc to set
	 */
	public void setSc(Integer sc) {
		this.sc = sc;
	}
	/**
	 * @return the createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * @return the toyear
	 */
	public String getToyear() {
		return toyear;
	}
	/**
	 * @param toyear the toyear to set
	 */
	public void setToyear(String toyear) {
		this.toyear = toyear;
	}
	/**
	 * @return the community
	 */
	public String getCommunity() {
		return community;
	}
	/**
	 * @param community the community to set
	 */
	public void setCommunity(String community) {
		this.community = community;
	}
	/**
	 * @return the houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}
	/**
	 * @param houseNumber the houseNumber to set
	 */
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}

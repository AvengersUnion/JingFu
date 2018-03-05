package com.app.entity;


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
    //订单创建时间
    private String createtime;
    //订单状态
    private String state;
    //支付类型
    private Integer flag;
    //定金
    private Double money;
    //尾款
    private Double endMoney;
    //服务时间
    private String serviceTime;
    //用户备注
    private String remark;
    
    private Double price;	//备用
    //支付成功返回信息
    private String orderIdW;//尾款订单号
    private String appkey;
    private String amount;
    private String result;
    private String ticketId;
    private String payAt;
    private String nonce_str;
    private String msg;
    private String channel;
    private String sign;
    
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
	public String getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(String serviceTime) {
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
	public String getCreatetime() {
		return createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(String createtime) {
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
	public Double getEndMoney() {
		return endMoney;
	}
	public void setEndMoney(Double endMoney) {
		this.endMoney = endMoney;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getOrderIdW() {
		return orderIdW;
	}
	public void setOrderIdW(String orderIdW) {
		this.orderIdW = orderIdW;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	public String getPayAt() {
		return payAt;
	}
	public void setPayAt(String payAt) {
		this.payAt = payAt;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Override
	public String toString() {
		return "ServiceOrder [id=" + id + ", orderId=" + orderId + ", uName=" + uName + ", uIphone=" + uIphone
				+ ", serviceId=" + serviceId + ", toyear=" + toyear + ", serviceType=" + serviceType + ", community="
				+ community + ", houseNumber=" + houseNumber + ", city=" + city + ", customerId=" + customerId
				+ ", addressId=" + addressId + ", sc=" + sc + ", discount=" + discount + ", serviceName=" + serviceName
				+ ", createtime=" + createtime + ", state=" + state + ", flag=" + flag + ", money=" + money
				+ ", endMoney=" + endMoney + ", serviceTime=" + serviceTime + ", remark=" + remark + ", price=" + price
				+ ", orderIdW=" + orderIdW + ", appkey=" + appkey + ", amount=" + amount + ", result=" + result
				+ ", ticketId=" + ticketId + ", payAt=" + payAt + ", nonce_str=" + nonce_str + ", msg=" + msg
				+ ", channel=" + channel + ", sign=" + sign + "]";
	}
	
	
	
}

package com.app.entity;
/**
 * mob支付接口实体类
 * @author taoxiangfei
 *
 */
public class Mobpay {
	private String appkey="";					//Mob应用唯一标示
	private String orderId="";					//商户网站唯一订单号
	private String orderIdSe="";				//内部订单号
	private String orderType="";				//订单类型，1——定金，2——尾款
	private String appUserId="";				//商户用户Id
	private String appUserNickname="";			//商户用户昵称
	private Integer amount=0;					//订单金额，单位为分
	private String subject="";					//商品的标题/交易标题/订单标题/订单关键字等。
	private String body="";						//商品描述
	private String description="";				//订单描述
	private String metadata="";					//开发者根据实际需求，上传的备份数据，json格式字符串
	private Integer payChannel=22;				//支付渠道 22-微信支付，50-支付宝支付
	private String clientIp="127.0.0.1";		//客户端IP
	private Integer plat=0;						//平台枚举1-android,2-ios
	private Boolean repeatPayAutoRefund=false;	//同一订单，重复支付，是否自动退款，默认为不自动退款
	private String timeout="";					//订单超时时间，默认为300m（分钟）
	private String sign="";						//签名
	private String nonceStr="";					//随机字符串RSA加密后的密文
	//以下是返回数据
	private String code="";						//响应状态码
	private String msg="";						//响应信息
	private String ticketId="";					//用户调起PaySDK的ticket_id
	private String nonceStrBack="";				//返回的RSA加密后的随机字符串
	private String signBack="";					//返回的签名
	private Long payAt=0L;						//支付时间
	private Integer result=0;					//交易结果
	private String tradeMsg="";					//交易信息
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderIdSe() {
		return orderIdSe;
	}
	public void setOrderIdSe(String orderIdSe) {
		this.orderIdSe = orderIdSe;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getAppUserId() {
		return appUserId;
	}
	public void setAppUserId(String appUserId) {
		this.appUserId = appUserId;
	}
	public String getAppUserNickname() {
		return appUserNickname;
	}
	public void setAppUserNickname(String appUserNickname) {
		this.appUserNickname = appUserNickname;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public Integer getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(Integer payChannel) {
		this.payChannel = payChannel;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public Integer getPlat() {
		return plat;
	}
	public void setPlat(Integer plat) {
		this.plat = plat;
	}
	public Boolean getRepeatPayAutoRefund() {
		return repeatPayAutoRefund;
	}
	public void setRepeatPayAutoRefund(Boolean repeatPayAutoRefund) {
		this.repeatPayAutoRefund = repeatPayAutoRefund;
	}
	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	public String getNonceStrBack() {
		return nonceStrBack;
	}
	public void setNonceStrBack(String nonceStrBack) {
		this.nonceStrBack = nonceStrBack;
	}
	public String getSignBack() {
		return signBack;
	}
	public void setSignBack(String signBack) {
		this.signBack = signBack;
	}
	public Long getPayAt() {
		return payAt;
	}
	public void setPayAt(Long payAt) {
		this.payAt = payAt;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getTradeMsg() {
		return tradeMsg;
	}
	public void setTradeMsg(String tradeMsg) {
		this.tradeMsg = tradeMsg;
	}
	@Override
	public String toString() {
		return "Mobpay [appkey=" + appkey + ", orderId=" + orderId + ", orderIdSe=" + orderIdSe + ", orderType="
				+ orderType + ", appUserId=" + appUserId + ", appUserNickname=" + appUserNickname + ", amount=" + amount
				+ ", subject=" + subject + ", body=" + body + ", description=" + description + ", metadata=" + metadata
				+ ", payChannel=" + payChannel + ", clientIp=" + clientIp + ", plat=" + plat + ", repeatPayAutoRefund="
				+ repeatPayAutoRefund + ", timeout=" + timeout + ", sign=" + sign + ", nonceStr=" + nonceStr + ", code="
				+ code + ", msg=" + msg + ", ticketId=" + ticketId + ", nonceStrBack=" + nonceStrBack + ", signBack="
				+ signBack + ", payAt=" + payAt + ", result=" + result + ", tradeMsg=" + tradeMsg + "]";
	}
	
	
	
}
package com.app.entity;

import java.util.Date;

/**
 * 阿里支付实体类
 * @author taoxiangfei
 *
 */
public class Alipay {
	String consumerid;		//用户id
	Date insertdatetime;	//订单创建时间
	String	outtradeno;		//商户订单号
	String moneyType;		//支付类型，start-定金，end-尾款
	String totalAmount;		//订单交易金额
	String subject;			//订单标题
	String timeoutExpress;	//订单超时时间
	String code;			//网关返回状态码
	String msg;				//网关返回码描述
	String subCode;			//业务返回码
	String subMsg;			//业务返回码描述
	String sign;			//签名
	String tradeno;			//支付宝交易号
	String buyerLogonId;	//买家支付宝账号
	String receiptAmount;	//实收金额
	String buyerPayAmount;	//买家付款的金额
	String pointAmount;		//使用积分宝付款的金额
	String invoiceAmount;	//交易中可给用户开具发票的金额
	Date gmtPayment;		//交易支付时间
	String buyerUserId;		//买家在支付宝的用户id
	String tradeStatus;		//交易状态
	Date notifyTime;		//通知时间
	public String getConsumerid() {
		return consumerid;
	}
	public void setConsumerid(String consumerid) {
		this.consumerid = consumerid;
	}
	public Date getInsertdatetime() {
		return insertdatetime;
	}
	public void setInsertdatetime(Date insertdatetime) {
		this.insertdatetime = insertdatetime;
	}
	public String getOuttradeno() {
		return outtradeno;
	}
	public void setOuttradeno(String outtradeno) {
		this.outtradeno = outtradeno;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTimeoutExpress() {
		return timeoutExpress;
	}
	public void setTimeoutExpress(String timeoutExpress) {
		this.timeoutExpress = timeoutExpress;
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
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public String getSubMsg() {
		return subMsg;
	}
	public void setSubMsg(String subMsg) {
		this.subMsg = subMsg;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTradeno() {
		return tradeno;
	}
	public void setTradeno(String tradeno) {
		this.tradeno = tradeno;
	}
	public String getBuyerLogonId() {
		return buyerLogonId;
	}
	public void setBuyerLogonId(String buyerLogonId) {
		this.buyerLogonId = buyerLogonId;
	}
	public String getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(String receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public String getBuyerPayAmount() {
		return buyerPayAmount;
	}
	public void setBuyerPayAmount(String buyerPayAmount) {
		this.buyerPayAmount = buyerPayAmount;
	}
	public String getPointAmount() {
		return pointAmount;
	}
	public void setPointAmount(String pointAmount) {
		this.pointAmount = pointAmount;
	}
	public String getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public Date getGmtPayment() {
		return gmtPayment;
	}
	public void setGmtPayment(Date gmtPayment) {
		this.gmtPayment = gmtPayment;
	}
	public String getBuyerUserId() {
		return buyerUserId;
	}
	public void setBuyerUserId(String buyerUserId) {
		this.buyerUserId = buyerUserId;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public Date getNotifyTime() {
		return notifyTime;
	}
	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}
	public String getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}
	
}

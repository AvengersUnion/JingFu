package com.app.entity;

import java.util.Date;

/**
 * 微信支付实体类
 * @author taoxiangfei
 *
 */
public class Wxpay {
	String consumerid;		//用户id
	Date insertdatetime;	//订单创建时间
	String	outtradeno;		//商户订单号
	String moneyType;		//支付类型，start-定金，end-尾款
	String totalFee;		//订单交易金额
	String subject;			//订单标题
	String returnCode;			//通信标识
	String returnMsg;				//通信标识描述
	String resultCode;			//业务返回码
	String errCode;			//错误返回的信息描述
	String errCodeDes;			//错误返回的信息描述
	String openid;			//用户在商户appid下的唯一标识
	String sign;			//签名
	String bankType;		//银行类型，采用字符串类型的银行标识，银行类型见银行列表
	String transactionId;	//微信支付订单号
	Date timeEnd;			//支付完成时间
	String prepayid;		//预付单id
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrCodeDes() {
		return errCodeDes;
	}
	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Date getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getPrepayid() {
		return prepayid;
	}
	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}
	
}

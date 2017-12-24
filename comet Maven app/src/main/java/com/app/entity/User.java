package com.app.entity;

import java.util.Date;

public class User {
	private Integer id;	//用户id
	private String userName;	//用户名
	private String password;	//密码
	private Date createTime;	//创建时间
	private String remark;		//备注
	private String expand1;		//扩展字段1
	private String expand2;		//扩展字段2
	
	private UserAuths userAuths;	//第三方登陆信息
	public UserAuths getUserAuths() {
		return userAuths;
	}
	public void setUserAuths(UserAuths userAuths) {
		this.userAuths = userAuths;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getExpand1() {
		return expand1;
	}
	public void setExpand1(String expand1) {
		this.expand1 = expand1;
	}
	public String getExpand2() {
		return expand2;
	}
	public void setExpand2(String expand2) {
		this.expand2 = expand2;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", createTime=" + createTime
				+ ", remark=" + remark + ", expand1=" + expand1 + ", expand2=" + expand2 + ", userAuths=" + userAuths
				+ "]";
	}
	
}

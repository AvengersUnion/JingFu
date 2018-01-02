package com.app.entity;

import java.util.Date;
/**
 * 第三方登陆信息
 * @author taoxiangfei
 *
 */
public class UserAuths {
	private Integer id;		//第三方登陆id
	private String userId;	//用户id
	private String identityType;	//登陆类型
	private String identifier;		//登陆标识
	private String credential;		//第三方登录凭证
	private Date nettime;			//有效时间，特别是手机验证码
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIdentityType() {
		return identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	public Date getNettime() {
		return nettime;
	}
	public void setNettime(Date nettime) {
		this.nettime = nettime;
	}
	@Override
	public String toString() {
		return "UserAuths [id=" + id + ", userId=" + userId + ", identityType=" + identityType + ", identifier="
				+ identifier + ", credential=" + credential + ", nettime=" + nettime + "]";
	}
	
	
}

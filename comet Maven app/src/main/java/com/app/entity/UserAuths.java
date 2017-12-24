package com.app.entity;

public class UserAuths {
	private Integer id;		//第三方登陆id
	private Integer userId;	//用户id
	private String identityType;	//登陆类型
	private String identifier;		//登陆标识
	private String credential;		//第三方登录凭证
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
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
	@Override
	public String toString() {
		return "UserAuths [id=" + id + ", userId=" + userId + ", identityType=" + identityType + ", identifier="
				+ identifier + ", credential=" + credential + "]";
	}
	
}

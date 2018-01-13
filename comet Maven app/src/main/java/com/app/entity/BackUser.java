package com.app.entity;

import java.util.Date;
import java.util.List;

public class BackUser {
	//用户表中的id,uName,userIphone,passWord
	private Integer id;
	private String uName;
	private String userIphone;
	private String passWord;
	private Date nettime;
	private String token;
	//address表中的country，province，city，county，village
	private String community;
	private String houseNumber;
	private String province;
	private String city;
	private String county;
	private String village;
	//最后返回到页面的地址
	private String address;
	private List<BackUser> backUserList;
	//第三方登陆信息
	private UserAuths wxAuths;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the userIphone
	 */
	public String getUserIphone() {
		return userIphone;
	}
	/**
	 * @param userIphone the userIphone to set
	 */
	public void setUserIphone(String userIphone) {
		this.userIphone = userIphone;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
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
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}
	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		address=this.community+this.houseNumber;
		return address;
	}
	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/**
	 * @return the village
	 */
	public String getVillage() {
		return village;
	}
	/**
	 * @param village the village to set
	 */
	public void setVillage(String village) {
		this.village = village;
	}
	/**
	 * @return the backUserList
	 */
	public List<BackUser> getBackUserList() {
		return backUserList;
	}
	/**
	 * @param backUserList the backUserList to set
	 */
	public void setBackUserList(List<BackUser> backUserList) {
		this.backUserList = backUserList;
	}
	public Date getNettime() {
		return nettime;
	}
	public void setNettime(Date nettime) {
		this.nettime = nettime;
	}
	public UserAuths getWxAuths() {
		return wxAuths;
	}
	public void setWxAuths(UserAuths wxAuths) {
		this.wxAuths = wxAuths;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "BackUser [id=" + id + ", uName=" + uName + ", userIphone=" + userIphone + ", passWord=" + passWord
				+ ", nettime=" + nettime + ", token=" + token + ", community=" + community + ", houseNumber="
				+ houseNumber + ", province=" + province + ", city=" + city + ", county=" + county + ", village="
				+ village + ", address=" + address + ", backUserList=" + backUserList + ", wxAuths=" + wxAuths + "]";
	}
	
}

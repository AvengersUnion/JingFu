package com.app.entity;

import java.util.List;

public class BackUser {
	//用户表中的id,uName,userIphone,passWord
	private String id;
	private String uName;
	private String userIphone;
	private String passWord;
	//address表中的country，province，city，county，village
	private String country;
	private String province;
	private String city;
	private String county;
	private String village;
	//最后返回到页面的地址
	private String address;
	private List<BackUser> backUserList;
	 
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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
		address=this.country+this.province+this.city+this.county+this.village;
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
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
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
	
}

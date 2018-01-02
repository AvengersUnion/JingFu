package com.app.entity;

public class Advisory {
	private int id;
	private String advisoryImage;
	private String advisoryTitle;
	private String advisoryDetails;
	private String advisoryTime;
	/*
	private int imageId;
	private String imagePath;
	private int imageCode;
	*/
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		if (null==id) {
			id=0;
		}
		this.id = id;
	}
	/**
	 * @return the advisoryImage
	 */
	public String getAdvisoryImage() {
		return advisoryImage;
	}
	/**
	 * @param advisoryImage the advisoryImage to set
	 */
	public void setAdvisoryImage(String advisoryImage) {
		this.advisoryImage = advisoryImage;
	}
	/**
	 * @return the advisoryTitle
	 */
	public String getAdvisoryTitle() {
		return advisoryTitle;
	}
	/**
	 * @param advisoryTitle the advisoryTitle to set
	 */
	public void setAdvisoryTitle(String advisoryTitle) {
		this.advisoryTitle = advisoryTitle;
	}
	/**
	 * @return the advisoryDetails
	 */
	public String getAdvisoryDetails() {
		return advisoryDetails;
	}
	/**
	 * @param advisoryDetails the advisoryDetails to set
	 */
	public void setAdvisoryDetails(String advisoryDetails) {
		this.advisoryDetails = advisoryDetails;
	}
	/**
	 * @return the advisoryTime
	 */
	public String getAdvisoryTime() {
		return advisoryTime;
	}
	/**
	 * @param advisoryTime the advisoryTime to set
	 */
	public void setAdvisoryTime(String advisoryTime) {
		this.advisoryTime = advisoryTime;
	}
	
}

package com.app.entity;

public class Collect {
	private Integer id;
	private Integer userId;
	private Integer advisoryId;
	private String collectTime;
	private Advisory advisory;
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
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		
		this.userId = userId;
	}
	/**
	 * @return the advisoryId
	 */
	public Integer getAdvisoryId() {
		return advisoryId;
	}
	/**
	 * @param advisoryId the advisoryId to set
	 */
	public void setAdvisoryId(Integer advisoryId) {
		
		this.advisoryId = advisoryId;
	}
	/**
	 * @return the collectTime
	 */
	public String getCollectTime() {
		return collectTime;
	}
	/**
	 * @param collectTime the collectTime to set
	 */
	public void setCollectTime(String collectTime) {
		if(collectTime.contains(",")){
			collectTime=collectTime.substring(collectTime.lastIndexOf("."));
		}
		this.collectTime = collectTime;
	}
	/**
	 * @return the advisory
	 */
	public Advisory getAdvisory() {
		return advisory;
	}
	/**
	 * @param advisory the advisory to set
	 */
	public void setAdvisory(Advisory advisory) {
		this.advisory = advisory;
	}
	
}

package com.app.entity;

public class ServiceContracts {
	private Integer id;
	private String contractTitle;
	private String contractDetails;
	private String createTime;
	private Integer state;
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
	 * @return the contractDetails
	 */
	public String getContractDetails() {
		return contractDetails;
	}
	/**
	 * @param contractDetails the contractDetails to set
	 */
	public void setContractDetails(String contractDetails) {
		this.contractDetails = contractDetails;
	}
	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * @return the contractTitle
	 */
	public String getContractTitle() {
		return contractTitle;
	}
	/**
	 * @param contractTitle the contractTitle to set
	 */
	public void setContractTitle(String contractTitle) {
		this.contractTitle = contractTitle;
	}
	
}

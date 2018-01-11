package com.app.entity;

public class StartPage {
	private Integer id;
	private String startPagePath;
	private String creatTime;
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
		if (null==id) {
			id=0;
		}
		this.id = id;
	}
	/**
	 * @return the startPagePath
	 */
	public String getStartPagePath() {
		return startPagePath;
	}
	/**
	 * @param startPagePath the startPagePath to set
	 */
	public void setStartPagePath(String startPagePath) {
		this.startPagePath = startPagePath;
	}
	/**
	 * @return the creatTime
	 */
	public String getCreatTime() {
		return creatTime;
	}
	/**
	 * @param creatTime the creatTime to set
	 */
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
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
		if(null==state){
			state=0;
		}
		this.state = state;
	}
	
}

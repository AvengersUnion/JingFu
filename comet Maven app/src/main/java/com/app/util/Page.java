package com.app.util;

import java.util.List;

public class Page<T> {

	//当前页数
	private Integer pageCode;
	//总记录数
	private Integer allNumber;
	//每一页的记录数
	private Integer pageSize;
	//每一页的数据
	private List<T> beanList;
	//总页数
	public int getPageNum() {
		// 计算
		int totalPage = allNumber / pageSize;
		// 说明整除
		if(allNumber % pageSize == 0){
			return totalPage;
		}else{
			return totalPage + 1;
		}
	}
	
	public Integer getPageCode() {
		return pageCode;
	}
	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}
	public Integer getAllNumber() {
		return allNumber;
	}

	public void setAllNumber(Integer allNumber) {
		this.allNumber = allNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	
}

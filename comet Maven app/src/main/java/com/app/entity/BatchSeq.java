package com.app.entity;

public class BatchSeq {
	private Integer id;		//批次号id
	private String fuwuId;	//生成一级服务的id
	private String erjifuwuId;	//生成二级服务的id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFuwuId() {
		return fuwuId;
	}
	public void setFuwuId(String fuwuId) {
		this.fuwuId = fuwuId;
	}
	public String getErjifuwuId() {
		return erjifuwuId;
	}
	public void setErjifuwuId(String erjifuwuId) {
		this.erjifuwuId = erjifuwuId;
	}
	
}

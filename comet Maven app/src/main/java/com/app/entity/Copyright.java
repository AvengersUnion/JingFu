package com.app.entity;



/**
 * 版权协议
 * @author taoxiangfei
 *
 */

public class Copyright {
	
	private Integer id;
	private String copyright;
	private String company;
	private String servicePhone;
	private String support;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getServicePhone() {
		return servicePhone;
	}
	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	public String getSupport() {
		return support;
	}
	public void setSupport(String support) {
		this.support = support;
	}
	@Override
	public String toString() {
		return "Copyright [id=" + id + ", copyright=" + copyright + ", company=" + company + ", servicePhone="
				+ servicePhone + ", support=" + support + "]";
	}
	
}

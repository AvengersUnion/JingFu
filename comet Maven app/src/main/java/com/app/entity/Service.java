package com.app.entity;

import java.util.List;

/**
 * 服务实体类
 */
public class Service {

    //服务唯一标识符
    private String id;
    //服务名称
    private String serviceName;
    //服务套餐
    private String period;
	//服务最低价格
    private Double price;
    //预付定金
    private Double prepay;
    //折扣比例
    private Float discount;
    private String spec;	//规格
    //服务概要说明
    private String summary;
    //服务详情
    private String serviceDetail;
    //服务所在地
    private String regionRc;
    //服务广告图片路径名称
    private String adImgPath;
    //服务详情图片路径
    private String detailImgPath;
    //服务父级id
    private String pid;
    //服务状态
    private String status;
    //服务状态说明
    private String vl;
    
    
    
    public String getServiceDetail() {
		return serviceDetail;
	}

	public void setServiceDetail(String serviceDetail) {
		this.serviceDetail = serviceDetail;
	}

	public String getVl() {
		return vl;
	}

	public void setVl(String vl) {
		this.vl = vl;
	}

	private List<Service> childService; //子服务集合

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrepay() {
		return prepay;
	}

	public void setPrepay(Double prepay) {
		this.prepay = prepay;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRegionRc() {
		return regionRc;
	}

	public void setRegionRc(String regionRc) {
		this.regionRc = regionRc;
	}

	public String getAdImgPath() {
		return adImgPath;
	}

	public void setAdImgPath(String adImgPath) {
		this.adImgPath = adImgPath;
	}

	public String getDetailImgPath() {
		return detailImgPath;
	}

	public void setDetailImgPath(String detailImgPath) {
		this.detailImgPath = detailImgPath;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Service> getChildService() {
		return childService;
	}

	public void setChildService(List<Service> childService) {
		this.childService = childService;
	}
    
    

  
    
	



 	
}

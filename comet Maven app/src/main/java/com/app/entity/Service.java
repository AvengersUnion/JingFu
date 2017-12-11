package com.app.entity;

/**
 * 服务实体类
 */
public class Service {

    //服务唯一标识符
    private String id;
    //服务名称
    private String name;
    
    private Integer ig;
	//服务价格
    private Double price;
    //预付定金
    private Double prepay;
    //折扣比例
    private Float discount;
    //服务概要说明
    private String summary;
    //服务周期
    private String period;
    //服务详情
    private String details;
    //
    private String regionRc;
    //服务广告图片路径
    private String adImgPath;
    //服务广告图片名称
    private String adImgName;
    //服务详情图片路径
    private String detImgPath;
    //服务详情图片名称
    private String detImgName;
    //服务类型标识符KY
    private String tp;
    private String stt;
    //控制商品排序ORDER
    private Integer od;

    
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

	public String getRegionRc() {
		return regionRc;
	}

	public void setRegionRc(String regionRc) {
		this.regionRc = regionRc;
	}

	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getSummary() {
        return summary;
    }

    public String getPeriod() {
        return period;
    }

    public String getDetails() {
        return details;
    }

    public String getTp() {
        return tp;
    }

    public Integer getOd() {
        return od;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public void setOd(Integer od) {
        this.od = od;
    }
    public Integer getIg() {
 		return ig;
 	}

 	public void setIg(Integer ig) {
 		this.ig = ig;
 	}

	public String getAdImgPath() {
		return adImgPath;
	}

	public void setAdImgPath(String adImgPath) {
		this.adImgPath = adImgPath;
	}

	public String getAdImgName() {
		return adImgName;
	}

	public void setAdImgName(String adImgName) {
		this.adImgName = adImgName;
	}

	public String getDetImgPath() {
		return detImgPath;
	}

	public void setDetImgPath(String detImgPath) {
		this.detImgPath = detImgPath;
	}

	public String getDetImgName() {
		return detImgName;
	}

	public void setDetImgName(String detImgName) {
		this.detImgName = detImgName;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", ig=" + ig + ", price=" + price + ", prepay=" + prepay
				+ ", discount=" + discount + ", summary=" + summary + ", period=" + period + ", details=" + details
				+ ", regionRc=" + regionRc + ", adImgPath=" + adImgPath + ", adImgName=" + adImgName + ", detImgPath="
				+ detImgPath + ", detImgName=" + detImgName + ", tp=" + tp + ", stt=" + stt + ", od=" + od + "]";
	}
	



 	
}

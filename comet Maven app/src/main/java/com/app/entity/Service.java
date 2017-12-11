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
    private String region_rc;
    //服务广告图片路径
    private String ad_img_path;
    //服务广告图片名称
    private String ad_img_name;
    //服务详情图片路径
    private String det_img_path;
    //服务详情图片名称
    private String det_img_name;
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

	public String getRegion_rc() {
		return region_rc;
	}

	public void setRegion_rc(String region_rc) {
		this.region_rc = region_rc;
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

    public String getAd_img_path() {
        return ad_img_path;
    }

    public String getAd_img_name() {
        return ad_img_name;
    }

    public String getDet_img_path() {
        return det_img_path;
    }

    public String getDet_img_name() {
        return det_img_name;
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

    public void setAd_img_path(String ad_img_path) {
        this.ad_img_path = ad_img_path;
    }

    public void setAd_img_name(String ad_img_name) {
        this.ad_img_name = ad_img_name;
    }

    public void setDet_img_path(String det_img_path) {
        this.det_img_path = det_img_path;
    }

    public void setDet_img_name(String det_img_name) {
        this.det_img_name = det_img_name;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ad_img_name == null) ? 0 : ad_img_name.hashCode());
		result = prime * result + ((ad_img_path == null) ? 0 : ad_img_path.hashCode());
		result = prime * result + ((det_img_name == null) ? 0 : det_img_name.hashCode());
		result = prime * result + ((det_img_path == null) ? 0 : det_img_path.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ig == null) ? 0 : ig.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((od == null) ? 0 : od.hashCode());
		result = prime * result + ((period == null) ? 0 : period.hashCode());
		result = prime * result + ((prepay == null) ? 0 : prepay.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((region_rc == null) ? 0 : region_rc.hashCode());
		result = prime * result + ((stt == null) ? 0 : stt.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result + ((tp == null) ? 0 : tp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		if (ad_img_name == null) {
			if (other.ad_img_name != null)
				return false;
		} else if (!ad_img_name.equals(other.ad_img_name))
			return false;
		if (ad_img_path == null) {
			if (other.ad_img_path != null)
				return false;
		} else if (!ad_img_path.equals(other.ad_img_path))
			return false;
		if (det_img_name == null) {
			if (other.det_img_name != null)
				return false;
		} else if (!det_img_name.equals(other.det_img_name))
			return false;
		if (det_img_path == null) {
			if (other.det_img_path != null)
				return false;
		} else if (!det_img_path.equals(other.det_img_path))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ig == null) {
			if (other.ig != null)
				return false;
		} else if (!ig.equals(other.ig))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (od == null) {
			if (other.od != null)
				return false;
		} else if (!od.equals(other.od))
			return false;
		if (period == null) {
			if (other.period != null)
				return false;
		} else if (!period.equals(other.period))
			return false;
		if (prepay == null) {
			if (other.prepay != null)
				return false;
		} else if (!prepay.equals(other.prepay))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (region_rc == null) {
			if (other.region_rc != null)
				return false;
		} else if (!region_rc.equals(other.region_rc))
			return false;
		if (stt == null) {
			if (other.stt != null)
				return false;
		} else if (!stt.equals(other.stt))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (tp == null) {
			if (other.tp != null)
				return false;
		} else if (!tp.equals(other.tp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", ig=" + ig + ", price=" + price + ", prepay=" + prepay
				+ ", discount=" + discount + ", summary=" + summary + ", period=" + period + ", details=" + details
				+ ", region_rc=" + region_rc + ", ad_img_path=" + ad_img_path + ", ad_img_name=" + ad_img_name
				+ ", det_img_path=" + det_img_path + ", det_img_name=" + det_img_name + ", tp=" + tp + ", stt=" + stt
				+ ", od=" + od + "]";
	}
 	
}

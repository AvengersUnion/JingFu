package com.app.entity;

/**
 * 商品实体
 * 
 * @author taoxiangfei
 *
 */
public class Goods {
	private String id;
	private String cn;
	private Integer ig;
	private Double price;
	private Double prepay;
	private Float discount;
	private String cs;
	private String cd;
	private String regionRc;
	private String aip;
	private String ain;
	private String dip;
	private String din;
	private String tp;
	private String stt;
	private Integer od;
	private Integer inventory;

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getAip() {
		return aip;
	}

	public void setAip(String aip) {
		this.aip = aip;
	}

	public String getAin() {
		return ain;
	}

	public void setAin(String ain) {
		this.ain = ain;
	}

	public String getDip() {
		return dip;
	}

	public void setDip(String dip) {
		this.dip = dip;
	}

	public String getDin() {
		return din;
	}

	public void setDin(String din) {
		this.din = din;
	}

	public String getTp() {
		return tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	public String getRegionRc() {
		return regionRc;
	}

	public void setRegionRc(String regionRc) {
		this.regionRc = regionRc;
	}

	public Integer getIg() {
		return ig;
	}

	public void setIg(Integer ig) {
		this.ig = ig;
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

	public Integer getOd() {
		return od;
	}

	public void setOd(Integer od) {
		this.od = od;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", cn=" + cn + ", ig=" + ig + ", price=" + price + ", prepay=" + prepay
				+ ", discount=" + discount + ", cs=" + cs + ", cd=" + cd + ", regionRc=" + regionRc + ", aip=" + aip
				+ ", ain=" + ain + ", dip=" + dip + ", din=" + din + ", tp=" + tp + ", stt=" + stt + ", od=" + od + "]";
	}

}

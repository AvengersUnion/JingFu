package com.app.entity;

public class Goods {
  private String id;
  private String cn;
  private String ig;
  private String price;
  private String prepay;
  private String discount;
  private String cs;
  private String cd;
  private String regionRc;
  private String aip;
  private String ain;
  private String dip;
  private String din;
  private String tp;
  private String stt;
  private String od;

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

  public String getIg() {
    return ig;
  }

  public void setIg(String ig) {
    this.ig = ig;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getPrepay() {
    return prepay;
  }

  public void setPrepay(String prepay) {
    this.prepay = prepay;
  }

  public String getDiscount() {
    return discount;
  }

  public void setDiscount(String discount) {
    this.discount = discount;
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

  public String getOd() {
    return od;
  }

  public void setOd(String od) {
    this.od = od;
  }



public String getRegionRc() {
	return regionRc;
}

public void setRegionRc(String regionRc) {
	this.regionRc = regionRc;
}

@Override
public String toString() {
	return "Goods [id=" + id + ", cn=" + cn + ", ig=" + ig + ", price=" + price + ", prepay=" + prepay + ", discount="
			+ discount + ", cs=" + cs + ", cd=" + cd + ", regionRc=" + regionRc + ", aip=" + aip + ", ain=" + ain
			+ ", dip=" + dip + ", din=" + din + ", tp=" + tp + ", stt=" + stt + ", od=" + od + "]";
}
  
}

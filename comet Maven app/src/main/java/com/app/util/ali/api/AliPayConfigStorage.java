package com.app.util.ali.api;

import com.app.util.pay.api.BasePayConfigStorage;

/**
 * 支付配置存储
 * @author  egan
 *
 * email egzosn@gmail.com
 * date 2016-5-18 14:09:01
 */
public class AliPayConfigStorage extends BasePayConfigStorage {

    /**
     *  商户应用id
     */
    private volatile  String appId ;
    /**
     *  商户签约拿到的pid,partner_id的简称，合作伙伴身份等同于 partner
     */
    private volatile  String pid ;

    /**
     * 商户收款账号
     */
    private volatile  String seller;



    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppid() {
        return appId;
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }




}

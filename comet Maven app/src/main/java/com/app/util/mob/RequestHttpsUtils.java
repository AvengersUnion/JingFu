package com.app.util.mob;

import java.util.Map;

import com.app.util.Application;
import com.xiaoleilu.hutool.crypto.digest.DigestUtil;
import com.xiaoleilu.hutool.http.HttpRequest;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;

public class RequestHttpsUtils {

    public static HttpsResult send(String reqUrl, Map<String, String> map) {
        HttpsResult httpResult = new HttpsResult();
        map.put("nonce_str", DigestUtil.md5Hex(String.valueOf(System.currentTimeMillis())));// 生成随机串
        System.out.println("随机串："+map.get("nonce_str").toString());
        String sign = SignUtils.generateSign(map).toUpperCase();
        map.put("sign", sign);
        System.out.println("sign:"+sign);
        String nonce_str = com.app.util.pay.util.sign.encrypt.RSA.sign(map.get("nonce_str").toString(), Application.mobprivatekey, "utf-8");
        System.out.println("RSAnonce_str:"+nonce_str);
        map.put("nonce_str", nonce_str);// 生成随机串
        System.out.println("请求参数："+map.toString());
        JSONObject sendJson = JSONUtil.parseFromMap(map);
        httpResult.setSendMsg(map.toString());
//        String replyMsg = HttpRequest.post(reqUrl).timeout(6000).setSSLSocketFactory(CertificateConfig.getsSLSocketFactory()).body(sendJson).execute().body();
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String replyMsg = httpClientUtil.doPost(Application.mobpreparepay, map,"utf-8");  
        httpResult.setReplyMsg(replyMsg);
        return httpResult;
    }


    public static HttpsResult sendXml(String reqUrl, Map<String, String> map) {
        HttpsResult httpResult = new HttpsResult();
        map.put("nonce_str", RandomUtils.randomAll(64));// 
        String sign = SignUtils.generateSign(map);
        map.put("sign", sign);
        String sendMsg = XmlUtils.parseXML(map);
        httpResult.setSendMsg(map.toString());
        String replyMsg = HttpRequest.post(reqUrl).setSSLSocketFactory(CertificateConfig.getsSLSocketFactory()).body(sendMsg,
                                                                                                                     "text/xml;charset=utf-8").execute().body();
        httpResult.setReplyMsg(replyMsg);
        return httpResult;
    }
}

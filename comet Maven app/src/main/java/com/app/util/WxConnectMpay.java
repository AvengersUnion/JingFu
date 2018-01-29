package com.app.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import com.app.entity.ServiceOrder;

public class WxConnectMpay {
	static final String queryurl=Application.wxqueryurl;
	static final String refundurl=Application.wxrefundurl;
	static final String key=Application.wxpayKey; 
	static final String appid = Application.wxAPPID;
	static final String mchid=Application.wxmchid;
	static final String createUrl=Application.wxcreateUrl;
	/**
	 * 创建订单
	 * @param serviceOrder
	 * @return
	 * @throws Exception
	 */
	public String createWXOrder(ServiceOrder serviceOrder) throws Exception{  
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
        parameters.put("appid", appid);
        parameters.put("mch_id", mchid);
        parameters.put("out_trade_no", serviceOrder.getOrderId());
        parameters.put("nonce_str", CreateNoncestr());
        parameters.put("body", serviceOrder.getServiceName());                                                    
        parameters.put("total_fee", String.valueOf(serviceOrder.getMoney()));                                               
        parameters.put("spbill_create_ip", "127.0.0.1");
        parameters.put("notify_url", "");
        parameters.put("trade_type", "APP");
        String sign = createSign("utf-8", parameters);
        System.out.println("sign:"+sign);
        parameters.put("sign", sign);
        String requestXml = getRequestXml(parameters);
        System.out.println("requestXml:"+requestXml);

        String resp = "";
        try{ 
        	HttpClient httpClient = new DefaultHttpClient();
           HttpPost httpPost = new HttpPost(createUrl);  
           StringEntity  reqEntity  = new StringEntity(requestXml);
           httpPost.setEntity(reqEntity);
        // 发送请求  
           HttpResponse response = httpClient.execute(httpPost);  
           System.out.println("response:"+response);
       
               HttpEntity entity = response.getEntity();
               System.out.println("entity:"+entity);
               System.out.println(response.getStatusLine());
               if (entity != null) {
                   System.out.println("Response content length: " + entity.getContentLength());
                   BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
                   StringBuffer buffer = new StringBuffer();
       			String line = "";
                   while ((line = in.readLine()) != null) {
                       System.out.println(line);
                       buffer.append(line);
                   }
                   resp = buffer.toString();
               }

        } catch (IOException e) {
			e.printStackTrace(); 
			return "";
       } 
       return resp;
   } 
	/**
	 * 查询订单
	 * @param outTradeNo
	 * @return
	 * @throws Exception
	 */
	public String QueryWXOrder(String outTradeNo) throws Exception{  
		
        return "";
    } 
	/**
	 * 退款订单
	 * @param outTradeNo
	 * @param tolAmount
	 * @param refundAmount
	 * @return
	 * @throws Exception
	 */
	public String RefundWXOrder(String outTradeNo,String tolAmount,String refundAmount) throws Exception{  
		
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
        parameters.put("appid", appid);
        parameters.put("mch_id", mchid);
        //��notify_url�н���΢�ŷ��ص���Ϣ��ȡ�� transaction_id������Ǳ���
        //parameters.put("transaction_id", "");
        parameters.put("out_trade_no", outTradeNo);
        parameters.put("out_refund_no", CreateNoncestr());   //��֤Ψһ����
        parameters.put("nonce_str", CreateNoncestr());
        parameters.put("total_fee", tolAmount) ;                                                                          //��λΪ��
        parameters.put("refund_fee", refundAmount);                                                                      //��λΪ��
        parameters.put("op_user_id", mchid);
        String sign = createSign("utf-8", parameters);
        parameters.put("sign", sign);
        
        String reuqestXml = getRequestXml(parameters);
  
        KeyStore keyStore = KeyStore.getInstance("PKCS12"); 
        String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        FileInputStream instream = new FileInputStream(new File(path.substring(0, path.length()-8)+"apiclient_cert.p12"));  

        try {
			keyStore.load(instream, mchid.toCharArray());
		
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
        }finally {  
            instream.close();  
        }  
 
      return null;
   } 
	
	
	public static String CreateNoncestr() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < 16; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
	}
	
	public static String createSign(String charSet,SortedMap<Object,Object> parameters) throws Exception{
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = md5(sb.toString()).toUpperCase();
        //String sign = MD5Util.MD5Encode(sb.toString(), charSet).toUpperCase();
        return sign;
	}
	public static String getRequestXml(SortedMap<Object,Object> parameters){
	    StringBuffer sb = new StringBuffer();
	    sb.append("<xml>");
	    Set es = parameters.entrySet();
	    Iterator it = es.iterator();
	    while(it.hasNext()) {
	        Map.Entry entry = (Map.Entry)it.next();
	        String k = (String)entry.getKey();
	        String v = (String)entry.getValue();
	        if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
	            sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
	        }else {
	            sb.append("<"+k+">"+v+"</"+k+">");
	        }
	    }
	    sb.append("</xml>");
	    return sb.toString();
	}
	 
    private static String md5(String value) throws Exception{
		//System.out.println(value);
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        mdInst.update(value.getBytes("UTF-8"));
        byte[] arr=mdInst.digest();
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < arr.length; ++i) {  
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,3));  
        }  
        return sb.toString();  
    } 
    


}

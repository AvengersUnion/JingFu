package com.app.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class AuthUtil {

	public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
		JSONObject jsonObject = null;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);						
		HttpResponse response = client.execute(httpGet);		
		HttpEntity entity = response.getEntity();				
		if(entity != null) {
			String result = EntityUtils.toString(entity);
			jsonObject = JSONObject.parseObject(new String(result.getBytes("ISO-8859-1"),"UTF-8"));
		}
//		httpGet.releaseConnection();
		return jsonObject;
	}
}

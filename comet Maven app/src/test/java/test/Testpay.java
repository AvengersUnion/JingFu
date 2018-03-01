package test;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.app.util.mob.HttpClientUtil;




public class Testpay {
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<>();
		map.put("sign", "B106A3D84A465DC24C09C5327B89F022");
		map.put("amount", "1");
		map.put("result", "2");
		map.put("ticketId", "F9A7420106989628ACF61E76D2575282");
		map.put("appkey", "23f45043a5216");
		map.put("payAt", "1519782681988");
		map.put("nonce_str", "YWWCsAuWF22VpFAKiNxWYnW6ilebxgA3%2BLhZMr6bVUzUrHSo%2BBYWnVTIvlpGrvk2efgg7%2B6aEMuP%0AIGdMjPHunBxgcHm7GWjfULWpusRT4xUUeARr%2FO46TY%2FbyuXE7u%2B2PYzPbdvYokr2rqA7CdV5J22F%0Afal8paiU0BX%2BaINX5Js%3D");
		map.put("msg", "TRADE_SUCCESS");
		map.put("channel", "22");
		map.put("orderId", "1000173634211519874753502");
		String  param= JSON.toJSONString(map);
		System.out.println(param);
		HttpClientUtil httpClientUtil = new HttpClientUtil();
        String replyMsg = httpClientUtil.doPost("http://www.yehaikeji.com:8080/comet/mobpay/payBackUrl.do", map,"utf-8");  
        System.out.println(replyMsg);
		
		
		
//		String r = "sign=B94BB1A5A3D0ADD9C8B4C9B86ED9C3D5&amount=1&result=2&ticketId=E6F6D7401916C531C384F3F74ED44557&appkey=23f45043a5216&payAt=1519875661738&nonce_str=YWWCsAuWF22VpFAKiNxWYnW6ilebxgA3%2BLhZMr6bVUzUrHSo%2BBYWnVTIvlpGrvk2efgg7%2B6aEMuP%0AIGdMjPHunBxgcHm7GWjfULWpusRT4xUUeARr%2FO46TY%2FbyuXE7u%2B2PYzPbdvYokr2rqA7CdV5J22F%0Afal8paiU0BX%2BaINX5Js%3D&msg=TRADE_SUCCESS&channel=22&orderId=1000073133111519875650665";
//		String jsonstr = "{";
//		String[] arraystr = r.split("&");
//		for (int i = 0; i < arraystr.length; i++) {
//			String[] arraystra = arraystr[i].split("=");
//			for(int j=0;j<arraystra.length;j++) {
//				jsonstr+="\""+arraystra[j]+"\":";
//			}
//			jsonstr = jsonstr.substring(0, jsonstr.length()-1)+",";
//		}
//		jsonstr = jsonstr.substring(0, jsonstr.length()-1)+"}";
//		System.out.println(jsonstr);
	}
}

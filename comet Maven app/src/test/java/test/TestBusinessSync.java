package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.xiaoleilu.hutool.json.JSONUtil;

public class TestBusinessSync {

    public static void main(String[] args) throws Exception {
        String postUrl = "http://127.0.0.1:9090/comet/mobpay/payBackUrl.do";
        Map<String, String> map = new HashMap<String, String>();
        map.put("sign", "B106A3D84A465DC24C09C5327B89F022");
		map.put("amount", "1");
		map.put("result", "2");
		map.put("ticketId", "F9A7420106989628ACF61E76D2575282");
		map.put("appkey", "23f45043a5216");
		map.put("payAt", "1519782681988");
		map.put("nonce_str", "YWWCsAuWF22VpFAKiNxWYnW6ilebxgA3%2BLhZMr6bVUzUrHSo%2BBYWnVTIvlpGrvk2efgg7%2B6aEMuP%0AIGdMjPHunBxgcHm7GWjfULWpusRT4xUUeARr%2FO46TY%2FbyuXE7u%2B2PYzPbdvYokr2rqA7CdV5J22F%0Afal8paiU0BX%2BaINX5Js%3D");
		map.put("msg", "TRADE_SUCCESS");
		map.put("channel", "22");
		map.put("orderId", "1000076004011519782679834");
        String preStr = JSONUtil.toJsonStr(JSONUtil.parseFromMap(map));
//        String sign = SignUtils.generateSign(map);
//        map.put("sign", sign);
        com.xiaoleilu.hutool.json.JSONObject sendJson = JSONUtil.parseFromMap(map);
        String postData = sendJson.toString();
        URL url = new URL(postUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setUseCaches(false);
        conn.setDoOutput(true);

        conn.setRequestProperty("Content-Length", "" + postData.length());
        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");

        try {
            out.write(postData);
            out.flush();
        } finally {
            out.close();
        }
        // ��ȡ��Ӧ״̬
        System.out.println(conn.getResponseCode());

        // ��ȡ��Ӧ������
        StringBuffer str_buff = new StringBuffer();
        String line = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        try {
            while ((line = in.readLine()) != null) {
                str_buff.append(line + "\n");
            }
        } finally {
            in.close();
            if (null != conn) {
                conn.disconnect();
            }
        }

        String respStr = str_buff.toString();
        System.out.println(respStr);
    }
}

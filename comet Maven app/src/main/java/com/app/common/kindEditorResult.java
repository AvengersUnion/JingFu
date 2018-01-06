package com.app.common;

import java.util.HashMap;
import java.util.Map;

/**
 * KindEdeior的返回对象
 * 
 * @author 567
 * 
 */
public class kindEditorResult {

	public static Map<String, Object> ok(String url) {
		Map<String, Object> okMsg = new HashMap<String, Object>();
		okMsg.put("error", 0);
		okMsg.put("url", url);
		return okMsg;
	}

	public static Map<String, Object> error(String msg) {
		Map<String, Object> errorMsg = new HashMap<String, Object>();
		errorMsg.put("error", 1);
		errorMsg.put("message", msg);
		return errorMsg;
	}
}

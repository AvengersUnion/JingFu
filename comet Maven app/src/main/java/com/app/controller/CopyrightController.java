package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.app.entity.Copyright;
import com.app.service.CopyrightService;




@Controller
@RequestMapping("copyright")
public class CopyrightController {
	
	private Logger logger = LoggerFactory.getLogger(CopyrightController.class);
	@Autowired
	private CopyrightService copyrightService;
	
	/**
	 * 保存版权
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/save", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String save(Copyright copyright,HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jsonobj = new JSONObject();
		if(copyright == null) {
			jsonobj.put("code", "1");
			jsonobj.put("mes", "传入参数为空！");
			return jsonobj.toJSONString();
		}
		logger.info("copyright:"+copyright.toString());
		int num = 0;
		if(copyright.getId()== null || "".equals(copyright.getId().toString())) {
			num = copyrightService.save(copyright);
			
		}else {
			num = copyrightService.update(copyright);
			
		}
		if(num == 1) {
			jsonobj.put("code", "0");
			jsonobj.put("mes", "保存成功！");
		}else {
			jsonobj.put("code", "1");
			jsonobj.put("mes", "保存失败，请稍后再试！");
		}
		
		return jsonobj.toJSONString();
	}
	
	
	/**
	 * 查看版权
	 * @return
	 */
	@RequestMapping(value = "/get", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAll() {
		Copyright copyright = copyrightService.get();
		String jsonStr = JSON.toJSONString(copyright);
		logger.info("jsonStr:"+jsonStr);
		return jsonStr;
	}

}

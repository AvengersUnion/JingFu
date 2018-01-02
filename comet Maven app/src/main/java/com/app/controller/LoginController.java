package com.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.app.entity.User;
import com.app.entity.UserAuths;
import com.app.service.LoginService;
import com.app.service.UserAuthsService;
import com.app.util.SendMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 登陆相关
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource(name = "loginService")
      LoginService loginService;
    @Resource(name = "userAuthsService")
    UserAuthsService userAuthsService;
    
    @RequestMapping("/doLogin")
    @ResponseBody
    public List doLogin(HttpServletRequest request,HttpServletResponse response){
        List<User> list = loginService.getData();

        return list;
    }
    /**
     * 手机短信获取验证码
     * @return
     */
    @RequestMapping(value="/getPhoneCode",produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String phoneLogin(HttpServletRequest request,HttpServletResponse response) {
    	String phone = request.getParameter("phone");
    	JSONObject obj = new JSONObject();
    	if(phone == null  || "".equals(phone)) {
    		obj.put("type", "1");
    		obj.put("mes", "手机号不能为空！");
    		return obj.toJSONString();
    	}
    	String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(phone);
        boolean ismatch = m.matches();
    	if(phone.length() != 11 || !ismatch) {
    		obj.put("type", "1");
    		obj.put("mes", "请输入正确的手机号！");
    		return obj.toJSONString();
    	}
    	if(ismatch) {
    		String code = SendMessage.sendLoginCode(phone);
    		
    		if(code == null) {
    			obj.put("type", "1");
    			obj.put("mes", "服务器错误，请稍后再试！");
    		}
    		UserAuths userAuths = new UserAuths();
    		userAuths.setIdentityType("phone");
    		userAuths.setIdentifier(phone);
    		userAuths.setCredential(code);
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Date date = null;
    		try {
				date = df.parse(df.format(new Date()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    		userAuths.setNettime(date);
    		userAuthsService.saveUserAuthsService(userAuths);
    		obj.put("type", "0");
    		obj.put("mes", "短信已发送，请注意查收！");
    		return obj.toJSONString();
    	}
    	return "";
    }
}

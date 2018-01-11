package com.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.app.entity.BackUser;
import com.app.entity.User;
import com.app.service.LoginService;
import com.app.service.UserAuthsService;
import com.app.service.UserService;
import com.app.util.Application;
import com.app.util.AuthUtil;
import com.app.util.SendMessage;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    @Resource(name = "userService")
    UserService userService;
    
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
    @RequestMapping(value="/getPhoneCode.do",produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String phoneLogin(HttpServletRequest request,HttpServletResponse response) {
    	String phone = request.getParameter("phone");
    	JSONObject obj = new JSONObject();
    	if(phone == null  || "".equals(phone)) {
    		obj.put("type", "1");
    		obj.put("mes", "手机号不能为空！");
    		return obj.toJSONString();
    	}
    	String regExp = "^((13[0-9])|(15[^4])|(18[0,1,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
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
    			obj.put("mes", "系统异常，请稍后再试！");
    			return obj.toJSONString();
    		}
    		BackUser user = new BackUser();
    		user.setUserIphone(phone);
    		user.setPassWord(code);
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Date date = null;
    		try {
				date = df.parse(df.format(new Date()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    		user.setNettime(date);
    		userService.saveUserPhone(user);
    		obj.put("type", "0");
    		obj.put("mes", "短信已发送，请注意查收！");
    		return obj.toJSONString();
    	}
    	return "";
    }
    
    /**
     * 手机短信验证码登陆
     * @return
     */
    @RequestMapping(value="/loginByPhone.do",produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String loginByPhone(HttpServletRequest request,HttpServletResponse response) {
    	String phone = request.getParameter("phone");
    	String code = request.getParameter("code");
    	String openid = request.getParameter("openid");
    	JSONObject obj = new JSONObject();
    	if(phone == null  || "".equals(phone)) {
    		obj.put("type", "1");
    		obj.put("mes", "手机号不能为空！");
    		return obj.toJSONString();
    	}
    	if(code == null  || "".equals(code)) {
    		obj.put("type", "1");
    		obj.put("mes", "验证码不能为空！");
    		return obj.toJSONString();
    	}
    	String regExp = "^((13[0-9])|(15[^4])|(18[0,1,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(phone);
        boolean ismatch = m.matches();
    	if(phone.length() != 11 || !ismatch) {
    		obj.put("type", "1");
    		obj.put("mes", "请输入正确的手机号！");
    		return obj.toJSONString();
    	}
		BackUser user = null;
		user = userService.getUserByPhone(phone);
		if(user == null) {
			obj.put("type", "1");
    		obj.put("mes", "手机号不正确！");
    		return obj.toJSONString();
		}else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Date date = null;
    		try {
				date = df.parse(df.format(new Date()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    		long currDate = date.getTime()-120000;
    		long userDate = user.getNettime().getTime();
    		if(currDate > userDate) {
    			obj.put("type", "1");
        		obj.put("mes", "验证码已过期，请重新获取！");
        		return obj.toJSONString();
    		}
			if(!code.equals(user.getPassWord())) {
				obj.put("type", "1");
        		obj.put("mes", "验证码不正确，请重新输入！");
        		return obj.toJSONString();
			}else {
				if(openid != null && !"".equals(openid)) {
					userAuthsService.updatePhone(phone,openid);
				}
	            HttpSession session = request.getSession();
	            session.setAttribute("user", user);
	            obj.put("type", "0");
        		obj.put("mes", "登陆成功！");
        		obj.put("phone", phone);
        		obj.put("id", user.getId());
        		return obj.toJSONString();
			}
		}
    }
    /**
     * 微信登陆
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value="/loginByWX.do")
    public void loginByWX(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
    	String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Application.wxAPPID
    			+ "&redirect_uri="+URLEncoder.encode(Application.wxBackUrl,"UTF-8")
    			+ "&response_type=code"
    			+ "&scope=snsapi_userinfo"
    			+ "&state=STATE#wechat_redirect";
    	try {
			response.sendRedirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(URLEncoder.encode(Application.wxBackUrl,"UTF-8"));
    }
    /**
     * 微信回调方法
     * @param request
     * @param response
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    @RequestMapping(value="/wxCallBack.do",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String wxCallBack(HttpServletRequest request,HttpServletResponse response) throws ClientProtocolException, IOException {
    	JSONObject result = new JSONObject();
    	String code = request.getParameter("code");
    	String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+Application.wxAPPID
    			+ "&secret="+Application.wxAPPSECRET
    			+ "&code="+code
    			+ "&grant_type=authorization_code";
		JSONObject jsonObj = AuthUtil.doGetJson(url);
		String openid = jsonObj.getString("openid");
		String token = jsonObj.getString("access_token");
    	String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+token
    			+ "&openid="+openid
    			+ "&lang=zh_CN";
    	JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
    	System.out.println(userInfo.toString());
    	//将微信与当前系统手机号进行绑定
    	String phone = userAuthsService.getPhonebyIdentifier(openid);
    	if(phone != null && !phone.equals("")) {
    		//已经绑定的
    		BackUser user = userService.getUserByPhone(phone);
    		HttpSession session = request.getSession();
            session.setAttribute("user", user);
            result.put("type", "0");
    		result.put("mes", "登陆成功！");
    		result.put("phone",phone);
    		result.put("id", user.getId());
    	}else {
    		//未绑定的
    		userAuthsService.saveOpenid("WX",openid);
    		result.put("type", "1");
    		result.put("mes", "未绑定手机号！");
    		result.put("openid",openid);
    	}
    	return result.toString();
    }
    
    /**
     * QQ登陆
     * @return
     * @throws IOException 
     */
    @RequestMapping(value="/loginByQQ.do",produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String loginByQQ(HttpServletRequest request,HttpServletResponse response){
    	JSONObject result = new JSONObject();
    	String openid = request.getParameter("openid");
        if(openid == null || "".equals(openid)) {
        	result.put("type", "1");
        	result.put("mes", "验证码不能为空！");
    		return result.toJSONString();
        }
      //将QQ与当前系统手机号进行绑定
    	String phone = userAuthsService.getPhonebyIdentifier(openid);
    	if(phone != null && !phone.equals("")) {
    		//已经绑定的
    		BackUser user = userService.getUserByPhone(phone);
    		HttpSession session = request.getSession();
            session.setAttribute("user", user);
            result.put("type", "0");
    		result.put("mes", "登陆成功！");
    		result.put("phone",phone);
    		result.put("id", user.getId());
    	}else {
    		//未绑定的
    		userAuthsService.saveOpenid("QQ",openid);
    		result.put("type", "1");
    		result.put("mes", "未绑定手机号！");
    		result.put("openid",openid);
    	}
    	return result.toString();
    }

    
}

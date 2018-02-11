package com.app.interceptor;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.app.entity.BackUser;
import com.app.entity.Manager;
import com.app.service.UserService;

public class LoginInterceptor implements HandlerInterceptor{
	
	@Resource(name = "userService")
    UserService userService;
	 /** 
     * Handler执行完成之后调用这个方法 
     */  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception exc)  
            throws Exception {  
          
    }  
  
    /** 
     * Handler执行之后，ModelAndView返回之前调用这个方法 
     */  
    public void postHandle(HttpServletRequest request, HttpServletResponse response,  
            Object handler, ModelAndView modelAndView) throws Exception {  
    }  
  
    /** 
     * Handler执行之前调用这个方法 
     */  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  
            Object handler) throws Exception {  
        //获取请求的URL  
    	
        String url = request.getRequestURI();  
        if(url.indexOf(".do")>=0){  
            return true;  
        }  
        if(url.indexOf(".html")>=0) {
        	return true;
        }
        if(url.indexOf(".action")>=0) {
        	String token = request.getHeader("token");
    		System.out.println("前置通知  token:"+token);
    		if(token == null || token == "") {
    			response.setContentType("application/json");
    	        JSONObject obj = new JSONObject();
    	        obj.put("type", "1");
    	        obj.put("mes", "未登录！");
    	        response.getWriter().print(obj.toJSONString());
    	        response.getWriter().flush();
    	        response.getWriter().close();
    			System.out.println("验证不通过！");
    			return false;
    		}else {
	    		BackUser user=userService.getUserByToken(token);
	    		System.out.println(user);
	    		if(user==null){
	    			response.setContentType("application/json");
	    	        JSONObject obj = new JSONObject();
	    	        obj.put("type", "1");
	    	        obj.put("mes", "登陆超时，请重新登录！");
	    	        response.getWriter().print(obj.toJSONString());
	    	        response.getWriter().flush();
	    	        response.getWriter().close();
	    			System.out.println("验证不通过！");
	    			return false;
	    		}else {
	    			return true;
	    		}
    		}
        }
        //获取Session  
        HttpSession session = request.getSession();  
        BackUser user = null;
        user = (BackUser)session.getAttribute("user");  
        Manager manager = null;
        manager = (Manager) session.getAttribute("manager");
        if(user != null || manager != null){  
            return true;  
        }  
//        response.setContentType("application/json");
//        JSONObject obj = new JSONObject();
//        obj.put("type", "1");
//        obj.put("mes", "未登录");
//        response.getWriter().print(obj.toJSONString());
//        response.getWriter().flush();
//        response.getWriter().close();
//        request.getRequestDispatcher("login.html").forward(request,response);
        response.sendRedirect("/comet/login.html");
//        response.sendRedirect("http://www.yehaikeji.com:8080/comet/login.html");
        return false;  

    }

}

package com.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.app.entity.BackUser;
import com.app.entity.Manager;

public class LoginInterceptor implements HandlerInterceptor{
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
        //获取Session  
        HttpSession session = request.getSession();  
        BackUser user = null;
        user = (BackUser)session.getAttribute("user");  
        if(user != null){  
            return true;  
        }  
        response.setContentType("application/json");
        JSONObject obj = new JSONObject();
        obj.put("type", "1");
        obj.put("mes", "未登录");
        response.getWriter().print(obj.toJSONString());
        response.getWriter().flush();
        response.getWriter().close();
        return false;  

    }

}

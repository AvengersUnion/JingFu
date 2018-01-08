package com.app.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.app.entity.Manager;
import com.app.service.ManagerService;

@Controller
@RequestMapping("/manager/")
public class ManagerController {
	@Resource(name = "managerService")
	private ManagerService managerService;

	/**
	 * 根据用户的用户名和密码登录
	 * 
	 * @param loginJson
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public Manager managerLogin(String uname, String passWord,HttpServletRequest request) {
		Manager managerboss = new Manager();
		managerboss.setUname(uname);
		managerboss.setPassWord(passWord);
		if (null == managerService.login(managerboss)) {
			return null;
		}
		
		Manager manager=managerService.login(managerboss);
		
		HttpSession session = request.getSession();
        session.setAttribute("manager", manager);
		return manager ;
	}

	/**
	 * 根据用户的用户名去修改密码
	 * 
	 * @param updateJson
	 * @return
	 */
	@RequestMapping("updateManager")
	@ResponseBody
	public String updateManager(String uname, String passWord) {
		Manager manager = new Manager();
		manager.setUname(uname);
		manager.setPassWord(passWord);
		if (passWord == null) {
			return "0";
		} else {
			managerService.updateManager(manager);
			return "1";
		}
	}

	/**
	 * 用户退出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("exitManager")
	@ResponseBody
	public String exitManager(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			response.sendRedirect(request.getContextPath() + "/login.html");
			return "1";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}
}

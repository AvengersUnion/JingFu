package com.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping("login.do")
	@ResponseBody
	public Map<String, String> managerLogin(String uname, String passWord,HttpServletRequest request) {
		Manager managerboss = new Manager();
		managerboss.setUname(uname);
		managerboss.setPassWord(passWord);
		Map<String, String> manMap=new HashMap<String, String>();
		if (null == managerService.login(managerboss)) {
			manMap.put("code", "0");
			manMap.put("message", "登录失败");
			return manMap;
		}
		
		Manager manager=managerService.login(managerboss);
		manMap.put("code", "1");
		manMap.put("id", manager.getId());
		HttpSession session = request.getSession();
        session.setAttribute("manager", manager);
		return manMap ;
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
			System.out.println(1);
			request.getSession().removeAttribute("manager");
			//request.session.removeAttribute("uiUsers")；
			//response.sendRedirect("http://www.yehaikeji.com:8080/comet/login.html");
			//System.out.println(request.getContextPath()+"/login.html");
			return "1";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}
	/**
	 * 根据mananger的id查询
	 * @param id
	 * @return
	 */
	@RequestMapping("find")
	@ResponseBody
	public Manager getManagerById(String id) {
		return managerService.getManagerById(id);
	}
}

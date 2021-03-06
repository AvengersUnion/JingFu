package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.app.entity.Address;
import com.app.entity.BackUser;
import com.app.service.UserService;
import com.app.util.PagingUtils;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Resource(name="userService")
	private UserService userService;
	
	
	
	/**
	 * 修改昵称
	 * @param request
	 * @param response
	 * @return
	 */
	 @RequestMapping(value="/updateNickName.action",produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	 @ResponseBody
	 public String updateNickName(HttpServletRequest request,HttpServletResponse response) {
		 JSONObject obj = new JSONObject();
		 String userId = request.getParameter("userId");
		 String nickName = request.getParameter("userName");
		 if(userId == null || "".equals(userId)) {
			 obj.put("code", "1");
			 obj.put("msg", "请输入用户id！");
			 return obj.toJSONString();
		 }
		 if(nickName == null || "".equals(nickName)) {
			 obj.put("code", "2");
			 obj.put("msg", "请输入用户昵称！");
			 return obj.toJSONString();
		 }
		 int num = userService.updateNickNameById(userId,nickName);
		 if(num==0) {
			 obj.put("code", "3");
			 obj.put("msg", "没有此用户！");
			 return obj.toJSONString(); 
		 }
		 if(num == 1) {
			 obj.put("code", "0");
			 obj.put("msg", "修改成功！");
			 obj.put("userId", userId);
			 obj.put("userName", nickName);
			 return obj.toJSONString(); 
		 }
		 return null;
	 }
	 /**
		 * 获取用户信息
		 * @param request
		 * @param response
		 * @return
		 */
		 @RequestMapping(value="/getUserById.action",produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
		 @ResponseBody
		 public String getUserById(HttpServletRequest request,HttpServletResponse response) {
			 JSONObject obj = new JSONObject();
			 String userId = request.getParameter("userId");
			 if(userId == null || "".equals(userId)) {
				 obj.put("code", "1");
				 obj.put("msg", "请输入用户id！");
				 return obj.toJSONString();
			 }
			 BackUser user =null;
			user = userService.getUserById(Integer.parseInt(userId));
			 if(user==null) {
				 obj.put("code", "2");
				 obj.put("msg", "没有此用户！");
				 return obj.toJSONString(); 
			 }else {
				 obj.put("code", "0");
				 obj.put("msg", "查询成功！");
				 obj.put("userId", String.valueOf(user.getId()));
				 obj.put("userName", user.getuName());
				 obj.put("phone", user.getUserIphone());
				 obj.put("portrait", user.getPortrait());
				 return obj.toJSONString(); 
			 }
		 }
	 
	/**
	 * 返回所有的用户
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping("all")
    @ResponseBody
	public List<BackUser> getUserList(Integer pageNumber) {
		//获取所有用户的信息
		List<BackUser> userList=userService.getUserList();
		//获取所有用户对应的用户和地址信息
		List<BackUser> users=new ArrayList<BackUser>();
		//分页后所得的用户信息
		List<BackUser> getUsers=new ArrayList<BackUser>();
		//int userNumber=userService.userNumber(null);
		for (int i = 0; i < userList.size(); i++) {
			BackUser user=new BackUser();
			user.setId(userList.get(i).getId());
			user.setuName(userList.get(i).getuName());
			user.setUserIphone(userList.get(i).getUserIphone());
			user.setPassWord(userList.get(i).getPassWord());
			//根据用户的id去查询该用户的所有地址信息，并存储到用户
			user.setBackUserList(userService.getUserAddress(userList.get(i).getId()));
			/*
			user.setCountry(userList.get(i).getCountry());
			user.setProvince(userList.get(i).getProvince());
			user.setCounty(userList.get(i).getCounty());
			user.setCity(userList.get(i).getCity());
			user.setVillage(userList.get(i).getVillage());
			*/
			//user.setAddress(userList.get(i).getAddress());
			//System.out.println(user.getId()+" "+ i);
			users.add(user);
			System.out.println(users.get(i).getId());
		}
		//return users;
		//避免空指针
		if (null==pageNumber) {
			pageNumber=0;
		}
		BackUser backUser=new BackUser();
		PagingUtils<BackUser> pagingUtils=new PagingUtils<BackUser>(backUser); 
		getUsers=pagingUtils.pageingDate(pageNumber, users);
		/*if (0==pageNumber) {
			if (users.size()<10) {
				for (int i = 0; i < users.size(); i++) {
					getUsers.add(users.get(i));
				}
				//return getUsers;
			}else {
				for (int i = 0; i < 10; i++) {
					getUsers.add(users.get(i));
				}
			}
			return getUsers;
		}else if (userNumber>(pageNumber-1)*10) {
			if (userNumber>pageNumber*10) {
				for (int i = (pageNumber-1)*10; i < pageNumber*10; i++) {
					getUsers.add(users.get(i));
				}
			}else {
				for (int i = (pageNumber-1)*10; i < users.size(); i++) {
					getUsers.add(users.get(i));
				}
			}
			return getUsers;
		}
		*/
		return getUsers;
	}
	/**
	 * 返回用户的数量
	 * @return
	 */
	@RequestMapping("count")
    @ResponseBody
	public int getUserNumber(String city) {
		BackUser user=new BackUser();
		user.setCity(city);
		return userService.userNumber(user);
	}
	/**
	 * 返回城市的名字
	 * @return
	 */
	@RequestMapping("cityName")
    @ResponseBody
	public List getRegionList() {
		return userService.getRegionList();
	}
	
	/**
	 * 根据城市的名字查询用户
	 * @param city
	 * @return
	 */
	@RequestMapping("cityUser")
    @ResponseBody
	public List<BackUser> getUserListByCity(String city,Integer pageNumber) {
		BackUser user=new BackUser();
		user.setCity(city);
		List<BackUser> users=new ArrayList<BackUser>();
		List<BackUser> userList=null;
		List<BackUser> getUsers=new ArrayList<BackUser>();
		//int userNumber=userService.userNumber(user);
		if (null==userService.getUserListByCity(user)) {
			return null;
		}
		if (null==pageNumber) {
			pageNumber=0;
		}
		userList=userService.getUserListByCity(user);
		for (int i = 0; i <userList.size(); i++) {
			BackUser userCity=new BackUser();
			userCity.setId(userList.get(i).getId());
			userCity.setuName(userList.get(i).getuName());
			userCity.setUserIphone(userList.get(i).getUserIphone());
			userCity.setPassWord(userList.get(i).getPassWord());
			userCity.setBackUserList(userService.getUserAddress(userList.get(i).getId()));
			/*
			userCity.setCountry(userList.get(i).getCountry());
			userCity.setProvince(userList.get(i).getProvince());
			userCity.setCounty(userList.get(i).getCounty());
			userCity.setCity(userList.get(i).getCity());
			userCity.setVillage(userList.get(i).getVillage());
			*/
			//userCity.setAddress(userList.get(i).getAddress());
			users.add(userCity);
		}
		if (null==pageNumber){
			pageNumber=0;
		}
		BackUser backUser=new BackUser();
		PagingUtils<BackUser> pagingUtils=new PagingUtils<BackUser>(backUser); 
		getUsers=pagingUtils.pageingDate(pageNumber, users);
		/*
		if (0==pageNumber) {
			if (users.size()<10) {
				for (int i = 0; i < users.size(); i++) {
					getUsers.add(users.get(i));
				}
				//return getUsers;
			}else {
				for (int i = 0; i < 10; i++) {
					getUsers.add(users.get(i));
				}
			}
			return getUsers;
		}else if (userNumber>(pageNumber-1)*10) {
			if (userNumber>pageNumber*10) {
				for (int i = (pageNumber-1)*10; i < pageNumber*10; i++) {
					getUsers.add(users.get(i));
				}
			}else {
				for (int i = (pageNumber-1)*10; i < users.size(); i++) {
					getUsers.add(users.get(i));
				}
			}
			return getUsers;
		}
		*/
		return getUsers;
		//return users;
	}
	/**
	 * 根据用户的id来查询用户
	 * @param id
	 * @return
	 */
	@RequestMapping("find")
    @ResponseBody
	public BackUser getUserById(Integer id) {
		
		return userService.getUserById(id);
	}
	
	/**
	 * 删除用户，先查询地址，在删除用户的地址
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
    @ResponseBody
	public int deleteUser(Integer id) {
		if (null==id) {
			id=0;
		}
		if (null==userService.getUserById(id)) {
			//List<String> strList=new ArrayList<String>();
			return 0;
			
		}else {
			userService.deleteUser(id);
			return 1;
		}
	}
	/**
	 * 删除多个用户，先查询地址，在删除用户的地址
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteUsers")
    @ResponseBody
	public String deleteUsers(String ids) {
		String[] userIds=null;
		if(null==ids||ids==""){
			return "0";
		}
		if (!ids.contains(",")) {
			Integer id=Integer.parseInt(ids);
			if(null==id){
				id=0;
			}
			if (null==userService.getUserById(id)) {
				return "0";
			}
			userService.deleteUser(id);
		}else {
			userIds=ids.split(",");
			
			for (int i = 0; i < userIds.length; i++) {
				int id=Integer.parseInt(userIds[i]);
				if (null==userService.getUserById(id)) {
					return "0";
				}
			}
			for (int i = 0; i < userIds.length; i++) {
				int id=Integer.parseInt(userIds[i]);
				userService.deleteUser(id);
			}
		}
		return "1";
	}
}

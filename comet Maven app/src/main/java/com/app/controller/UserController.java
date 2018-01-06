package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.entity.Address;
import com.app.entity.BackUser;
import com.app.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Resource(name="userService")
	private UserService userService;
	/**
	 * 返回所有的用户
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping("all")
    @ResponseBody
	public List getUserList(int pageNumber) {
		List<BackUser> userList=userService.getUserList();
		List<BackUser> users=new ArrayList<BackUser>();
		List<BackUser> getUsers=new ArrayList<BackUser>();
		int userNumber=userService.userNumber(null);
		for (int i = 0; i < userList.size(); i++) {
			BackUser user=new BackUser();
			user.setId(userList.get(i).getId());
			user.setuName(userList.get(i).getuName());
			user.setUserIphone(userList.get(i).getUserIphone());
			user.setPassWord(userList.get(i).getPassWord());
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
		return null;
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
	public List getUserListByCity(String city,Integer pageNumber) {
		BackUser user=new BackUser();
		user.setCity(city);
		List<BackUser> users=new ArrayList<BackUser>();
		List<BackUser> userList=null;
		List<BackUser> getUsers=new ArrayList<BackUser>();
		int userNumber=userService.userNumber(user);
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
		return null;
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
	 * 添加用户
	 * @param uName
	 * @param userIphone
	 * @param province
	 * @param city
	 * @param county
	 * @param address
	 * @param passWord
	 * @return
	 */
	@RequestMapping(value="add",produces="text/html;charset=UTF-8",method=RequestMethod.POST)
    @ResponseBody
	public String addUser(String uName,String userIphone,String province,
			String city,String county,String address,String passWord) {
		
		UUID uuid=UUID.randomUUID();
		if (null==uName||null==userIphone||null==passWord) {
			return "0";
		}else if (""==uName||""==userIphone||""==passWord) {
			return "0";
		}else {
			if (null!=userService.getUserById(uuid.toString())) {
				uuid=UUID.randomUUID();
			}
			BackUser user=new BackUser();
			user.setId(uuid.toString());
			user.setuName(uName);
			user.setUserIphone(userIphone);
			user.setPassWord(passWord);
			
			user.setProvince(province);
			user.setCity(city);
			user.setCounty(county);
			userService.addUser(user);
			return "1";
		}
	}
	/**
	 * 修改用户
	 * @param id
	 * @param uName
	 * @param userIphone
	 * @param province
	 * @param city
	 * @param county
	 * @param address
	 * @param passWord
	 * @return
	 */
	@RequestMapping(value="update",produces="text/html;charset=UTF-8",method=RequestMethod.POST)
    @ResponseBody
	public String updateUser(String id,String uName,String userIphone,String province,
			String city,String county,String passWord) {
		if (null==id||id=="") {
			return "0";
		}else {
			BackUser user=new BackUser();
			user.setId(id);
			user.setuName(uName);
			user.setUserIphone(userIphone);
			user.setPassWord(passWord);
			user.setProvince(province);
			user.setCity(city);
			user.setCounty(county);
			userService.updateUser(user);
			return "1";
		}
		
	}
	/**
	 * 删除用户，先查询地址，在删除用户的地址
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
    @ResponseBody
	public int deleteUser(String id) {
		
		if (null==userService.getUserById(id)) {
			List<String> strList=new ArrayList<String>();
			
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
			if (null==userService.getUserById(ids)) {
				return "0";
			}
			userService.getUserById(ids);
		}else {
			userIds=ids.split(",");
			for (int i = 0; i < userIds.length; i++) {
				if (null==userService.getUserById(userIds[i])) {
					return "0";
				}
			}
			for (int i = 0; i < userIds.length; i++) {
				userService.getUserById(userIds[i]);
			}
		}
		return "1";
	}
}

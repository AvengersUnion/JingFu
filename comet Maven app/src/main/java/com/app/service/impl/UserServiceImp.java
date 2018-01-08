package com.app.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.UserDao;
import com.app.entity.Region;
import com.app.entity.BackUser;
import com.app.service.UserService;

@Service("userService")
public class UserServiceImp implements UserService{
	
	@Resource
	private UserDao userDao;
	public int userNumber(BackUser user) {
		// TODO Auto-generated method stub
		return userDao.userNumber(user);
	}

	public List<BackUser> getUserList() {
		// TODO Auto-generated method stub
		return userDao.getUserList();
	}

	public List<String> getRegionList() {
		// TODO Auto-generated method stub
		return userDao.getRegionList();
	}

	public List<BackUser> getUserListByCity(BackUser user) {
		// TODO Auto-generated method stub
		return userDao.getUserListByCity(user);
	}

	public BackUser getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}

	public void addUser(BackUser user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	public void updateUser(BackUser user) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);
	}

	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userDao.deleteUser(id);
	}

	public List<BackUser> getUserAddress(int userId) {
		// TODO Auto-generated method stub
		return userDao.getUserAddress(userId);
	}
	/**
	 * 手机登陆时保存用户
	 */
	public void saveUserPhone(BackUser user) {
		String userId = userDao.getUserIdByPhone(user.getUserIphone());
		if(userId != null && !"".equals(userId)) {
			//不是新用户
			userDao.updateCodeByPhone(user);
			return;
		}
		//新用户
		//创建系统账户
		/*
		UUID uuid=UUID.randomUUID();
		if (null!=userDao.getUserById(uuid.toString())) {
			uuid=UUID.randomUUID();
		}
		*/
		String chars = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
		String str = "";
		for(int i = 0;i<10;i++) {
			str = str + chars.charAt((int)(Math.random() * 52));
		}
		//user.setId(uuid.toString());
		user.setuName(str);
		userDao.addUser(user);
	}
	/**
	 * 根据手机号查找用户
	 */
	public BackUser getUserByPhone(String phone) {
		
		return userDao.getUserByPhone(phone);
	}

}

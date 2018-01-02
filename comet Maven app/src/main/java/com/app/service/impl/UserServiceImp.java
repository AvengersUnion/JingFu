package com.app.service.impl;

import java.util.List;

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

	public BackUser getUserById(String id) {
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

	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		userDao.deleteUser(id);
	}

	public List<BackUser> getUserAddress(String userId) {
		// TODO Auto-generated method stub
		return userDao.getUserAddress(userId);
	}

}

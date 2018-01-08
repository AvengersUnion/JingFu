package com.app.service;

import java.util.List;

import com.app.entity.Region;
import com.app.entity.BackUser;

public interface UserService {
	int userNumber(BackUser user);
	List<BackUser> getUserList();
	
	List<String> getRegionList();
	List<BackUser> getUserListByCity(BackUser user);
	
	BackUser getUserById(int id);
	void addUser(BackUser user);
	void updateUser(BackUser user);
	void deleteUser(int id);
	List<BackUser> getUserAddress(int userId);
	/**
	 * 手机登陆时保存用户
	 * @param user
	 */
	void saveUserPhone(BackUser user);
	/**
	 * 根据手机号查找用户
	 * @param phone
	 * @return
	 */
	BackUser getUserByPhone(String phone);
}

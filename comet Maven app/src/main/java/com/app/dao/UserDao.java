package com.app.dao;

import java.util.List;

import com.app.entity.Region;
import com.app.entity.BackUser;

public interface UserDao {
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
	 * 根据手机号查找userid
	 * @param userIphone
	 * @return
	 */
	String getUserIdByPhone(String userIphone);
	/**
	 * 更新手机验证码
	 * @param user
	 */
	void updateCodeByPhone(BackUser user);
	/**
	 * 根据手机号查找用户信息
	 * @param phone
	 * @return
	 */
	BackUser getUserByPhone(String phone);
}

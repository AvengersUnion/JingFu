package com.app.service;

import java.util.List;

import com.app.entity.Region;
import com.app.entity.BackUser;

public interface UserService {
	int userNumber(BackUser user);
	List<BackUser> getUserList();
	
	List<String> getRegionList();
	List<BackUser> getUserListByCity(BackUser user);
	
	BackUser getUserById(Integer id);
	void addUser(BackUser user);
	void updateUser(BackUser user);
	void deleteUser(Integer id);
	List<BackUser> getUserAddress(Integer userId);
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
	/**
	 * 用户登陆成功后更新token
	 * @param token
	 * @param phone
	 */
	void updateToken(String token, String phone);
	/**
	 * 根据token查找用户
	 * @param token
	 * @return
	 */
	BackUser getUserByToken(String token);
	/**
	 * 修改昵称
	 * @param userId
	 * @param nickName
	 * @return
	 */
	int updateNickNameById(String userId, String nickName);
}

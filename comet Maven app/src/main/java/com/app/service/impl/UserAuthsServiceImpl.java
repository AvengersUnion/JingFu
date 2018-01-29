package com.app.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UserAuthsDao;
import com.app.dao.UserDao;
import com.app.entity.UserAuths;
import com.app.service.UserAuthsService;
@Service("userAuthsService")
public class UserAuthsServiceImpl implements UserAuthsService{
	
	@Autowired
	UserAuthsDao userAuthsDao;
	
	@Autowired
	UserDao userDao;
	/**
	 * 保存第三方登陆信息
	 */
	public void saveUserAuthsService(UserAuths userAuths) {
		
	}
	/**
	 * 通过第三方登陆名获取绑定手机号
	 */
	public UserAuths getPhonebyIdentifier(String openid) {
		
		return userAuthsDao.getPhoneByIdentifier(openid);
	}
	/**
	 * 根据手机号获取userAuths对象
	 */
	public UserAuths getUserAuthsByphone(String phone) {
		return userAuthsDao.getUserAuthsByphone(phone);
	}
	/**
	 * 保存微信的openid
	 */
	public void saveOpenid(String type,String openid) {
		 userAuthsDao.saveOpenid(type,openid);
		
	}
	/**
	 * 更新手机号
	 */
	public void updatePhone(String phone, String openid) {
		userAuthsDao.updatePhone(phone,openid);
	}

}

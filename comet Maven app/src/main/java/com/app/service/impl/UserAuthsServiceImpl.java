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

}

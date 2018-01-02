package com.app.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UserAuthsDao;
import com.app.dao.UserDao;
import com.app.entity.BackUser;
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
		String userId = userAuthsDao.getUserIdByPhone(userAuths.getIdentifier());
		if(userId != null && !"".equals(userId)) {
			//不是新用户
			userAuthsDao.updateInfoByIdentifier(userAuths);
		}
		//新用户
		//创建系统账户
		UUID uuid=UUID.randomUUID();
		if (null!=userDao.getUserById(uuid.toString())) {
			uuid=UUID.randomUUID();
		}
		String chars = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
		String str = "";
		for(int i = 0;i<10;i++) {
			str = str + chars.charAt((int)(Math.random() * 52));
		}
		BackUser user=new BackUser();
		user.setId(uuid.toString());
		user.setuName(str);
		user.setUserIphone(userAuths.getIdentifier());
		userDao.addUser(user);
		//保存第三方登陆信息
		userAuths.setUserId(uuid.toString());
		userAuthsDao.saveUserAuths(userAuths);
	}

}

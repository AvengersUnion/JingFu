package com.app.dao;

import com.app.entity.UserAuths;

public interface UserAuthsDao {
	/**
	 * 保存第三方登陆信息
	 * @param userAuths
	 */
	void saveUserAuths(UserAuths userAuths);
	/**
	 * 根据登陆手机号查找userId
	 * @param identifier
	 * @return
	 */
	String getUserIdByPhone(String identifier);
	/**
	 * 更新第三方登陆信息凭证
	 * @param userAuths
	 */
	void updateInfoByIdentifier(UserAuths userAuths);
}

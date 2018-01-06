package com.app.dao;

import com.app.entity.UserAuths;

public interface UserAuthsDao {
	/**
	 * 保存第三方登陆信息
	 * @param userAuths
	 */
	void saveUserAuths(UserAuths userAuths);
	/**
	 * 更新第三方登陆信息凭证
	 * @param userAuths
	 */
	void updateInfoByIdentifier(UserAuths userAuths);
	/**
	 * 根据第三方登录名查询绑定手机号
	 * @param openid
	 * @return
	 */
	String getPhoneByIdentifier(String openid);
	/**
	 * 根据手机号获取userAuths
	 * @param phone
	 * @return
	 */
	UserAuths getUserAuthsByphone(String phone);
	/**
	 * 保存登录凭证
	 * @param openid
	 * @return
	 */
	void saveOpenid(String type,String openid);
	/**
	 * 更新手机号
	 * @param phone
	 * @param openid
	 */
	void updatePhone(String phone, String openid);
}

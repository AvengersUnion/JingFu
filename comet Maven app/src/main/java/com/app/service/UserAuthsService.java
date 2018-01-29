package com.app.service;

import com.app.entity.UserAuths;

/**
 * 第三方登陆信息
 * @author taoxiangfei
 *
 */
public interface UserAuthsService {
	public void saveUserAuthsService(UserAuths userAuths);
	/**
	 * 通过第三方登陆名获取当前账户
	 * @param openid
	 * @return
	 */
	public UserAuths getPhonebyIdentifier(String openid);
	/**
	 * 根据手机号获取userAuths对象
	 * @param phone
	 * @return
	 */
	public UserAuths getUserAuthsByphone(String phone);
	
	/**
	 * 保存微信的openid
	 * @param openid
	 */
	public void saveOpenid(String type,String openid);
	/**
	 * 更新手机号
	 * @param phone
	 * @param openid
	 */
	public void updatePhone(String phone, String openid);
}

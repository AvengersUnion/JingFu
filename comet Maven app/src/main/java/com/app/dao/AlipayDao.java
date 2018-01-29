package com.app.dao;

import com.app.entity.Alipay;

public interface AlipayDao {
	/**
	 * 创建订单
	 * @param alipay
	 */
	void createOrder(Alipay alipay);
	/**
	 * 更新创建订单通知结果
	 * @param alipay
	 */
	void updateNotify(Alipay alipay);

}

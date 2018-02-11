package com.app.dao;

import com.app.entity.Wxpay;

public interface WxpayDao {
	/**
	 * 创建订单
	 * @param alipay
	 */
	void createOrder(Wxpay wxpay);
	/**
	 * 更新创建订单通知结果
	 * @param alipay
	 */
	void updateNotify(Wxpay wxpay);

}

package com.app.service;

import com.app.entity.Wxpay;

public interface WxpayService {
	/**
	 * 创建订单
	 */
	void createOrder(Wxpay wxpay);
	/**
	 * 更新订单通知状态
	 */
	void updateNotify(Wxpay wxpay);

}

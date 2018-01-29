package com.app.service;

import com.app.entity.Alipay;

public interface AlipayService {
	/**
	 * 创建订单
	 * @param alipay
	 */
	void createOrder(Alipay alipay);
	/**
	 * 更新订单通知状态
	 * @param alipay
	 */
	void updateNotify(Alipay alipay);

}

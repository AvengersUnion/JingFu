package com.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.AlipayDao;
import com.app.entity.Alipay;
import com.app.service.AlipayService;
@Service("alipayService")
public class AlipayServiceImpl implements AlipayService {
	@Resource
	private AlipayDao alipayDao;
	/**
	 * 创建订单
	 */
	public void createOrder(Alipay alipay) {
		
		alipayDao.createOrder(alipay);
	}
	/**
	 * 更新创建订单通知结果
	 */
	public void updateNotify(Alipay alipay) {
		alipayDao.updateNotify(alipay);
	}

}

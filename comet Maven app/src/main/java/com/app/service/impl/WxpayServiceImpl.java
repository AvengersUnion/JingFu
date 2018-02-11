package com.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.WxpayDao;
import com.app.entity.Wxpay;
import com.app.service.WxpayService;
@Service("wxpayService")
public class WxpayServiceImpl implements WxpayService {
	@Resource
	private WxpayDao wxpayDao;
	/**
	 * 创建订单
	 */
	public void createOrder(Wxpay wxpay) {
		
		wxpayDao.createOrder(wxpay);
	}
	/**
	 * 更新创建订单通知结果
	 */
	public void updateNotify(Wxpay wxpay) {
		wxpayDao.updateNotify(wxpay);
	}

}

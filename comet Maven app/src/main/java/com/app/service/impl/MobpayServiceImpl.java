package com.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.MobpayDao;
import com.app.entity.Mobpay;
import com.app.service.MobpayService;

@Service("mobpayService")
public class MobpayServiceImpl implements MobpayService{

	@Autowired
	MobpayDao mobpayDao;

	@Override
	public void save(Mobpay mobpay) {
		mobpayDao.save(mobpay);
	}

    

}

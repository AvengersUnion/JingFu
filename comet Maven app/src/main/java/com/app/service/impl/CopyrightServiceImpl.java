package com.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.CopyrightDao;
import com.app.entity.Copyright;
import com.app.service.CopyrightService;

@Service("copyrightService")
public class CopyrightServiceImpl implements CopyrightService {
	
	@Resource
	private CopyrightDao copyrightDao;
	@Override
	public Copyright get() {
		// TODO Auto-generated method stub
		return copyrightDao.get();
	}

	@Override
	public int save(Copyright copyright) {
		// TODO Auto-generated method stub
		return copyrightDao.save(copyright);
	}

	@Override
	public int update(Copyright copyright) {
		// TODO Auto-generated method stub
		return copyrightDao.update(copyright);
	}

}

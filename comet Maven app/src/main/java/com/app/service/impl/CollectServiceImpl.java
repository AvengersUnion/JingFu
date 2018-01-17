package com.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.CollectDao;
import com.app.entity.Collect;
import com.app.service.CollectService;

@Service("collectService")
public class CollectServiceImpl implements CollectService {

	@Resource
	private CollectDao collectDao;
	public void addCollect(Collect collect) {
		// TODO Auto-generated method stub
		collectDao.addCollect(collect);
	}

	public void deleteCollect(int id) {
		// TODO Auto-generated method stub
		collectDao.deleteCollect(id);
	}

	public List<Collect> getCollectByUserId(int userId) {
		// TODO Auto-generated method stub
		
		return collectDao.getCollectByUserId(userId);
	}

	public Integer getCollectId(Collect collect) {
		// TODO Auto-generated method stub
		return collectDao.getCollectId(collect);
	}

}

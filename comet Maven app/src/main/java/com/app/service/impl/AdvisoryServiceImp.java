package com.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.AdvisoryDao;
import com.app.entity.Advisory;
import com.app.service.AdvisoryService;
@Service("advisoryService")
public class AdvisoryServiceImp implements AdvisoryService {

	@Resource
	private AdvisoryDao advisoryDao;
	public List<Advisory> getAdvisorieList() {
		// TODO Auto-generated method stub
		return advisoryDao.getAdvisorieList();
	}

	public Advisory getAdvisoryById(int id) {
		// TODO Auto-generated method stub
		return advisoryDao.getAdvisoryById(id);
	}

	public void addAdvisory(Advisory advisory) {
		// TODO Auto-generated method stub
		advisoryDao.addAdvisory(advisory);
	}

	public void deleteAdvisoryById(int id) {
		// TODO Auto-generated method stub
		advisoryDao.deleteAdvisoryById(id);
	}

	public void addAdvisoryImage(String imagePath) {
		// TODO Auto-generated method stub
		advisoryDao.addAdvisoryImage(imagePath);
	}

}

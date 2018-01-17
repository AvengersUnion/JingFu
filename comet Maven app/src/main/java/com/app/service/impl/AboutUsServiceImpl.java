package com.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.AboutUsDao;
import com.app.entity.AboutUs;
import com.app.service.AboutUsService;

@Service("aboutUsService")
public class AboutUsServiceImpl implements AboutUsService{

	@Resource
	private AboutUsDao aboutUsDao;
	public List<AboutUs> getAboutUsList() {
		// TODO Auto-generated method stub
		return aboutUsDao.getAboutUsList();
	}

	public AboutUs getAboutUsById(int id) {
		// TODO Auto-generated method stub
		return aboutUsDao.getAboutUsById(id);
	}

	public void addAboutUs(AboutUs aboutUs) {
		// TODO Auto-generated method stub
		aboutUsDao.addAboutUs(aboutUs);
	}

	public void updateAboutUs(AboutUs aboutUs) {
		// TODO Auto-generated method stub
		aboutUsDao.updateAboutUs(aboutUs);
	}

	public void deleteAboutUsById(int id) {
		// TODO Auto-generated method stub
		aboutUsDao.deleteAboutUsById(id);
	}

}

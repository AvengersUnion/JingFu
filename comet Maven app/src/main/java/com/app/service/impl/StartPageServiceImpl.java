package com.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.StartPageDao;
import com.app.entity.StartPage;
import com.app.service.StartPageService;
@Service("startPageService")
public class StartPageServiceImpl implements StartPageService {

	@Resource
	private StartPageDao startPageDao;
	public List<StartPage> getStartPageList() {
		// TODO Auto-generated method stub
		return startPageDao.getStartPageList();
	}

	public StartPage getStartPageById(Integer id) {
		// TODO Auto-generated method stub
		return startPageDao.getStartPageById(id);
	}

	public List<StartPage> getStartPageByState() {
		// TODO Auto-generated method stub
		return startPageDao.getStartPageByState();
	}

	public void addStartPage(StartPage startPage) {
		// TODO Auto-generated method stub
		startPageDao.addStartPage(startPage);
	}

	public void updateStartPage(StartPage startPage) {
		// TODO Auto-generated method stub
		startPageDao.updateStartPage(startPage);
	}

	public void updateStartPageState(StartPage startPage) {
		// TODO Auto-generated method stub
		startPageDao.updateStartPageState(startPage);
	}

	public void deleteStartPageById(Integer id) {
		// TODO Auto-generated method stub
		startPageDao.deleteStartPageById(id);
	}

}

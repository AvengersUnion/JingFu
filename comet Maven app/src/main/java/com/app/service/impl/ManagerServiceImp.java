package com.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.ManagerDao;
import com.app.entity.Manager;
import com.app.service.ManagerService;

@Service("managerService")
public class ManagerServiceImp implements ManagerService{

	@Resource
	private ManagerDao managerDao;
	public Manager login(Manager manager) {
		// TODO Auto-generated method stub
		return managerDao.login(manager);
	}
	public void updateManager(Manager manager) {
		// TODO Auto-generated method stub
		managerDao.updateManager(manager);
	}
	public List<String> getManagerId(String regionId) {
		// TODO Auto-generated method stub
		return managerDao.getManagerId(regionId);
	}
	public void deleteManagerById(String id) {
		// TODO Auto-generated method stub
		managerDao.deleteManagerById(id);
	}
	public void addManager(Manager manager) {
		// TODO Auto-generated method stub
		managerDao.addManager(manager);
	}
	public void updateManagerAll(Manager manager) {
		// TODO Auto-generated method stub
		managerDao.updateManager(manager);
	}
	public Manager getManagerById(String id) {
		// TODO Auto-generated method stub
		return managerDao.getManagerById(id);
	}

}

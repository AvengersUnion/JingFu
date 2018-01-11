package com.app.service;

import java.util.List;

import com.app.entity.Manager;

public interface ManagerService {
	Manager login(Manager manager);
	void updateManager(Manager manager);
	
	List<String> getManagerId(String regionId);
	void deleteManagerById(String id);
	void addManager(Manager manager);
	void updateManagerAll(Manager manager);
	Manager getManagerById(String id);
}

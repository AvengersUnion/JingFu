package com.app.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.entity.Manager;

@Component
public interface ManagerDao {
	Manager login(Manager manager);
	void updateManager(Manager manager);
	
	List<String> getManagerId(String regionId);
	void deleteManagerById(String id);
	void addManager(Manager manager);
	void updateManagerAll(Manager manager);
	Manager getManagerById(String id);
}

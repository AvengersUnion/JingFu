package com.app.service;

import java.util.List;

import com.app.entity.Region;
import com.app.entity.BackUser;

public interface UserService {
	int userNumber(BackUser user);
	List<BackUser> getUserList();
	
	List<String> getRegionList();
	List<BackUser> getUserListByCity(BackUser user);
	
	BackUser getUserById(String id);
	void addUser(BackUser user);
	void updateUser(BackUser user);
	void deleteUser(String id);
	List<BackUser> getUserAddress(String userId);
}

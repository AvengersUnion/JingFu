package com.app.dao;

import java.util.List;

import com.app.entity.Advisory;

public interface AdvisoryDao {
	
	List<Advisory> getAdvisorieList();
	Advisory getAdvisoryById(int id);
	void addAdvisory(Advisory advisory);
	void deleteAdvisoryById(int id);
	void addAdvisoryImage(String imagePath);
}

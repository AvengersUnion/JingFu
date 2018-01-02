package com.app.service;

import java.util.List;

import com.app.entity.Advisory;

public interface AdvisoryService {

	List<Advisory> getAdvisorieList();
	Advisory getAdvisoryById(int id);
	void addAdvisory(Advisory advisory);
	void deleteAdvisoryById(int id);
	void addAdvisoryImage(String imagePath);
}

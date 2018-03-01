package com.app.service;


import com.app.entity.Copyright;

public interface CopyrightService {
	Copyright get();
	int save(Copyright copyright);
	int update(Copyright copyright);
}

package com.app.dao;

import com.app.entity.Copyright;

/**
 * 版权dao
 * @author taoxiangfei
 *
 */
public interface CopyrightDao {
	Copyright get();
	int save(Copyright copyright);
	int update(Copyright copyright);
}

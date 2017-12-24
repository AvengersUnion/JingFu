package com.app.dao;

public interface BatchSeqDao {
	//获取id
	public String getId(String column);
	
	//插入id
	public int insertId(String column,String id);
	
	//更新id
	public int updateId(String column,String id);
}

package com.app.service;

import java.util.List;

import com.app.entity.Collect;

public interface CollectService {

	void addCollect(Collect collect);
	void deleteCollect(int id);
	List<Collect> getCollectByUserId(int userId);//根据用户的id查询咨询
	Integer getCollectId(Collect collect);//根据用户的id和咨询的id查询收藏的id
}

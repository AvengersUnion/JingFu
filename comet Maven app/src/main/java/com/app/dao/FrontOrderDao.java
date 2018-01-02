package com.app.dao;

import java.util.List;

import com.app.entity.FrontOrder;

public interface FrontOrderDao {

	List<FrontOrder> getFrontOrdersOnce();
	List<FrontOrder> getFrontOrdersMore();
}

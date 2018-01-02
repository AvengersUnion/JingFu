package com.app.service;

import java.util.List;

import com.app.entity.FrontOrder;

public interface FrontOrderService {
	List<FrontOrder> getFrontOrdersOnce();
	List<FrontOrder> getFrontOrdersMore();
}

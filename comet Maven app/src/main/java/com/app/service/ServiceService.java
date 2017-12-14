package com.app.service;

import com.app.entity.Image;
import com.app.entity.Service;
import com.app.entity.ServiceOrder;

import java.util.List;
import java.util.Map;

public interface ServiceService {
	/**
	 * 获取所有的一级服务 
	 * @return
	 */
	List<Service> getAllService();
    List<Service> getsubListByType(String type);
    Service getdetailListById(String id);
    List<Image> loginImage();
    List<Image> advertImage();
    void saveOrder(ServiceOrder serviceOrder);
}

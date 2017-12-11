package com.app.dao;

import com.app.entity.Image;
import com.app.entity.Service;
import com.app.entity.ServiceOrder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


public interface ServiceDao {
	/**
	 * 获取所有一级服务
	 * @return
	 */
	List<Map<String,String>> getAllService();
    List<Service> getsubListByType(String type);
    Service getdetailListById(String id);
    List<Image> loginImage();
    List<Image> advertImage();
    void saveOrder(ServiceOrder serviceOrder);
}

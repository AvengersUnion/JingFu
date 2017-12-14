package com.app.service.impl;

import com.app.dao.ServiceDao;
import com.app.entity.Image;
import com.app.entity.ServiceOrder;
import com.app.service.ServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("serviceService")
public class ServiceServiceImpl implements ServiceService{

	@Autowired
    ServiceDao serviceDao;
	
    /**
     * 获取所有的一级服务
     */
    public List<com.app.entity.Service> getAllService() {
    	return serviceDao.getAllService();
    }
    public List<com.app.entity.Service> getsubListByType(String type) {
        return serviceDao.getsubListByType(type);
    }

    public com.app.entity.Service getdetailListById(String id) {
        return serviceDao.getdetailListById(id);
    }

    public List<Image> loginImage() {
        return serviceDao.loginImage();
    }

    public List<Image> advertImage() {
        return serviceDao.advertImage();
    }


    public void saveOrder(ServiceOrder serviceOrder) {
        serviceDao.saveOrder(serviceOrder);
    }
}

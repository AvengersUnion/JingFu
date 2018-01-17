package com.app.service;

import com.app.entity.Image;
import com.app.entity.Service;
import com.app.entity.ServiceOrder;

import java.util.List;
import java.util.Map;

public interface ServiceService {
//--------------控制台接口-----------------------------------------------------------
	//获取所有的服务
	List<Service> getServiceList();
	//保存主服务
	int savePafuwu(Service service);
	//保存子服务
	int saveFuwu(Service service);
	//获取父级服务的详情
	Service getPaDetail(String id);
	//获取子服务的详情
	Service getServiceDetailById(String id);
	//删除服务
	int deleteService(String id);
	
//-------------------APP接口----------------------------------------------------------------------------
	/**
	 * 获取所有的一级服务 
	 * @return
	 */
	List<Service> getAllService();
	//获取一级服务下的子服务列表
    Service getServiceListById(String id);
    List<Image> loginImage();
    List<Image> advertImage();
    int saveOrder(ServiceOrder serviceOrder);
    /**
     * 获取所有的一级服务列表
     * @return
     */
    List<Map> getFuService();
	

    
}

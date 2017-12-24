package com.app.dao;

import com.app.entity.Image;
import com.app.entity.Service;
import com.app.entity.ServiceOrder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


public interface ServiceDao {
//-------------------控制台接口------------------------------------------------------------------------------------
	//获取所有的服务
	List<Service> getServiceList();
	//保存主服务
	int savePafuwu(Service service);
	//更新主服务
	int updatePafuwu(Service service);
	//保存子服务
	int saveFuwu(Service service);
	//更新子服务
	int updateFuwu(Service service);
	//获取父级服务的详情
	Service getPaDetail(String id);
	//获取自己服务的详情
	Service getServiceDetailById(String id);
	//删除服务
	int deleteService(String id);
	
//------------------app接口---------------------------------------------------------------------------------
	//获取所有的一级服务
	List<Service> getAllService();
	//根据一级服务id获取子服务列表
    List<Service> getServiceListById(String id);
    List<Image> loginImage();
    List<Image> advertImage();
    void saveOrder(ServiceOrder serviceOrder);
	
	
}

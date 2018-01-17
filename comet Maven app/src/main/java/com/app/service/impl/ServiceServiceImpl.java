package com.app.service.impl;

import com.app.dao.ServiceDao;
import com.app.entity.Image;
import com.app.entity.ServiceOrder;
import com.app.service.ServiceService;
import com.app.util.BatchSeqUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("serviceService")
public class ServiceServiceImpl implements ServiceService{

	@Autowired
    ServiceDao serviceDao;
	
//------------------控制台接口--------------------------------------------------------------------------
	/**
	 * 获取所有的一级服务
	 */
	public List<Map> getFuService() {
		
		return serviceDao.getFuService();
	}

	public List<com.app.entity.Service> getServiceList() {
		List<com.app.entity.Service> serviceList = serviceDao.getServiceList();
		List<com.app.entity.Service> resultList = new ArrayList<com.app.entity.Service>(); 
		//找到一级服务
		for (int i = 0; i < serviceList.size(); i++) {
	        // 一级服务的pid为FW0000000000
	        if ("FW0000000000".equals(serviceList.get(i).getPid())) {
	            resultList.add(serviceList.get(i));
	        }
	    }
		//找到一级服务下面的子服务
		for (com.app.entity.Service service : resultList) {
	        service.setChildService(getChild(service.getId(), serviceList));
	    }
		return resultList;
	}
	private List<com.app.entity.Service> getChild(String id, List<com.app.entity.Service> serviceList) {
	    // 子菜单
	    List<com.app.entity.Service> childList = new ArrayList<com.app.entity.Service>();
	    for (com.app.entity.Service service : serviceList) {
	        // 遍历所有节点，将父菜单id与传过来的id比较
	        if (StringUtils.isNotBlank(service.getPid())) {
	            if (service.getPid().equals(id)) {
	                childList.add(service);
	            }
	        }
	    }
	    // 递归退出条件
	    if (serviceList.size() == 0) {
	        return null;
	    }
	    return childList;
	}
	/**
	 * 保存父级服务
	 */
	public int savePafuwu(com.app.entity.Service service) {
		int result;
		String id = service.getId();
		if(id == null || "".equals(id)) {
			//新增
			id = BatchSeqUtil.getFuwuId();
			service.setId(id);
			result = serviceDao.savePafuwu(service);
		}else {
			//更新
			result = serviceDao.updatePafuwu(service);
		}
		return result;
	}
	/**
	 * 保存子服务
	 */
	public int saveFuwu(com.app.entity.Service service) {
		int result;
		String id = service.getId();
		if(id == null || "".equals(id)) {
			//新增
			id = BatchSeqUtil.getFuwuId();
			service.setId(id);
			result = serviceDao.saveFuwu(service);
		}else {
			//更新
			result = serviceDao.updateFuwu(service);
		}
		return result;
	}
	/**
	 * 获取父级服务的详情
	 */
	public com.app.entity.Service getPaDetail(String id) {
		return serviceDao.getPaDetail(id);
		
	}
	/**
	 * 获取子服务详情
	 */
    public com.app.entity.Service getServiceDetailById(String id) {
        return serviceDao.getServiceDetailById(id);
    }
    /**
     * 删除服务
     */
	public int deleteService(String id) {
		return serviceDao.deleteService(id);
	}
	
//--------------------------APP接口--------------------------------------------
    /**
     * 获取所有的一级服务
     */
    public List<com.app.entity.Service> getAllService() {
    	return serviceDao.getAllService();
    }
    /**
     * 根据一级服务id获取对应子服务列表
     */
    public com.app.entity.Service getServiceListById(String id) {
    	List<com.app.entity.Service> serviceList = serviceDao.getServiceListById(id);
		List<com.app.entity.Service> resultList = new ArrayList<com.app.entity.Service>(); 
		com.app.entity.Service resultService = new com.app.entity.Service();
		//找到一级服务
		for (int i = 0; i < serviceList.size(); i++) {
	        // 一级服务的pid为FW0000000000
	        if ("FW0000000000".equals(serviceList.get(i).getPid())) {
	        	resultService = serviceList.get(i);
	        }
	    }
		//找到一级服务下面的子服务
		
		resultService.setChildService(getChild(resultService.getId(), serviceList));
	 
		return resultService;
    }


    public List<Image> loginImage() {
        return serviceDao.loginImage();
    }

    public List<Image> advertImage() {
        return serviceDao.advertImage();
    }


    public int saveOrder(ServiceOrder serviceOrder) {
        return serviceDao.saveOrder(serviceOrder);
    }


	

    

}

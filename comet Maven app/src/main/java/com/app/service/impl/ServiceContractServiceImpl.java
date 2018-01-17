package com.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.ServiceContractDao;
import com.app.entity.ServiceContracts;
import com.app.service.ServiceContractService;
@Service("serviceContractService")
public class ServiceContractServiceImpl implements ServiceContractService{

	@Resource
	private ServiceContractDao serviceContractDao;
	public List<ServiceContracts> getContractList() {
		// TODO Auto-generated method stub
		return serviceContractDao.getContractList();
	}

	public ServiceContracts getContractById(int id) {
		// TODO Auto-generated method stub
		return serviceContractDao.getContractById(id);
	}

	public List<ServiceContracts> getContractByState(int state) {
		// TODO Auto-generated method stub
		return serviceContractDao.getContractByState(state);
	}

	public void addContracts(ServiceContracts contract) {
		// TODO Auto-generated method stub
		serviceContractDao.addContracts(contract);
	}

	public void updateContract(ServiceContracts contract) {
		// TODO Auto-generated method stub
		serviceContractDao.updateContract(contract);
	}

	public void updateContractByState(ServiceContracts contract) {
		// TODO Auto-generated method stub
		serviceContractDao.updateContractByState(contract);
	}

	public void deleteContractById(int id) {
		// TODO Auto-generated method stub
		serviceContractDao.deleteContractById(id);
	}

}

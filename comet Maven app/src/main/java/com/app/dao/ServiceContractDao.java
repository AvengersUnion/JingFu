package com.app.dao;

import java.util.List;

import com.app.entity.ServiceContracts;


public interface ServiceContractDao {
	List<ServiceContracts> getContractList();//返回所有协议
	ServiceContracts getContractById(int id);//根据id返回协议
	List<ServiceContracts> getContractByState(int state);//根据状态返回协议
	void addContracts(ServiceContracts contract);//添加协议
	void updateContract(ServiceContracts contract);//修改协议类容
	void updateContractByState(ServiceContracts contract);//修改协议状态
	void deleteContractById(int id);//删除协议
}

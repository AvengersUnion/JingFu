package com.app.dao;

import java.util.List;

import com.app.entity.AboutUs;

public interface AboutUsDao {

	List<AboutUs> getAboutUsList();//返回所有关于我们
	AboutUs getAboutUsById(int id);//根据id返回我们的信息
	//List<ServiceContracts> getContractByState(int state);//根据状态返回协议
	void addAboutUs(AboutUs aboutUs);//添加我们的信息
	void updateAboutUs(AboutUs aboutUs);//修改我们的信息
	//void updateContractByState(AboutUs aboutUs);//修改协议状态
	void deleteAboutUsById(int id);//删除信息
}

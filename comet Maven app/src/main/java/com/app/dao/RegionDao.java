package com.app.dao;

import java.util.List;

import com.app.entity.Manager;
import com.app.entity.Region;

public interface RegionDao {
	List<Region> getRegionList();//变量所有的地区
	Region getRegionById(String postcode);//根据邮编查询地址
	void updateRegion(Region region);//更新地区
	void addRegion(Region region);//添加地区
	void deleteRegion(String postcode);//删除地区
	
	//manger表相关联的数据
	List<String> getManagerId(String regionId);//根据地区的邮编查询管理员
	void deleteManagerById(String id);//根据管理员的id删除管理员
	void addManager(Manager manager);//添加管理员
	void updateManagerAll(Manager manager);//更新管理员
	Region getManagerById(String id);//根据管理员id查询管理员
	List<Region> getManagersByPostcode(String postcode);//根据邮编查询所有的管理员
	Region getManangerByName(String name);//根据管理员的名字查询
}

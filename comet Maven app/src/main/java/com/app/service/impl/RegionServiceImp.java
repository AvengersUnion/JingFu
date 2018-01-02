package com.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.RegionDao;
import com.app.entity.Manager;
import com.app.entity.Region;
import com.app.service.RegionService;
@Service("regionService")
public class RegionServiceImp implements RegionService {

	@Resource
	private RegionDao regionDao;

	public List<Region> getRegionList() {
		// TODO Auto-generated method stub
		return regionDao.getRegionList();
	}

	public Region getRegionById(String postcode) {
		// TODO Auto-generated method stub
		return regionDao.getRegionById(postcode);
	}

	public void updateRegion(Region region) {
		// TODO Auto-generated method stub
		regionDao.updateRegion(region);
	}

	public void addRegion(Region region) {
		// TODO Auto-generated method stub
		regionDao.addRegion(region);
	}

	public void deleteRegion(String postcode) {
		// TODO Auto-generated method stub
		regionDao.deleteRegion(postcode);
	}

	public List<String> getManagerId(String regionId) {
		// TODO Auto-generated method stub
		return regionDao.getManagerId(regionId);
	}

	public void deleteManagerById(String id) {
		// TODO Auto-generated method stub
		regionDao.deleteManagerById(id);
	}

	public void addManager(Manager manager) {
		// TODO Auto-generated method stub
		regionDao.addManager(manager);
	}

	public void updateManagerAll(Manager manager) {
		// TODO Auto-generated method stub
		regionDao.updateManagerAll(manager);
	}

	public Region getManagerById(String id) {
		// TODO Auto-generated method stub
		return regionDao.getManagerById(id);
	}

	public List<Region> getManagersByPostcode(String postcode) {
		// TODO Auto-generated method stub
		return regionDao.getManagersByPostcode(postcode);
	}

	public Region getManangerByName(String name) {
		// TODO Auto-generated method stub
		return regionDao.getManangerByName(name);
	}

}

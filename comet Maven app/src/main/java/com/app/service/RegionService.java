package com.app.service;

import java.util.List;

import com.app.entity.Manager;
import com.app.entity.Region;

public interface RegionService {
	List<Region> getRegionList();
	Region getRegionById(String postcode);
	void updateRegion(Region region);
	void addRegion(Region region);
	void deleteRegion(String postcode);
	
	List<String> getManagerId(String regionId);
	void deleteManagerById(String id);
	void addManager(Manager manager);
	void updateManagerAll(Manager manager);
	Region getManagerById(String id);
	List<Region> getManagersByPostcode(String postcode);
	Region getManangerByName(String name);
}

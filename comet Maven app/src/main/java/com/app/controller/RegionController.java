package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.app.entity.Manager;
import com.app.entity.Region;
import com.app.service.RegionService;

@Controller
@RequestMapping("/region/")
public class RegionController {
	@Resource(name = "regionService")
	private RegionService regionService;

	/**
	 * 获取所有的地区
	 * 
	 * @return
	 */
	@RequestMapping("all")
	@ResponseBody
	public List getRegionList() {
		// Map<String, String> =new HashMap<K, V>();
		Map<String, List<Region>> regionMap = new HashMap<String, List<Region>>();
		List<Region> listRegions = regionService.getRegionList();
		for (int i = 0; i < listRegions.size(); i++) {

			List<Region> managerList = regionService
					.getManagersByPostcode(listRegions.get(i).getPostcode());
			listRegions.get(i).setManagerList(managerList);
		}
		return listRegions;
	}

	/**
	 * 根据地区的邮编来获取地区
	 * 
	 * @param postcode
	 * @return
	 */
	@RequestMapping("find")
	@ResponseBody
	public Region getRegionById(String postcode) {
		Region region = regionService.getRegionById(postcode);

		return region;
	}

	/**
	 * 根据管理员id查询管理员
	 * 
	 * @param managerId
	 * @return
	 */
	@RequestMapping("getManagerByid")
	@ResponseBody
	public Region getManagerById(String managerId) {
		Region manager = regionService.getManagerById(managerId);
		return manager;
	}

	/**
	 * 修改地区
	 * 
	 * @param postcode
	 * @param regionName
	 * @return
	 */
	@RequestMapping(value = "updateregion", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String updateRegion(String postcode, String regionName) {
		if (null == postcode || null == regionService.getRegionById(postcode)) {
			return "0";
		} else if (!postcode.equals("")) {
			// 更新region表
			Region region = new Region();
			region.setPostcode(postcode);
			region.setRegionName(regionName);
			regionService.updateRegion(region);
			return "1";
		}
		return "0";
	}

	/**
	 * 更新管理员
	 * 
	 * @param uname
	 * @param passWord
	 * @param managerId
	 * @return
	 */
	@RequestMapping(value = "updatemanager", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String updateMananger(String uname, String passWord, String managerId) {
		// String region=regionService.getManagerById(managerId).toString();
		if (null != regionService.getManagerById(managerId)) {
			Manager manager = new Manager();
			manager.setUname(uname);
			manager.setPassWord(passWord);
			manager.setId(managerId);
			regionService.updateManagerAll(manager);
			return "1";
		}
		return "0";
	}

	/**
	 * 添加地区
	 * 
	 * @param postcode
	 * @param regionName
	 * @return
	 */
	@RequestMapping(value = "addregion", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String addRegion(String postcode, String regionName) {
		// String regionString=regionService.getRegionById(postcode).toString();
		if (null != regionService.getRegionById(postcode)) {
			return "0";
		} else if (null != postcode && !postcode.equals("")) {
			// 添加region表的数据
			Region region = new Region();
			region.setPostcode(postcode);
			region.setRegionName(regionName);
			regionService.addRegion(region);
			// System.out.println(regionService.getRegionById(postcode).get(0).getPostcode());
			return "1";
		}
		return "0";
	}

	/***
	 * 添加管理员信息
	 * 
	 * @param postcode
	 * @param uname
	 * @param passWord
	 * @return
	 */
	@RequestMapping(value = "addmanager", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String addmanager(String postcode, String uname, String passWord) {
		if (null == postcode || postcode.equals("")
				|| null != regionService.getManangerByName(uname)) {
			return "0";
		} else {
			// 添加manger表的数据
			UUID uuid = UUID.randomUUID();// 随机真加一个manger的id
			Manager manager = new Manager();
			manager.setId(uuid.toString());
			manager.setUname(uname);
			manager.setPassWord(passWord);
			manager.setRegion_rc(postcode);
			regionService.addManager(manager);
			return "1";
		}
	}

	/**
	 * 删除地区
	 * 
	 * @param postcode
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String deleteRegion(String postcode) {
		if (null != postcode && null != regionService.getRegionById(postcode)) {
			List<String> idsList = regionService.getManagerId(postcode);
			for (int i = 0; i < idsList.size(); i++) {
				regionService.deleteManagerById(idsList.get(i));
			}
			regionService.deleteRegion(postcode);
			return "1";
		}
		return "0";
	}

	/**
	 * 删除管理员
	 * 
	 * @param managerId
	 * @return
	 */
	@RequestMapping("deletemanager")
	@ResponseBody
	public String deleteManager(String managerId) {
		if (null != managerId
				&& null != regionService.getManagerById(managerId)) {
			regionService.deleteManagerById(managerId);
			return "1";
		}
		return "0";
	}

	/**
	 * 多个地区删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteRegions")
	@ResponseBody
	public String deleteRegionsStr(String ids) {
		String[] postcode = null;
		if (null == ids || ids == "") {
			return "0";
		}
		if (!ids.contains(",")) {
			if (null == regionService.getRegionById(ids)) {
				return "0";
			}
			List<String> idsList = regionService.getManagerId(ids);
			for (int i = 0; i < idsList.size(); i++) {
				regionService.deleteManagerById(idsList.get(i));
			}
			regionService.deleteRegion(ids);
		} else {
			postcode = ids.split(",");
			for (int i = 0; i < postcode.length; i++) {
				if (null == regionService.getRegionById(postcode[i])) {
					return "0";
				}
			}
			for (int i = 0; i < postcode.length; i++) {
				List<String> idsList = regionService.getManagerId(postcode[i]);
				for (int j = 0; j < idsList.size(); j++) {
					regionService.deleteManagerById(idsList.get(j));
				}
				regionService.deleteRegion(postcode[i]);
			}
		}

		return "1";
	}
}

package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.common.BaseResult;
import com.app.entity.Advisory;
import com.app.entity.Collect;
import com.app.service.AdvisoryService;
import com.app.service.CollectService;

@Controller
@RequestMapping("collect")
public class CollectController {

	@Resource(name="collectService")
	private CollectService collectService;
	@Resource(name="advisoryService")
	private AdvisoryService advisoryService;
	/**
	 * 根据用户的id和咨询的id收藏或者取消
	 * @param userId
	 * @param advisoryId
	 * @return
	 */
	@RequestMapping(value = "add.action", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult addCollect(Integer userId, Integer advisoryId) {
		Collect collect=new Collect();
		// 1.获取当前时间
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(new Date());
		if (null!=userId&&null!=advisoryId) {
			collect.setUserId(userId);
			collect.setAdvisoryId(advisoryId);
			Integer id=collectService.getCollectId(collect);
			if (null==id) {
				collect.setCollectTime(date);
				collectService.addCollect(collect);
				return BaseResult.ok("已收藏");
			}else {
				collectService.deleteCollect(id);
				return BaseResult.ok("已取消");
			}
		}
		return BaseResult.build(500, "操作错误");
	}
	/**
	 * 根据id取消收藏
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteId.action")
	@ResponseBody
	public BaseResult deleteCollect(Integer id) {
		if (null!=id&&id>0) {
			collectService.deleteCollect(id);
			return BaseResult.ok();
		}
		return BaseResult.build(500, "操作错误");
	}

	/**
	 * 根据用户的id查询所有的咨询
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="findByUserId.action")
	@ResponseBody
	public List<Collect> getCollectByUserId(int userId) {
		// TODO Auto-generated method stub
		List<Collect> advisoryIds=collectService.getCollectByUserId(userId);
		List<Collect> list=new ArrayList<Collect>();
		//Collect collectList=new Collect();
		if(null==advisoryIds){
			return null;
		}
		//循环咨询的id查询咨询
		for (int i = 0; i < advisoryIds.size(); i++) {
			Collect collect=new Collect();
			if (null==advisoryService.getAdvisoryById(advisoryIds.get(i).getAdvisoryId())) {
				continue;
			}
			collect.setId(advisoryIds.get(i).getId());
			collect.setAdvisoryId(advisoryIds.get(i).getAdvisoryId());
			collect.setAdvisory(advisoryService.getAdvisoryById(advisoryIds.get(i).getAdvisoryId()));
			
			list.add(collect);
		}
		return list;
	}
	/**
	 * 判断是否收藏了
	 * @param userId
	 * @param advisoryId
	 * @return
	 */
	@RequestMapping(value="findId.action", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult getCollectOne(Integer userId, Integer advisoryId) {
		Collect collect=new Collect();
		if (null!=userId&&null!=advisoryId) {
			collect.setUserId(userId);
			collect.setAdvisoryId(advisoryId);
			Integer id=collectService.getCollectId(collect);
			if (null==id) {
				id=0;
			}
			return BaseResult.ok(id);
		}
		return BaseResult.build(500, "操作错误",0);
	}
}

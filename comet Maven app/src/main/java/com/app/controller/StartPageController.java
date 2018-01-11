package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.app.common.BaseResult;
import com.app.entity.Banner;
import com.app.entity.StartPage;
import com.app.service.StartPageService;
import com.app.util.UpdateFile;

@Controller
@RequestMapping("startPage")
public class StartPageController {
	@Resource(name="startPageService")
	private StartPageService startPageService;
	
	/**
	 * 返回全部的启动图
	 * @return
	 */
	@RequestMapping("all")
	@ResponseBody
	public List<StartPage> getStartPageList() {
		return startPageService.getStartPageList();
	}
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@RequestMapping("findById")
	@ResponseBody
	public StartPage getStartPageById(Integer id){
		if (null==id) {
			id=0;
		}
		return startPageService.getStartPageById(id);
	}
	/**
	 * 根据状态查询
	 * @return
	 */
	@RequestMapping("findByState")
	@ResponseBody
	public StartPage getStartPageByState() {
		List<StartPage> list=null;
		list=startPageService.getStartPageByState();
		if (null!=list&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 添加
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult addStartPage(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		if (startPageService.getStartPageList().size()>9) {
			return BaseResult.build(500, "超过了10条，请不要在插入数据");
		}
		// 实例化StartPage类
		StartPage startPage = new StartPage();
		// 上传文件
		BaseResult result = UpdateFile.upload(request, file,"start/");
		// 判断是否上传成功
		if (result.getCode() == 200) {
			// 上传成功 得到文件路径
			String filePath = result.getData().toString();
			// 将路径名上传到数据库
			// 1.获取当前时间
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String date = formatter.format(new Date());
			// 2.上传路径
			startPage.setStartPagePath(filePath);
			// 3.上传状态
			startPage.setState(0);
			// 4.上传时间
			startPage.setCreatTime(date);
			// 将数据上传到数据库
			startPageService.addStartPage(startPage);
			return BaseResult.ok(filePath);
		}
		return BaseResult.build(500, "操作失败");
	}

	/**
	 * 更新图片
	 * @param file
	 * @param path
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updatePic", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult updateStartPage(@RequestParam("file") MultipartFile file,String path,
			Integer id, HttpServletRequest request) {
		if (null==id||id<1) {
			BaseResult.build(500, "操作失败");
		}
		BaseResult result=new BaseResult();
		// 实例化banner类
		StartPage startPage = new StartPage();
		if (null!=file.getOriginalFilename()) {
			// 上传文件
			result = UpdateFile.upload(request, file,"start/");
			// 判断是否上传成功
			if (result.getCode() == 200) {
				// 上传成功 得到文件路径
				String filePath = result.getData().toString();
				// 将路径名上传到数据库
				// 1.上传路径
				startPage.setStartPagePath(filePath);
				// 4.上传id
				startPage.setId(id);
				// 将数据上传到数据库
				startPageService.updateStartPage(startPage);
				//bannerService.addBanner(banner);
				return BaseResult.ok(filePath);
			}
			return BaseResult.build(500, "操作错误");
		}else if (null==file.getOriginalFilename()&&null!=path)  {
			// 将路径名上传到数据库
			// 1.上传路径
			startPage.setStartPagePath(path);
			// 2.上传id
			startPage.setId(id);
			// 将数据上传到数据库
			startPageService.updateStartPage(startPage);
			return BaseResult.ok(path);
		}
		return BaseResult.build(500, "操作错误");
	}

	/**
	 * 更新状态
	 * @param id
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "updateByState", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult updateStartPageState(Integer id,Integer state) {
		// TODO Auto-generated method stub
		StartPage startPage=new StartPage();
		if (null==id||id<1||null==state) {
			return BaseResult.build(500, "操作错误");
		}
		if(startPageService.getStartPageById(id).getState().equals(state)){
			return BaseResult.ok();
		}
		if (state==0||state==1) {
			startPage.setId(id);
			startPage.setState(state);
			startPageService.updateStartPageState(startPage);
			return BaseResult.ok();
		}
		return BaseResult.build(500, "操作错误");
	}

	/**
	 * 根据id单个删除
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteById")
	@ResponseBody
	public BaseResult deleteStartPageById(Integer id) {
		if (null==id||null==startPageService.getStartPageById(id)) {
			return BaseResult.build(500, "操作失败");
		}
		startPageService.deleteStartPageById(id);
		return BaseResult.ok();
	}
	/**
	 * 根据id多个删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("deletes")
	@ResponseBody
	public BaseResult deleteStartPageById(String ids) {
		if (null==ids||ids=="") {
			return BaseResult.build(500, "操作失败");
		}else {
			if (!ids.contains("")) {
				int id=Integer.parseInt(ids);
				if (null==startPageService.getStartPageById(id)||id<1) {
					return BaseResult.build(500, "操作失败");
				}
				startPageService.deleteStartPageById(id);
				return BaseResult.ok();
			}else {
				String[] postcode = ids.split(",");
				for (int i = 0; i < postcode.length; i++) {
					int id=Integer.parseInt(postcode[i]);
					if (null==startPageService.getStartPageById(id)||id<1) {
						return BaseResult.build(500, "操作失败");
					}
				}
				for (int i = 0; i < postcode.length; i++) {
					int id=Integer.parseInt(postcode[i]);
					startPageService.deleteStartPageById(id);
				}
				return BaseResult.ok();
			}
			
		}
		
	}
}

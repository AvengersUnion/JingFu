package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.common.BaseResult;
import com.app.entity.AboutUs;
import com.app.service.AboutUsService;

@Controller
@RequestMapping("aboutus")
public class AboutUsController {

	@Resource(name="aboutUsService")
	private AboutUsService aboutUsService;
	/**
	 * 返回所有关于我们的信息
	 * @return
	 */
	@RequestMapping("all.do")
	@ResponseBody
	public List<AboutUs> getAboutUsList() {
		return aboutUsService.getAboutUsList();
	}

	/**
	 * 根据id返回关于我们的信息
	 * @param id
	 * @return
	 */
	@RequestMapping("findById.do")
	@ResponseBody
	public AboutUs getAboutUsById(Integer id) {

		if(null==id||id<1){
			return null;
		}
		return aboutUsService.getAboutUsById(id);
	}

	/**
	 * 添加信息
	 * @param ourTitle
	 * @param ourDetails
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult addAboutUs(String ourTitle,String ourDetails) {
		//获取当前时间
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(new Date());
		//实例化对象
		AboutUs aboutUs=new AboutUs();
		//加入时间
		aboutUs.setCreateTime(date);
		//加入标题
		aboutUs.setOurTitle(ourTitle);
		//加入类容
		aboutUs.setOurDetails(ourDetails);
		aboutUsService.addAboutUs(aboutUs);
		return BaseResult.ok();
	}

	/**
	 * 修改信息
	 * @param id
	 * @param ourTitle
	 * @param ourDetails
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult updateAboutUs(Integer id,String ourTitle,String ourDetails) {
		//实例化服务协议对象
		AboutUs aboutUs=new AboutUs();
		if (null==id||id<1||null==aboutUsService.getAboutUsById(id)) {
			return BaseResult.build(500, "操作错误");
		}
		//加入要修改的id
		aboutUs.setId(id);
		//加入要修改的标题
		aboutUs.setOurTitle(ourTitle);
		//加入要修改的类容
		aboutUs.setOurDetails(ourDetails);
		aboutUsService.updateAboutUs(aboutUs);
		return BaseResult.ok();
	}

	/**
	 * 根据id删除信息
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteById")
	@ResponseBody
	public BaseResult deleteAboutUsById(Integer id) {
		if (null==id||id<1||null==aboutUsService.getAboutUsById(id)) {
			return BaseResult.build(500, "操作错误");
		}
		aboutUsService.deleteAboutUsById(id);
		return BaseResult.ok();
	}
	/**
	 * 根据多个id删除协议
	 * @param ids
	 * @return
	 */
	@RequestMapping("deletes")
	@ResponseBody
	public BaseResult deleteContracts(String ids) {
		if (null==ids||ids.equals("")) {
			return BaseResult.build(500, "操作错误");
		}else if (!ids.contains(",")) {
			Integer id=Integer.parseInt(ids);
			if (null==aboutUsService.getAboutUsById(id)||id<1) {
				return BaseResult.build(500, "操作错误");
			}
			aboutUsService.deleteAboutUsById(id);
			return BaseResult.ok();
		} else {
			String[] postcode = ids.split(",");
			for (int i = 0; i < postcode.length; i++) {
				int id=Integer.parseInt(postcode[i]);
				if (null==aboutUsService.getAboutUsById(id)||id<1) {
					return BaseResult.build(500, "操作失败");
				}
			}
			for (int i = 0; i < postcode.length; i++) {
				int id=Integer.parseInt(postcode[i]);
				aboutUsService.deleteAboutUsById(id);
			}
			return BaseResult.ok();
		}
	}
}

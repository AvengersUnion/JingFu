package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import com.app.common.BaseResult;
import com.app.entity.Advisory;
import com.app.service.AdvisoryService;
import com.app.util.UpdateFile;


@Controller
@RequestMapping("advisory")
public class AdvisoryController {
	@Resource(name="advisoryService")
	private AdvisoryService advisoryService;
	/**
	 * 查询所有咨询
	 * @return
	 */
	@RequestMapping("all")
    @ResponseBody
	public List<Advisory> getAdvisorieList(){
		
		return advisoryService.getAdvisorieList();
	}
	/**
	 * 根据id查询咨询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("findById")
    @ResponseBody
	public Advisory getAdvisoryById(int id){
		if(id<1){
			return null;
		}
		return advisoryService.getAdvisoryById(id);
	}
	/**
	 * 添加咨询
	 * @param file
	 * @param advisoryTitle
	 * @param advisoryDetails
	 * @param request
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
    @ResponseBody
	public BaseResult addAdvisory(@RequestParam("file")MultipartFile file,String advisoryTitle,
			String content,HttpServletRequest request){
		//实例化咨询类
		Advisory advisory=new Advisory();
		//上传文件
		BaseResult result = UpdateFile.upload(request, file);
		//判断是否上传成功
		if(result.getCode()==200){
			//上传成功 得到文件路径
			String filePath = result.getData().toString();
			//将路径名上传到数据库
			//1.获取当前时间
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date=formatter.format(new Date());
			//2.上传路径
			advisory.setAdvisoryImage(filePath);
			//3.上传标题
			advisory.setAdvisoryTitle(advisoryTitle);
			//4.上传详情
			advisory.setAdvisoryDetails(content);
			//5.上传时间
			advisory.setAdvisoryTime(date);
			//将数据上传到数据库
			advisoryService.addAdvisory(advisory);
		}
		return result;
	}
	/**
	 * 删除咨询
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
    @ResponseBody
	public String deleteAdvisoryById(int id){
		if (id<1||null==advisoryService.getAdvisoryById(id)) {
			return "0";
		}
		advisoryService.deleteAdvisoryById(id);
		return "1";
	}
	//添加详情图片
	@RequestMapping("addDetails")
    @ResponseBody
	public String pathString(@RequestParam("imgFile")MultipartFile imgFile,
			HttpServletRequest request, HttpServletResponse response) {
		//上传文件
		BaseResult result= UpdateFile.upload(request, imgFile);
		JSONObject obj = new JSONObject();
		String message="上传失败";
		//判断是否上传成功
		if(result.getCode()==200){
			//上传成功 得到文件路径
			String filePath = result.getData().toString();
			
			//将数据上传到数据库
			advisoryService.addAdvisoryImage(filePath);
			message = "上传成功";
			obj.put("error", 0);
			obj.put("url", filePath);
			return obj.toJSONString();
		}
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
		
		//return request.getSession().getServletContext().getRealPath("/static/images/zixun/");
	}
	/**
	 * 删除多个咨询
	 * @param ids
	 */
	@RequestMapping("ids")
    @ResponseBody
	public void deleteByIds(Integer ids[]) {
		if(null==ids){
			return ;
		}else {
			for (int i = 0; i < ids.length; i++) {
				if (ids[i]<1||null==advisoryService.getAdvisoryById(ids[i])) {
					return ;
				}
				advisoryService.deleteAdvisoryById(ids[i]);
				return ;
			}
		}
	}
}

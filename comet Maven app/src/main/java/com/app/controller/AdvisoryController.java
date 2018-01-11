package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.app.common.BaseResult;
import com.app.entity.Advisory;
import com.app.service.AdvisoryService;
import com.app.util.PagingUtils;
import com.app.util.UpdateFile;

@Controller
@RequestMapping("advisory")
public class AdvisoryController {
	@Resource(name = "advisoryService")
	private AdvisoryService advisoryService;

	/**
	 * 查询所有咨询
	 * 
	 * @return
	 */
	@RequestMapping("all")
	@ResponseBody
	public List<Advisory> getAdvisorieList(Integer pageNumber) {
		//查询出所有的咨询
		List<Advisory> advisories=advisoryService.getAdvisorieList();
		Advisory advisory=new Advisory();
		PagingUtils<Advisory> pagingUtils=new PagingUtils<Advisory>(advisory);
		//避免空指针异常
		if (null==pageNumber) {
			pageNumber=0;
		}
		//分页
		List<Advisory> advisorieList=pagingUtils.pageingDate(pageNumber, advisories);
		return advisorieList;
	}

	/**
	 * 查询所有的咨询(前台用)
	 * @return
	 */
	@RequestMapping("forntAll")
	@ResponseBody
	public List<Advisory> getAdvisories(){
		return advisoryService.getAdvisorieList();
	}
	/**
	 * 返回咨询的总条数
	 * @return
	 */
	@RequestMapping("getNumber")
	@ResponseBody
	public int getAdvisoriesNumber() {
		return advisoryService.getAdvisorieList().size();
	}
	/**
	 * 根据id查询咨询详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("findById")
	@ResponseBody
	public Advisory getAdvisoryById(int id) {
		if (id < 1) {
			return null;
		}
		return advisoryService.getAdvisoryById(id);
	}

	/**
	 * 添加咨询
	 * 
	 * @param file
	 * @param advisoryTitle
	 * @param advisoryDetails
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult addAdvisory(@RequestParam("file") MultipartFile file,
			String advisoryTitle, String content, HttpServletRequest request) {
		// 实例化咨询类
		Advisory advisory = new Advisory();
		// 上传文件
		BaseResult result = UpdateFile.upload(request, file,"zixun/");
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
			advisory.setAdvisoryImage(filePath);
			// 3.上传标题
			advisory.setAdvisoryTitle(advisoryTitle);
			// 4.上传详情
			advisory.setAdvisoryDetails(content);
			// 5.上传时间
			advisory.setAdvisoryTime(date);
			// 将数据上传到数据库
			advisoryService.addAdvisory(advisory);
		}
		return result;
	}

	/**
	 * 删除咨询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String deleteAdvisoryById(int id) {
		if (id < 1 || null == advisoryService.getAdvisoryById(id)) {
			return "0";
		}
		advisoryService.deleteAdvisoryById(id);
		return "1";
	}

	/**
	 * 
	 * @param imgFile
	 * @param request
	 * @param response
	 * @return
	 */
	// 添加详情图片
	@RequestMapping(value = "addDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> pathString(
			@RequestParam("imgFile") MultipartFile imgFile,
			HttpServletRequest request, HttpServletResponse response) {
		// 上传文件
		// BaseResult result= UpdateFile.upload(request, imgFile);
		// JSONObject obj = new JSONObject();
		Map<String, Object> obj = new HashMap<String, Object>();
		String message = "上传失败";
		// 上传文件
		BaseResult result = UpdateFile.upload(request, imgFile,"zixun");
		// 判断是否上传成功
		if (result.getCode() == 200) {
			// 上传成功 得到文件路径
			String filePath = result.getData().toString();
			System.out.println(filePath);
			// 将数据上传到数据库
			String url = filePath;
			advisoryService.addAdvisoryImage(url);
			message = "上传成功";
			// 上传成功
			obj.put("error", 0);
			obj.put("url", url);
			return obj;
		} else {
			// 文件上传失败
			obj.put("error", 1);
			obj.put("message", message);
			return obj;
		}

		/*
		 * JSONObject obj = new JSONObject(); String message="上传失败"; //判断是否上传成功
		 * if(result.getCode()==200){ //上传成功 得到文件路径 String filePath =
		 * result.getData().toString();
		 * 
		 * //将数据上传到数据库 advisoryService.addAdvisoryImage(filePath);
		 * //System.out.println(filePath); String
		 * pathStr[]=filePath.split("'\'"); message = "上传成功"; obj.put("error",
		 * 0); obj.put("url",
		 * "/comet/static/images/zixun/"+pathStr[pathStr.length-1]); return
		 * obj.toJSONString(); }
		 */
		/*
		 * obj.put("error", 1); obj.put("message", message); return
		 * obj.toJSONString();
		 */
		// return
		// request.getSession().getServletContext().getRealPath("/static/images/zixun/");
	}

	/**
	 * 删除多个咨询
	 * 
	 * @param ids
	 */
	@RequestMapping(value = "deleteIds")
	@ResponseBody
	public BaseResult deleteByIds(String ids) {
		BaseResult result = new BaseResult();
		if (null == ids || ids == "") {
			result.setCode(500);
			result.setMessage("操作错误");
		} else if (!ids.contains(",")) {
			int id = Integer.parseInt(ids);
			if (id > 0 && null != advisoryService.getAdvisoryById(id)) {
				advisoryService.deleteAdvisoryById(id);
				result.setCode(200);
				result.setMessage("删除成功");
			} else {
				result.setCode(500);
				result.setMessage("删除错误");
			}
		} else {
			String[] postcode = ids.split(",");
			for (int i = 0; i < postcode.length; i++) {
				int id = Integer.parseInt(postcode[i]);
				if (id < 1 || null == advisoryService.getAdvisoryById(id)) {
					result.setCode(500);
					result.setMessage("删除错误");
				}
				advisoryService.deleteAdvisoryById(id);
			}
			result.setCode(200);
			result.setMessage("删除成功");
		}
		return result;
	}
}

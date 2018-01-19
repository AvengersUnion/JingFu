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
import com.app.entity.Banner;
import com.app.service.BannerService;
import com.app.util.UpdateFile;

@Controller
@RequestMapping("banner")
public class BannerController {
	@Resource(name="bannerService")
	private BannerService bannerService;
	/**
	 * 查询所有banner
	 * 
	 * @return
	 */
	@RequestMapping("all.do")
	@ResponseBody
	public List<Banner> getBannerList(){
		return bannerService.getBannerList();
	}
	/**
	 * 查询单个banner
	 * @param id
	 * @return
	 */
	@RequestMapping("findById.do")
	@ResponseBody
	public Banner getBannerById(int id){
		return bannerService.getBannerById(id);
	}
	/**
	 * 添加banner
	 * @param file
	 * @param title
	 * @param bannerDetails
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult addBanner(@RequestParam("file") MultipartFile file,
			String title,String content, HttpServletRequest request){
		if (bannerService.getBannerNumber()>10) {
			return BaseResult.build(500, "超过了10条，请不要在插入数据");
		}
		// 实例化banner类
		Banner banner = new Banner();
		// 上传文件
		BaseResult result = UpdateFile.upload(request, file,"banner/");
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
			banner.setImagePath(filePath);
			// 3.上传标题
			banner.setTitle(title);
			// 4.上传详情
			banner.setBannerDetails(content);
			// 5.上传时间
			banner.setBannerTime(date);
			// 将数据上传到数据库
			bannerService.addBanner(banner);
		}
		return result;
	}
	/**
	 * 根据id删除banner
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteById")
	@ResponseBody
	public BaseResult deleteBannerById(Integer id){
		BaseResult result=new BaseResult();
		if (null==id||null==bannerService.getBannerById(id)) {
			result.setCode(500);
			result.setMessage("操作错误");
		}else {
			bannerService.deleteBannerById(id);
			result.setCode(200);
			result.setMessage("删除成功");
		}
		return result;
	}
	/**
	 * 删除多个banner
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteIds")
	@ResponseBody
	public BaseResult deleteBanners(String ids) {
		BaseResult result = new BaseResult();
		if (null == ids || ids == "") {
			result.setCode(500);
			result.setMessage("操作错误");
		} else if (!ids.contains(",")) {
			int id = Integer.parseInt(ids);
			if (id > 0 && null != bannerService.getBannerById(id)) {
				bannerService.deleteBannerById(id);
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
				if (id < 1 || null == bannerService.getBannerById(id)) {
					result.setCode(500);
					result.setMessage("删除错误");
				}
				bannerService.deleteBannerById(id);
			}
			result.setCode(200);
			result.setMessage("删除成功");
		}
		return result;
	}
	/**
	 * 根据id修改banner
	 * @param file
	 * @param title
	 * @param bannerDetails
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult updateBanner(@RequestParam("file") MultipartFile file,String path,
			String title,String content,Integer id, HttpServletRequest request){
		if (null==id||id<1) {
			return BaseResult.build(500, "操作失败");
		}
		BaseResult result=new BaseResult();
		// 实例化banner类
		Banner banner = new Banner();
		// 1.得到文件名
		String fileName = file.getOriginalFilename();
		if (null!=file.getOriginalFilename()&&fileName.contains(".")) {
			// 上传文件
			result = UpdateFile.upload(request, file,"banner/");
			// 判断是否上传成功
			if (result.getCode() == 200) {
				// 上传成功 得到文件路径
				String filePath = result.getData().toString();
				// 将路径名上传到数据库
				// 1.上传路径
				banner.setImagePath(filePath);
				// 2.上传标题
				banner.setTitle(title);
				// 3.上传详情
				banner.setBannerDetails(content);
				// 4.上传id
				banner.setId(id);
				// 将数据上传到数据库
				bannerService.updateBanner(banner);
				//bannerService.addBanner(banner);
				return BaseResult.ok(filePath);
			}
			return BaseResult.build(500, "操作错误");
		}else if (null!=path&&!fileName.contains("."))  {
			// 将路径名上传到数据库
			// 1.上传路径
			banner.setImagePath(path);
			// 2.上传标题
			banner.setTitle(title);
			// 3.上传详情
			banner.setBannerDetails(content);
			// 4.上传id
			banner.setId(id);
			// 将数据上传到数据库
			bannerService.updateBanner(banner);
			return BaseResult.ok(path);
		}
		return BaseResult.build(500, "操作错误");
	}
	/**
	 * 咨询详情图路径
	 * @param imgFile
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addBannerDetails(@RequestParam("imgFile") MultipartFile imgFile,
			HttpServletRequest request, HttpServletResponse response) {
		// 上传文件
		// BaseResult result= UpdateFile.upload(request, imgFile);
		// JSONObject obj = new JSONObject();
		Map<String, Object> obj = new HashMap<String, Object>();
		String message = "上传失败";
		// 上传文件
		BaseResult result = UpdateFile.upload(request, imgFile,"banner/");
		// 判断是否上传成功
		if (result.getCode() == 200) {
			// 上传成功 得到文件路径
			String filePath = result.getData().toString();
			System.out.println(filePath);
			// 将数据上传到数据库
			String url = filePath;
			//advisoryService.addAdvisoryImage(url);
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
	}
	/**
	 * 获得banner的条数
	 * @return
	 */
	@RequestMapping("getNumber")
	@ResponseBody
	public int getBannerNumber(){
		return bannerService.getBannerNumber();
	}
}

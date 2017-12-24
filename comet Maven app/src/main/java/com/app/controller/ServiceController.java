package com.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.app.entity.Goods;
import com.app.entity.Image;
import com.app.entity.Service;
import com.app.entity.ServiceOrder;
import com.app.service.ServiceService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 服务相关
 */

@Controller
@RequestMapping("/getService")
public class ServiceController {

	@Autowired
	private ServiceService serviceService;

	@RequestMapping(value = "/listPage")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("list");
		return "serviceList";
	}

	@RequestMapping(value = "/paForm")
	public String form(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("form");
		String id = request.getParameter("id");
		if(id != null && !("".equals(id))) {
			Service service = serviceService.getPaDetail(id);
			model.addAttribute("service", service);
		}
		return "paServiceForm";
	}
//-------------------------控制台接口--------------------------------------------------------------------------------------------
	/**
	 * 获取所有服务的列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getServiceList")
	@ResponseBody
	public String getServiceList(HttpServletRequest request, HttpServletResponse response) {
		List<Service> list = serviceService.getServiceList();
		String json = JSON.toJSON(list).toString();
		System.out.println(json);
		return json;
	}
	/**
	 * 保存更新主服务
	 * @param service
	 * @param picFile
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/savePaFuwu")
	@ResponseBody//完全实现接口，不做页面跳转，后期如果做页面跳转再改
	public String savePaFuwu(Service service, MultipartFile[] picFile, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JSONObject obj = new JSONObject();
		boolean temp = false;
		try {
			// 判断服务名字是否输入
			if (service.getServiceName() == null || "".equals(service.getServiceName())) {
				//model.addAttribute("NameEror", "请输入服务名字");
				//temp=true;
				obj.put("type", "ERROR");
				obj.put("mes", "请输入服务名字");
				return obj.toString();
			}
			MultipartFile file1 = picFile[0];
			String pic1;
			// 图片名称
			String originalFilename1 = file1.getOriginalFilename();
			// 上传广告图片
			if (file1 != null && originalFilename1 != null && originalFilename1.length() > 0) {
				if (originalFilename1.lastIndexOf(".") != -1) {
					pic1 = originalFilename1.substring(originalFilename1.lastIndexOf("."));
					// 后缀名不为jpg等格式报错
					if (!".jpg.jpeg.png.bmp.tif.psd.svg.PNG.JPG.JPEG.BMP.TIF.PSD.SVG".contains(pic1)) {
						//model.addAttribute("ImageEror", "请传入正确的主图图片格式");
//						temp = true;
						obj.put("type", "ERROR");
						obj.put("mes", "请传入正确的主图图片格式");
						return obj.toString();
					} else {
						String pic_path = request.getSession().getServletContext().getRealPath("");
						// 新的图片名称
						String newFileName = UUID.randomUUID()
								+ originalFilename1.substring(originalFilename1.lastIndexOf("."));
						// 新图片
						File newFile = new File(pic_path + "/static/images/fuwu/", newFileName);
						if (!newFile.exists()) {
							newFile.mkdirs();
						}
						// 将内存中的数据写入磁盘
						file1.transferTo(newFile);
						service.setAdImgPath("/static/images/fuwu/"+newFileName);
					}
					// 没有.后缀名报错
				} else {
//					model.addAttribute("ImageEror", "请传入正确的主图图片格式");
//					temp = true;
					obj.put("type", "ERROR");
					obj.put("mes", "请传入正确的主图图片格式");
					return obj.toString();
				}
				// 上传图片为空并且本地没有存储
			} else if (service.getAdImgPath()==null || "".equals(service.getAdImgPath())) {
//				model.addAttribute("ImageEror","请传入主图图片");
//				temp = true;
				obj.put("type", "ERROR");
				obj.put("mes", "请传入主图图片");
				return obj.toString();
			}
			MultipartFile file2 = picFile[1];
			String pic2;
			// 图片名称
			String originalFilename2 = file2.getOriginalFilename();
			// 上传详情图片
			if (file2 != null && originalFilename2 != null && originalFilename2.length() > 0) {
				if (originalFilename2.lastIndexOf(".") != -1) {
					pic2 = originalFilename2.substring(originalFilename2.lastIndexOf("."));
					// 后缀名不为jpg等格式报错
					if (!".jpg.jpeg.png.bmp.tif.psd.svg.PNG.JPG.JPEG.BMP.TIF.PSD.SVG".contains(pic2)) {
//						model.addAttribute("ImageEror", "请传入正确的详情图片格式");
//						temp = true;
						obj.put("type", "ERROR");
						obj.put("mes", "请传入正确的详情图片格式");
						return obj.toString();
					} else {
						String pic_path = request.getSession().getServletContext().getRealPath("");
						// 新的图片名称
						String newFileName = UUID.randomUUID()
								+ originalFilename2.substring(originalFilename2.lastIndexOf("."));
						// 新图片
						File newFile = new File(pic_path + "/static/images/fuwu/", newFileName);
						if (!newFile.exists()) {
							newFile.mkdirs();
						}
						// 将内存中的数据写入磁盘
						file2.transferTo(newFile);
						service.setDetailImgPath("/static/images/fuwu/"+newFileName);
					}
					// 没有.后缀名报错
				} else {
//					model.addAttribute("ImageEror", "请传入正确的详情图片格式");
//					temp = true;
					obj.put("type", "ERROR");
					obj.put("mes", "请传入正确的详情图片格式");
					return obj.toString();
				}
				// 上传图片为空并且本地没有存储
			} else if (service.getDetailImgPath()==null || "".equals(service.getDetailImgPath())) {
//				model.addAttribute("ImageEror", "请传入详情图片");
//				temp = true;
				obj.put("type", "ERROR");
				obj.put("mes", "请传入详情图片");
				return obj.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("type", "ERROR");
			obj.put("mes", "系统异常，请稍后再试！");
			return obj.toString();
			
		}
//		if (temp) {
//			return "paServiceForm";
//		}
		int result = serviceService.savePafuwu(service);
		if(result == 1) {
			obj.put("type", "SUCCESS");
			obj.put("mes", "保存成功！");
			return obj.toString();
		}else {
			obj.put("type", "ERROR");
			obj.put("mes", "保存失败，请稍后再试！");
			return obj.toString();
		}
//		return "redirect:serviceList";
	}
	/**
	 * 保存更新子服务
	 * @param service
	 * @param picFile
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveFuwu")
	public String save(Service service, MultipartFile[] picFile, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		JSONObject obj = new JSONObject();
		boolean temp = false;
		try {
			// 判断服务名字是否输入
			if (service.getServiceName() == null || "".equals(service.getServiceName())) {
				obj.put("type", "ERROR");
				obj.put("mes", "请输入服务名字！");
				return obj.toString();
			}
			//判断父级服务是否选择
			if(service.getPid() == null || "".equals(service.getPid())) {
				obj.put("type", "ERROR");
				obj.put("mes", "请选择父级服务！");
				return obj.toString();
			}
			//判断服务套餐内容是否输入
			if(service.getPeriod() == null || "".equals(service.getPeriod())) {
				obj.put("type", "ERROR");
				obj.put("mes", "请输入服务套餐内容！");
				return obj.toString();
			}
			//判断价格是否输入
			if(service.getPrice() == null || "".equals(service.getPrice())) {
				obj.put("type", "ERROR");
				obj.put("mes", "请输入最低价格！");
				return obj.toString();
			}
			MultipartFile file1 = picFile[0];
			String pic1;
			// 图片名称
			String originalFilename1 = file1.getOriginalFilename();
			// 上传广告图片
			if (file1 != null && originalFilename1 != null && originalFilename1.length() > 0) {
				if (originalFilename1.lastIndexOf(".") != -1) {
					pic1 = originalFilename1.substring(originalFilename1.lastIndexOf("."));
					// 后缀名不为jpg等格式报错
					if (!".jpg.jpeg.png.bmp.tif.psd.svg.PNG.JPG.JPEG.BMP.TIF.PSD.SVG".contains(pic1)) {
//						model.addAttribute("ImageEror", "请传入正确的广告图片格式");
//						temp = true;
						obj.put("type", "ERROR");
						obj.put("mes", "请传入正确的主图图片格式！");
						return obj.toString();
					} else {
						String pic_path = request.getSession().getServletContext().getRealPath("");
						// 新的图片名称
						String newFileName = UUID.randomUUID()
								+ originalFilename1.substring(originalFilename1.lastIndexOf("."));
						// 新图片
						File newFile = new File(pic_path + "/static/images/fuwu/", newFileName);
						if (!newFile.exists()) {
							newFile.mkdirs();
						}
						// 将内存中的数据写入磁盘
						file1.transferTo(newFile);
						service.setAdImgPath("/static/images/fuwu/"+newFileName);
					}
					// 没有.后缀名报错
				} else {
//					model.addAttribute("ImageEror", "请传入正确的广告图片格式");
//					temp = true;
					obj.put("type", "ERROR");
					obj.put("mes", "请传入正确的主图图片格式！");
					return obj.toString();
				}
				// 上传图片为空并且本地没有存储
			} else if (service.getAdImgPath()==null || "".equals(service.getAdImgPath())) {
//				model.addAttribute("ImageEror", "请传入广告图片");
//				temp = true;
				obj.put("type", "ERROR");
				obj.put("mes", "请传入主图图片！");
				return obj.toString();
			}
			
			MultipartFile file2 = picFile[1];
			String pic2;
			// 图片名称
			String originalFilename2 = file2.getOriginalFilename();
			// 上传详情图片
			if (file2 != null && originalFilename2 != null && originalFilename2.length() > 0) {
				if (originalFilename2.lastIndexOf(".") != -1) {
					pic2 = originalFilename2.substring(originalFilename2.lastIndexOf("."));
					// 后缀名不为jpg等格式报错
					if (!".jpg.jpeg.png.bmp.tif.psd.svg.PNG.JPG.JPEG.BMP.TIF.PSD.SVG".contains(pic2)) {
//						model.addAttribute("ImageEror", "请传入正确的详情图片格式");
//						temp = true;
						obj.put("type", "ERROR");
						obj.put("mes", "请传入正确的详情图片格式！");
						return obj.toString();
					} else {
						String pic_path = request.getSession().getServletContext().getRealPath("");
						// 新的图片名称
						String newFileName = UUID.randomUUID()
								+ originalFilename2.substring(originalFilename2.lastIndexOf("."));
						// 新图片
						File newFile = new File(pic_path + "/static/images/fuwu/", newFileName);
						if (!newFile.exists()) {
							newFile.mkdirs();
						}
						// 将内存中的数据写入磁盘
						file2.transferTo(newFile);
						service.setDetailImgPath("/static/images/fuwu/"+newFileName);
					}
					// 没有.后缀名报错
				} else {
//					model.addAttribute("ImageEror", "请传入正确的详情图片格式");
//					temp = true;
					obj.put("type", "ERROR");
					obj.put("mes", "请传入正确的详情图片格式！");
					return obj.toString();
				}
				// 上传图片为空并且本地没有存储
			} else if (service.getDetailImgPath()==null || "".equals(service.getDetailImgPath())) {
//				model.addAttribute("ImageEror", "请传入详情图片");
//				temp = true;
				obj.put("type", "ERROR");
				obj.put("mes", "请传入详情图片！");
				return obj.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
			obj.put("type", "ERROR");
			obj.put("mes", "系统错误，请稍后再试！");
			return obj.toString();
		}
//		if (temp) {
//			return "paServiceForm";
//		}
//		return "redirect:serviceList";
		int result = serviceService.saveFuwu(service);
		if(result == 1) {
			obj.put("type", "SUCCESS");
			obj.put("mes", "保存成功！");
			return obj.toString();
		}else {
			obj.put("type", "ERROR");
			obj.put("mes", "保存失败，请稍后再试！");
			return obj.toString();
		}
	}
	
	/**
	 * 根据子服务的id获取单个子服务详情
	 *
	 *
	 */
	@RequestMapping(value = "/app/getServiceDetail", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String getServiceDetailById(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {
			return "[]";
		}
		Service service = serviceService.getServiceDetailById(id);
		String json = JSON.toJSON(service).toString();
		System.out.println(json);
		return json;
	}
	
	
	/**
	 * 根据主服务的id获取单个主服务详情
	 *
	 *
	 */
	@RequestMapping(value = "/getPaServiceDetail", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String getPaServiceDetail(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {
			return "[]";
		}
		Service service = serviceService.getPaDetail(id);
		String json = JSON.toJSON(service).toString();
		System.out.println(json);
		return json;
	}
	
	/**
	 * 删除服务
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deleteService", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String deleteService(HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {
			obj.put("type", "0");
			obj.put("mes", "请传入商品id进行删除！");
			return obj.toString();
		}
		int result = serviceService.deleteService(id);
		if(result == 1) {
			obj.put("type", "1");
			obj.put("mes", "删除成功！");
			return obj.toString();
		}else {
			obj.put("type", "0");
			obj.put("mes", "删除失败，请稍后再试！");
			return obj.toString();
		}
	}
	
	
//-------------------APP接口--------------------------------------------------------------------------------------------------------------------
	/**
	 * 获取所有的一级服务
	 */
	@RequestMapping(value = "/app/getAllService", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllService(HttpServletRequest request, HttpServletResponse response) {
		List<Service> list = serviceService.getAllService();
		String json = JSON.toJSON(list).toString();
		System.out.println(json);
		return json;
	}

	/**
	 * 功能描述：通过一级服务的类别id 获取对应的子服务列表
	 *
	 *
	 */
	@RequestMapping(value = "/app/getServiceList", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String getServiceListById(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("getServiceListById");
		String id = request.getParameter("id");
		Service list = serviceService.getServiceListById(id);
		String json = JSON.toJSON(list).toString();
		System.out.println(json);
		return json;
	}


	/**
	 * 功能描述：获取登陆图片
	 *
	 *
	 */
	@RequestMapping("getLoginImage")
	@ResponseBody
	public List getLoginImage() {
		List<Image> list = serviceService.loginImage();
		return list;
	}

	/**
	 * 功能描述：获取广告图片
	 *
	 *
	 */
	@RequestMapping("getAdvertImage")
	@ResponseBody
	public List getAdvertImage() {
		List<Image> list = serviceService.advertImage();
		return list;
	}

	/**
	 * 功能描述：下达服务订单
	 *
	 *
	 */
	@RequestMapping("serviceOrder")
	@ResponseBody
	public String saveOrder(ServiceOrder serviceOrder) {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String datetime = sdf.format(date); // 订单下达时间
		serviceOrder.setSC(datetime);

		String state = "false";

		try {
			serviceService.saveOrder(serviceOrder);
			state = "success";
		} catch (Exception excpetion) {
			excpetion.printStackTrace();
		}

		return state;
	}

}

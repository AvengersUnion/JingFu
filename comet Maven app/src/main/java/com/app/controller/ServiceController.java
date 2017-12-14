package com.app.controller;

import com.alibaba.fastjson.JSON;
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
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务相关
 */

@Controller
@RequestMapping("/getService")
public class ServiceController {

	@Autowired
    private ServiceService serviceService;
	
	@RequestMapping(value = "/listPage")
    public String list(HttpServletRequest request,HttpServletResponse response,Model model) {
    	System.out.println("list");
    	model.addAttribute("name", "20171213222110.png");
         return "listService";
    }
    /**
     * 获取所有的一级服务
     */
	@RequestMapping(value="/app/getAllService",produces="text/html;charset=UTF-8")
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
    @RequestMapping(value="/app/getServiceList",produces="text/html;charset=UTF-8",method=RequestMethod.POST)
    @ResponseBody
    public String getsubListById(HttpServletRequest request, HttpServletResponse response){
    	System.out.println("getsublist");
    	String type = request.getParameter("type");
        List<Service> list=serviceService.getsubListByType(type);
        String json = JSON.toJSON(list).toString();
    	System.out.println(json);
		return json;
    }



    /**
     * 功能描述：根据服务的id获取单个服务详情
     *
     *
     */
    @RequestMapping(value="/app/getServiceDetail",produces="text/html;charset=UTF-8",method=RequestMethod.POST)
    @ResponseBody
    public String getDetailListById(HttpServletRequest request, HttpServletResponse response){
    	String id = request.getParameter("id");
    	if(id == null || id == "") {
    		return "[]";
    	}
        Service service=serviceService.getdetailListById(id);
        String json = JSON.toJSON(service).toString();
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
    public List getLoginImage(){
        List<Image> list=serviceService.loginImage();
        return list;
    }



    /**
     * 功能描述：获取广告图片
     *
     *
     */
    @RequestMapping("getAdvertImage")
    @ResponseBody
    public List getAdvertImage(){
        List<Image> list=serviceService.advertImage();
        return list;
    }



    /**
     * 功能描述：下达服务订单
     *
     *
     */
    @RequestMapping("serviceOrder")
    @ResponseBody
    public String saveOrder(ServiceOrder serviceOrder){


        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String datetime = sdf.format(date); //订单下达时间
        serviceOrder.setSC(datetime);

        String state ="false";

        try {
            serviceService.saveOrder(serviceOrder);
            state="success";
        }catch (Exception excpetion){
            excpetion.printStackTrace();
        }

        return state;
    }
    
    


}

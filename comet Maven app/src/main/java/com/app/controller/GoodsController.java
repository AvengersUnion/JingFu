package com.app.controller;

import com.alibaba.fastjson.JSON;
import com.app.entity.Goods;
import com.app.service.GoodsService;
import com.app.util.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * 商品相关
 */
@Controller
@RequestMapping("/goods/")
public class GoodsController {
  @Resource(name = "goodsService")
  private GoodsService goodsService;
  
  
  @RequestMapping(value="/list")
  public String list() {
	  return "listGoods";
  }
  
  /**
   * 功能描述：获取商品信息
   */
  @RequestMapping(value="/getGoodsList",produces="text/html;charset=UTF-8",method=RequestMethod.POST)
  @ResponseBody
  public String getAllList(Goods goods,HttpServletRequest request, HttpServletResponse response,Model model) {
	List<Goods> goodsList = goodsService.getGoodsInfo(goods);
	String json = JSON.toJSON(goodsList).toString();
	System.out.println(json);
	return json;
  }
  /**
   * 功能描述：获取商品详情
   */
  @RequestMapping(value="/getGoodsDetail",produces="text/html;charset=UTF-8",method=RequestMethod.POST)
  @ResponseBody
  public String getGoodsDetail(HttpServletRequest request, HttpServletResponse response) {
	String id = request.getParameter("id");
	if(id == null || id == "") {
		return "[]";
	}
	Goods goods = goodsService.getGoodsById(id);
	String json = JSON.toJSON(goods).toString();
	System.out.println(json);
	return json;
  }
  /**
   * pc端获取商品列表数据
   * @param goods
   * @param request
   * @param response
   * @param model
   * @return
   */
  @RequestMapping(value="/listData",produces="text/html;charset=UTF-8",method=RequestMethod.POST)
  @ResponseBody
  public String listData(Goods goods,HttpServletRequest request, HttpServletResponse response,Model model) {
	  String pageCodeStr = request.getParameter("pageCode");
	  String pageSizeStr = request.getParameter("pageSize");
	  Integer pageCode,pageSize;
	  if(pageCodeStr == null || "".equals(pageCodeStr)) {
		  pageCode = 1;
	  }else {
		  pageCode = Integer.valueOf(pageCodeStr);
	  }
	  if(pageSizeStr == null || "".equals(pageSizeStr)) {
		  pageSize = 20;
	  }else {
		  pageSize = Integer.valueOf(pageSizeStr);
	  }
	  Page goodsPage = goodsService.getGoodsByPage(pageCode,pageSize);
	  
	  String json = JSON.toJSON(goodsPage).toString();
	  System.out.println(json);
	  return json;

  }
  /**
   * 保存商品
   * @param goods
   * @param request
   * @param response
   * @param model
   * @return
   */
  @RequestMapping(value="/save")
  @ResponseBody
  public String save(Goods goods,HttpServletRequest request, HttpServletResponse response,Model model) {
	  
	  return null;
	  
  }
}

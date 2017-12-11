package com.app.controller;

import com.alibaba.fastjson.JSON;
import com.app.entity.Goods;
import com.app.service.GoodsService;
import org.springframework.stereotype.Controller;
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

  /**
   * 功能描述：获取商品信息
   */
  @RequestMapping(value="/getGoodsInfo",produces="text/html;charset=UTF-8",method=RequestMethod.POST)
  @ResponseBody
  public String getAllList(Goods goods,HttpServletRequest request, HttpServletResponse response) {
	System.out.println("getGoods:" + goods);
	List<Goods> goodsList = goodsService.getGoodsInfo(goods);
	String json = JSON.toJSON(goodsList).toString();
	System.out.println(json);
	return json;
  }



}

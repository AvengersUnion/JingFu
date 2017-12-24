package com.app.service.impl;

import com.app.dao.GoodsDao;
import com.app.entity.Goods;
import com.app.service.GoodsService;
import com.app.util.Page;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * 商品service实现
 * @author taoxiangfei
 *
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
  @Resource
  private GoodsDao goodsDao;
  /**
   * 获取商品列表信息
   */
  public List<Goods> getGoodsInfo(Goods goods) {
    return goodsDao.getGoodsInfo(goods);
  }
  /**
   * 获取商品详情信息
   */
  public Goods getGoodsById(String id) {
	return goodsDao.getGoodsById(id);
}
  /**
   * 根据分页获取商品数据
 * @param <T>
 * @param <T>
   */
public Page getGoodsByPage(Integer pageCode, Integer pageSize) {
	Page page = new Page();
	//当前页数
	page.setPageCode(pageCode);
	//每页大小
	page.setPageSize(pageSize);
	
	//查找总记录数
	int allNumber = goodsDao.getAllNumber();
	page.setAllNumber(allNumber);
	//查找分页数据
	List<Goods> goodsList = goodsDao.findByPage((pageCode-1)*pageSize, pageSize);
	page.setBeanList(goodsList);
	
	return page;
}
  
}

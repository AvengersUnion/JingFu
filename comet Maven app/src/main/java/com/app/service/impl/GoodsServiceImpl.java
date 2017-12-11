package com.app.service.impl;

import com.app.dao.GoodsDao;
import com.app.entity.Goods;
import com.app.service.GoodsService;
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
  
}

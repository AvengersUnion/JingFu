package com.app.service;

import com.app.entity.Goods;
import com.app.util.Page;

import java.util.List;
/**
 * 商品service接口
 * @author taoxiangfei
 *
 */
public interface GoodsService {
/**
 * 获取商品列表
 * @param goods
 * @return
 */
  List<Goods> getGoodsInfo(Goods goods);
  /**
   * 获取商品详情
   * @param id
   * @return
   */
  Goods getGoodsById(String id);
  /**
   * 根据分页获取商品
 * @param <T>
   * @param pageCode
   * @param pageSize
   * @return
   */
  Page getGoodsByPage(Integer pageCode, Integer pageSize);
}

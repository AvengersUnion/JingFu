package com.app.service;

import com.app.entity.Goods;

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
}

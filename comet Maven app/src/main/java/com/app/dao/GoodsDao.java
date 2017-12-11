package com.app.dao;

import com.app.entity.Goods;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品Dao
 * @author taoxiangfei
 *
 */
public interface GoodsDao {
	/**
	 * 获取商品列表信息
	 * @param Goods
	 * @return
	 */
  List<Goods> getGoodsInfo(Goods Goods);
  /**
   * 获取服务详情信息
   * @param id
   * @return
   */
  Goods getGoodsById(String id);
}

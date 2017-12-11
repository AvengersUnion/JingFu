package com.app.service.impl;

import com.app.dao.GoodsDao;
import com.app.entity.Goods;
import com.app.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
  @Resource
  private GoodsDao goodsDao;

  public List<Goods> getGoodsInfo(Goods goods) {
    return goodsDao.getGoodsInfo(goods);
  }

}

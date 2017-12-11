package com.app.service;

import com.app.entity.Goods;

import java.util.List;

public interface GoodsService {
  List<Goods> getGoodsInfo(Goods goods);

}

package com.app.dao;

import com.app.entity.Goods;
import org.springframework.stereotype.Component;

import java.util.List;


public interface GoodsDao {
  List<Goods> getGoodsInfo(Goods Goods);

}

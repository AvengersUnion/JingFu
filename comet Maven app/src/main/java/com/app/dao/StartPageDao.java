package com.app.dao;

import java.util.List;

import com.app.entity.StartPage;

public interface StartPageDao {

	List<StartPage> getStartPageList();//查看所有的启动图片
	StartPage getStartPageById(Integer id);//根据id查询
	List<StartPage> getStartPageByState();//根据状态查询
	void addStartPage(StartPage startPage);//添加启动图片
	void updateStartPage(StartPage startPage);//更新
	void updateStartPageState(StartPage startPage);//更新状态
	void deleteStartPageById(Integer id);//根据id删除
	
}

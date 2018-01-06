package com.app.util;

import java.util.ArrayList;
import java.util.List;
/**
 * 这是一个分页的工具类
 * @author 李洋
 *
 * @param <T>
 */
public class PagingUtils<T> {
	//每一页的数据
	private T beanT;
	public PagingUtils(T beanT){
		super();
		this.beanT=beanT;
	}
	public List<T> pageingDate (int pageNumber,List<T> list) {
		int userNumber=list.size();
		List<T> beanList=new ArrayList<T>();
		if (0==pageNumber||pageNumber==1) {
			if (list.size()<10) {
				for (int i = 0; i < list.size(); i++) {
					beanList.add( list.get(i));
				}
				//return getUsers;
			}else {
				for (int i = 0; i < 10; i++) {
					beanList.add(list.get(i));
				}
			}
			return beanList;
		}else if (userNumber>(pageNumber-1)*10) {
			if (userNumber>pageNumber*10) {
				for (int i = (pageNumber-1)*10; i < pageNumber*10; i++) {
					beanList.add(list.get(i));
				}
			}else {
				for (int i = (pageNumber-1)*10; i < list.size(); i++) {
					beanList.add(list.get(i));
				}
			}
			return beanList;
		}
		return null;
		
	}
}

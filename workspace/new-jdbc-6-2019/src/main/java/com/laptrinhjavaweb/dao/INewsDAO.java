package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewsDAO extends IGenericDAO<NewsModel> {
	List<NewsModel> findByCategoryId(Long categoryId);

	NewsModel findOne(Long id);
	
	Long save(NewsModel newsModel);

	void update(NewsModel newsModel);
	
	void delete(Long id);
	
	List<NewsModel> findAll(Pageble pageble);
	
	int getTotalItem();
}

package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.INewsService;

public class NewsService implements INewsService{

	@Inject
	private INewsDAO newsDao;
	
	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		return newsDao.findByCategoryId(categoryId);
	}

	@Override
	public NewsModel save(NewsModel newsModel) {
		newsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		newsModel.setCreatedBy("");
		Long newId = newsDao.save(newsModel);
		return newsDao.findOne(newId);
	}

	@Override
	public NewsModel update(NewsModel newsModel) {
		NewsModel oldModel = newsDao.findOne(newsModel.getId());
		newsModel.setCreatedBy(oldModel.getCreatedBy());
		newsModel.setCreatedDate(oldModel.getCreatedDate());
		newsModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		newsModel.setModifiedBy("");
		newsDao.update(newsModel);
		return newsDao.findOne(newsModel.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id: ids){
			newsDao.delete(id);
		}
	}

	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		return newsDao.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return newsDao.getTotalItem();
	}

}

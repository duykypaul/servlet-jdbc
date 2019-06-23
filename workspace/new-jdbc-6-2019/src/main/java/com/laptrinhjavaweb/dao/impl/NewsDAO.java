package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.apache.commons.collections.functors.IfClosure;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.mapper.NewsMapper;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO {

	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryid = ?";
		return query(sql, new NewsMapper(), categoryId);
	}

	@Override
	public Long save(NewsModel newsModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO news (title, content,");
		sql.append(" thumbnail, shortdescription, categoryid, createddate, createdby)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newsModel.getTitle(), newsModel.getContent(), 
				newsModel.getThumbnail(), newsModel.getShortDescription(), newsModel.getCategoryId(),
				newsModel.getCreatedDate(), newsModel.getCreatedBy());
	}

	@Override
	public void update(NewsModel newsModel) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
		sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
		sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
		this.update(sql.toString(), newsModel.getTitle(), newsModel.getThumbnail(),
				newsModel.getShortDescription(), newsModel.getContent(), newsModel.getCategoryId(),
				newsModel.getCreatedDate(), newsModel.getCreatedBy(), newsModel.getModifiedDate(), 
				newsModel.getModifiedBy(), newsModel.getId() );
				
	}

	@Override
	public NewsModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewsModel> newsModels = query(sql, new NewsMapper(), id);
		return newsModels.isEmpty() ? null : newsModels.get(0);
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		this.update(sql, id);
	}

	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		if(pageble.getSorter() != null) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
		}
		if (pageble.getOffset() != null & pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit());
		}
		return query(sql.toString(), new NewsMapper());
	}

	@Override
	public int getTotalItem() {
		return count("SELECT count(*) FROM news");
	}

}

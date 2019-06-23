package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;


@WebServlet( urlPatterns = {"/admin-news"})
public class NewsController extends HttpServlet {

	/**
	 * 
	 */
	@Inject
	private INewsService newsService;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		NewsModel newsModel = FormUtil.toModel(NewsModel.class, request);
		Pageble pageble = new PageRequest(newsModel.getPage(), newsModel.getMaxPageItem(),
							   new Sorter(newsModel.getSortName(), newsModel.getSortBy())
							   );
		
		newsModel.setListResult(newsService.findAll(pageble));
		newsModel.setTotalItem(newsService.getTotalItem());
		newsModel.setTotalpage((int)Math.ceil((double) newsModel.getTotalItem() / newsModel.getMaxPageItem()));
		request.setAttribute(SystemConstant.MODEL, newsModel);
		
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/admin/news/list.jsp");
		rDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

package com.yi.board.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yi.board.model.Article;
import com.yi.board.mvc.CommandHandler;
import com.yi.board.service.ArticleService;

public class ArticleListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ArticleService service = ArticleService.getInstance();
		List<Article> list = service.articleList();
		
		req.setAttribute("list", list);
		
		return "/WEB-INF/view/articleList.jsp";
	}

}

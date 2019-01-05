package com.yi.board.handler;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yi.board.model.Article;
import com.yi.board.model.ArticleContent;
import com.yi.board.mvc.CommandHandler;
import com.yi.board.service.ArticleService;

public class ArticleModifyHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		if(req.getMethod().equalsIgnoreCase("get")){
			//수정할 게시물 번호가 넘어와야 함.
			String articleNo = req.getParameter("no");
			int no = Integer.parseInt(articleNo);
			
			ArticleService service = ArticleService.getInstance();
			Map<String, Object> maps = service.readArticle(no);
			
			req.setAttribute("maps", maps);
			
			return "/WEB-INF/view/articleModifyForm.jsp";
		} else if(req.getMethod().equalsIgnoreCase("post")){
			//수정해야 할 게시물 번호가 넘어와야 함.
			String articleNo = req.getParameter("no");
			int no = Integer.parseInt(articleNo);
			String title = req.getParameter("title");
			String userContent = req.getParameter("content");
			
			ArticleService service = ArticleService.getInstance();
			Article article = new Article();
			article.setArticle_no(no);
			article.setModdate(new Date());
			article.setTitle(title);
			
			ArticleContent content = new ArticleContent();
			content.setArticle_no(no);
			content.setContent(userContent);
			
			int isModify = service.modifyArticle(article, content);
			
			if(isModify < 0){
				System.out.println("isModify " + isModify);
			}
			
			req.setAttribute("isModify", isModify);
			
			return "/WEB-INF/view/articleModifySuccess.jsp";
		}
		return null;
	}

}

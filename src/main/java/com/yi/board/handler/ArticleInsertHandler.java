package com.yi.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yi.board.mvc.CommandHandler;
import com.yi.board.service.ArticleService;

public class ArticleInsertHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")){
			return "/WEB-INF/view/articleInsertForm.jsp";
		} else if(req.getMethod().equalsIgnoreCase("post")){
			//id : 세션에 있는 id 꺼내야 하므로 getAttribute
			String id = (String) req.getSession().getAttribute("AUTH");
			//title, content : form에서 입력한 값
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			ArticleService service = ArticleService.getInstance();
			//insert 결과값을 int로 했으니까 그 값을 받을 변수 선언
			int error = service.insertArticle(id, title, content);
			
			//실패한 경우 : insert시 실패한 경우 -1, -2, -3, -4 리턴되니까
			if(error < 0){
				System.out.println("error :" + error);
			}
			return "/WEB-INF/view/articleInsertSuccess.jsp";
		}
		return null;
	}

}

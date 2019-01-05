package com.yi.board.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yi.board.mvc.CommandHandler;
import com.yi.board.service.ArticleService;

public class ArticleReadHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//read도 화면이 하나이기 때문에..........if해서 get 사용안해도 됨. (사용해도 상관없음)
		if(req.getMethod().equalsIgnoreCase("get")){
			//번호 => 리스트 클릭했을 때 번호가 넘어와야 함.
			String articleNo = req.getParameter("no");
			int no = Integer.parseInt(articleNo);
			//session에 있는 아이디
			String id = (String) req.getSession().getAttribute("AUTH");
			
			ArticleService service = ArticleService.getInstance();
			
			Map<String, Object> map = service.readArticle(no);
			service.increaseReadCnt(no);
			
			req.setAttribute("map", map);
			req.setAttribute("id", id);
			return "/WEB-INF/view/articleRead.jsp";
		}
		return null;
	}

}

package com.yi.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yi.board.mvc.CommandHandler;
import com.yi.board.service.ArticleService;

public class ArticleDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")){
			//삭제할 게시물 번호가 넘어와야 함.
			String articleNo = req.getParameter("no");
			int no = Integer.parseInt(articleNo);
			
			ArticleService service = ArticleService.getInstance();
			int isDel = service.deleteArticle(no);
			
			if(isDel < 0){
				System.out.println("isDel " + isDel);
			}
			/*
			 * 삭제 후에 목록을 보이게 하려면 articleList.jsp 파일에 있는 내용을 보여야 하는데, 이 내용이 list에 있는 내용들을 실어서 보내야 하기 때문에
			 * ArticleListHandler를 거쳐야 한다.
			 * ArticleListHandler는 properties 파일에서 list.do로 했기 때문에 return을 list.do로 이동시켜야 함!!
			 * */
			return "list.do";
		}
		return null;
	}

}

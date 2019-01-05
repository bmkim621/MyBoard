package com.yi.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yi.board.mvc.CommandHandler;
import com.yi.board.service.MemberService;

public class MemberDuplicatedHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		//ajax로 호출되는 핸들러. get, post인지 알아서 선택.
		if(req.getMethod().equalsIgnoreCase("get")){
			//아이디 받아오기
			String id = req.getParameter("id");
			
			MemberService service = MemberService.getInstance();
			boolean result = service.duplicatedMember(id);
			req.setAttribute("result", result);
			
			return "/WEB-INF/view/duplicatedId.jsp";
		}
		return null;
	}

}

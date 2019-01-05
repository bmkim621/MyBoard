package com.yi.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yi.board.mvc.CommandHandler;
import com.yi.board.service.MemberService;

public class MemberLoginHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		if(req.getMethod().equalsIgnoreCase("get")){
			return "/WEB-INF/view/loginForm.jsp";
		} else if(req.getMethod().equalsIgnoreCase("post")){
			//form에서 입력한 id, password값 받기
			String id = req.getParameter("id");
			String password = req.getParameter("password");
			
			//아이디, 비밀번호 일치하는 지 확인 => 서비스호출
			MemberService service = MemberService.getInstance();
			int error = service.checkLoginMember(id, password);	//return 0, -1, -2, -3을 받을 수 있는 변수필요
			
			if(error == 0){
				//id ==> session에서 저장.(바로 session 못쓰고 request 이용해야 함) ex) ____님 반갑습니다.
				req.getSession().setAttribute("AUTH", id);
				//로그인되었음 ==> 홈 화면으로 이동
				return "index.jsp";
			}else if(error == -1 || error == -2){
				//비밀번호 일치X 또는 회원이 아닌 경우 ==> form에서 창 띄우도록
				return "/WEB-INF/view/loginForm.jsp";
			}

		}
		return null;
	}

}

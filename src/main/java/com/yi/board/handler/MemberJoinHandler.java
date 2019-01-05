package com.yi.board.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yi.board.model.Member;
import com.yi.board.mvc.CommandHandler;
import com.yi.board.service.MemberService;

public class MemberJoinHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		if(req.getMethod().equalsIgnoreCase("get")){
			//경로 맞춰주기! (기준: WebContent)
			return "/WEB-INF/view/joinForm.jsp";
		} else if(req.getMethod().equalsIgnoreCase("post")){
			//값들이 깨지지 않게 UTF-8 ===> 매번 입력하기 힘드니까 자동으로 처리할 수 있도록 한다.
			//mvc패키지에 CharacterEncodingFilter 클래스만들기.
			//req.setCharacterEncoding("utf-8");
			
			//input에 입력한 키들 넘겨받기
			String memberId = req.getParameter("memberId");
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			
			//매개변수가 모두 받는 생성자 있으니까 new Member()안에 바로 넣기(시간은 서버가 알아서 넣도록)
			Member member = new Member(memberId, name, password, new Date());
			
			MemberService service = MemberService.getInstance();
			service.insertMember(member);
			
			//키를 실어보낸다.(결과화면에 name값이 필요하기 때문에 name만!!)
			req.setAttribute("name", name);
			
			//회원가입됐을 때 나오는 화면
			//insert, update는 forward시키지 않기 때문에 로그아웃과 마찬가지로 Redirect시켜야 함. 안시키면 새로고침할 때 계속 insert하기 때문에 오류남.
			//redirect : get방식
			res.sendRedirect("login.do");
			
			return null;
		}
		return null;
	}

}

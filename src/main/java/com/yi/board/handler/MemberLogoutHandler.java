package com.yi.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yi.board.mvc.CommandHandler;

public class MemberLogoutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// session을 지우면 로그아웃이 됨.
		
		//getSession(true): 세션이 이미 있는지 확인을 하여, 이미 있다면 그 세션을 반환시키고 없으면 새로운 세션을 생성한다.
		//getSession(false): 세션이 있다면 그 세션을 리턴하지만, 세션이 존재하지 않는다면 null을 리턴한다.
		//로그아웃 ==> 기존 세션만 받아오면 됨. true처럼 새로운 세션 받아올 필요는 없음.
		HttpSession session = req.getSession(false);
		if(session != null){
			session.invalidate(); //세션을 삭제한다.
		}
		//로그아웃되면 홈 화면으로 이동한다.
		//"index.jsp"로 하면 usingURI에서 null이 아닐 경우 forward되기 때문에 같은 logout.do를 새로고침하는 것과 똑같음.
		//그리고 null일 경우 forward시키지 않기 때문에 return null로 하고, 그 위에 페이지 Redirect시킨다.
		res.sendRedirect("index.jsp");
		
		return null;
	}

}

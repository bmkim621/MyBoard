package com.yi.board.handler;

import java.lang.management.MemoryManagerMXBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yi.board.model.Member;
import com.yi.board.mvc.CommandHandler;
import com.yi.board.service.MemberService;

public class MemberChangePwdHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		if(req.getMethod().equalsIgnoreCase("get")){
			
			return "/WEB-INF/view/changePwdForm.jsp";
		} else if(req.getMethod().equalsIgnoreCase("post")){
			//changePwdForm에서 비밀번호를 받아온다. ==> 비밀번호 변경할 때 session에 저장된 아이디 값을 가져올 수 있어야 한다.
			String id = (String) req.getSession().getAttribute("AUTH");
			//changePwdForm에서 고객이 입력한 비밀번호
			String password = req.getParameter("password");
			//changePwdForm에서 고객이 입력한 새로운 비밀번호
			String newPassword = req.getParameter("newPassword");
			
			//서비스
			MemberService service = MemberService.getInstance();
			boolean isChangePwd = service.changePwd(id, password, newPassword);
			
//			System.out.println("isChangePwd " + isChangePwd);		
			if(isChangePwd == true){
				//변경된 경우
				return "/WEB-INF/view/changePwdSuccess.jsp";
			} else{
				//현재 암호와 일치하지 않는다면 error를 실어서 changePwdForm으로 보낸다. ==> error 발생
				req.setAttribute("error", true);
				
				return "/WEB-INF/view/changePwdForm.jsp";
			}
		}
		return null;
	}

}

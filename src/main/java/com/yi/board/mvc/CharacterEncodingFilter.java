package com.yi.board.mvc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//필터가 있으면 필터를 거쳐서 핸들러로 이동(==> web.xml에 파일 수정해야함)
		//필터 : 책 chap19, 필터 주로 쓰는 용도 : 로그인, utf8 등등..
		req.setCharacterEncoding("utf-8");
		//chain : 필터를 여러개 만들 수 있음, 다른 필터 호출하는 역할.
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

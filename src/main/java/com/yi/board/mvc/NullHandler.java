package com.yi.board.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		res.sendError(HttpServletResponse.SC_NOT_FOUND);	// 404 error(파일 못찾으면 404 에러 발생함)
		return null;
	}

}

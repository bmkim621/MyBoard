package com.yi.board.dao;

import java.sql.SQLException;

import com.yi.board.model.ArticleContent;

public interface ArticleContentDao {
	public int insert(ArticleContent content) throws SQLException;
	
	public ArticleContent selectByNo(int no) throws SQLException;
	
	public int delete(int articleNo) throws SQLException;
	
	public int modify(ArticleContent content) throws SQLException;
}

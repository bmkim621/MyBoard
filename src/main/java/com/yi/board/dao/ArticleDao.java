package com.yi.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.yi.board.model.Article;

public interface ArticleDao {
	public int insert(Article article) throws SQLException;
	
	public int selectLastNo() throws SQLException;
	
	public List<Article> select() throws SQLException;
	
	public int modify(Article article) throws SQLException;
	
	public int delete(int articleNo) throws SQLException;
	
	public Article selectByNo(int no) throws SQLException;
	
	public int increaseReadCnt(int articleNo) throws SQLException;
}

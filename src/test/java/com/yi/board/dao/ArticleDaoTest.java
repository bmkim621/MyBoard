package com.yi.board.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yi.board.model.Article;
import com.yi.board.mvc.MySqlSessionFactory;

public class ArticleDaoTest {
/*	@Test
	public void testModify(){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			ArticleDao dao = session.getMapper(ArticleDao.class);
			
			Article article = new Article();
			article.setTitle("JUNIT 테스트");
			article.setModdate(new Date());
			article.setArticle_no(10);
			
			dao.modify(article);
			
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
	}*/
	
	@Test
	public void testSelectByAll(){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			ArticleDao dao = session.getMapper(ArticleDao.class);
			
			List<Article> list = new ArrayList<>();
			list = dao.select();
			System.out.println(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}

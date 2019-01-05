package com.yi.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yi.board.model.ArticleContent;
import com.yi.board.mvc.MySqlSessionFactory;

public class ArticleContentDaoTest {
	@Test
	public void testSelectByNo(){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			ArticleContentDao dao = session.getMapper(ArticleContentDao.class);
			
			ArticleContent result= dao.selectByNo(10);
			System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}

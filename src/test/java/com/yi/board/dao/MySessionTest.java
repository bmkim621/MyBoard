package com.yi.board.dao;


import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yi.board.mvc.MySqlSessionFactory;

public class MySessionTest {
	
	@Test
	public void testSession(){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			System.out.println(session);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}

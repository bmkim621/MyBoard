package com.yi.board.dao;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yi.board.model.Member;
import com.yi.board.mvc.MySqlSessionFactory;

public class MemberDaoTest {
	
	/*@Test
	public void testInsert(){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			MemberDao dao = session.getMapper(MemberDao.class);
			
			Member member = new Member();
			member.setMemberId("user004");
			member.setName("테스트");
			member.setPassword("12341234");
			member.setRegDate(new Date());
			
			dao.insert(member);
			
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
	}*/
	
	@Test
	public void testSelectById(){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			MemberDao dao = session.getMapper(MemberDao.class);
			
			Member mem = dao.selectById("qwerty");
			System.out.println(mem);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}

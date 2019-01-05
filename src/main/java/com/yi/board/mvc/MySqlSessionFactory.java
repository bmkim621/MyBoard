package com.yi.board.mvc;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionFactory {
	private static SqlSessionFactory sqlSessionFactory;
	
	public static SqlSessionFactory  getSqlSessionFactory(){
		if(sqlSessionFactory == null){
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream("mybatis-config.xml");
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new RuntimeException(e.getCause());
			}
		}
		return sqlSessionFactory;
	}
	
	public static SqlSession openSession(){
		return getSqlSessionFactory().openSession();
	}
	
	//JDBC -> ConnectionPool
	//MyBatis -> SqlSession
	//SqlSession 사용하기 위해서 SqlSessionFactory 만든 다음 openSession 만들어야 함.
}

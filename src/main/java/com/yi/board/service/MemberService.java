package com.yi.board.service;

import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yi.board.dao.MemberDao;
import com.yi.board.dao.MemberDaoTest;
import com.yi.board.model.Member;
import com.yi.board.mvc.MySqlSessionFactory;

public class MemberService {
	private static MemberService service = new MemberService();
	
	public static MemberService getInstance(){
		return service;
	}
	
	// return 값이 0: 정상적으로 insert가 수행됐을 때, -1 : insert에 실패했을 때(함수 만드는 개발자 마음대로..)
	public int insertMember(Member mem){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			//insert ===> MemberDao 호출하기
			MemberDao dao = session.getMapper(MemberDao.class);
			dao.insert(mem);
			
			//commit반드시! (select 빼고)
			session.commit();
			
			//exception 발생하지 않을경우
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//Connection을 종료시켜준다.(실행시켰으면 항상 마지막에 종료시켜주기!)
			session.close();
		}
		
		return -1;
	}
	
	//true: 중복된 멤버 있음, false: 중복된 멤버 없음
	public boolean duplicatedMember(String id){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			MemberDao dao = session.getMapper(MemberDao.class);
			Member mem = dao.selectById(id);
			if(mem != null){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return false;
	}
	
	
	//MemberDao에서 받은 list를 핸들러한테 return 시킨다.
	public List<Member> getList(){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			MemberDao dao = session.getMapper(MemberDao.class);
			
			return dao.selectList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}
	
	
	//  0 : id, 비밀번호 일치
	// -1 : id, 비밀번호 불일치
	// -2 : id가 없는 경우(회원이 아님)
	// -3 : error
	public int checkLoginMember(String id, String password){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			MemberDao dao = session.getMapper(MemberDao.class);
			
			//id를 가지고 회원인지 아닌지 판단
			Member member = dao.selectById(id);
			if(member == null){
				return -2;	//id가 없으니까 회원X
			}
			if(member.getPassword().equals(password) == false){
				return -1;	//비밀번호가 일치하지 않는 경우
			}
			
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return -3;	//error
		
	}
	
	//true: 새로운 비밀번호로 변경, false: 비밀번호 변경 실패
	public boolean changePwd(String id, String password, String newPassword){
		SqlSession session = null;
	
		/*System.out.println("id " + id);	
		System.out.println("password " + password);
		System.out.println("newPassword " + newPassword);*/
		
		try {
			session = MySqlSessionFactory.openSession();
			MemberDao dao = session.getMapper(MemberDao.class);
			
			//고객이 입력한 id가 DB에 있는 id라면 그 id의 모든 결과를 가지고 온다.
			Member mem = dao.selectById(id);
			
			//DB에 있는 비밀번호와 고객이 비밀번호 변경 폼에서 입력한 비밀번호와 같으면
			if(mem.getPassword().equals(password)){
				//update를 수행한다. (==> 바뀌는 값이 새로운 비밀번호)
				dao.updatePwd(id, newPassword);
				
				session.commit();
				
				return true;
			} else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
}	

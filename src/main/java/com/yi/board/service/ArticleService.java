package com.yi.board.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yi.board.dao.ArticleContentDao;
import com.yi.board.dao.ArticleDao;
import com.yi.board.dao.MemberDao;
import com.yi.board.model.Article;
import com.yi.board.model.ArticleContent;
import com.yi.board.model.Member;
import com.yi.board.mvc.MySqlSessionFactory;

public class ArticleService {
	//Singleton
	private static ArticleService service = new ArticleService();
	
	public static ArticleService getInstance(){
		return service;
	}
	
	//insert
	//id는 어떻게 받아올까? 로그인될 때 session에 저장되니까
	// -1 : id에 해당하는 member가 없는 경우
	// -2 : article에 저장 실패한 경우
	// -3 : articleContent에 저장 실패한 경우
	// -4 : 특수 경우
	// 0 : 성공
	public int insertArticle(String id, String title, String content){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			
			//id, title, content 외 나머지는 service에서 받아오도록 처리하기
			//name가져올려면 memberDao가 필요하니까
			MemberDao memberDao = session.getMapper(MemberDao.class);
			//1. id에 해당하는 name값 가져오기(Member로부터)
			Member member = memberDao.selectById(id);
			//member가 있는지 없는지 여부(위에 selectById에서 member가 없는 경우 null로 처리했음)
			if(member == null){
				return -1;
			}
			
			//2. article에 저장하기
			ArticleDao articleDao = session.getMapper(ArticleDao.class);
			
			//insert에는 Article 클래스가 와야되므로 만들어주기.
			//article_no ==> DB에서 알아서 증가하므로 처리하기 때문에 default값으로 넘기기, default 값 : 0
			//writer_name ==> member로부터 받은 name
			//date ==> new Date()
			Date now = new Date();
			//read_cnt ==> 처음 insert하기 때문에 0 
			Article article = new Article(0, id, member.getName(), title, now, now, 0);
			
			//ArticleDao에서 newNo받도록 처리했었는데 mapper에는 그냥 insert만 하고, newNo를 못가지고 오기 때문에 다시 selectLastNo를 호출해야 한다.
			articleDao.insert(article);
			int article_no = articleDao.selectLastNo();
			
			
			//위에 insert 성공했을 경우 newNo넘어옴, 그렇지 않을 경우 -1이므로 0보다 작음.
			if(article_no < 0){
				return -2;
			}
			
			//3. article_content에 저장하기
			ArticleContentDao contentDao = session.getMapper(ArticleContentDao.class);
			//insert에는 ArticleContent 클래스가 와야되므로 만들어주기.
			ArticleContent articleContent = new ArticleContent(article_no, content);
			int result = contentDao.insert(articleContent);
			//insert함수에서 실패할 경우 -1 return하므로 0보다 작음.
			if(result < 0){
				return -3;
			}
			
			session.commit();	//article 테이블, article_content 테이블에 모두 insert 성공했을 시
			return 0;	//성공!
		} catch (Exception e) {
			//insert 과정에서 SqlException발생 시 rollback 처리한다.
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return -4;
	}
	
	
	//리스트
	public List<Article> articleList(){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			
			ArticleDao dao = session.getMapper(ArticleDao.class);
			return dao.select();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	//
	public Map<String, Object> readArticle(int no){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			
			ArticleDao articleDao = session.getMapper(ArticleDao.class);
			ArticleContentDao contentDao = session.getMapper(ArticleContentDao.class);
			
			Article article = articleDao.selectByNo(no);
			ArticleContent content = contentDao.selectByNo(no);
			
			//article, content 둘 다 return 시켜야한다. ==> 객체 2개 리턴할 경우 hashmap 사용하기
			Map<String, Object> map = new HashMap<>();
			
			map.put("article", article);
			map.put("content", content);
			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;	//객체 리턴할 값 없으면 null로 처리하기
	}
	
	
	//delete
	//0: 삭제성공, -1: article에서 삭제 실패, -2: article_content에서 삭제 실패, -3: 그 외 나머지
	public int deleteArticle(int no){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			
			//================== 1. article
			//article에 있는 것들 가져와야 됨.
			ArticleDao articleDao = session.getMapper(ArticleDao.class);
			//article에 있는 article_no로 검색 ==> no에 해당하는 article 내용 다 들고오기
			Article article = articleDao.selectByNo(no);
			//article에 있는 article_no와 int와 같아야 삭제
			if(article.getArticle_no() == no){
				articleDao.delete(no);
				
			} else{
				return -1;
			}
			
			//================== 2. article_content
			ArticleContentDao contentDao = session.getMapper(ArticleContentDao.class);
			ArticleContent content = contentDao.selectByNo(no);
			if(content.getArticle_no() == no){
				contentDao.delete(no);
			} else{
				return -2;
			}
			
			session.commit();	//두 테이블에 모두 delete 됐을 경우
			return 0;	//삭제성공	
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return -3;
	}
	
	
	//modify
	//0: 수정 성공, -1: article에서  수정 실패, -2: article_content에서 수정 실패, -3: 그 외 나머지
	public int modifyArticle(Article userArticle, ArticleContent userArticleContent){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			
			//================== 1. article
			ArticleDao articleDao = session.getMapper(ArticleDao.class);
			articleDao.modify(userArticle);
			
			
			//================== 2. article_content
			ArticleContentDao contentDao = session.getMapper(ArticleContentDao.class);
			contentDao.modify(userArticleContent);
			
			session.commit();
			return 0;
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return -3;
	}
	
	public void increaseReadCnt(int articleNo){
		SqlSession session = null;
		
		try {
			session = MySqlSessionFactory.openSession();
			
			ArticleDao dao = session.getMapper(ArticleDao.class);
			dao.increaseReadCnt(articleNo);
			
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}

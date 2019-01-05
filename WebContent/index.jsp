<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- AUTH가 있으면 회원이니까 회원가입, 로그인하기 안보이게 c:if로 처리하기 -->
	<c:if test="${AUTH == null }">
		<a href="join.do">[회원가입]</a>
		<a href="login.do">로그인하기</a>
	</c:if>
	<!-- joinForm.jsp파일로 접근을 못하니까 링크를 클릭하면 바로 join.do로 이동하게 한다. -->
	<c:if test="${AUTH != null }">
		<p>${AUTH }님, 반갑습니다!</p>
		<a href="changePwd.do">비밀번호 변경</a>
		<a href="memberList.do">회원리스트 보기</a>
		<a href="logout.do">로그아웃</a>
		<!-- 로그인하면 글 쓸 수 있도록-->
		<br>
		<a href="article/insert.do">게시글 작성하기</a>
		<a href="article/list.do">게시글 목록 보기</a>
	</c:if>
</body>
</html>
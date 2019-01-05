<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		border: 1px solid gray;
		border-collapse: collapse;
	}
	
	td{
		border: 1px solid gray;
		padding: 5px;
		text-align: center;
		font-size: 14px;
	}
	
	a{
		text-decoration: none;
		font-weight: bold;
		color: black;
	}
</style>
</head>
<body>
	<table>
		<tr>
			<td colspan="6"><a href="${pageContext.request.contextPath }/article/insert.do">[게시글 쓰기]</a></td>
		</tr>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>조회수</td>
			<td>작성일</td>
			<td>수정일</td>
		</tr>
		<c:forEach var="article" items="${list }">
			<tr>
				<td>${article.article_no }</td>
				<!-- no와 함께 같이 실어보내야 됨!! -->
				<td><a href="read.do?no=${article.article_no }">${article.title }</a></td>
				<td>${article.writer_name }</td>
				<td>${article.read_cnt }</td>
				<td>${article.regdate }</td>
				<td>${article.moddate }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
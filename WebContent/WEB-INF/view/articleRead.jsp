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
		width: 500px;
	}
	
	td{
		border: 1px solid gray;
		padding: 5px;
		font-size: 14px;
	}
	
	a{
		font-size: 14px;
		text-decoration: none;
		color: red;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(function(){
		$("#del").click(function(){
			var delConfirm = confirm("정말 삭제하시겠습니까?");
			if(delConfirm == true){
				//location으로 삭제~
				location.href = "delete.do?no=${map.article.article_no }";
			} else{
				
			}
			return false;
		})
	})
</script>
</head>
<body>
	<!-- 번호, 작성자 이름, 제목, 내용 -->
	<table>
		<tr>
			<td>번호</td>
			<!-- map => 키 => 객체 article_no의 get 호출 -->
			<td>${map.article.article_no }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${map.article.writer_name }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${map.article.title }</td>
		</tr>
		<tr>	
			<td>내용</td>
			<td>${map.content.content }</td>
		</tr>
	</table>
	<!-- session에 있는 아이디와 작성자가 같아야 삭제, 수정 메뉴 나오도록 한다. -->
	<a href="list.do">[목록]</a>
	<c:if test="${map.article.writer_id == id}">
		<a href="modify.do?no=${map.article.article_no }">[게시글수정]</a>
		<a href="delete.do?no=${map.article.article_no }" id="del">[게시글삭제]</a>
	</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		padding: 5px 10px;
	}
</style>
</head>
<body>
	<c:if test="${list.size() > 0 }">
	<table>
	<c:forEach var="member" items="${list }">
		<tr>
			<td>${member.memberId }</td>
			<td>${member.name }</td>
			<td>${member.password }</td>
			<td><fmt:formatDate value="${member.regDate }" pattern="yyyy-MM-dd hh:mm" /></td>
		</tr>
	</c:forEach>
	</table>
	</c:if>
</body>
</html>
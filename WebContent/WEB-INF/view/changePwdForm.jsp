<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	label{
		width: 100px;
		float: left;
	}
	
	.error{
		color: red;
		font-size: 12px;
	}
</style>
</head>
<body>
	<form action="changePwd.do" method="post">
		<p>
			<label>현재 암호 :</label>
			<input type="password" name="password">
			<!-- c:if문으로 error가 있을 경우에만 span 태그가 보이도록 처리한다. -->
			<c:if test="${error == true}">
				<span class="error">현재 암호가 일치하지 않습니다.</span>
			</c:if>			
		</p>
		<p>
			<label>새 암호 :</label>
			<input type="password" name="newPassword">
		</p>
		<p>
			<input type="submit" value="암호 변경">
		</p>
	</form>
</body>
</html>
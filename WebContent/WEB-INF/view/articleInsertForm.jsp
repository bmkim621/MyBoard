<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	label{
		display: block;
	}
	
	form{
		width: 400px;
		margin: 0 auto;
	}
	
	p:LAST-CHILD {
		text-align: center;	
	}
	
	fieldset {
		padding: 10px;
	}
</style>
</head>
<body>
	<!-- command가 /부터 있게 되면 8080뒤에 맨 앞 /부터 변경됨. -->
	<form action="${pageContext.request.contextPath }/article/insert.do" method="post">
		<fieldset>
			<p>
				<label>제목</label>
				<input type="text" name="title" size="40">
			</p>
			<p>
				<label>내용</label>
				<textarea rows="10" cols="50" name="content"></textarea>
			</p>
			<p>
				<input type="submit" value="새글 등록">
			</p>
		</fieldset>
	</form>
</body>
</html>
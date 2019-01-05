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
	<form action="${pageContext.request.contextPath }/article/modify.do" method="post">
		<fieldset>
			<p>
				<label>번호</label>
				<input type="text" name="no" readonly="readonly" value="${maps.article.article_no }">
			</p>
			<p>
				<label>이름</label>
				<input type="text" name="name" readonly="readonly" value="${maps.article.writer_name }">
			</p>
			<p>
				<label>제목</label>
				<input type="text" name="title" size="40" value="${maps.article.title }">
			</p>
			<p>
				<label>내용</label>
				<textarea rows="10" cols="50" name="content">${maps.content.content }</textarea>
			</p>
			<p>
				<input type="submit" value="수정">
			</p>
		</fieldset>
	</form>
</body>
</html>
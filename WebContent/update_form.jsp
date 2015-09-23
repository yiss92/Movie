<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>글 수정 화면</title>
</head>
<body>
	<form action="board" method="post">
		제목 : <input type="text" name="title" size="20" value="${requestScope.ori.title}"><br>
		글내용 : <br>
		<textarea rows="5" cols="20" name="content">${requestScope.ori.content}</textarea>
		<hr>
		<input type="submit" value="수정">
		
		<input type="hidden" name="type" value="update">
		<input type="hidden" name="articleNum" value="${requestScope.ori.articleNo}"> 
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�� ���� ȭ��</title>
</head>
<body>
	<form action="board" method="post">
		���� : <input type="text" name="title" size="20" value="${requestScope.ori.title}"><br>
		�۳��� : <br>
		<textarea rows="5" cols="20" name="content">${requestScope.ori.content}</textarea>
		<hr>
		<input type="submit" value="����">
		
		<input type="hidden" name="type" value="update">
		<input type="hidden" name="articleNum" value="${requestScope.ori.articleNo}"> 
	</form>
</body>
</html>
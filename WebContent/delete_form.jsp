<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시글 삭제 화면</title>
</head>
<body>
	<form action="board" method="post">
	
		<input type="submit" value="삭제">
		
		<input type="hidden" name="type" value="delete">
			<input type="hidden" name="articleNum" value="${requestScope.ori.articleNo}"> 
	</form>
</body>
</html>
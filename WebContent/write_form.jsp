<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>write_form.jsp 글쓰기 입력폼</title>
</head>
<body>
	<form action="board" method="post">
		제목 : <input type="text" name="title" size="20"><br>
		글내용 : <br>
		<textarea rows="5" cols="20" placeholder="내용입력" name="content"></textarea>
		<hr>
		<input type="submit" value="저장">
		
		<input type="hidden" name="type" value="write">
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>write_form.jsp �۾��� �Է���</title>
</head>
<body>
	<form action="board" method="post">
		���� : <input type="text" name="title" size="20"><br>
		�۳��� : <br>
		<textarea rows="5" cols="20" placeholder="�����Է�" name="content"></textarea>
		<hr>
		<input type="submit" value="����">
		
		<input type="hidden" name="type" value="write">
	</form>
</body>
</html>
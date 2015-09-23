
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>글쓰기 완료 화면</title>
</head>
<body>
	<c:if test="${requestScope.result>0}">
		글쓰기가 완료되었습니다.<br>
		<a href="board?type=list">[목록으로]</a>
	</c:if>

	<c:if test="${requestScope.result==0}">
		글쓰기가 실패했습니다.<br>
		<a href="board?type=list">[목록으로]</a>
	</c:if>

</body>
</html>
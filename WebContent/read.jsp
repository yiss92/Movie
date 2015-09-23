<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시글 읽기</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty requestScope.free}">
			게시글이 존재하지 않습니다.<br>
			<a href="board?type=list">[목록으로]</a>
		</c:when>
		<c:otherwise>
			<table border="1">
				<tr>
					<td>글 번호</td>
					<td>${requestScope.free.articleNo}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>${requestScope.free.articleTitle}</td>
				</tr>
				<tr>
					<td>게시 날짜</td>
					<td>${requestScope.free.ymd}</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td>조회수</td> -->
<%-- 					<td>${requestScope.ar.readCount}</td> --%>
<!-- 				</tr> -->
				<tr>
					<td>내용</td>
					<td>${requestScope.free.content}</td>
				</tr>
				<tr>
					<td colspan="2">
					<a href="board?type=update_form&articleNum=${free.articleNo}">[수정하기]</a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<a href="board?type=delete_form&articleNum=${free.articleNo}">[삭제하기]</a>
					</td>
				</tr>
			</table>
			<a href="board?type=list">[목록으로]</a>
		</c:otherwise>
	
	</c:choose>
</body>
</html>
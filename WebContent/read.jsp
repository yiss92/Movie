<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խñ� �б�</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty requestScope.free}">
			�Խñ��� �������� �ʽ��ϴ�.<br>
			<a href="board?type=list">[�������]</a>
		</c:when>
		<c:otherwise>
			<table border="1">
				<tr>
					<td>�� ��ȣ</td>
					<td>${requestScope.free.articleNo}</td>
				</tr>
				<tr>
					<td>����</td>
					<td>${requestScope.free.articleTitle}</td>
				</tr>
				<tr>
					<td>�Խ� ��¥</td>
					<td>${requestScope.free.ymd}</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td>��ȸ��</td> -->
<%-- 					<td>${requestScope.ar.readCount}</td> --%>
<!-- 				</tr> -->
				<tr>
					<td>����</td>
					<td>${requestScope.free.content}</td>
				</tr>
				<tr>
					<td colspan="2">
					<a href="board?type=update_form&articleNum=${free.articleNo}">[�����ϱ�]</a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<a href="board?type=delete_form&articleNum=${free.articleNo}">[�����ϱ�]</a>
					</td>
				</tr>
			</table>
			<a href="board?type=list">[�������]</a>
		</c:otherwise>
	
	</c:choose>
</body>
</html>
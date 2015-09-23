<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list.jsp</title>
</head>
<body>
<table border="1">
	<tr>
		 <th>글번호</th> <th>제목</th> <th>아이디</th> <th>날짜</th> 
<!-- 		 <th>조회수</th> -->
	</tr>
	
	<c:choose>
		<c:when test="${empty requestScope.freeArticlePage.freeList}">
			<tr>
				<td colspan="5"><br>글이 한개도  없는데~<br></td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="free" items="${requestScope.freeArticlePage.freeList}">
				<tr>
					<td>${free.articleNo}</td>
					<td><a href="board?type=read&articleNum=${free.articleNo}" >${free.articleTitle}</a></td>
					<td>${free.userId}</td>
					<td><fmt:formatDate value="${free.ymd}" pattern="MM/dd"/></td>
<%-- 					<td>${ar.readCount}</td> --%>
				</tr>
			</c:forEach>
				<tr>
					<td colspan="5">
						<c:forEach var="i" begin="${requestScope.freeArticlePage.startArticleNo}" end="${requestScope.freeArticlePage.endArticleNo}">
							<a href="board?type=list&page=${i}">[${i}]</a>			
			
						</c:forEach>
					</td>
				</tr>
		</c:otherwise>
	</c:choose>
</table>
	<a href="board?type=write_form"><button>글쓰기</button></a>

</body>
</html>
	
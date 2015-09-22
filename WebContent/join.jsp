<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
  <c:if test ="${requestScope.result ==0}">
          회원가입에 실패했습니다. <br >
            <a href ="movie?type=JoinForm"> [다시 회원가입]</a >
     </c:if >
     
     <c:if test ="${requestScope.result >0}">
           회원가입이 성공했습니다. <br >
            <a href ="movie?type=loginForm"> [로그인]</a >
     </c:if >

</body>
</html>
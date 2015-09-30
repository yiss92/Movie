<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html lang="en">
	<head>
	<title>Contact</title>
	<meta charset="utf-8">
	<link rel="icon" href="img/favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
	<meta name="description" content="Your description">
	<meta name="keywords" content="Your keywords">
	<meta name="author" content="Your name">
	<link rel="stylesheet" href="css/bootstrap.css" type="text/css" media="screen">
	<link rel="stylesheet" href="css/responsive.css" type="text/css" media="screen">
	<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/superfish.js"></script>
	<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script src="js/forms.js"></script>
	<script>		
   jQuery(window).load(function() {	
    jQuery('.spinner').animate({'opacity':0},1000,'easeOutCubic',function (){jQuery(this).css('display','none')});	
   });			
	</script>

	<!--[if lt IE 8]> 
  		<div style='text-align:center'><a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/img/upgrade.jpg"border="0"alt=""/></a></div>  
 	<![endif]-->
	<!--[if (gt IE 9)|!(IE)]><!-->
	<!--<![endif]-->
	<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <link rel="stylesheet" href="css/docs.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400' rel='stylesheet' type='text/css'>
  <![endif]-->
	</head>

	<body>
<div class="spinner"></div>
<!--============================== header =================================-->
<header>
      <div class="container clearfix">
    <div class="row">
          <div class="span12">
        <div class="navbar navbar_">
              <div class="container">
            <h1 class="brand brand_"><a href="movie?type=login-1"><img alt="" src="img/MainTopic.JPG"> </a></h1>
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse_">Menu <span class="icon-bar"></span> </a>
            <div class="nav-collapse nav-collapse_  collapse">
                  <ul class="nav sf-menu">
                <li><a href="index.html">����</a></li>
                <li><a href="index-1.html">��������</a>
<!--                       <ul> -->
<!--                     <li><a href="#">Dolore </a></li> -->
<!--                     <li><a href="#">Consecte</a></li> -->
<!--                     <li><a href="#">Conseq</a></li> -->
<!--                   </ul> -->
                    </li>
                <li><a href="index-2.html">����</a></li>
                <li><a href="index-3.html">����</a></li>
                <li class="sub-menu active"><a href="index-4.html">�Խ���</a></li>
              </ul>
                </div>
          </div>
            </div>
      </div>
        </div>
  </div>
    </header>
<div class="bg-content"> 
      <!--============================== content =================================-->
      <div id="content"><div class="ic"></div>
    <div class="container">
          <div class="row">
        <article class="span8">
        <br>

			<table border="1">
	<tr>
		 <th width="250" height="50">�۹�ȣ</th> <th width="250" height="50">����</th> <th width="250" height="50">���̵�</th> <th width="250" height="50">��¥</th> 
<!-- 		 <th>��ȸ��</th> -->
	</tr>
	
	<c:choose>
		<c:when test="${empty requestScope.freeArticlePage.freeList}">
			<tr>
				<td colspan="5" height="350"><br><br></td>
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
	<a href="board?type=write_form"><button>�۾���</button></a>

        

            </article>
            </div>
        </div>
  </div>
    </div>



</body>
</html>
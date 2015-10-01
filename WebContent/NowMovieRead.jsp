<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>About</title>
<meta charset="utf-8">
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
<meta name="description" content="Your description">
<meta name="keywords" content="Your keywords">
<meta name="author" content="Your name">
<link rel="stylesheet" href="css/bootstrap.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/responsive.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css"
	media="screen">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300'
	rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/superfish.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>

<script type="text/javascript">if($(window).width()>1024){document.write("<"+"script src='js/jquery.preloader.js'></"+"script>");}	</script>
<script>		
		 jQuery(window).load(function() {	
		 $x = $(window).width();		
	if($x > 1024)
	{			
	jQuery("#content .row").preloader();}	
	
	jQuery(".list-blog li:last-child").addClass("last"); 
	jQuery(".list li:last-child").addClass("last"); 

	
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
							<h1 class="brand brand_">
								<a href="movie?type=login-1"><img alt="" src="img/MainTopic.JPG">
								</a>
							</h1>
							<a class="btn btn-navbar" data-toggle="collapse"
								data-target=".nav-collapse_">Menu <span class="icon-bar"></span>
							</a>
							<div class="nav-collapse nav-collapse_  collapse">
								<ul class="nav sf-menu">
									<li class="sub-menu active"><a href="Mmovie?type=nowMovie">����</a></li>
									<li><a href="Mmovie?type=soonMovie">��������</a> <!--                       <ul> -->
										<!--                     <li><a href="#">Dolore </a></li> -->
										<!--                     <li><a href="#">Consecte</a></li> -->
										<!--                     <li><a href="#">Conseq</a></li> --> <!--                   </ul> -->
									</li>
									<li><a href="index-2.html">����</a></li>
									<li><a href="index-3.html">����</a></li>
									<li><a href="board?type=list">�Խ���</a></li>
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
		<div id="content">
			<div class="ic"></div>
			<div class="container">
				<div class="row">
					<article class="span8">
						<div class="inner-1">
							<ul class="list-blog">
								<li>
									<h3>${requestScope.now.movieTitle}
										(${requestScope.now.ymd})</h3> <!--             <div class="name-author">by Admin</div> -->
									<div class="clear"></div> <img alt=""
									src="/Movie${requestScope.now.movieImage}" width="250px">
									<h5>���� : ${requestScope.now.director}</h5>
									<h5>�ֿ���� : ${requestScope.now.star}</h5>
									<h5>�帣 : ${requestScope.now.genre1} ${requestScope.now.genre2}</h5>
									<h5>${requestScope.now.story}</h5> 
									
									<table border="1">
										<tr>
											<th width="100" height="30">����</th>
											<th width="400" height="30">���� ��</th>
											<th width="100" height="30">ID</th>
										</tr>
										<c:choose>
											<c:when test="${empty requestScope.now.movieTitle}">
												<tr>
													<td colspan="3"><br>-<br></td>
												</tr>
											</c:when>
											<c:otherwise>
												<c:forEach var="com"
													items="${requestScope.comment.scoreList}">
													<tr>
														<td align=center>${com.score}</td>
														<td align=center>${com.comment}</td>
														<td align=center>${com.userId}</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</table> <%--               <h5>${requestScope.comment.scoreList.comment} </h5> --%>

								</li>



							</ul>
						</div>
					</article>

				</div>
			</div>
		</div>
	</div>


</body>
</html>
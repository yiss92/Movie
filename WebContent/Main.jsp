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
<link rel="stylesheet" href="css/touchTouch.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/kwicks-slider.css" type="text/css"
	media="screen">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300'
	rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/superfish.js"></script>
<script type="text/javascript" src="js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="js/jquery.kwicks-1.5.1.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/touchTouch.jquery.js"></script>
<script type="text/javascript">
	if ($(window).width() > 1024) {
		document.write("<"+"script src='js/jquery.preloader.js'></"+"script>");
	}
</script>
<style type="text/css">
.LogInForm {
	position: relative;
	right: -860px;
	/*  		top: -800px; */
	//
}
</style>

<script>
	$(function() {
		$x = $(window).width();
		if ($x > 1024) {
			jQuery("#content .row").preloader();
		}

		jQuery('.magnifier').touchTouch();
		jQuery('.spinner').animate({
			'opacity' : 0
		}, 1000, 'easeOutCubic', function() {
			jQuery(this).css('display', 'none')
		});

		$('#joinBtn').click(function() {
			document.location.href = "movie?type=joinForm";
			return false;
		})
	});
</script>

<style>
input.tooltip {
	position: absolute;
	visibility: hidden;
	z-index: 1000;
	filter: alpha(opacity = 100);
}
</style>
<script>
	function tooltip_layer_over(id) {
		var obj = document.getElementById(id);
		if (obj !== null) {
			if (window.pageXOffset == undefined) {
				var left_set = parseInt(window.event.clientX
						+ document.body.scrollLeft + 2);
			} else {
				var left_set = parseInt(window.event.clientX
						+ document.body.scrollLeft + 2);
			}
			if (window.pageYOffset == undefined) {
				obj.style.pixelTop = parseInt(window.event.clientY
						+ document.body.scrollTop + 2);
			} else {
				var top_set = parseInt(window.event.clientY
						+ document.body.scrollTop + 2);
			}
			$(obj).offset({
				top : top_set,
				left : left_set
			});
			obj.style.visibility = 'visible';
		}
	}
	function tooltip_layer_out(id) {
		var obj = document.getElementById(id);
		if (obj != null) {
			obj.style.visibility = 'hidden';
		}
	}
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
		<form action="movie" class="LogInForm" method="post">
			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="member_id" size="10"></td>
					<td>PW</td>
					<td><input type="password" name="member_pw" size="10"></td>
					<td><input type="submit" value="로그인"> <input
						type="hidden" name="type" value="login"></td>
					<td><button id="joinBtn">회원가입</button></td>
					<!--  			<td><input type ="submit" value ="회원가입"> -->
					<!--  			<input type="hidden" name="type" value="login" ></td> -->
				</tr>
			</table>
		</form>
		<!-- <a href ="movie?type=joinForm"><button>회원가입</button></a > -->
		<div class="container clearfix">
			<div class="row">
				<div class="span12">
					<div class="navbar navbar_">
						<div class="container">
							<h1 class="brand brand_">
								<a href="movie?type=loginForm"><img alt=""
									src="img/MainTopic.JPG"> </a>
							</h1>
							<a class="btn btn-navbar btn-navbar_" data-toggle="collapse"
								data-target=".nav-collapse_">Menu <span class="icon-bar"></span>
							</a>
							<div class="nav-collapse nav-collapse_  collapse">
								<ul class="nav sf-menu">
									<!--                 <li class="active"><a href="index.html">상영작</a></li> -->
									<li><a href="Mmovie?type=nowMovie">상영작</a></li>
									<li class="sub-menu"><a href="Mmovie?type=soonMovie">개봉예정</a>
										<!--                       <ul> --> <!--                     <li><a href="#">Dolore </a></li> -->
										<!--                     <li><a href="#">Consecte</a></li> -->
										<!--                     <li><a href="#">Conseq</a></li> --> <!--                   </ul> -->
									</li>
									<li><a href="index-2.html">평점</a></li>
									<li><a href="index-3.html">리뷰</a></li>
									<li><a href="index-4.html">게시판</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<div class="bg-content">
		<div class="container">
			<div class="row">
				<div class="span12" align="center">

					<!--============================== slider =================================-->
					<div class="flexslider" style="z-index: 1;">
						<ul class="slides" style="z-index: 1;">
							<!--             <li> <img  src="/Movie${main.movieImage}" alt=""></li> -->
							<c:forEach var="main" items="${requestScope.movie}">
								<li><form action="링크" method="post">
										<input type="image" src="/Movie${main.movieImage}"
											style="width: 450px; Z-index: 1;"
											onMouseOver="tooltip_layer_over('${main.movieTitle}')"
											onMouseMove="tooltip_layer_over('${main.movieTitle}')"
											onMouseOut="tooltip_layer_out('${main.movieTitle}')">
										<input type="text" value="내용내용" id="${main.movieTitle}" class="tooltip">
									</form></li>

							</c:forEach>
						</ul>
					</div>
					<span id="responsiveFlag"></span>
					<div class="block-slogan">
						<h2>Welcome!</h2>
						<!--               <div> -->
						<!--             <p><a href="http://www.oswt.co.uk" target="_blank" class="link-1">Click here</a> for more info about this website created by our special project This is a Bootstrap template that goes with several layout options (for desktop, tablet, smartphone landscape and portrait) to fit all popular screen resolutions. The PSD source files of this template are available for free for the registered members of TemplateMonster.com. Feel free to get them!</p> -->
						<!--           </div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
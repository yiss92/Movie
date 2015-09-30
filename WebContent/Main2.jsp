<%@ page language ="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang= "en">
     <head >
     <title >About </title >
     <meta charset ="utf-8">
     <link rel ="icon" href="img/favicon.ico" type= "image/x-icon">
     <link rel ="shortcut icon" href="img/favicon.ico" type= "image/x-icon" />
     <meta name ="description" content="Your description">
     <meta name ="keywords" content="Your keywords">
     <meta name ="author" content="Your name">
     <link rel ="stylesheet" href="css/bootstrap.css" type= "text/css" media ="screen">
     <link rel ="stylesheet" href="css/responsive.css" type= "text/css" media ="screen">
     <link rel ="stylesheet" href="css/style.css" type= "text/css" media ="screen">
     <link rel ="stylesheet" href="css/touchTouch.css" type= "text/css" media ="screen">
     <link rel ="stylesheet" href="css/kwicks-slider.css" type= "text/css" media ="screen">
     <link href= 'http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel= 'stylesheet' type ='text/css'>
     <script type ="text/javascript" src= "js/jquery.js"></script >
    <script type ="text/javascript" src= "js/superfish.js"></script >
     <script type ="text/javascript" src= "js/jquery.flexslider-min.js"></script >
     <script type ="text/javascript" src= "js/jquery.kwicks-1.5.1.js"></script >
     <script type ="text/javascript" src= "js/jquery.easing.1.3.js"></script >
     <script type ="text/javascript" src= "js/jquery.cookie.js"></script >   
     <script type ="text/javascript" src= "js/touchTouch.jquery.js"></script >
     <script type= "text/javascript">if ($(window).width()>1024){document.write("<"+"script src='js/jquery.preloader.js'></"+ "script>");}      </script >
     <style type ="text/css">
            #inform{
            position: relative;
            right: -1300px;
/*         top: -800px; */
//
     } 
     </style >
     
     <script >
     
           $( function() {
                $x = $(window).width();
                 if ($x > 1024) {
                     jQuery( "#content .row").preloader();
                }

                jQuery( '.magnifier').touchTouch();
                jQuery( '.spinner').animate({
                      'opacity' : 0
                }, 1000, 'easeOutCubic', function() {
                     jQuery( this).css( 'display', 'none')
                });
                
                $( '#joinBtn').click( function(){
                     document.location.href = "movie?type=joinForm";
                      return false;
                })
           });
     </script >

     <!--[if lt IE 8]>
           <div style='text-align:center'><a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/img/upgrade.jpg"border="0"alt=""/></a></div> 
     <![endif]-->
     <!--[if (gt IE 9)|!(IE)]><!-->
     <!--<![endif]-->
     <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <link rel="stylesheet" href="css /docs.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css /ie.css" type="text/css" media="screen">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300' rel='stylesheet ' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400' rel='stylesheet ' type='text/css'>
  <![endif]-->
     </head >

     <body >
    <div class ="spinner"></div>
<!--============================== header =================================-->
<header>
           
<a href ="movie?type=myPage"><button id= "inform">회원정보 </button ></a >
<a href ="movie?type=logout"><button id= "inform">로그아웃 </button ></a >
      <div class ="container clearfix">
    <div class ="row">
          <div class ="span12">
        <div class ="navbar navbar_">
              <div class ="container">
            <h1 class ="brand brand_"><a href= "movie?type=login-1"><img alt ="" src="img/MainTopic.JPG"> </a></h1>
            <a class ="btn btn-navbar btn-navbar_" data-toggle= "collapse" data-target =".nav-collapse_"> Menu <span class= "icon-bar"></span > </a >
            <div class ="nav-collapse nav-collapse_  collapse">
                  <ul class ="nav sf-menu">
                <li ><a href= "Mmovie?type=nowMovie">상영작 </a ></li >
                <li class ="sub-menu"><a href= "Mmovie?type=soonMovie">개봉예정 </a >
<!--                       <ul> -->
<!--                     <li><a href="#">Dolore </a></li> -->
<!--                     <li><a href="#">Consecte</a></li> -->
<!--                     <li><a href="#">Conseq</a></li> -->
<!--                   </ul> -->
                    </li >
                <li ><a href ="index-2.html"> 평점</a></li>
                <li ><a href ="index-3.html"> 리뷰</a></li>
                <li ><a href ="board?type=list"> 게시판</a></li>
              </ul >
                </div >
          </div >
            </div >
      </div >
        </div >
  </div>
    </header >
<div class="bg-content">
      <div class ="container">
    <div class ="row">
          <div class ="span12">

					<!--============================== slider =================================-->
					<div class="flexslider">
						<ul class="slides">
							<!--             <li> <img  src="/Movie${main.movieImage}" alt=""></li> -->
							<c:forEach var="main" items="${requestScope.movie}">
								<li><img src="/Movie${main.movieImage}" alt=""></li>
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
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<%@ page import="com.event.java.model.UserE"%>

<!DOCTYPE html>

<html lang="en">

<head>
<meta charset="utf-8">
<title>${pageTitle}</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- theme meta -->
<meta name="theme-name" content="revolve" />

<!--Favicon-->
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

<!-- THEME CSS
	================================================== -->
<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/template/Resources/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/template/Resources/themify-icons.css"
	rel="stylesheet" type="text/css" />
<!-- Themify -->
<link
	href="${pageContext.request.contextPath}/template/Resources/slick-theme.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/template/Resources/slick.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/template/Resources/owl.carousel.min.css"
	rel="stylesheet" type="text/css" />
<!-- Slick Carousel -->
<link
	href="${pageContext.request.contextPath}/template/Resources/owl.theme.default.min.css"
	rel="stylesheet" type="text/css" />

<link
	href="${pageContext.request.contextPath}/template/Resources/magnific-popup.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/template/Resources/style.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/fontawesome-free-6.3.0-web/css/all.min.css">

<c:url var="slickCssUrl"
	value="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css" />
<link rel="stylesheet" type="text/css" href="${slickCssUrl}" />

<c:url var="jqueryUrl"
	value="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js" />
<script type="text/javascript" src="${jqueryUrl}"></script>

<c:url var="slickJsUrl"
	value="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js" />
<script type="text/javascript" src="${slickJsUrl}"></script>

<style>
.imgslide {
	width: 100%;
	height: 300px;
}
.pagination li a {
	color: #495057;
	background-color: #fff;
	border: 1px solid #dee2e6;
}

.pagination li.active a {
	color: #fff;
	background-color: #007bff;
	border-color: #007bff;
}

.avatar {
	width: 60px;
	height: 60px;
	border-radius: 50%;
	background-size: cover;
	background-position: center;
}

.pagination li a:hover:not(.active) {
	color: #007bff;
	background-color: #e9ecef;
	border-color: #dee2e6;
}
</style>




</head>

<body>

	<header class="header-top bg-grey justify-content-center">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-2 col-md-4 text-center d-none d-lg-block">
					<c:url var="list" value="/flower/list" />
					<a class="navbar-brand " href="${list}"> <img
						src="${pageContext.request.contextPath }/template/images/logo.png"
						alt="" class="img-fluid">
					</a>
				</div>

				<div class="col-lg-8 col-md-12">
					<nav class="navbar navbar-expand-lg navigation-2 navigation">


						<div class="collapse navbar-collapse" id="navbar-collapse"
							style="align-items: center; display: flex;">
							<ul id="menu" class="menu navbar-nav mx-auto">
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle"
									href="${pageContext.request.contextPath }/flower/list"
									id="navbarDropdown" role="button" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false" title="Trang Chủ"><i
										class="fa-solid fa-house" style="font-size: 3em; float: right "></i>
								</a></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle"
									href="${pageContext.request.contextPath }/flower/list"
									id="navbarDropdown" role="button" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false"> Sự Kiện Của Bạn
								</a></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle"
									href="${pageContext.request.contextPath }/flower/list"
									id="navbarDropdown" role="button" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false"> Sự Kiện Bạn
										Tham Gia </a></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle"
									href="${pageContext.request.contextPath }/flower/list"
									id="navbarDropdown" role="button" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false" style=""><i
										class=""
										style="font-size: 3em; float: right"> </i> Tạo Sự Kiện Mới </a></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle "
									href="${pageContext.request.contextPath}/flower/inforshop"
									id="navbarDropdown2" role="button" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false" title=""><i
										class=""
										style="font-size: 3em; float: right"></i> Giới thiệu </a></li>

								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="navbarDropdown3"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"> <i class="fa-solid fa-bars"
										style="font-size: 3em; float: right" title="Chủ Đề"></i>
								</a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown3">
										<a class="dropdown-item" href="post-link.html">Thể Thao </a> <a
											class="dropdown-item" href="post-gallery.html">Văn Nghệ </a>
										<a class="dropdown-item" href="post-image.html">Phúc Lợi </a>
									</div></li>
							</ul>


						</div>
					</nav>
				</div>

				<div class="col-lg-2 col-md-4 col-6">
					<div class="header-socials-2 text-right d-none d-lg-block">
						<ul class="list-inline mb-0">
							<%
							UserE temp = (UserE) session.getAttribute("loginsession");
							String x= temp.getImg();
							if (temp == null) {
							%>
							<li class="list-inline-item "><a
								href="${pageContext.request.contextPath }/user/login"
								class="fa-solid fa-right-to-bracket" style="font-size: 3em" title="Login"> </a><a>Login</a></li>
							<%
							}
							else {
							%>
							<ul
								style="display: flex; justify-content: center; align-items: center;">

								<li><img class="avatar" alt="avatar"
									src="<%=temp.getImg()%>" /></li>
								<li class="list-inline-item"><a
									href="${pageContext.request.contextPath }/user/updateinfor?id=<%=temp.getId()%>"><%=temp.getName()%>
								</a></li>

								<li class="list-inline-item "><a
									class="fas fa-sign-out-alt" style="font-size: 3em"
									href="${pageContext.request.contextPath }/user/logout"> </a></li>
							</ul>


							<%
							}
							%>



						</ul>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!--------------------------------------->


	<div class="slider">
		>
		<div>
			<img class="imgslide"
				src="https://thongbaosukien24h.com/wp-content/uploads/2020/09/to-chuc-su-kien-am-nhac-scaled.jpg">
		</div>
		<div>
			<img class="imgslide"
				src="https://tochucsukien161.com/upload/images/article//dich-vu-to-chuc-event-am-nhac%20(2).jpg">
		</div>
		<div>
			<img class="imgslide"
				src="https://cdnimg.vietnamplus.vn/t1200/Uploaded/zgnokt/2022_03_21/giai-chay.jpg">
		</div>
		<div>
			<img class="imgslide"
				src="https://xdcs.cdnchinhphu.vn/446259493575335936/2022/12/22/chup-anh-phao-hoa-800x450-1671704031655740143071.jpg">
		</div>
		<div>
			<img class="imgslide"
				src="https://sohanews.sohacdn.com/thumb_w/660/160588918557773824/2022/6/20/photo1655661134093-165566113419465954149.jpg">
		</div>

	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('.slider').slick({
				autoplay : true,
				autoplaySpeed : 1000,
				arrows : false,
				dots : false,
				slidesToShow : 3,
				slidesToScroll : 1,
				centerMode : true,
				centerPadding : '0px',
				responsive : [ {
					breakpoint : 992,
					settings : {
						slidesToShow : 2
					}
				}, {
					breakpoint : 768,
					settings : {
						slidesToShow : 1
					}
				} ]
			});
		});
	</script>

	<!--------------------------------------->













	<!-- ------------------------------------- -->




</body>

</html>
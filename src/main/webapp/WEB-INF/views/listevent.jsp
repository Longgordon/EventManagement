<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<%@ page import="com.event.java.model.UserE"%>


<!DOCTYPE html>

<html lang="en">

<head>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/css/toastr.css"
	rel="stylesheet" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/js/toastr.js"></script>



</head>

<body>



	<jsp:include page="user/layoutshare.jsp" />


	<div class="d-flex justify-content-center"
		style="margin-top: 50px; margin-bottom: 50px">
		<form class="form-inline" action="<c:url value='/flower/search'/>"
			style="background-color: #666; padding: 10px; border-radius: 25px;">
			<div class="form-group mx-sm-3 mb-2">
				<label for="keyword" class="sr-only">Search</label> <input
					type="text" class="form-control" name="keyword"
					placeholder="Search">
			</div>
			<button type="submit" class="btn btn-primary mb-2">Search</button>
		</form>
	</div>
	<h1 style="text-align: center;">Sự Kiện Gần Đây</h1>

	<section class="section-padding">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="row">
						<c:forEach items="${events}" var="event">
							<div class="col-lg-3 col-md-6">
								<article class="post-grid mb-5">
									<%-- <c:url var="detailUrl"
										value="/flower/detail?id=${event.event_id}" /> --%>

									<a type="button" class="btn btn-primary mt-3"
										href="${pageContext.request.contextPath }/event/detail?id=${event.event_id}">Xem
										chi tiết</a><a style="color: red; margin-left: 70px;"
										"  class="fa-solid fa-heart">${event.like}</a> <a
										class="post-thumb mb-4 d-block" href="${detailUrl}"> <img
										style="width: 35vw; height: 35vh;"
										src="<c:out  value="${event.img1}" />" alt=""
										class="img-fluid w-100">
										<h4 style="color: red !important">${event.event_start}-
											${event.event_end }</h4> <a>${event.host}</a>
									</a> <span
										class="cat-name text-color font-sm font-extra text-uppercase letter-spacing">Tổ
										Chức</span>
									<h3 class="post-title mt-1">
										<a
											href="${pageContext.request.contextPath }/event/detail?id=${event.event_id}"><c:out
												value="${event.name}" /></a>
									</h3>
									<span class="text-muted letter-spacing text-uppercase font-sm">
										<%-- <fmt:formatNumber value="${event.donate_link}"
											pattern="###,### VNĐ" /> --%>
									</span>
								</article>
							</div>
						</c:forEach>
					</div>
					<div class="row justify-content-center">
						<div class="col-md-12">
							<nav aria-label="Page navigation">
								<ul class="pagination">
									<li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
										<a class="page-link"
										href="?pageNum=${currentPage - 1}&amp;pageSize=${pageSize}"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
											<span class="sr-only">Previous</span>
									</a>
									</li>
									<c:forEach var="page" items="${pages}">
										<li class="page-item ${page == currentPage ? 'active' : ''}">
											<a class="page-link"
											href="?pageNum=${page}&amp;pageSize=${pageSize}">${page}</a>
										</li>
									</c:forEach>
									<li
										class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
										<a class="page-link"
										href="?pageNum=${currentPage + 1}&amp;pageSize=${pageSize}"
										aria-label="Next"> <span aria-hidden="true">&raquo;</span>
											<span class="sr-only">Next</span>
									</a>
									</li>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- ------------------------------------- -->



	<jsp:include page="user/chat.jsp" />
	<jsp:include page="user/footer.jsp" />




	<!-- ------------------------------------- -->




</body>

</html>
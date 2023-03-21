
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page session="true"%>
<%@ page import="com.event.java.model.UserE"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<style>
#content-wrapper {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.column {
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 100%;
	max-width: 1200px;
	padding: 10px;
}

#featured {
	width: 1200px;
	height: 700px;
	object-fit: cover;
	cursor: pointer;
	border: 2px solid black;
}

.thumbnail {
	object-fit: cover;
	max-width: 180px;
	max-height: 100px;
	cursor: pointer;
	opacity: 0.5;
	margin: 5px;
	border: 2px solid black;
}

.thumbnail:hover {
	opacity: 1;
}

.active {
	opacity: 1;
}

#slide-wrapper {
	max-width: 500px;
	display: flex;
	min-height: 100px;
	align-items: center;
}

#slider {
	width: 440px;
	display: flex;
	flex-wrap: nowrap;
	overflow-x: auto;
}

#slider::-webkit-scrollbar {
	width: 8px;
}

#slider::-webkit-scrollbar-track {
	-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
}

#slider::-webkit-scrollbar-thumb {
	background-color: #dede2e;
	outline: 1px solid slategrey;
	border-radius: 100px;
}

#slider::-webkit-scrollbar-thumb:hover {
	background-color: #18b5ce;
}

.arrow {
	width: 30px;
	height: 30px;
	cursor: pointer;
	transition: .3s;
}

.arrow:hover {
	opacity: .5;
	width: 35px;
	height: 35px;
}
</style>
</head>
<body>

	<jsp:include page="user/headerpreminum.jsp" />


	<section class="pt-5 padding-bottom"
		style="margin-top: 0%; text-align: center;">
		<h1 style="text-align: center; color: red;">Tên sự kiện:
			${eventAttr.name}</h1>

		<form modelAttribute="eventAttr"
			style="margin-top: 5%; margin-bottom: 10%">
			<div id="content-wrapper">
				<div class="column">
					<img id=featured src="${eventAttr.img1}">

					<div id="slide-wrapper">

						<i id="slideLeft" class="arrow fa-solid fa-arrow-left"
							style="font-size: 2em"></i>
						<div id="slider">
							<img class="thumbnail active"
								src="<c:out  value="${eventAttr.img1}" />" alt=""
								class="img-fluid w-100"> <img class="thumbnail"
								src="${eventAttr.img2}"> <img class="thumbnail"
								src="${eventAttr.img3}"> <img class="thumbnail	"
								src="${eventAttr.img4}">

						</div>

						<i id="slideRight" class="arrow fa-solid fa-arrow-right"
							style="font-size: 2em"></i>
					</div>
				</div>
			</div>
			<div class="" style="margin-top: 10%">
				<h4 class="text-uppercase letter-spacing mb-4"></h4>
				<h4 style="color: red !important">${eventAttr.event_start}-
					${eventAttr.event_end }</h4>
				<h4>Người tổ chức: ${eventAttr.host}</h4>
				<h3>Mô tả: ${eventAttr.note}</h3>
				<a type="button" class="btn btn-primary mt-3"
					href="${pageContext.request.contextPath }/cart/buy/${eventAttr.event_id}">Tham
					gia sự kiện</a>
			</div>
		</form>
	</section>
	<script type="text/javascript">
		let thumbnails = document.getElementsByClassName('thumbnail')

		let activeImages = document.getElementsByClassName('active')

		for (var i = 0; i < thumbnails.length; i++) {

			thumbnails[i].addEventListener('mouseover', function() {
				console.log(activeImages)

				if (activeImages.length > 0) {
					activeImages[0].classList.remove('active')
				}

				this.classList.add('active')
				document.getElementById('featured').src = this.src
			})
		}

		let buttonRight = document.getElementById('slideRight');
		let buttonLeft = document.getElementById('slideLeft');

		buttonLeft.addEventListener('click', function() {
			document.getElementById('slider').scrollLeft -= 180
		})

		buttonRight.addEventListener('click', function() {
			document.getElementById('slider').scrollLeft += 180
		})
	</script>

	<jsp:include page="user/footer.jsp" />


</body>
</html>
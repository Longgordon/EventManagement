<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
<title>Document</title>
<style>

body {
	color: #333;
	background: linear-gradient(to bottom, #E0EAFC 0%, #CFDEF3 100%);
}

input {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	margin-bottom: 20px;
}

/* CSS cho phần tử textarea */
textarea {
	width: 100%;
	padding: 10px;
	border: 1px solid #dd8c7e;
	border-radius: 5px;
	margin-bottom: 20px;
	height: 100px;
}

/* CSS cho phần tử .container */
.container {
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
}

/* CSS cho phần tử .row */
.row {
	display: flex;
	flex-wrap: wrap;
	margin-bottom: 20px;
	margin: 10px 0;
}

/* CSS cho phần tử .col-md-6 */
.col-md-6 {
	flex-basis: 50%;
	padding-right: 10px;
	padding-left: 10px;
	width: 50%;
	padding: 0 10px;
}

/* CSS cho phần tử label */
label {
	display: block;
	margin-bottom: 5px;
}

/* CSS cho phần tử .custom-file-upload */
.custom-file-upload {
	display: inline-block;
	padding: 6px 12px;
	cursor: pointer;
	border: 1px solid #ccc;
	border-radius: 5px;
	background-color: #eee;
}

/* CSS cho phần tử .file-upload */
.file-upload {
	display: none;
}

* {
	box-sizing: border-box;
	font-family: Arial, Helvetica, sans-serif;
}

.container {
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
	background-color: #f7f7f7;
	border-radius: 10px;
}

h1, h2, h3, h4, h5, h6 {
	font-weight: bold;
	font-size: 2em;
	margin-bottom: 10px;
	color: RGB(146, 168, 209)
}

input[type="text"], input[type="date"], textarea {
	width: 100%;
	padding: 10px;
	border: 2px solid #ccc;
	border-radius: 8px;
	margin-bottom: 10px;
}

input[type="text"]:focus {
	border-color: rgb(245, 151, 151);
	outline: none;
}

textarea:focus {
	border-color: rgb(245, 151, 151);
	outline: none;
}

input[type=""]:focus {
	border-color: rgb(245, 151, 151);
	outline: none;
}

.custom-file-upload {
	display: inline-block;
	padding: 6px 12px;
	cursor: pointer;
	border-radius: 5px;
	background-color: #eee;
}

.custom-file-upload:hover {
	background-color: #ccc;
}

.file-upload {
	display: none;
}

.btn {
	padding: 10px 20px;
	background-color: #D65076;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.btn:hover {
	background-color: #3e8e41;
}
.combobox {
      position: relative;
      display: inline-block;
      width: 200px;
    }
    
    /* Định dạng cho nút mũi tên */
    .combobox select {
      background-color: #FFFFFF;
      border: none;
      padding: 10px;
      font-size: 16px;
      width: 100%;
      height: 100%;
      -webkit-appearance: none;
      -moz-appearance: none;
      appearance: none;
      cursor: pointer;
    }
    
    /* Định dạng cho phần hiển thị kết quả */
    .combobox:after {
      content: "\25BC";
      font-size: 16px;
      color: #666666;
      position: absolute;
      top: 0;
      right: 0;
      padding: 10px;
      pointer-events: none;
    }
</style>

</head>
<body>
<jsp:include page="user/layoutshare.jsp" />

	<div class="container">
		<h1>Tên sự kiện:</h1>
		<input type="text" id="eventName">

		<h2>Ngày sự kiện:</h2>
		<div class="row">
			<div class="col-md-6">
				<label for="startDate">Ngày bắt đầu:</label> <input type="date"
					id="startDate">
			</div>
			<div class="col-md-6">
				<label for="endDate">Ngày kết thúc:</label> <input type="date"
					id="endDate">
			</div>
		</div>

		<h2>Nơi diễn ra:</h2>
		<input type="text" id="eventLocation">

		<h2>người tổ chức:</h2>
		<input type="text" id="eventOrganizer">

		<h2>Mô tả:</h2>
		<textarea id="eventDescription"></textarea>

		<h2>Chủ đề:</h2>
		<div class="combobox">
			<select>
				<option value="Ca Nhạc">Ca Nhạc</option>
				<option value="Thể Thao">Thể Thao</option>
				<option value="Thiện Nguyện">Thiện Nguyện</option>
				<option value="Thời Trang">Thời Trang</option>
				<option value="Ẩm Thực">Ẩm Thực</option>
				<option value="Văn Hóa">Văn Hóa</option>
			</select>
		</div>

		<h2>Hình ảnh:</h2>
		<label for="eventImages" class="custom-file-upload"> <i
			class="fas fa-plus"></i> Hình 1
		</label> <input type="file" id="eventImages" class="file-upload" multiple>
		<label for="eventImages" class="custom-file-upload"> <i
			class="fas fa-plus"></i> Hình 2
		</label> <input type="file" id="eventImages" class="file-upload" multiple>
		<label for="eventImages" class="custom-file-upload"> <i
			class="fas fa-plus"></i> Hình 3
		</label> <input type="file" id="eventImages" class="file-upload" multiple>
		<label for="eventImages" class="custom-file-upload"> <i
			class="fas fa-plus"></i> Hình 4
		</label> <input type="file" id="eventImages" class="file-upload" multiple>
		<br>
		<button type="submit" class="btn btn-primary mt-3"
			onclick="submitForm()">Tạo</button>
	</div>
<jsp:include page="user/footer.jsp" />

</body>
</html>


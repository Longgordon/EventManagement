<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ page import="com.event.java.model.UserE"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${sessionScope.username} ${sessionScope.emailsession}</h1>
	<%
	UserE temp = (UserE) session.getAttribute("loginsession");
	%>
	<h2>
		<h1>
			Hello
			<%=temp.getName()%>!
		</h1>
		<h1>
			Hello
			<%=temp.getEmail()%>!
		</h1>
				<h1>
			Hello
			<%=temp.getType()%>!
		</h1>
		<h1>
			Hello
			<%=temp.getBirth()%>!
		</h1>
		<h1>
			Hello
			<%=temp.getPhonenum()%>!
		</h1>
		
		<a href="${pageContext.request.contextPath }/user/logout">Click me</a>
	</h2>
</body>
</html>

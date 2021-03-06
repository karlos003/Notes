<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="label.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styles/mystyle.css">
<link rel="stylesheet" href="styles/labelStyle.css">
<link rel="shortcut icon" href="<%=basePath%>images/icon.png">
<title>个人信息</title>
</head>
<body>
	<div class="content_1">
		<div class="content_2">
			<h1>头像:</h1>
			<c:if test="${sessionScope.user.user_photo==0}">
				<img src="images/default_photo.jpg" width="300" height="300">
				<form action="userController/updateUserPhotoControl" method="post" enctype="multipart/form-data">
					修改头像：<input type="file" name="user_photo" id="user_photo">
					<input type="submit" value="提交"><br/>
				</form>
			</c:if>
			
			<c:if test="${sessionScope.user.user_photo!=0}">
				<img src="/upload/${sessionScope.user.account}.jpg" width="300" height="300">
				<form action="userController/updateUserPhotoControl" method="post" enctype="multipart/form-data">
					修改头像：<input type="file" name="user_photo" id="user_photo">
					<input type="submit" value="提交"><br/>
				</form>
			</c:if>
			<hr>
			<h1>账号：</h1>
			${sessionScope.user.account }
			<hr>
			<h1>密码</h1>
			<a href="changePassword.jsp"><button>修改密码</button></a>
			<br/>
			<br/>
		</div>
	</div>
</body>
</html>
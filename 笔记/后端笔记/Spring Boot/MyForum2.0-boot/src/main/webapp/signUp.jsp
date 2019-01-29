<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styles/loginSignUpStyle.css">
<link rel="shortcut icon" href="<%=basePath%>images/icon.png">
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
<title>注册</title>
</head>
<body>
	<a href="homePage.jsp" style="color: white;font-size: 20px;font-style: italic;float:right;">返回首页</a><br/>
	<hr>
	<form id="signUpForm" action="userController/signUpControl" style="text-align: center;">
		账号：<input name="account" id="account" required="required" maxlength="12"><br/>
		密码：<input type="password" id="pwd" name="pwd" required="required" maxlength="15"><br/>
		昵称：<input name="name" id="name" required="required" maxlength="10"><br/>
		<input type="submit" value="注册">
		<a href="login.jsp">返回</a>
	</form>
	

</body>
</html>
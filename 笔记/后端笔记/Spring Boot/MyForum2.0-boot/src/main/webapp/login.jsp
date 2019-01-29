<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styles/loginSignUpStyle.css">
<link rel="shortcut icon" href="<%=basePath%>images/icon.png">
<title>登录/注册到罗天齐的教务处</title>
</head>
<body>
	<a href="homePage.jsp" style="color: white;font-size: 20px;font-style: italic;float:right;">返回首页</a><br/>
	<hr>
	<form action="userController/loginControl" onsubmit="return valid()" style="text-align: center;">
		账号：<input name="account" id="account" value="${cookie.account.value}" required="required" maxlength="12"><br/>
		密码：<input type="password" id="upwd" name="upwd" value="${cookie.pwd.value}" required="required" maxlength="15"><br/>
		<input type="submit" value="登录" onclick="check();" style="text-align: center;">
	</form>
	
	<form action="signUp.jsp" style="text-align: center;">
		<input type="submit" value="注册">
	</form>
	
	<script type="text/javascript">
		function valid(){
			var uid = document.getElementById("account").value;
			var upwd = document.getElementById("upwd").value;
			if(account==null||account==""){
				alert("账号不能为空！");
				return false;
			}else if(upwd==null||upwd==""){
				alert("密码不能为空！");
				return false;
			}else{
				return true;
			}
		}
	</script>
</body>
</html>
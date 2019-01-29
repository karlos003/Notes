<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
		<!-- 菜单栏 -->
		<div style="font-size:large;float: right;">
			<c:if test="${sessionScope.user != null}">
					<c:if test="${sessionScope.user.user_photo==0}">
						<a href="<%=basePath%>userInfo.jsp"><img src="<%=basePath%>images/default_photo.jpg" width="35" height="35"></a>
					</c:if>
					<c:if test="${sessionScope.user.user_photo!=0}">
						<a href="<%=basePath%>userInfo.jsp"><img src="/upload/${sessionScope.user.account}.jpg" width="35" height="35"></a>
					</c:if>
					<a href="<%=basePath%>userInfo.jsp">${sessionScope.user.name}</a>，
					<a href="<%=basePath%>userController/logOffControl">注销</a>
			</c:if>
			
			<c:if test="${sessionScope.user == null}">
				<font>
					<a href="<%=basePath%>login.jsp"><img src="<%=basePath%>images/default_photo.jpg" width="35" height="35"></a>
					<a href="<%=basePath%>login.jsp" style="color: blue;font-style: italic;">点击登录</a>
				</font>
			</c:if>
		</div>
		
		<div style="margin-left: 400px">
			<ul id="menu"> 
			<li><img alt="LogoHere..." src="<%=basePath%>images/logo.png"></li>
			<li><a href="<%=basePath%>homePage.jsp" class="last">首页</a></li> 
			<li><a href="<%=basePath%>commentController/queryAllCommentsControl" class="last">留言板</a></li> 
			<li><a href="<%=basePath%>forum.jsp" class="last">论坛</a></li>
			</ul>
		</div>
		 
		<br/><br/>
		<hr>
		<div id="footer">CopyRight@copy2012 </div>
	<!-- 菜单栏结束 -->
</body>	
</html>
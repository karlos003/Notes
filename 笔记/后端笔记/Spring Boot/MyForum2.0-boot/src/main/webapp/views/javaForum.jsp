<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/label.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=basePath%>styles/mystyle.css">
<link rel="stylesheet" href="<%=basePath%>styles/labelStyle.css">
<link rel="shortcut icon" href="<%=basePath%>images/icon.png">
<title>JAVA论坛</title>
</head>
<body>

	
	<div class="content_1">
		<div class="content_2">
		
			<a href="<%=basePath%>views/postJava.jsp"><button>发帖</button></a>
			
			<hr>
			
			<c:forEach items="${requestScope.posts }" var="post">
				<a href="" style="font-size: 23px;color:black;text-decoration:none;">${post.post_title }</a><br/>
				<p>
				<img alt=" " src="/upload/${post.user_account}.jpg" width="30" height="30">${post.user_name }，${post.post_time }
				，阅读量：，喜欢：，收藏：
				</p>
				<hr>
			</c:forEach>
		</div>
	</div>
	
</body>
</html>
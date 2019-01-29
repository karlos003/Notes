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
<link rel="stylesheet" href="<%=basePath %>styles/mystyle.css">
<link rel="shortcut icon" href="<%=basePath%>images/icon.png">
<style type="text/css">
a {
	color: white;
}
#requestResult{
	color: white;text-align: center;margin-top: 100px;
}
</style>
<title>结果</title>
</head>
<body>
	<div id="requestResult">
		<c:if test="${requestScope.resultType==1}">
			<h1>登录成功！欢迎</h1>
			<a href="<%=basePath %>homePage.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==2}">
			<h1>注册成功！欢迎</h1><br/>
			<a href="<%=basePath%>homePage.jsp">首页</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==3}">
			<h1>注册失败！</h1><br/>
			<p style="font-size: x-large;">账号已存在！请更换<br/>
			<br/>
			<a href="<%=basePath%>signUp.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==4}">
			<h1>注册失败！</h1>
			<p>属性不能为空！请重试<br/>
			<br/>
			<a href="<%=basePath%>signUp.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==5}">
			<h1>留言发表成功！</h1>
			<a href="queryAllCommentsControl">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==6}">
			<h1>发表失败！请输入内容</h1>
			<a href="queryAllCommentsControl">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==7}">
			<h1>留言删除成功！</h1>
			<a href="queryAllCommentsControl">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==8}">
			<h1>文件上传失败！请上传jpg格式图片</h1>
		</c:if>
		
		<c:if test="${requestScope.resultType==9}">
			<h1>帖子发表成功！</h1>
			<a href="<%=basePath %>postController/queryAllJavaPostsControl">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==10}">
			<h1>登录失败</h1>
			<p>用户名或密码错误！<br/>
			<br/>
			<a href="<%=basePath %>login.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==11}">
			<h1>上传成功！</h1>
			<a href="<%=basePath%>userInfo.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==12}">
			<h1>上传失败！请上传.jpg.png.jpeg格式的图片</h1>
			<a href="<%=basePath%>userInfo.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==13}">
			<h1>修改失败！原密码错误！</h1>
			<a href="<%=basePath%>changePassword.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==14}">
			<h1>修改成功！</h1>
			<a href="<%=basePath%>userInfo.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==15}">
			<h1>请输入标题！</h1>
			<a href="<%=basePath%>views/JSP_forumPage/write/postJava.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==16}">
			<h1>请选择图片</h1>
			<a href="<%=basePath%>userInfo.jsp">返回</a>
		</c:if>
		
		<c:if test="${requestScope.resultType==17}">
			<h1>不能为空！</h1>
			<a href="<%=basePath%>changePassword.jsp">返回</a>
		</c:if>
	</div>
	
</body>
</html>
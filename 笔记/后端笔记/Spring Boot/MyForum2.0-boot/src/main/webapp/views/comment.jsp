<%@page import="org.ltq.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/label.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
User user = (User)session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=basePath%>styles/mystyle.css">
<link rel="stylesheet" href="<%=basePath%>styles/labelStyle.css">
<link rel="shortcut icon" href="<%=basePath%>images/icon.png">
<script type="text/javascript">
	function valid(){
		var content = document.getElementById("content").value;
		if(<%=user%>==null){
			alert("登录后可以留言！");
			return false;	
		}else if(content==null||content==""||(content.length>0 && content.trim().length == 0)){
			alert("请输入评论");
			return false;
		}else if(content.length>400){
			alert("请输入少于400个字！");
			return false;	
		}
		return true;
	}
</script>
<title>留言板</title>
</head>
<body>
	
	<div class="content_1">
		<div class="content_2">
			<!-- 评论输入栏 -->
			<form action="<%=basePath %>commentController/addCommentControl" onsubmit="return valid()" enctype="multipart/form-data" method="post">
				<textarea name="content" id="content" rows="8" cols="80"></textarea><br/>
				<input type="submit" value="发表留言">
				选择图片：<input type="file" name="comPic">
			</form>
			
			<hr>
			
			<!-- 循环打印所有评论 -->
			<c:forEach items="${requestScope.comments}" var="comment">
			
				<p style="color: blue;">
					<img alt=" " src="/upload/${comment.account}.jpg" width="30" height="30">
					${comment.uname}，${comment.time}
					<c:if test="${sessionScope.user.account == comment.account}">
						，<a href="<%=basePath %>commentController/deleteCommentControl?commentid=${comment.commentid}">删除</a>
					</c:if>
				</p>
				
				<!-- 评论正文内容 -->
				<p style="font-size: 20px;font-weight: bold;">${comment.content}</p>
				
				<c:if test="${comment.image!=0}">
					<!-- 评论图片 -->
					<a href="/commentPic/${comment.commentid}.jpg" target="_blank"><img src="/commentPic/${comment.commentid}.jpg" onload="AutoResizeImage(300,400,this);" alt="评论图片"></a>
					<br/><br/>
				</c:if>
				
				<br/><br/>
				<hr>
				
			</c:forEach>
		</div>
	</div>
<!-- 图片大小控制 -->
<script type="text/javascript">
function AutoResizeImage(maxWidth,maxHeight,objImg){
	var img= new Image();
	img.src=objImg.src;//定义一个图片对象
	var hRatio;
	var wRatio;
	var Ratio=1;
	var w=img.width;//图片的宽
	var h=img.height;//图片的长
	wRatio=maxWidth/w;//宽压缩的比例
	hRatio=maxHeight/h;//长压缩的比例
	if(wRatio==0&&hRatio==0){
		Ratio=1;//如果压缩最大值都为零，按原图比例压缩
	}else if(wRatio==0){
		if(hRatio<1)Ratio=hRatio;//如果宽为零，长的比例小于一的情况按长比例压缩
	}else if(hRatio==0){
		if(wRatio<1)Ratio=wRatio;//如果长的压缩最大值为零，宽的比例小于一的情况按宽压缩
	}else if(wRatio<1&&hRatio<1){
		Ratio=(wRatio<hRatio?wRatio:hRatio);//如果长宽最大压缩值都不为零，取比例的最小值
	}
	if(Ratio<1){
		w=w*Ratio;
		h=h*Ratio;
	}
	//赋值
	objImg.height=h;
	objImg.width=w;
}
</script>
</body>
</html>
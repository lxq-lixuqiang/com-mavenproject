<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_自动跳转中...</title>
		<%@ include file="common/commonLink.jsp" %>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/pages/css/jumpPage.css">
	</head>
	<body>
		<div class="jumpPage">
			<div><img src="${pageContext.request.contextPath}/assets/pages/img/common/TiShi.png"></div>
			<div>
				<p>对不起！想要访问其他页面请先<span id="tiShiClass">登录</span></p>
				<p><span id="sClass">3</span>秒后，自动跳转登录页面，请稍候......</p>
				<p><a id="toHome" href="home">[点击这里跳转到首页]</a></p>
				<p><a id="toLogin" href="login">如果你的浏览器没有自动跳转，请点击此链接</a></p>
			</div>
		</div>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/jumpPage.js"></script>
	</body>
</html>
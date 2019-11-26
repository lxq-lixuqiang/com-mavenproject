<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_宅动漫</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonFootLink.jsp" %>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
		<link href="assets/pages/css/animationInfoVisit.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
			</div>
			<div id="log"><div></div></div>
			
			<div class="section">
				<div class="animationInfo">
					<div>
						<img src="assets/pages/img/animationInfoImg/${animationInfo.animationPicture}">
					</div>
					<div>
						<h3>${animationInfo.animationName}</h3>
						<p>日期：<fmt:formatDate value="${animationInfo.animationDate}" pattern="yyyy年MM月" /></p>
						<p>季度：${animationInfo.animationQuarter}季</p>
						<p>标签：${animationInfo.animationType}</p>
						<p>状态：${animationInfo.classificationId.id == 8?'已完结':'连载中'}</p>
						<p>简介：${animationInfo.animationContent}</p>
					</div>
				</div>
				<div class="animation">
					<p><span>在线观看</span></p>
					<div>
						<ul>
							<c:forEach items="${requestScope.animations}" var="animation">
								<li><a target="_blank" href="animationVisit?animationId=${animation.id}">${animation.setName}</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
		
		<%@ include file="common/foot.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	</body>
</html>
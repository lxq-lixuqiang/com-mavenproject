<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_宅视频</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonFootLink.jsp" %>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
		<link href="assets/pages/css/homeNavigationBarVisit.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
			</div>
			<div id="log">
				<div>
					<h1>宅视频</h1>
				</div>
			</div>
			<div class="section">
				<h2>${requestScope.homeNavigationBar.title}</h2>
				<div class="videoClass">
					<c:choose>
						<c:when test="${requestScope.homeNavigationBar.homeNavigationBar != null && fn:length(requestScope.homeNavigationBar.homeNavigationBar)>0}">
							<video width="100%" height="550" controls="controls">
								<source src="assets/pages/video/animation/${requestScope.homeNavigationBar.homeNavigationBar}" type="video/mp4" />
								<source src="assets/pages/video/animation/${requestScope.homeNavigationBar.homeNavigationBar}" type="video/ogg" />
								<source src="assets/pages/video/animation/${requestScope.homeNavigationBar.homeNavigationBar}" type="video/webm" />
							</video>
						</c:when>
						<c:when test="${requestScope.homeNavigationBar.homeNavigationBarPath != null && fn:length(requestScope.homeNavigationBar.homeNavigationBarPath)>0}">
							<embed src="${requestScope.homeNavigationBar.homeNavigationBarPath}" width="100%" height="550">
						</c:when>
						<c:otherwise>
							<div style="width:100%;height:550px;text-align: center;padding-top: 200px;">
								<h1 style="color:#FE7322;">暂无资源</h1>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<%@ include file="common/foot.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	</body>
</html>
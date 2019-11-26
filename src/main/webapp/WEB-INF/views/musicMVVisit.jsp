<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_宅视频</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonFootLink.jsp" %>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
		<link href="assets/pages/css/musicMVVisit.css" type="text/css" rel="stylesheet">
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
				<h2>《${requestScope.musicMV.themeName}》MV</h2>
				<div class="videoClass">
					<video width="100%" height="550" controls="controls">
						<source src="assets/pages/video/musicMV/${requestScope.musicMV.mv}" type="video/mp4" />
						<source src="assets/pages/video/musicMV/${requestScope.musicMV.mv}" type="video/ogg" />
						<source src="assets/pages/video/musicMV/${requestScope.musicMV.mv}" type="video/webm" />
					</video>
				</div>
			</div>
		</div>
		<%@ include file="common/foot.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	</body>
</html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_宅游戏</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonFootLink.jsp" %>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
		<link href="assets/pages/css/gameVisit.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
			</div>
			<div id="log">
				<div>
					<h1>宅游戏</h1>
				</div>
			</div>
			<div class="section">
				<div class="sectionInfo">
					<div class="infoLeft">
						<div><img src="assets/pages/img/gameTheme/${requestScope.games.picture}"/></div>
						<div>
							<c:choose>
								<c:when test="${requestScope.games.game != null && fn:length(requestScope.games.game)>0}">
									<a class="downloadClass" href="downLoadGame?game=${requestScope.games.game}">下载游戏</a>
								</c:when>
								<c:when test="${requestScope.games.gamePath != null && fn:length(requestScope.games.gamePath)>0}">
									<a class="downloadClass" href="${requestScope.games.gamePath}">下载游戏</a>
								</c:when>
								<c:otherwise>
									<a class="noDownloadClass" href="javascript:;">暂无资源</a>
								</c:otherwise>
							</c:choose>
							
						</div>
					</div>
					<div class="infoCenter">
						<p><span>${requestScope.games.platformId.platformName}</span></p>
						<h1>${requestScope.games.name}</h1>
						<p><label><fmt:formatDate value="${requestScope.games.date}" pattern="yyyy年MM月dd日" /> - ${requestScope.games.platformId.platformName}上市</label></p>
						<p><em>游戏类型：${requestScope.games.typeId.typeName}</em><em>游戏语言：${requestScope.games.language}</em></p>
						<p><em>发行公司：${requestScope.games.issuer}</em></p>
						<h4><span>游戏简介</span></h4>
						<p class="contextClass">
							${requestScope.games.content}
						</p>
					</div>
					<div class="infoRight">
						<h4>安装说明：</h4>
						<p>${requestScope.games.gameExplain}</p>
					</div>
				</div>
			</div>
			<div class="sectionImg">
				<div>
					<div class="sectionImgLeft">
						<c:if test="${requestScope.gameWallpapers[0].wallpaper != null}" var="notNull">
							<img id="imgClass" src="assets/pages/img/gameImg/${requestScope.gameWallpapers[0].wallpaper}" />
						</c:if>
					</div>
					<div class="sectionImgRight">
						<div>
							<p><span><span></span>游戏图片</span></p>
							<div id="Img">
								<c:if test="${requestScope.gameWallpapers[0].wallpaper != null}" var="notNull">
									<c:forEach items="${requestScope.gameWallpapers}" var="gameWallpaper" varStatus="status">
										<img ${status.index == 0?'class="clickClass"':''} src="assets/pages/img/gameImg/${gameWallpaper.wallpaper}" />
									</c:forEach>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="common/foot.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/pages/scripts/gameView.js"></script>
	</body>
</html>
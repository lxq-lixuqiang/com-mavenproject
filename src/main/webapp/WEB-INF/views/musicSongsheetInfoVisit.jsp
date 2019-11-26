<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_宅音乐</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonFootLink.jsp" %>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
		<link href="assets/global/plugins/bootstrap-vpplayer/css/vpplayer.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/css/jquery-spinkit.min.css" />
		<link href="assets/pages/css/musicSongsheetInfoVisit.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
			</div>
			<div id="log"><div></div></div>
			
			<div class="section">
				<div class="musicSingerClass">
					<div>
						<img src="assets/pages/img/musicSongsheetImg/${musicSongsheet.picture}" />
					</div>
					<div>
						<h4>&#60;${musicSongsheet.name}&#62; - 歌曲列表</h4>
						<p>日期：<fmt:formatDate value="${musicSongsheet.date}" pattern="yyyy年MM月dd日" /></p>
					</div>
				</div>
				<div class="musicClass">
					<div id="musicTable">
						<div class="fixdBorder">
						<table>
							<thead>
								<tr>
									<th></th>
									<th>序号</th>
									<th>歌曲</th>
									<th>播放</th>
									<th>下载</th>
									<th>日期</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${requestScope.pageInfo.total >0}" var="notNullMusic">
									<c:forEach items="${requestScope.pageInfo.list}" var="music" varStatus="status">
										<tr>
											<td data-musicid="${music.id}" class="playLoad"></td>
											<td>${status.index+1 <10?'0':''}${status.index+1}</td>
											<td>${music.musicSinger.name} - ${music.songName}</td>
											<td>
												<span class="playMusic" data-src="assets/pages/audio/music/${music.music}"></span>
											</td>
											<td>
												<a class="downloadMusic" href="downloadMusic?songName=${music.music}"></a>
											</td>
											<td><fmt:formatDate value="${music.date}" pattern="yyyy-MM-dd" /></td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${not notNullMusic}">
									<tr class="nullClass">
										<td class="nullImg" colspan="5"><img src="assets/pages/img/common/Imageddd.png" /></td>
									</tr>
								</c:if>
							</tbody>
						</table>
						</div>
					</div>
					<script>
						if(document.getElementById("musicTable").height <522){
							document.getElementById("musicTable").style.height="522px";
						}
					</script>
				</div>
			</div>	
		</div>
		
		<div id="musicPlay">
			<div><span id="SingerName"></span></div>
			<div id="player"></div>
		</div>
		
		<%@ include file="common/foot.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-vpplayer/vpplayer.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/moment-2.22.2-dist/moment.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/handlebars-v4.0.11-dist/handlebars-v4.0.11.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/handlebars-v4.0.11-dist/handlebars-ext.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/jquery-spinkit.min.js"></script>
		<script type="text/javascript" src="assets/pages/scripts/musicSongsheetInfoVisit.js"></script>
	</body>
</html>
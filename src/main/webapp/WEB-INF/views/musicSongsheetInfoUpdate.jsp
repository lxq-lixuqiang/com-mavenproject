<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>zhai_音乐数据-修改歌单歌曲</title>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<%@ include file="common/dataManagement-link.jsp" %>
		<link href="assets/global/plugins/notice-dist/css/noticejs.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/animate.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/Lobibox.min.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/LobiboxClass.css" rel="stylesheet">
		<link href="assets/global/plugins/radiobutton-Class/style.css" rel="stylesheet">
		<link href="assets/global/plugins/jquery-spinkit-1.2.5-dist/css/jquery-spinkit.min.css" rel="stylesheet">
		<link href="assets/pages/css/musicSongsheetInfoUpdate.css" rel="stylesheet">
	</head>
	<body>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
				<%@ include file="common/userImg.jsp" %>
			</div>
			<div class="section">
				<div class="section-top">
					<%@ include file="common/section-top.jsp" %>
					<script>
						document.getElementById("dataManagement").setAttribute("class","click");
					</script>
				</div>
				<div class="section-botton">
					<div class="section-botton-left">
						<%@ include file="common/dataManagement-leftMenu.jsp" %>
						<script> 
							$(".sortable-accordion div:nth-of-type(6)").attr("id","showDiv");
							$('.sortable-accordion div:not(#showDiv)').slideToggle(1);
						</script>
					</div>
					<div class="section-botton-right">
						<div id="currentLocation">
							<p><a href="dataManagement">音乐数据</a><span>&#155;</span><a href="musicSongsheet">音乐歌单</a><span>&#155;</span><a href="musicSongsheetInfo">查看歌曲</a><span>&#155;</span>修改歌单歌曲</p>
						</div>
						<input type="hidden" value="${requestScope.msg}" id="msg" />
						<input type="hidden" value="${requestScope.musicIds}" id="musicIds"/>
						
						<div class="controllerSize">
							<div class="heightFixed">
								<p class="findClass">
									<input type="text" autocomplete="off" id="name" value="${requestScope.musicSongsheetInfoUpdate_name}" placeholder="搜索歌手或歌名" maxlength="10" /><img data-pagesize="${requestScope.pageInfo.pageSize}" id="select" alt="搜索" src="${pageContext.request.contextPath}/assets/pages/img/common/ImageSelect01.png" />
								</p>
								<div id="musicInfo">
									<c:if test="${requestScope.pageInfo.total >0}" var="notNull">
										<table>
											<tr>
												<th class="text-center">选择</th>
												<th class="text-center">歌手</th>
												<th class="text-center">歌名</th>
											</tr>
											<c:forEach var="music" items="${requestScope.pageInfo.list}">
												<tr>
													<td>
														<input data-musicid="${music.id}" class="blue" type="checkbox">
													</td>
													<td>${music.musicSinger.name}</td>
													<td>${music.songName}</td>
												</tr>
											</c:forEach>
										</table>
									</c:if>
									<c:if test="${not notNull}">
										<div id="nullImgDiv">
											<img alt="空" id="nullImg" src="${pageContext.request.contextPath}/assets/pages/img/common/Imageddd.png"/>
										</div>
									</c:if>
								</div>
							</div>
							
							<div class="text-right buttonMarginSize">
								<button id="musicSongsheetInfoUpdate" data-songsheetid="${sessionScope.musicSongsheetInfoUpdate_songsheetId}" class="btn btn-info btn-sm">
									<span class="glyphicon glyphicon-pencil"></span> 修改完成
								</button>
								<a href="musicSongsheetInfo" class="btn btn-default btn-sm">
									<span class="glyphicon glyphicon-chevron-left"></span> 返 回
								</a>
							</div>
							
							<c:if test="${notNull}">
								<div id="pageSizeAndpageNum">
									<ul>
										<c:if test="${!requestScope.pageInfo.isFirstPage}">
											<li data-pagesize="${requestScope.pageInfo.pageSize}" data-pagenum="1" class="ulNotClick">&#171;</li>
											<li data-pagesize="${requestScope.pageInfo.pageSize}" data-pagenum="${requestScope.pageInfo.prePage}" class="ulNotClick">&#139;</li>
										</c:if>
										<c:forEach var="pagingButton" items="${requestScope.pageInfo.navigatepageNums}">
											<li data-pagesize="${requestScope.pageInfo.pageSize}" data-pagenum="${pagingButton}" class="${pagingButton==requestScope.pageInfo.pageNum?'ulClick':'ulNotClick'}">${pagingButton}</li>
										</c:forEach>
										<c:if test="${!requestScope.pageInfo.isLastPage}">
											<li data-pagesize="${requestScope.pageInfo.pageSize}" data-pagenum="${requestScope.pageInfo.nextPage}" class="ulNotClick">&#155;</li>
											<li data-pagesize="${requestScope.pageInfo.pageSize}" data-pagenum="${requestScope.pageInfo.pages}" class="ulNotClick">&#187;</li>
										</c:if>
									</ul>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<script id="musicInfoTemplate" type="text/template">
			{{#if total}}
				<table>
					<tr>
						<th class="text-center">选择</th>
						<th class="text-center">歌手</th>
						<th class="text-center">歌名</th>
					</tr>
					{{#each list}}
						<tr>
							<td>
								<input data-musicid="{{id}}" class="blue" type="checkbox">
							</td>
							<td>{{musicSinger.name}}</td>
							<td>{{songName}}</td>
						</tr>
					{{/each}}
				</table>
			{{else}}
				<div id="nullImgDiv">
					<img alt="空" id="nullImg" src="${pageContext.request.contextPath}/assets/pages/img/common/Imageddd.png"/>
				</div>
			{{/if}}
		</script>
		<script id="musicPagingTemplate" type="text/template">
			{{#if total}}
				<ul>
					{{#if isFirstPage}}
					{{else}}
						<li data-pagesize={{pageSize}} data-pagenum="1" class="ulNotClick">&#171;</li>
						<li data-pagesize={{pageSize}} data-pagenum="{{prePage}}" class="ulNotClick">&#139;</li>
					{{/if}}

					{{#each navigatepageNums}} 
						<li data-pagesize={{../pageSize}} data-pagenum="{{this}}" {{#eq this ../pageNum}}class="ulClick"{{/eq}} {{#neq this ../pageNum}}class="ulNotClick"{{/neq}} >{{this}}</li>
					{{/each}}

					{{#if isLastPage}}
					{{else}}
						<li data-pagesize={{pageSize}} data-pagenum="{{nextPage}}" class="ulNotClick">&#155;</li>
						<li data-pagesize={{pageSize}} data-pagenum="{{pages}}" class="ulNotClick">&#187;</li>
					{{/if}}
				</ul>
			{{/if}}
		</script>
		
		<script src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<%@ include file="common/dataManagement-script.jsp" %>
		<script src="assets/global/plugins/notice-dist/js/notice.js"></script>
		<script src="assets/global/plugins/lobibox-dist/js/lobibox.min.js"></script>
		<script src="assets/global/plugins/jquery-cookie-1.4.1-dist/jquery.cookie.min.js"></script>
		<script src="assets/global/plugins/moment-2.22.2-dist/moment.min.js"></script>
		<script src="assets/global/plugins/handlebars-v4.0.11-dist/handlebars-v4.0.11.min.js"></script>
		<script src="assets/global/plugins/handlebars-v4.0.11-dist/handlebars-ext.js"></script>
		<script src="assets/global/plugins/jquery-spinkit-1.2.5-dist/jquery-spinkit.min.js"></script>
		<script src="assets/pages/scripts/musicSongsheetInfoUpdate.js"></script>
	</body>
</html>

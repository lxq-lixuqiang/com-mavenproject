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
		<link href="assets/pages/css/musicSingerVisit.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
			</div>
			<div id="log"><div></div></div>
			
			<div class="section">
				<div class="musicSingerClass">
					<input type="hidden" id="singerId" value="${musicSinger.id}"/>
					<div>
						<img src="assets/pages/img/musicSingerImg/${musicSinger.singerPicture}" />
					</div>
					<div>
						<h3>${musicSinger.name}</h3>
						<p>${musicSinger.synopsis}</p>
					</div>
				</div>
				<div>
					<ul class="musicUlClass">
						<li data-info="musicClass" style="color:#9DCEF4;" class="clickLi">歌曲</li>
						<li data-info="musicMVClass">mv</li>
					</ul>
					
					<div class="musicClass">
						<c:if test="${requestScope.pageInfoMusics.total >0}" var="notNullMusic">
							<ul>
								<c:forEach items="${requestScope.pageInfoMusics.list}" var="music" varStatus="status">
									<li>
										<span data-musicid="${music.id}" class="playLoad"></span>
										<span>${music.songName}</span>
										<span class="playMusic" data-src="assets/pages/audio/music/${music.music}"></span>
										<a class="downloadMusic" href="downloadMusic?songName=${music.music}"></a>
										<span><fmt:formatDate value="${music.date}" pattern="yyyy-MM-dd" /></span>
									</li>
								</c:forEach>
							</ul>
							<p>
								<c:if test="${requestScope.pageInfoMusics.isFirstPage ==false}">
									<label data-musicpagesize="${requestScope.pageInfoMusics.pageSize}" data-musicpagenum="${requestScope.pageInfoMusics.prePage}">&#139;</label>
								</c:if>
								${requestScope.pageInfoMusics.pageNum}/${requestScope.pageInfoMusics.pages}
								<c:if test="${requestScope.pageInfoMusics.isLastPage == false}">
								<label data-musicpagesize="${requestScope.pageInfoMusics.pageSize}" data-musicpagenum="${requestScope.pageInfoMusics.nextPage}">&#155;</label>
								</c:if>
							</p>
						</c:if>
						<c:if test="${not notNullMusic}">
							<div>
								<img src="assets/pages/img/common/Imageddd.png" />
							</div>
						</c:if>
					</div>
					
					<div class="musicMVClass music03">
						<c:if test="${requestScope.pageInfoMusicMVs.total>0}" var="notNullMV">
							<ul>
								<c:forEach items="${requestScope.pageInfoMusicMVs.list}" var="musicMV">
									<li>
										<a target="_blank" href="musicMVVisit?id=${musicMV.id}">
											<span>
												<img src="assets/pages/img/musicMVImg/${musicMV.themePicture}"/>
												<label class="playMv"></label>
											</span>
											<label>${musicMV.themeName}</label>
										</a>
									</li>
								</c:forEach>
							</ul>
							<p>
								<c:if test="${requestScope.pageInfoMusicMVs.isFirstPage == false}">
									<label data-musicmvpagesize="${requestScope.pageInfoMusicMVs.pageSize}" data-musicmvpagenum="${requestScope.pageInfoMusicMVs.prePage}">&#139;</label>
								</c:if>
								${requestScope.pageInfoMusicMVs.pageNum}/${requestScope.pageInfoMusicMVs.pages}
								<c:if test="${requestScope.pageInfoMusicMVs.isLastPage == false}">
									<label data-musicmvpagesize="${requestScope.pageInfoMusicMVs.pageSize}" data-musicmvpagenum="${requestScope.pageInfoMusicMVs.nextPage}">&#155;</label>
								</c:if>
							</p>
						</c:if>
						<c:if test="${not notNullMV}">
							<div>
								<img src="assets/pages/img/common/Imageddd.png" />
							</div>
						</c:if>
					</div>
					
				</div>
			</div>	
		</div>
		
		<div id="musicPlay">
			<div><span id="SingerName"></span></div>
			<div id="player"></div>
		</div>
		
		<%@ include file="common/foot.jsp" %>
		
		<script type="text/template" id="musicTemplate">
			{{#if total}}
				<ul>
					{{#each list}}
						<li data-src="assets/pages/audio/music/{{music}}">
							<span data-musicid="{{id}}" class="playLoad"></span>
							<span>{{songName}}</span>
							<span class="playMusic" data-src="assets/pages/audio/music/{{music}}"></span>
							<a class="downloadMusic" href="downloadMusic?songName={{music}}"></a>
							<span>{{date}}</span>
						</li>
					{{/each}}
				</ul>
				<p>
					{{#if isFirstPag}}
					{{else}}
						<label data-musicpagesize="{{pageSize}}" data-musicpagenum="{{prePage}}">&#139;</label>
					{{/if}}
					{{pageNum}}/{{pages}}
					{{#if isLastPage}}
					{{else}}
						<label data-musicpagesize="{{pageSize}}" data-musicpagenum="{{nextPage}}">&#155;</label>
					{{/if}}
				</p>
			{{else}}
				<div>
					<img src="assets/pages/img/common/Imageddd.png" />
				</div>
			{{/if}}		
		</script>
		
		<script type="text/template" id="musicMVTemplate">
			{{#if total}}
				<ul>
					{{#each list}}
						<li>
							<a target="_blank" href="musicMVVisit?id={{id}}">
								<span><img src="assets/pages/img/musicMVImg/{{themePicture}}"/></span>
								<label>{{themeName}}</label>
							</a>
						</li>
					{{/each}}
				</ul>
				<p>
					{{#if isFirstPage}}
					{{else}}
						<label data-musicmvpagesize="{{pageSize}}" data-musicmvpagenum="{{prePage}}">&#139;</label>
					{{/if}}
					{{pageNum}}/{{pages}}
					{{#if isLastPage}}
					{{else}}
						<label data-musicmvpagesize="{{pageSize}}" data-musicmvpagenum="{{nextPage}}">&#155;</label>
					{{/if}}
				</p>
			{{else}}
				<div>
					<img src="assets/pages/img/common/Imageddd.png" />
				</div>
			{{/if}}		
		</script>
		
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-vpplayer/vpplayer.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/moment-2.22.2-dist/moment.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/handlebars-v4.0.11-dist/handlebars-v4.0.11.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/handlebars-v4.0.11-dist/handlebars-ext.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/jquery-spinkit.min.js"></script>
		<script type="text/javascript" src="assets/pages/scripts/musicSingerVisit.js"></script>
	</body>
</html>
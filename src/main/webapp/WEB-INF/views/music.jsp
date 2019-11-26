<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_音乐</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonFootLink.jsp" %>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/Jquery- wheelChartPlug-in/css/style.css" />
		<link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-vpplayer/css/vpplayer.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/css/jquery-spinkit.min.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/music.css" />
	</head>
	<body>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
				<div class="logmusic">
					<div class="main_banner">
					  <div class="main_banner_wrap">
					    <div class="main_banner_box" id="m_box">
					      <a href="javascript:void(0)" class="banner_btn js_pre">
					        <span class="banner_btn_arrow"><i></i></span>
					      </a>
					      <a href="javascript:void(0)" class="banner_btn btn_next js_next">
					        <span class="banner_btn_arrow"><i></i></span>
					      </a>
					      <ul>
					      	<c:forEach items="${requestScope.topMusicMVs}" var="topMusicMV" varStatus="status">
						        <li id="imgCard${status.index}">
						          <a target="_blank" href="musicMVVisit?id=${topMusicMV.id}"><span ${status.index ==0?'style="opacity:0;"':'style="opacity:0.4;"'}></span></a>      
						          <img src="assets/pages/img/musicMVImg/${topMusicMV.themePicture}" alt="">
						          <p ${status.index ==0?'style="bottom:0"':''} >《${topMusicMV.themeName}》MV</p>
						        </li> 
					        </c:forEach>
					      </ul>
					      <!--火狐倒影图层-->
					      <p id="rflt"></p>
					      <!--火狐倒影图层-->
					    </div>
					    <!--序列号按钮-->
					    <div class="btn_list">
					      <span class="curr"></span><span></span><span></span><span></span><span></span>        
					    </div>
					  </div>
					</div>
				</div>
			</div>
			<div class="section">
				<div class="musicSongsheetClass">
					<div class="musicSongsheetClassLeft">
						<p><span>精选</span>歌单<a class="selectMusicSongsheetSeach" href="musicSongsheetSeach">更多></a></p>
						<dl>
							<c:forEach items="${requestScope.topMusicSongsheets}" varStatus="status" var="MusicSongsheet">
								<c:if test="${status.index == 0}" var="isFirst">
									<dt>
										<a target="_blank" href="musicSongsheetInfoVisit?musicSongsheetId=${MusicSongsheet.id}">
											<img src="assets/pages/img/musicSongsheetImg/${MusicSongsheet.picture}"/>
											<label>${MusicSongsheet.name}</label>
											<em></em>
										</a>
									</dt>
								</c:if>
								<c:if test="${not isFirst}">
									<dd>
										<a target="_blank" href="musicSongsheetInfoVisit?musicSongsheetId=${MusicSongsheet.id}">
											<img src="assets/pages/img/musicSongsheetImg/${MusicSongsheet.picture}"/>
											<label>${MusicSongsheet.name}</label>
											<em></em>
										</a>
									</dd>
								</c:if>
							</c:forEach>
						</dl>
					</div>
					<div class="musicSongsheetClassRight">
						<p><span>MV</span>推荐</p>
						<dl>
							<c:forEach items="${requestScope.musicMVs}" var="musicMV" varStatus="status">
								<c:if test="${status.index == 0}" var="isFirst">
									<dt>
										<a target="_blank" href="musicMVVisit?id=${musicMV.id}">
											<img src="assets/pages/img/musicMVImg/${musicMV.themePicture}"/>
											<span></span>
											<label>《${musicMV.themeName}》</label>
											<em></em>
										</a>
									</dt>
								</c:if>
								<c:if test="${not isFirst}">
									<dd>
										<a target="_blank" href="musicMVVisit?id=${musicMV.id}">
											<img src="assets/pages/img/musicMVImg/${musicMV.themePicture}"/>
											<span></span>
											<label>《${musicMV.themeName}》</label>
											<em></em>
										</a>
									</dd>
								</c:if>
							</c:forEach>
							<dd class="selectDD"><a href="musicMVSeach">查看更多>></a></dd>
						</dl>
					</div>
				</div>
				
				<div class="title">
					<span>热门</span>歌手
					<div class="findClass">
						<input type="text" autocomplete="off" id="musicSingerName" placeholder="搜索歌手" maxlength="10" />
						<img id="select" data-pagesize="${requestScope.pageInfoMusicSinger.pageSize}" alt="搜索" src="${pageContext.request.contextPath}/assets/pages/img/common/ImageSelect01.png" />
					</div>
				</div>
				<div id="musicSingerDiv">
					<ul class="musicSingerAll">
						<c:forEach items="${requestScope.pageInfoMusicSinger.list}" var="musicSinger">
							<li>
								<a target="_blank" href="musicSingerVisit?id=${musicSinger.id}">
									<img src="assets/pages/img/musicSingerImg/${musicSinger.singerPicture}"/>
									<span></span>
								</a>
								<span>${musicSinger.name}</span>
							</li>
						</c:forEach>
					</ul>
					<p class="musicSingerPaging">
						<c:if test="${requestScope.pageInfoMusicSinger.isFirstPage == false}">
							<a data-pagenum="${requestScope.pageInfoMusicSinger.prePage}" data-pagesize="${requestScope.pageInfoMusicSinger.pageSize }" href="javascript:;">&#139;</a>
						</c:if>
						${requestScope.pageInfoMusicSinger.pageNum}/${requestScope.pageInfoMusicSinger.pages}
						<c:if test="${requestScope.pageInfoMusicSinger.isLastPage == false}">
							<a data-pagenum="${requestScope.pageInfoMusicSinger.nextPage}" data-pagesize="${requestScope.pageInfoMusicSinger.pageSize }" href="javascript:;">&#155;</a>
						</c:if>
					</p>
				</div>
					
				<div class="musicClass">
					<div class="newMusicDiv">
						<span>新歌</span>发布
						<div class="findClass">
							<input type="text" autocomplete="off" id="musicName" placeholder="搜索歌手或歌名" maxlength="10" />
							<img id="selectMusic" data-pagesize="${requestScope.pageInfoMusics.pageSize}" alt="搜索" src="${pageContext.request.contextPath}/assets/pages/img/common/ImageSelect01.png" />
						</div>
					</div>
					<div id="musicTable">
						<div class="fixdBorder">
						<table>
							<thead>
								<tr>
									<th></th>
									<th>歌曲</th>
									<th>播放</th>
									<th>下载</th>
									<th>日期</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${requestScope.pageInfoMusics.total >0}" var="notNullMusic">
									<c:forEach items="${requestScope.pageInfoMusics.list}" var="music" varStatus="status">
										<tr>
											<td data-musicid="${music.id}" class="playLoad"></td>
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
						<c:if test="${notNullMusic}">
							<p class="musicPaging">
								<c:if test="${requestScope.pageInfoMusics.isFirstPage ==false}">
									<label data-musicpagesize="${requestScope.pageInfoMusics.pageSize}" data-musicpagenum="${requestScope.pageInfoMusics.prePage}">&#139;</label>
								</c:if>
								${requestScope.pageInfoMusics.pageNum}/${requestScope.pageInfoMusics.pages}
								<c:if test="${requestScope.pageInfoMusics.isLastPage == false}">
									<label data-musicpagesize="${requestScope.pageInfoMusics.pageSize}" data-musicpagenum="${requestScope.pageInfoMusics.nextPage}">&#155;</label>
								</c:if>
							</p>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="common/foot.jsp" %>
		
		<div id="musicPlay">
			<div><span id="SingerName"></span></div>
			<div id="player"></div>
		</div>
		
		<script type="text/template" id="musicSingerTemplate">
			{{#if total}}
				<ul class="musicSingerAll">
					{{#each list}}
						<li>
							<a target="_blank" href="musicSingerVisit?id={{id}}">
								<img src="assets/pages/img/musicSingerImg/{{singerPicture}}"/>
								<span></span>
							</a>
							<span>{{name}}</span>
						</li>
					{{/each}}
				</ul>
				<p class="musicSingerPaging">
					{{#if isFirstPage}}
					{{else}}
						<a data-pagenum="{{prePage}}" data-pagesize="{{pageSize}}" href="javascript:;">&#139;</a>
					{{/if}}
					{{pageNum}}/{{pages}}
					{{#if isLastPage}}
					{{else}}
						<a data-pagenum="{{nextPage}}" data-pagesize="{{pageSize}}" href="javascript:;">&#155;</a>
					{{/if}}
				</p>
			{{else}}
				<div class="nullDiv">
					<img src="${pageContext.request.contextPath}/assets/pages/img/common/Imageddd.png"/>
				</div>
			{{/if}}		
		</script>
		<script type="text/template" id="musicTemplate">
			<div class="fixdBorder">
			<table>
				<thead>
					<tr>
						<th></th>
						<th>歌名</th>
						<th>播放</th>
						<th>下载</th>
						<th>日期</th>
					</tr>
				</thead>
				<tbody>
					{{#if total}}
						{{#each list}}
							<tr>
								<td data-musicid="{{id}}" class="playLoad"></td>
								<td>{{musicSinger.name}} - {{songName}}</td>
								<td>
									<span class="playMusic" data-src="assets/pages/audio/music/{{music}}"></span>
								</td>
								<td>
									<a class="downloadMusic" href="downloadMusic?songName={{music}}"></a>
								</td>
								<td>{{date}}</td>
							</tr>
						{{/each}}
					{{else}}
						<tr class="nullClass">
							<td class="nullImg" colspan="5"><img src="assets/pages/img/common/Imageddd.png" /></td>
						</tr>
					{{/if}}
				</tbody>
			</table>
			</div>
			{{#if total}}
			<p class="musicPaging">
				{{#if isFirstPage}}
				{{else}}
					<label data-musicpagesize="{{pageSize}}" data-musicpagenum="{{prePage}}">&#139;</label>
				{{/if}}
				{{pageNum}}/{{pages}}
				{{#if isLastPage}}
				{{else}}
					<label data-musicpagesize="{{pageSize}}" data-musicpagenum="{{nextPage}}">&#155;</label>
				{{/if}}
			</p>
		{{/if}}
		</script>
		
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/Jquery- wheelChartPlug-in/main.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/moment-2.22.2-dist/moment.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/handlebars-v4.0.11-dist/handlebars-v4.0.11.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/handlebars-v4.0.11-dist/handlebars-ext.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/jquery-spinkit.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-vpplayer/vpplayer.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-cookie-1.4.1-dist/jquery.cookie.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/music.js"></script>
	</body>
</html>
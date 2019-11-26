<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_游戏</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonFootLink.jsp" %>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/game.css" />
	</head>
	<body>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
				<div class="loggame">
					<ul class="gameHeadMaxImg">
					
						<c:forEach items="${requestScope.newGameInformationThemePageInfo.list}" varStatus="status" var="gameTheme">
							<li ${status.index == 0?'class="moveLi"':'' }>
								<a target="_blank" href="gameInformationVisit?informationThemeId=${gameTheme.id}">
									<img src="${pageContext.request.contextPath}/assets/pages/img/gameInformationImg/${gameTheme.picture}"/>
									<span>${gameTheme.theme}</span>
								</a>
							</li>
						</c:forEach>
						
					</ul>
					<div class="backgroundColor"></div>
					<ul class="gameHeadMinImg">
						<li><a class="leftAndRight" id="left" href="javascript:;"><img src="${pageContext.request.contextPath}/assets/pages/img/common/leftMover02.png"/></a></li>
						
						<c:forEach items="${requestScope.newGameInformationThemePageInfo.list}" varStatus="status" var="gameTheme">
							<li>
								<a data-imgid="${status.index+1}" class="imgClassA ${status.index == 0?'moverClass':''}" target="_blank" href="gameInformationVisit?informationThemeId=${gameTheme.id}">
									<img src="${pageContext.request.contextPath}/assets/pages/img/gameInformationImg/${gameTheme.picture}"/>
								</a>
							</li>
						</c:forEach>
						
						<li><a class="leftAndRight" id="right" href="javascript:;"><img src="${pageContext.request.contextPath}/assets/pages/img/common/rightMover02.png"/></a></li>
					</ul>
				</div>
			</div>
			<div class="section">
				<div class="section-header">
					<div class="header-left">
						<div class="borderH3">
							<h3>最新资讯</h3>
							<ul>
								<c:forEach items="${requestScope.newGameInformationThemePageInfo02.list}" varStatus="status" var="gameTheme">
									<c:if test="${status.index+1 !=9 }">
										<li>
											<div>
												<a target="_blank" href="gameInformationVisit?informationThemeId=${gameTheme.id}">
													<img src="${pageContext.request.contextPath}/assets/pages/img/gameInformationImg/${gameTheme.picture}"/>
													<span>
														<label>${gameTheme.theme}</label>
														<label>日期：<fmt:formatDate value="${gameTheme.date}" pattern="MM-dd" /></label>
													</span>
												</a>
											</div>
										</li>
									</c:if>
								</c:forEach>
								<li class="seeMore01">
									<p><a href="gameInformationThemeSeach">查看更多 >></a></p>
								</li>
							</ul>
						</div>
					</div>
					
					
					<div class="header-center">
						<ul class="center-ul01">
							<li data-title="1"class="clickHourLiClass">宣传</li>
							<li data-title="2">cosplay</li>
							<li data-title="3">手办</li>
							<li data-title="4">专访</li>
						</ul>
						<div class="title">
							<div class="title01">
								<ul class="title01Ul">
								
									<c:forEach items="${requestScope.InformationTheme01.list}" var="informationTheme">
										<li>
											<a target="_blank" href="gameInformationVisit?informationThemeId=${informationTheme.id}">
												<img class="defaultImg" src="${pageContext.request.contextPath}/assets/pages/img/common/2018-07-03(12_11_25).png"/>
												<label>${informationTheme.theme}</label>
												<span><fmt:formatDate value="${informationTheme.date}" pattern="MM-dd"/></span>
											</a>
										</li>
									</c:forEach>
									
								</ul>
							</div>
							<div class="title02">
								<dl>
									<c:forEach items="${requestScope.InformationTheme02.list}" var="informationTheme">
										<dt>
											<a target="_blank" href="gameInformationVisit?informationThemeId=${informationTheme.id}">
												${informationTheme.theme}
											</a>
											<span><fmt:formatDate value="${informationTheme.date}" pattern="MM-dd"/></span>
										</dt>
										<dd>
											<a target="_blank" href="gameInformationVisit?informationThemeId=${informationTheme.id}">
												<img src="${pageContext.request.contextPath}/assets/pages/img/gameInformationImg/${informationTheme.picture}"/>
											</a>
										</dd>
									</c:forEach>
								</dl>
							</div>
							<div class="title03">
								<dl>
									<c:forEach items="${requestScope.InformationTheme03.list}" var="informationTheme">
										<dt>
											<a target="_blank" href="gameInformationVisit?informationThemeId=${informationTheme.id}">
												${informationTheme.theme}
											</a>
											<span><fmt:formatDate value="${informationTheme.date}" pattern="MM-dd"/></span>
										</dt>
										<dd>
											<a target="_blank" href="gameInformationVisit?informationThemeId=${informationTheme.id}">
												<img src="${pageContext.request.contextPath}/assets/pages/img/gameInformationImg/${informationTheme.picture}"/>
											</a>
										</dd>
									</c:forEach>
								</dl>
							</div>
							<div class="title04">
								<ul class="title01Ul">
								
									<c:forEach items="${requestScope.InformationTheme04.list}" var="informationTheme">
										<li>
											<a target="_blank" href="gameInformationVisit?informationThemeId=${informationTheme.id}">
												<img class="defaultImg" src="${pageContext.request.contextPath}/assets/pages/img/common/2018-07-03(12_11_25).png"/>
												<label>${informationTheme.theme}</label>
												<span><fmt:formatDate value="${informationTheme.date}" pattern="MM-dd"/></span>
											</a>
										</li>
									</c:forEach>
									
								</ul>
							</div>
						</div>
					</div>
					<div class="header-right">
						<h3>最新单机游戏下载</h3>
						<ul>
						
							<c:forEach items="${requestScope.newPCGames}" var="gameInfo" >
								<li>
									<c:choose>
										<c:when test="${gameInfo.language == '中文'}"><label class="class01">中</label></c:when>
										<c:when test="${gameInfo.language == '英文'}"><label class="class02">英</label></c:when>
										<c:when test="${gameInfo.language == '日文'}"><label class="class03">日</label></c:when>
									</c:choose>
									<a target="_blank" href="gameVisit?gameId=${gameInfo.id}">
										${gameInfo.name}
									</a>
									<span>${gameInfo.language}|${gameInfo.typeId.typeName}</span>
								</li>
							</c:forEach>
							
						</ul>
					</div>
				</div>
				<div class="section-section01">
					<h1>近期<span>大作</span></h1>
					<div class="section-section01Left">
						<ul class="LeftUl01">
							<c:forEach items="${requestScope.newMaxGames}" var="gameInfo" varStatus="status">
								<li ${status.index == 0?'class="liMoveClass"':''}><span>${status.index+1}</span><label data-showimg="${status.index+1}">${gameInfo.name}</label></li>
							</c:forEach>
							
						</ul>
						<ul class="RightUl01">
						
							<c:forEach items="${requestScope.newMaxGames}" var="gameInfo" varStatus="status">
								<li ${status.index == 0?'class="liShow"':''}>
									<p>${gameInfo.name}<span>${gameInfo.platformId.platformName}</span></p>
									<span>[${gameInfo.typeId.typeName}]&nbsp;<fmt:formatDate value="${gameInfo.date}" pattern="MM-dd" />上市</span>
									<dl>
										<dt><a target="_blank" href="gameVisit?gameId=${gameInfo.id}"><img src="${pageContext.request.contextPath}/assets/pages/img/gameTheme/${gameInfo.picture}"/></a></dt>
										<dd>
											<h4>游戏简介：<a target="_blank" href="gameVisit?gameId=${gameInfo.id}">更多信息></a></h4>
											<p>${gameInfo.content}</p>
										</dd>
									</dl>
								</li>
							</c:forEach>
							
						</ul>
						<p class="TitleP01">最新上市</p>
						<ul class="TitleUl01">
						
							<c:forEach items="${requestScope.NewGameInfos}" var="gameInfo">
								<li>
									<a target="_blank" href="gameVisit?gameId=${gameInfo.id}">
										<img src="${pageContext.request.contextPath}/assets/pages/img/gameTheme/${gameInfo.picture}"/>
										<br>
										<span>${gameInfo.name}</span>
									</a>
									<br>
									<span>${gameInfo.typeId.typeName}</span>
									<br>
									<span><fmt:formatDate value="${gameInfo.date}" pattern="yyyy-MM-dd" />上市</span>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="section-section01Right">
						<p>即将上市 </p>
						<ul>
						
							<c:forEach items="${requestScope.ComingNewMaxGames}" var="gameInfo">
								<li><span>${gameInfo.platformId.platformName}-${gameInfo.language}</span><a target="_blank" href="gameVisit?gameId=${gameInfo.id}">${gameInfo.name}</a></li>
							</c:forEach>
							
						</ul>
					</div>
				</div>
				<div class="section-section04">
					<h1>美图<span>壁纸</span></h1>
					<dl>
						<c:forEach items="${requestScope.WallpaperGames}" varStatus="status" var="wallpapergame">
							<c:if test="${status.index == 0}" var="isFirst">
								<dt>
									<a target="_blank" href="wallpaperVisit?themeId=${wallpapergame.id}">
										<img src="assets/pages/img/wallpaperImg/${wallpapergame.wallpaper}"/>
										<label>${wallpapergame.theme}</label>
										<em></em>
									</a>
								</dt>
							</c:if>
							<c:if test="${not isFirst}">
								<dd>
									<a target="_blank" href="wallpaperVisit?themeId=${wallpapergame.id}">
										<img src="assets/pages/img/wallpaperImg/${wallpapergame.wallpaper}"/>
										<label>${wallpapergame.theme}</label>
										<em></em>
									</a>
								</dd>
							</c:if>
						</c:forEach>
					</dl>
				</div>
				<div class="section-floot">
					<div class="section-flootDiv01">
						<p>即将上市游戏<a href="gameSeach">更多+</a></p>
						<table id="flootDiv01Table01">
							<thead>
								<tr>
									<td>序号</td>
									<td>游戏</td>
									<td>日期</td>
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${requestScope.ComingNewMaxGameInfos02}" var="gameInfo" varStatus="status">
									<tr>
										<td>${status.index+1}</td>
										<td><a target="_blank" class="gameName" href="gameVisit?gameId=${gameInfo.id}">${gameInfo.name}</a></td>
										<td><fmt:formatDate value="${gameInfo.date}" pattern="MM-dd" /></td>
									</tr>
									<tr class="gameInfo">
										<td></td>
										<td colspan="2">
											<a target="_blank" href="gameVisit?gameId=${gameInfo.id}"><img src="${pageContext.request.contextPath}/assets/pages/img/gameTheme/${gameInfo.picture}"/></a>
											<span>类型：${gameInfo.typeId.typeName}</span>
											<span>发行：${gameInfo.issuer}</span>
											<span>平台：${gameInfo.platformId.platformName}</span>
											<a target="_blank" class="buttonClass" href="gameVisit?gameId=${gameInfo.id}">专题</a>
											<c:choose>
												<c:when test="${gameInfo.game != null && fn:length(gameInfo.game)>0}">
													<a target="_blank" class="buttonClass" href="downLoadGame?game=${gameInfo.game}">下载</a>
												</c:when>
												<c:when test="${gameInfo.gamePath != null && fn:length(gameInfo.gamePath)>0}">
													<a target="_blank" class="buttonClass" href="${gameInfo.gamePath}">下载</a>
												</c:when>
												<c:otherwise>
													<a class="noButtonClass" href="javascript:;">下载</a>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
					<div class="section-flootDiv01">
						<p>近期大作游戏<a href="gameSeach">更多+</a></p>
						<table id="flootDiv01Table02">
							<thead>
								<tr>
									<td>序号</td>
									<td>游戏</td>
									<td>日期</td>
								</tr>
							</thead>
							<tbody>
								
								<c:forEach items="${requestScope.newMaxGameInfos02}" var="gameInfo" varStatus="status">
									<tr>
										<td>${status.index+1}</td>
										<td><a target="_blank" class="gameName" href="gameVisit?gameId=${gameInfo.id}">${gameInfo.name}</a></td>
										<td><fmt:formatDate value="${gameInfo.date}" pattern="MM-dd" /></td>
									</tr>
									<tr class="gameInfo">
										<td></td>
										<td colspan="2">
											<a target="_blank" href="gameVisit?gameId=${gameInfo.id}"><img src="${pageContext.request.contextPath}/assets/pages/img/gameTheme/${gameInfo.picture}"/></a>
											<span>类型：${gameInfo.typeId.typeName}</span>
											<span>发行：${gameInfo.issuer}</span>
											<span>平台：${gameInfo.platformId.platformName}</span>
											<a target="_blank" class="buttonClass" href="gameVisit?gameId=${gameInfo.id}">专题</a>
											<c:choose>
												<c:when test="${gameInfo.game != null && fn:length(gameInfo.game)>0}">
													<a target="_blank" class="buttonClass" href="downLoadGame?game=${gameInfo.game}">下载</a>
												</c:when>
												<c:when test="${gameInfo.gamePath != null && fn:length(gameInfo.gamePath)>0}">
													<a target="_blank" class="buttonClass" href="${gameInfo.gamePath}">下载</a>
												</c:when>
												<c:otherwise>
													<a class="noButtonClass" href="javascript:;">下载</a>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
					<div class="section-flootDiv01">
						<p>最新上市游戏<a href="gameSeach">更多+</a></p>
						<table id="flootDiv01Table03">
							<thead>
								<tr>
									<td>序号</td>
									<td>游戏</td>
									<td>日期</td>
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${requestScope.NewGameInfos02}" var="gameInfo" varStatus="status">
									<tr>
										<td>${status.index+1}</td>
										<td><a target="_blank" class="gameName" href="gameVisit?gameId=${gameInfo.id}">${gameInfo.name}</a></td>
										<td><fmt:formatDate value="${gameInfo.date}" pattern="MM-dd" /></td>
									</tr>
									<tr class="gameInfo">
										<td></td>
										<td colspan="2">
											<a target="_blank" href="gameVisit?gameId=${gameInfo.id}"><img src="${pageContext.request.contextPath}/assets/pages/img/gameTheme/${gameInfo.picture}"/></a>
											<span>类型：${gameInfo.typeId.typeName}</span>
											<span>发行：${gameInfo.issuer}</span>
											<span>平台：${gameInfo.platformId.platformName}</span>
											<a target="_blank" class="buttonClass" href="gameVisit?gameId=${gameInfo.id}">专题</a>
											<c:choose>
												<c:when test="${gameInfo.game != null && fn:length(gameInfo.game)>0}">
													<a target="_blank" class="buttonClass" href="downLoadGame?game=${gameInfo.game}">下载</a>
												</c:when>
												<c:when test="${gameInfo.gamePath != null && fn:length(gameInfo.gamePath)>0}">
													<a target="_blank" class="buttonClass" href="${gameInfo.gamePath}">下载</a>
												</c:when>
												<c:otherwise>
													<a class="noButtonClass" href="javascript:;">下载</a>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="common/foot.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/game.js"></script>
	</body>
</html>
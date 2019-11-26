<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>www.zhai.com</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/pages/img/common/zhai.ico" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/common/common.css" />
		<%@ include file="common/commonFootLink.jsp" %>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/header.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/home.css" />
	</head>
	<body>
		<div id="home" class="home" style="transition:all 0.7s;">
			<div id="background02"></div>
			<div class="container">
				<div class="row">
					<div class="headerFormat col-md-offset-1">
						<%@ include file="common/header.jsp" %>
						<div class="loggame">
							<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
							  <!-- Indicators -->
							  <ol class="carousel-indicators" style="bottom:0;">
							  	<c:forEach items="${requestScope.homeNavigationBars}" var="homeNavigationBar" varStatus="status">
								    <li data-target="#carousel-example-generic" data-backgroundcolor="${homeNavigationBar.backgroundColor}" data-slide-to="${status.index}" ${status.index ==0?'id="active" class="active"':''}></li>
								</c:forEach>
							  </ol>
							
							  <div class="carousel-inner" role="listbox">
							  	<c:forEach items="${requestScope.homeNavigationBars}" var="homeNavigationBar" varStatus="status">
								    <div class="item ${status.index == 0?'active':''}">
								      <a target="_blank" href="homeNavigationBarVisit?id=${homeNavigationBar.id}"><img src="${pageContext.request.contextPath}/assets/pages/img/homeNavigationBarImg/${homeNavigationBar.picture}"></a>
								      <div class="carousel-caption"></div>
								    </div>
								</c:forEach>
							  </div>
							
							  <!-- Controls -->
							  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
							    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							    <span class="sr-only">Previous</span>
							  </a>
							  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
							    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							    <span class="sr-only">Next</span>
							  </a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
			var backgroundColor=document.getElementById("active").getAttribute('data-backgroundcolor');
			document.getElementById("home").style.backgroundColor=backgroundColor;
		</script>
		<div class="container">
			<div class="row">
				<div class="section col-md-offset-1">
					<div class="row">
						<div class="col-md-9 section-headerLeft">
							<h4>新番导视</h4>
							<ul>
							
								<c:forEach items="${requestScope.animationEntertainmentAnimation}" var="animationEntertainmentAnimation" >
									<li>
										<a target="_blank" href="animationEntertainmentAnimationVisit?id=${animationEntertainmentAnimation.id}">
											<span><img src="${pageContext.request.contextPath}/assets/pages/img/animationEntertainmentAnimationImg/${animationEntertainmentAnimation.picture}"/><span></span></span>
											<span>${animationEntertainmentAnimation.name}</span><br>
											<span><fmt:formatDate value="${animationEntertainmentAnimation.dateTime}" pattern="MM.dd" /></span>
										</a>
									</li>
								</c:forEach>
							</ul>
						</div>
						<div class="col-md-3 section-headerRight">
							<h4>最新导视</h4>
						  	<ul>
						  		<c:forEach items="${requestScope.newAnimationEntertainmentAnimation}" var="newAnimationEntertainmentAnimation">
							  		<li>
								  		<span><fmt:formatDate value="${newAnimationEntertainmentAnimation.dateTime}" pattern="MM.dd" /></span>
								  		<a target="_blank" href="animationEntertainmentAnimationVisit?id=${newAnimationEntertainmentAnimation.id}">
								  		${newAnimationEntertainmentAnimation.name}
								  		</a>
							  		</li>
						  		</c:forEach>
						  	</ul>
						</div>
					</div>
					<div class="row">
					  <div class="col-md-12">
					  	<div class="newComicInfo">
					  		<h4>新番推荐</h4>
					  		<div class="newComic-Info">
					  			<a id="leftImg" href="javascript:;"></a>
					  			<ul class="newComicUl">
					  				<li class="newcomicUlLi">
					  				
					  					<c:forEach items="${requestScope.animationInfos}" var="animationInfo">
					  						<a target="_blank" href="animationInfoVisit?animationInfoId=${animationInfo.id}">
						  						<label><img src="${pageContext.request.contextPath}/assets/pages/img/animationInfoImg/${animationInfo.animationPicture}"/></label>
						  						<br>
						  						<span>${animationInfo.animationName}</span>
					  						</a>
										</c:forEach>
										
					  				</li>
					  			</ul>
					  			<a id="rightImg" href="javascript:;"></a>
					  		</div>
					  	</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="game01">
							<h4>近期大作</h4>
							<span class="game01_Span01">
							
								<c:forEach items="${requestScope.newMaxGames}" var="game" varStatus="status">
									<a target="_blank" class="${status.index+1 == 1?'showGameImg':''}" href="gameVisit?gameId=${game.id}"><img src="${pageContext.request.contextPath}/assets/pages/img/gameTheme/${game.picture}"/></a>
					  			</c:forEach>
					  			
							</span>
							<span class="game01_Span02">
							
								<c:forEach items="${requestScope.newMaxGames}" var="game" varStatus="status">
					  				<a target="_blank" class="${status.index+1 == 1?'showGameA':''}" href="gameVisit?gameId=${game.id}"><span>${game.name}</span><span>${game.platformId.platformName}</span><br><span>${game.typeId.typeName}</span><span>${game.language}</span><br><span>${game.issuer}</span><br></a>
					  			</c:forEach>
					  			
							</span>
							<span class="game01_Span03">
							
								<c:forEach items="${requestScope.newMaxGames}" var="game" varStatus="status">
					  				<a target="_blank" data-aid="${status.index+1}" class="${status.index+1 == 1?'showGameAAndImg':''}" href="gameVisit?gameId=${game.id}"><span><img src="${pageContext.request.contextPath}/assets/pages/img/gameTheme/${game.picture}"/></span><span>${game.name}</span></a>
					  			</c:forEach>
					  			
							</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-9">
						<div class="game02">
							<h4>COSPLAY</h4>
							<ul>
								
								<c:forEach items="${requestScope.InformationThemeCosPlay.list}" var="informationTheme">
									<li>
										<a target="_blank" href="gameInformationVisit?informationThemeId=${informationTheme.id}">
											<span>
												<img src="${pageContext.request.contextPath}/assets/pages/img/gameInformationImg/${informationTheme.picture}"/>
											</span>
											<br>
											<span>${informationTheme.theme}</span>
											<br>
											<span><fmt:formatDate value="${informationTheme.date}" pattern="yyyy-MM-dd"/></span>
										</a>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="col-md-3">
						<div class="newGame">
							<h4>最新上市</h4>
							<ul>
								<c:forEach items="${requestScope.newGameInfos}" var="newGameInfo">
									<li>
										<a target="_blank" href="gameVisit?gameId=${newGameInfo.id}">
											<img src="${pageContext.request.contextPath}/assets/pages/img/gameTheme/${newGameInfo.picture}"/>
											<span>
												<span>${newGameInfo.name}</span>
												<span><fmt:formatDate value="${newGameInfo.date}" pattern="yyyy-MM-dd" />上市</span>
											</span>
										</a>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 game03">
						<h4>游戏资讯</h4>
						<ul>
						
							<c:forEach items="${requestScope.newGameInformationThemePageInfo.list}" var="gameInformationTheme">
								<li>
									<a target="_blank" href="gameInformationVisit?informationThemeId=${gameInformationTheme.id}">
										<img src="${pageContext.request.contextPath}/assets/pages/img/gameInformationImg/${gameInformationTheme.picture}"/>
										<span>${gameInformationTheme.theme}</span>
									</a>
								</li>
							</c:forEach>
							
						</ul>
					</div>
				</div>
				<div class="row">
					<div class="col-md-9 music01">
						<h4>歌单推荐</h4>
						<ul>
							<c:forEach items="${requestScope.pageInfotopMusicSongsheets.list}" var="topMusicSongsheet">
								<li>
									<a target="_blank" href="musicSongsheetInfoVisit?musicSongsheetId=${topMusicSongsheet.id}">
										<img src="${pageContext.request.contextPath}/assets/pages/img/musicSongsheetImg/${topMusicSongsheet.picture}"/>
										<span></span>
									</a>
									<span>${topMusicSongsheet.name}</span>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="col-md-3 music01-right">
						<h4>每日精选</h4>
						<ul>
							<c:forEach items="${requestScope.pageInfobottomMusicSongsheets.list}" var="bottomMusicSongsheet">
								<li>
									<a target="_blank" href="musicSongsheetInfoVisit?musicSongsheetId=${bottomMusicSongsheet.id}">
										<img src="${pageContext.request.contextPath}/assets/pages/img/musicSongsheetImg/${bottomMusicSongsheet.picture}"/>
										<label>${bottomMusicSongsheet.name}</label>
										<span><fmt:formatDate value="${bottomMusicSongsheet.date}" pattern="YYYY-MM-dd" /></span>
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 music02">
						<h4>歌手推荐</h4>
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
					</div>
				</div>
				<div class="row">
					<div class="col-md-9 music03">
						<h4>热门MV</h4>
						<ul>
							<c:forEach items="${requestScope.musicMVs}" var="musicMV">
								<li>
									<a target="_blank" href="musicMVVisit?id=${musicMV.id}">
										<span>
											<img src="${pageContext.request.contextPath}/assets/pages/img/musicMVImg/${musicMV.themePicture}"/>
											<label class="playMv"></label>
										</span>
										<label>${musicMV.themeName}</label>
										<span>${musicMV.musicSinger.name}</span>
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="col-md-3 music03-right">
						<h4>热门明星</h4>
						<ul>
							<c:forEach items="${requestScope.wallpaperMusicThtemes}" var="wallpaperMusicThteme">
								<li>
									<a target="_blank" href="wallpaperVisit?themeId=${wallpaperMusicThteme.id}">
										<img src="${pageContext.request.contextPath}/assets/pages/img/wallpaperImg/${wallpaperMusicThteme.wallpaper}"/>
										<span></span>
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="row">
					<div class="col-md-9 wallpaper01">
						<h4>精彩推荐</h4>
						<ul class="wallpaper01Ul02">
						
							<c:forEach items="${requestScope.pageInfo.list}" var="wallpaperTheme">
								<li>
									<a target="_blank" href="wallpaperVisit?themeId=${wallpaperTheme.id}"><img src="${pageContext.request.contextPath}/assets/pages/img/wallpaperImg/${wallpaperTheme.wallpaper}"/>
										<span>${wallpaperTheme.theme}</span>
									</a>
								</li>
							</c:forEach>

						</ul>	
					</div>
					<div class="col-md-3 wallpaper01-right">
						<fieldset>
    						<legend>最新图片</legend>
							<ul>
								<c:forEach items="${requestScope.newWallpaperThemes}" var="newWallpaperTheme">
									<li>
										<a target="_blank" href="wallpaperVisit?themeId=${newWallpaperTheme.id}">
											<img src="${pageContext.request.contextPath}/assets/pages/img/common/block.gif">
											<span>${newWallpaperTheme.theme}</span>
										</a>
									</li>
								</c:forEach>
							</ul>
						</fieldset>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 wallpaper02">
						<h4>最新壁纸</h4>
						<ul>
							<c:forEach items="${requestScope.TopWallpaperThemes}" var="TopWallpaperTheme">
								<li>
									<a target="_blank" href="wallpaperVisit?themeId=${TopWallpaperTheme.id}">
										<img src="${pageContext.request.contextPath}/assets/pages/img/wallpaperImg/${TopWallpaperTheme.wallpaper}"/>
										<span>
											${TopWallpaperTheme.theme}
											<span>(<fmt:formatDate value="${TopWallpaperTheme.date}" pattern="MM.dd" />)</span>
										</span>
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				</div>
			</div>
			<img id="footFinxedImgLeft" src="${pageContext.request.contextPath}/assets/pages/img/common/0697eb1d55bf274f9e7eeeb3651c9136.png"/>
			<img id="footFinxedImgRight" src="${pageContext.request.contextPath}/assets/pages/img/common/7e980865686871d2a5001a0814ff75a9.png"/>
			<div id="borderTop">
				<ul>
					<li><a id="comic" href="javascript:;">动漫</a></li>
					<li><a id="game" href="javascript:;">游戏</a></li>
					<li><a id="music" href="javascript:;">音乐</a></li>
					<li><a id="wallpaper" href="javascript:;">壁纸</a></li>
					<li class="none" id="top"><a href="javascript:scrollTo(0,0);">回顶部</a></li>
				</ul>
			</div>
		</div>
		<%@ include file="common/foot.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/home.js"></script>
	</body>
</html>
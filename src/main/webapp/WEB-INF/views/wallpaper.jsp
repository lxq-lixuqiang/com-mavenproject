<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_壁纸</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonFootLink.jsp" %>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/css/jquery-spinkit.min.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/wallpaper.css" />
	</head>
	<body>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
				<div class="logwallpaper">
					<img src="${pageContext.request.contextPath}/assets/pages/img/wallpaperTop/${requestScope.wallpaperNavigationBar.wallpaper}"  />
				</div>
			</div>
			<div class="section">
				<div class="sectionHeader">
					<div class="sectonHeader-left">
						<c:forEach items="${requestScope.topWallpaperThemes}" var="topWallpaperTheme" varStatus="status">
							<a class="${status.index == 0?'notDisplay':''}" target="_blank" href="wallpaperVisit?themeId=${topWallpaperTheme.id}">
								<img src="${pageContext.request.contextPath}/assets/pages/img/wallpaperImg/${topWallpaperTheme.wallpaper}"/>
							</a>
						</c:forEach>
					</div>
					<div class="sectonHeader-right">
						<ul>
							<c:forEach items="${requestScope.topWallpaperThemes}" var="topWallpaperTheme" varStatus="status">
								<li>
									<a data-imgid="${status.index+1}" class="${status.index == 0?'moveClass':''}" target="_blank" href="wallpaperVisit?themeId=${topWallpaperTheme.id}">
										<span>${topWallpaperTheme.theme}</span>
										<br><fmt:formatDate value="${topWallpaperTheme.date}" pattern="yyyy-MM-dd" />
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="sectionClass">
					<fieldset>
    					<legend><span data-num="1" class="select">壁纸分类</span><span data-num="2">日期选择</span></legend>
    					<div class="block">
	    					<a class="wallpaperType" data-typeid="null" href="javascript:;">所有壁纸</a>
	    					<c:forEach items="${requestScope.wallpaperTypes}" var="wallpaperType">
	    						<a class="wallpaperType" data-typeid="${wallpaperType.id}" href="javascript:;">
	    							${wallpaperType.name}壁纸
	    						</a>
	    					</c:forEach>
	    					<a href="wallpaperSeach">更多壁纸</a>
    					</div>
    					<div>
	    					<a class="wallpaperDate" data-date="null" href="javascript:;">所有壁纸</a>
	    					<c:forEach items="${requestScope.wallpaperThemeDates}" var="wallpaperThemeDate">
	    						<a class="wallpaperDate" data-date="<fmt:formatDate value="${wallpaperThemeDate}" pattern="yyyy-MM-dd" />" href="javascript:;">
	    							<fmt:formatDate value="${wallpaperThemeDate}" pattern="yyyy-MM-dd" />
	    						</a>
	    					</c:forEach>
	    					<a href="wallpaperSeach">更多壁纸</a>
    					</div>
  					</fieldset>
				</div>
				<div class="sectionFloot">
					<div class="sectionFloot-left">
						<h2 data-typeid="null" data-date="null" id="title" >所有壁纸</h2>
						<ul id="wallpaperThemeUl">
						
							<c:forEach items="${requestScope.pageInfo.list}" var="wallpaper">
								<li>
									<a target="_blank" href="wallpaperVisit?themeId=${wallpaper.id}">
										<span>
											<img src="${pageContext.request.contextPath}/assets/pages/img/wallpaperImg/${wallpaper.wallpaper}"/>
											<span>${wallpaper.wallpaperType.name}壁纸</span>
										</span>
										<br>
										<em>${wallpaper.theme}</em>
									</a>
								</li>
							</c:forEach>
							
						</ul>
						<ol id="wallpaperThemeOl">
						
							<c:if test="${requestScope.pageInfo.isFirstPage == false}">
								<li class="pagingButton"><a class="pageNum" data-pagesize="${requestScope.pageInfo.pageSize}" data-pagenum="${requestScope.pageInfo.prePage}" href="javascript:;">上一页</a></li>
							</c:if>
							<c:forEach items="${requestScope.pageInfo.navigatepageNums}" var="pageNum">
								<li>
									<a ${pageNum == requestScope.pageInfo.pageNum?'class="moverClassColor"':'class="pageNum"'} data-pagesize="${requestScope.pageInfo.pageSize}" data-pagenum="${pageNum}" href="javascript:;">
										${pageNum}
									</a>
								</li>
							</c:forEach>
							<c:if test="${requestScope.pageInfo.isLastPage == false}">
								<li class="pagingButton"><a class="pageNum" data-pagesize="${requestScope.pageInfo.pageSize}" data-pagenum="${requestScope.pageInfo.nextPage}" href="javascript:;">下一页</a></li>
							</c:if>
							
						</ol>
					</div>
					<div class="sectionFloot-right">
						<div class="wallpaper01-right">
							<h3>最新壁纸推荐</h3>
							<ul>
								<c:forEach items="${requestScope.newWallpaperThemes}" var="newWallpaperTheme">
									<li>
										<a target="_blank" href="wallpaperVisit?themeId=${newWallpaperTheme.id}">
											<img src="${pageContext.request.contextPath}/assets/pages/img/common/block.gif">
											${newWallpaperTheme.theme}
										</a>
									</li>
								</c:forEach>
							</ul>
						</div>	
					</div>	
				</div>
			</div>
		</div>
		<%@ include file="common/foot.jsp" %>
		
		<script type="text/template" id="wallpaperThemeTemplate">
			{{#if total}}
				{{#each list}}
					<li>
						<a target="_blank" href="wallpaperVisit?themeId={{id}}">
							<span>
								<img src="${pageContext.request.contextPath}/assets/pages/img/wallpaperImg/{{wallpaper}}"/>
								<span>{{wallpaperType.name}}壁纸</span>
							</span>
							<br>
							<em>{{theme}}</em>
						</a>
					</li>
				{{/each}}
			{{/if}}
		</script>
		<script type="text/template" id="wallpaperPagingTemplate">
			{{#if total}}
				{{#if isFirstPage}}
				{{else}}
					<li class="pagingButton"><a class="pageNum" data-pagesize="{{pageSize}}" data-pagenum="{{prePage}}" href="javascript:;">上一页</a></li>
				{{/if}}

				{{#each navigatepageNums}}
					<li>
						<a {{#eq this ../pageNum}} class="moverClassColor" {{/eq}} {{#neq this ../pageNum}} class="pageNum" data-pagesize="{{../pageSize}}" data-pagenum="{{this}}" {{/neq}}  href="javascript:;">
							{{this}}
						</a>
					</li>
				{{/each}}

				{{#if isLastPage}}
				{{else}}
					<li class="pagingButton"><a class="pageNum" data-pagesize="{{pageSize}}" data-pagenum="{{nextPage}}" href="javascript:;">下一页</a></li>
				{{/if}}
			{{/if}}
		</script>
		
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/handlebars-v4.0.11-dist/handlebars-v4.0.11.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/handlebars-v4.0.11-dist/handlebars-ext.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/jquery-spinkit.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/wallpaper.js"></script>
	</body>
</html>
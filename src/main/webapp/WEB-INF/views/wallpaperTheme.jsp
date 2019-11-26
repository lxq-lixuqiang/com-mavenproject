<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>zhai_壁纸数据-主题壁纸</title>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<%@ include file="common/dataManagement-link.jsp" %>
		<link href="assets/global/plugins/notice-dist/css/animate.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/noticejs.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/Lobibox.min.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/LobiboxClass.css" rel="stylesheet">
		<link href="assets/pages/css/wallpaperTheme.css" rel="stylesheet">
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
							$(".sortable-accordion div:nth-of-type(7)").attr("id","showDiv");
							$('.sortable-accordion div:not(#showDiv)').slideToggle(1);
						</script>
					</div>
					<div class="section-botton-right">
						<div class="heightFixed">
						<div id="currentLocation">
							<p><a href="dataManagement">壁纸数据</a><span>&#155;</span>主题壁纸</p>
						</div>
						<input type="hidden" id="msg" value="${requestScope.msg}" />
						
						<div>
							<p class="findClass">
								<select id="typeId">
									<option value="-1">全部</option>
									<c:forEach items="${requestScope.types}" var="type">
										<option ${sessionScope.wallpaperTheme_typeId == type.id?'selected':'' } value="${type.id}">${type.name}</option>
									</c:forEach>
								</select>
								<input type="text" autocomplete="off" id="theme" value="${sessionScope.wallpaperTheme_theme}" placeholder="搜索主题" maxlength="10" /><img id="select" alt="搜索" src="${pageContext.request.contextPath}/assets/pages/img/common/ImageSelect01.png" />
							</p>
						</div>
						
						<c:if test="${requestScope.pageInfo.total >0}" var="notNull">
							<c:forEach var="wallpaperTheme" items="${requestScope.pageInfo.list}">
								<dl class="dlClass text-left">
									<dt class="text-center"><img class="imgClass" src="${pageContext.request.contextPath}/assets/pages/img/wallpaperImg/${wallpaperTheme.wallpaper}"/></dt>
									<dd>
										<p class="themeClass">主题：${wallpaperTheme.theme}</p>
										<p>类型：${wallpaperTheme.wallpaperType.name}</p>
										<p>日期：<fmt:formatDate value="${wallpaperTheme.date}" pattern="yyyy-MM-dd" /></p>
										<p class="text-center">
											<a href="wallpaperThemeUpdate?id=${wallpaperTheme.id}" class="updateButton btn btn-info">
												<span class="glyphicon glyphicon-pencil"></span> 修 改
											</a>
											<a href="wallpaperSelect?wallpaperThemeId=${wallpaperTheme.id}" class="updateButton btn btn-info">
												<span class="glyphicon glyphicon-eye-open"></span> 查看壁紙
											</a>
											<button data-id="${wallpaperTheme.id}" type="button" class="updateButton btn btn-danger delete">
												<span class="glyphicon glyphicon-trash"></span> 删 除
											</button>
										</p>
									</dd>
								</dl>
							</c:forEach>
						</c:if>
						<c:if test="${not notNull}">
							 <div id="nullImgDiv">
								<img alt="空" id="nullImg" src="${pageContext.request.contextPath}/assets/pages/img/common/Imageddd.png"/>
							</div>
						</c:if>
						</div>
						
						<div style="clear: both;"></div>
						<div class="text-right buttonMarginSize">
							<a href="wallpaperThemeAdd" class="updateButton btn btn-info">
								<span class="glyphicon glyphicon-plus"></span> 添加主题壁纸
							</a>
						</div>
						
						<c:if test="${notNull}">
							<div id="pageSizeAndpageNum">
								<ul>
									<c:if test="${!requestScope.pageInfo.isFirstPage}">
										<li data-pagenum="1" class="ulNotClick">&#171;</li>
										<li data-pagenum="${requestScope.pageInfo.prePage}" class="ulNotClick">&#139;</li>
									</c:if>
									<c:forEach var="pagingButton" items="${requestScope.wallpaperTheme_pagingButton}">
										<li data-pagenum="${pagingButton}" class="${pagingButton=='...'?'ulreadlyClick':pagingButton==requestScope.pageInfo.pageNum?'ulClick':'ulNotClick'}">${pagingButton}</li>
									</c:forEach>
									<c:if test="${!requestScope.pageInfo.isLastPage}">
										<li data-pagenum="${requestScope.pageInfo.nextPage}" class="ulNotClick">&#155;</li>
										<li data-pagenum="${requestScope.pageInfo.pages}" class="ulNotClick">&#187;</li>
									</c:if>
								</ul>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<script src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<%@ include file="common/dataManagement-script.jsp" %>
		<script src="assets/global/plugins/notice-dist/js/notice.js"></script>
		<script src="assets/global/plugins/lobibox-dist/js/lobibox.min.js"></script>
		<script src="assets/pages/scripts/wallpaperTheme.js"></script>
	</body>
</html>

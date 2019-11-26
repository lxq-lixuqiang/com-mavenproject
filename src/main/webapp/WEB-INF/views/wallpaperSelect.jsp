<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>zhai_壁纸数据-查看壁纸</title>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
		<link href="assets/global/plugins/tabularStylePlug-in/css/font-awesome.min.css" rel="stylesheet">
		<link href="assets/global/plugins/tabularStylePlug-in/css/tabularStylePlug-in.css" rel="stylesheet">
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<%@ include file="common/dataManagement-link.jsp" %>
		<link href="assets/global/plugins/notice-dist/css/noticejs.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/animate.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/Lobibox.min.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/LobiboxClass.css" rel="stylesheet">
		<link href="assets/pages/css/wallpaperSelect.css" rel="stylesheet">
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
						<div id="currentLocation">
							<p><a href="dataManagement">壁纸数据</a><span>&#155;</span><a href="wallpaperTheme">主题壁纸</a><span>&#155;</span>查看壁纸</p>
						</div>
						<input type="hidden" id="errorInfo" value="${requestScope.msg}"/>
						<input type="hidden" id="wallpaperThemeId" value="${sessionScope.WallpaperSelect_wallpaperThemeId}" />
					
						<c:if test="${requestScope.pageInfo.total >0}" var="notNull">
							<ul class="ulImgClass">
								<c:forEach var="wallpaper" items="${requestScope.pageInfo.list}">
									<li class="li">
										<h4></h4>
										<div class="position-relative">
											<div class="imgSize">
												<c:choose>
													<c:when test="${wallpaper.wallpaper != ''}"><img src="${pageContext.request.contextPath}/assets/pages/img/wallpaperImg/${wallpaper.wallpaper}" class="imgClass" /></c:when>
													<c:otherwise></c:otherwise>
												</c:choose>
											</div>
											<input data-id="${wallpaper.id}" accept="image/*" type="file" class="fileUpload" />
										</div>
										<div class="divClass">
											<button data-id="${wallpaper.id}" type="button" class="btn btn-info update">
												<span class="glyphicon glyphicon-pencil"></span> 修 改
											</button>
											<button data-id="${wallpaper.id}" type="button" class="btn btn-danger delete">
												<span class="glyphicon glyphicon-trash"></span> 删 除
											</button>
											<button type="button" class="btn btn-primary save">
												<span class="glyphicon glyphicon-ok"></span> 保 存
											</button>
											<button data-id="${wallpaper.id}" type="button" class="btn btn-default cancel">
												<span class="glyphicon glyphicon-remove"></span> 取 消
											</button>
										</div>
									</li>
								</c:forEach>
							</ul>
						</c:if>
						<c:if test="${not notNull}">
							 <div id="nullImgDiv">
								<img alt="空" id="nullImg" src="${pageContext.request.contextPath}/assets/pages/img/common/Imageddd.png"/>
							</div>
						</c:if>
						
						<div style="clear:both;"></div>
						<p class="text-right">
							<button type="button" id="add" class="btn btn-info addButton">
								<span class="glyphicon glyphicon-plus"></span> 添加壁纸
							</button>
							<a href="wallpaperTheme" class="btn btn-default">
								<span class="glyphicon glyphicon-chevron-left"></span> 返 回
							</a>
						</p>

						<c:if test="${notNull}">
							<div id="pageSizeAndpageNum">
								<ul>
									<c:if test="${!requestScope.pageInfo.isFirstPage}">
										<li data-pagenum="1" class="ulNotClick">&#171;</li>
										<li data-pagenum="${requestScope.pageInfo.prePage}" class="ulNotClick">&#139;</li>
									</c:if>
									<c:forEach var="pagingButton" items="${requestScope.WallpaperSelect_pagingButton}">
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
		<script src="assets/global/plugins/tabularStylePlug-in/js/bootstable.js"></script>
		<script src="assets/global/plugins/tabularStylePlug-in/js/tabularStylePlug-in.js"></script>
		<%@ include file="common/dataManagement-script.jsp" %>
		<script src="assets/global/plugins/notice-dist/js/notice.js"></script>
		<script src="assets/global/plugins/lobibox-dist/js/lobibox.min.js"></script>
		<script src="assets/pages/scripts/wallpaperSelect.js"></script>
	</body>
</html>

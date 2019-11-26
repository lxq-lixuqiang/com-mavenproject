<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_宅图库</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonFootLink.jsp" %>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
		<link href="assets/global/plugins/3Dchange/css/style.css" type="text/css" rel="stylesheet">
		<link href="assets/pages/css/wallpaperVisit.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
			</div>
			<div id="log">
				<div>
					<h1>宅图库</h1>
				</div>
			</div>
			
			<div class="section">
				<h2>${requestScope.pageInfo.list[0].wallpaperTheme.theme}<span>(${requestScope.pageInfo.pageNum}/${requestScope.pageInfo.pages})</span></h2>
				<c:if test="${requestScope.pageInfo.total>0}" var="notNull">
					<p>
					壁纸日期：<fmt:formatDate value="${requestScope.pageInfo.list[0].wallpaperTheme.date}" pattern="yyyy年MM月dd日" />
					<a href="download?downloadName=${requestScope.pageInfo.list[0].wallpaper}">下载原图</a>
					</p>
				</c:if>
			</div>
			<c:if test="${notNull}" >
				<div class="section_imgSize">
					<div class="slider__nav">
					    <div class="slider__nav-arrows" style="height:100%;">
					   	 	<c:if test="${requestScope.pageInfo.isFirstPage}" var="isFirst">
						      <div class="slider__nav-arrow" style="height:100%;" id="left">
						      	to left
						      </div>
					    	</c:if>
					    	<c:if test="${not isFirst}">
						      <a href="wallpaperVisit?pageNum=${requestScope.pageInfo.prePage}" style="height:100%;" class="slider__nav-arrow slider__nav-arrow--left" id="left">
						      	to left
						      </a>
					    	</c:if>
					    	
					    	<c:if test="${requestScope.pageInfo.isLastPage}" var="isLast">
						      <div class="slider__nav-arrow" style="height:100%;" id="right">
						      	to right
						      </div>
					      	</c:if>
					      	<c:if test="${not isLast}">
						      <a href="wallpaperVisit?pageNum=${requestScope.pageInfo.nextPage}" style="height:100%;" class="slider__nav-arrow slider__nav-arrow--right" id="right">
						      	to right
						      </a>
					      	</c:if>
					    </div>
					  </div>
					  <img id="ImgClass" src="assets/pages/img/wallpaperImg/${requestScope.pageInfo.list[0].wallpaper}" />
				</div>
			</c:if>
			<c:if test="${not notNull}">
				<div style="width:100%;height:550px;text-align: center;padding-top: 200px;">
					<h1 style="color:#FE7322;">暂无图片</h1>
				</div>
			</c:if>
			<c:if test="${notNull}">
				<div class="section">
					<div class="paging">
						<h3>
							<c:if test="${requestScope.pageInfo.isLastPage == false}">
								<a href="wallpaperVisit?pageNum=${requestScope.pageInfo.nextPage}">下一张</a>
							</c:if>
							<c:if test="${requestScope.pageInfo.isFirstPage == false}">
								<a href="wallpaperVisit?pageNum=${requestScope.pageInfo.prePage}">上一张</a>
							</c:if>
						</h3>
						<h4>跳转到：</h4>
						<ul class="pagingClass">
							<c:forEach items="${requestScope.pageInfo.navigatepageNums}" var="wallpaperPaging">
								<li>
									<c:if test="${wallpaperPaging == requestScope.pageInfo.pageNum}" var="isEq">
										<a class="notA" href="javascript:;">
											[${wallpaperPaging}]
										</a>
									</c:if>
									<c:if test="${not isEq}">
										<a href="wallpaperVisit?pageNum=${wallpaperPaging}">
											[${wallpaperPaging}]
										</a>
									</c:if>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</c:if>
		</div>
		<%@ include file="common/foot.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/global/plugins/3Dchange/index.js"></script>
		<script type="text/javascript" src="assets/pages/scripts/wallpaperVisit.js"></script>
	</body>
</html>
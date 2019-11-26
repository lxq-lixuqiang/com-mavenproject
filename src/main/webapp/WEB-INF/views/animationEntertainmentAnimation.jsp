<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>zhai_动漫数据-娱乐动漫</title>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<%@ include file="common/dataManagement-link.jsp" %>
		<link href="assets/global/plugins/notice-dist/css/animate.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/noticejs.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/Lobibox.min.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/LobiboxClass.css" rel="stylesheet">
		<link href="assets/pages/css/animationEntertainmentAnimation.css" rel="stylesheet">
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
							$(".sortable-accordion div:nth-of-type(4)").attr("id","showDiv");
							$('.sortable-accordion div:not(#showDiv)').slideToggle(1);
						</script>
					</div>
					<div class="section-botton-right">
						<div class="heightFixed">
						<div id="currentLocation">
							<p><a href="dataManagement">动漫数据</a><span>&#155;</span>娱乐动漫</p>
						</div>
						<input type="hidden" id="msg" value="${requestScope.msg}" />
						
						<div>
							<p class="findClass">
								<select id="entertainmentTypeId">
									<option value="-1">全部</option>
									<c:forEach items="${requestScope.animationEntertainmentAnimation_animationEntertainmentTypes}" var="animationEntertainmentType">
										<option ${sessionScope.animationEntertainmentAnimation_entertainmentTypeId == animationEntertainmentType.id?'selected':'' } value="${animationEntertainmentType.id}">${animationEntertainmentType.typeName}</option>
									</c:forEach>
								</select>
								<input type="text" autocomplete="off" id="name" value="${sessionScope.animationEntertainmentAnimation_name}" placeholder="搜索名称" maxlength="10" /><img id="select" alt="搜索" src="${pageContext.request.contextPath}/assets/pages/img/common/ImageSelect01.png" />
							</p>
						</div>
						
						<c:if test="${requestScope.pageInfo.total >0}" var="notNull">
							<c:forEach var="animationEntertainmentAnimation" items="${requestScope.pageInfo.list}">
								<dl class="dlClass text-left">
									<dt class="text-center"><img class="imgClass" src="${pageContext.request.contextPath}/assets/pages/img/animationEntertainmentAnimationImg/${animationEntertainmentAnimation.picture}"/></dt>
									<dd>
										<p class="themeClass">名称：${animationEntertainmentAnimation.name}</p>
										<p>日期：<fmt:formatDate value="${animationEntertainmentAnimation.dateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
										<p class="text-center">
											<a href="animationEntertainmentAnimationUpdate?id=${animationEntertainmentAnimation.id}" class="updateButton btn btn-info">
												<span class="glyphicon glyphicon-pencil"></span> 修 改
											</a>
											<button data-id="${animationEntertainmentAnimation.id}" type="button" class="updateButton btn btn-danger delete">
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
							<a href="animationEntertainmentAnimationAdd" class="updateButton btn btn-info">
								<span class="glyphicon glyphicon-plus"></span> 添加娱乐动漫
							</a>
						</div>
						
						<c:if test="${notNull}">
							<div id="pageSizeAndpageNum">
								<ul>
									<c:if test="${!requestScope.pageInfo.isFirstPage}">
										<li data-pagenum="1" class="ulNotClick">&#171;</li>
										<li data-pagenum="${requestScope.pageInfo.prePage}" class="ulNotClick">&#139;</li>
									</c:if>
									<c:forEach var="pagingButton" items="${requestScope.animationEntertainmentAnimation_pagingButton}">
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
		<script src="assets/pages/scripts/animationEntertainmentAnimation.js"></script>
	</body>
</html>

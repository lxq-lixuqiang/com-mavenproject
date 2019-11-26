<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_更改图片</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/notice-dist/css/noticejs.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/notice-dist/css/animate.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/updateUserHeadPictureId.css" />
	</head>
	<body>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
				<%@ include file="common/userImg.jsp" %>
			</div>
			<div class="section">
				<div class="section-top">
					<c:if test="${sessionScope.loginUser.loginIdentity=='Administrators'}" var="isNo">
						<%@ include file="common/section-top.jsp" %>
					</c:if>
					<c:if test="${not isNo}">
						<%@ include file="common/userSection-top.jsp" %>
					</c:if>
					<script>
						document.getElementById("updateUserHeadPictureId").setAttribute("class","click");
					</script>
				</div>
				<div class="section-botton">
					<input type="hidden" id="userId" value="${sessionScope.loginUser.id}"/>
					<input type="hidden" id="oldId" value="${sessionScope.loginUser.userHeadPicture.id}" />
					<c:if test="${requestScope.pageInfo.total >0}" var="notNull">
						<ul class="pictureClass">
							<c:forEach items="${requestScope.pageInfo.list}" var="updateUserHeadPicture">
								<li class="position-relative">
									<img class="imgClass" src="${pageContext.request.contextPath}/assets/pages/img/headPicture/${updateUserHeadPicture.headPicture}" />
									<div class="spinkitDiv"></div>
									<p data-headpicture="${updateUserHeadPicture.headPicture}" data-id="${updateUserHeadPicture.id}" class="spinkitPClick"></p>
								</li>
							</c:forEach>
						</ul>
					</c:if>
					<c:if test="${not notNull}">
						<div id="nullImgDiv">
							<img alt="空" id="nullImg" src="${pageContext.request.contextPath}/assets/pages/img/common/Imageddd.png"/>
						</div>
					</c:if>
					
					<c:if test="${notNull}">
						<div id="pageSizeAndpageNum">
							<ul>
								<c:if test="${!requestScope.pageInfo.isFirstPage}">
									<li data-pagenum="1" class="ulNotClick">&#171;</li>
									<li data-pagenum="${requestScope.pageInfo.prePage}" class="ulNotClick">&#139;</li>
								</c:if>
								<c:forEach var="pagingButton" items="${requestScope.userHeadPictureId_pagingButton}">
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
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="assets/global/plugins/notice-dist/js/notice.js" ></script>
		<script type="text/javascript" src="assets/pages/scripts/updateUserHeadPictureId.js" ></script>
	</body>
</html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_宅资讯</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonFootLink.jsp" %>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
		<link href="assets/pages/css/gameInformationVisit.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
			</div>
			<div id="log">
				<div>
					<h1>宅资讯</h1>
				</div>
			</div>
			<div id="title">
				<div>
					<h1>${requestScope.pageInfo.list[0].informationThemeId.theme}</h1>
				</div>
				<div>
					<p>发布日期：<fmt:formatDate value="${requestScope.pageInfo.list[0].informationThemeId.date}" pattern="yyyy年MM月dd日"/></p>
				</div>
			</div>
			
			<div class="section">
				<div class="sectionInfo">
					<ul>
						<c:if test="${requestScope.pageInfo.total >0}" var="notNull">
							<c:forEach items="${requestScope.pageInfo.list}" var="gameInformation" >
								<c:if test="${gameInformation.infoOrImgType == 1}">
									<li class="sectionContentImg">
										<img class="imgSize" src="assets/pages/img/gameInformationImg/${gameInformation.infoOrImg}"/>
									</li>
								</c:if>
								<c:if test="${gameInformation.infoOrImgType == 0}">
									<li class="sectionContentInfo">
										<p>${gameInformation.infoOrImg}</p>
									</li>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${not notNull}">
							<li class="nullImg">
								<h1>暂无内容</h1>
								<img src="assets/pages/img/common/Imageddd.png"/>
							</li>
						</c:if>
					</ul>
					<c:if test="${notNull}">
						<div id="pageSizeAndpageNum">
							<ul>
								<c:forEach var="pagingButton" items="${requestScope.gameInformationVisit_pagingButton}">
									<li data-pagenum="${pagingButton}" class="${pagingButton=='...'?'ulreadlyClick':pagingButton==requestScope.pageInfo.pageNum?'ulClick':'ulNotClick'}">
										${pagingButton}
									</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
				</div>
			</div>
		</div>
		<%@ include file="common/foot.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/pages/scripts/gameInformationVisit.js"></script>
	</body>
</html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>zhai_动漫数据-动漫信息</title>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<%@ include file="common/dataManagement-link.jsp" %>
		<link href="assets/global/plugins/notice-dist/css/animate.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/noticejs.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/Lobibox.min.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/LobiboxClass.css" rel="stylesheet">
		<link href="assets/global/plugins/bootstrap-popover-dist/css/bootstrap-popover-x.min.css" rel="stylesheet">
		<link href="assets/pages/css/animationInfo.css" rel="stylesheet">
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
							<p><a href="dataManagement">动漫数据</a><span>&#155;</span>动漫信息</p>
						</div>
						<input type="hidden" id="msg" value="${requestScope.msg}" />
						
						<div>
							<p class="findClass">
								<select id="animationType">
									<option value="全部">全部</option>
									<c:forEach items="${requestScope.animationInfo_animationTypes}" var="animationType">
										<option ${sessionScope.animationInfo_animationType == animationType.type ?'selected':''} value="${animationType.type}">${animationType.type}</option>
									</c:forEach>
								</select>
								<input type="text" autocomplete="off" id="animationName" value="${sessionScope.animationInfo_animationName}" placeholder="搜索动漫名称" maxlength="10" /><img id="select" alt="搜索" src="${pageContext.request.contextPath}/assets/pages/img/common/ImageSelect01.png" />
							</p>
						</div>
						<div>点击图片可查看其他信息或修改信息</div>
						
						<c:if test="${requestScope.pageInfo.total >0}" var="notNull">
							<c:forEach var="animationInfo" items="${requestScope.pageInfo.list}" varStatus="status">
								<dl class="dlClass text-left">
									<dt class="text-center">
										<img data-target="#myPopover${status.index}" data-toggle="popover-x" data-placement="right right-top" class="imgClass" src="${pageContext.request.contextPath}/assets/pages/img/animationInfoImg/${animationInfo.animationPicture}"/>
									</dt>
									<dd>
										<div id="myPopover${status.index}" class="popover popover-default">
											<div class="arrow"></div>
											<h3 class="popover-title">
												<span class="close pull-right" data-dismiss="popover-x">×</span>
												 简 介：
											</h3>
											<div class="popover-content">
											 	${animationInfo.animationContent}
											</div>
											<div class="popover-footer">
												<a href="animationInfoUpdate?id=${animationInfo.id}" class="updateButton btn btn-info btn-sm">
												<span class="glyphicon glyphicon-pencil"></span> 修 改
												</a>
											</div>
										</div>
									
										<p class="text-left">名称：${animationInfo.animationName}</p>
										<p class="text-left">季度：${animationInfo.animationQuarter}季</p>
										<p class="text-left">标签：${animationInfo.animationType}</p>
										<p class="text-left">日期：<fmt:formatDate value="${animationInfo.animationDate}" pattern="yyyy-MM-dd" /></p>
										<p class="text-left">分类：${animationInfo.classificationId.name}</p>
										<p class="text-center">
											<a href="animationSelect?animationInfoId=${animationInfo.id}" class="updateButton btn btn-sm btn-info">
												<span class="glyphicon glyphicon-eye-open"></span> 查 看
											</a>
											<button data-id="${animationInfo.id}" class="updateButton btn btn-danger btn-sm delete">
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
							<a href="animationInfoAdd" class="updateButton btn btn-info">
								<span class="glyphicon glyphicon-plus"></span> 添加动漫
							</a>
						</div>
						
						<c:if test="${notNull}">
							<div id="pageSizeAndpageNum">
								<ul>
									<c:if test="${!requestScope.pageInfo.isFirstPage}">
										<li data-pagenum="1" class="ulNotClick">&#171;</li>
										<li data-pagenum="${requestScope.pageInfo.prePage}" class="ulNotClick">&#139;</li>
									</c:if>
									<c:forEach var="pagingButton" items="${requestScope.animationInfo_pagingButton}">
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
		<script src="assets/global/plugins/bootstrap-popover-dist/bootstrap-popover-x.min.js"></script>
		<script src="assets/pages/scripts/animationInfo.js"></script>
	</body>
</html>

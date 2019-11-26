<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>zhai_用户数据-用户信息</title>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<%@ include file="common/dataManagement-link.jsp" %>
		<link href="assets/global/plugins/notice-dist/css/noticejs.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/animate.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/Lobibox.min.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/LobiboxClass.css" rel="stylesheet">
		<link href="assets/pages/css/userInfo.css" rel="stylesheet">
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
							$(".sortable-accordion div:nth-of-type(2)").attr("id","showDiv");
							$('.sortable-accordion div:not(#showDiv)').slideToggle(1);
						</script>
					</div>
					<div class="section-botton-right">
						<div id="currentLocation">
							<p><a href="dataManagement">用户数据</a><span>&#155;</span>用户信息</p>
						</div>
						<input type="hidden" value="${requestScope.msg}" id="msg" />
						
						<div class="controllerSize">
							<div class="heightFixed">
							<p class="findClass">
								<input type="text" autocomplete="off" id="username" value="${sessionScope.UserInfo_username}" placeholder="搜索用户名" maxlength="10" /><img id="select" alt="搜索" src="${pageContext.request.contextPath}/assets/pages/img/common/ImageSelect01.png" />
							</p>
							<c:if test="${requestScope.pageInfo.total >0}" var="notNull">
								<table>
									<tr>
										<th class="text-center">头像</th>
										<th class="text-center">用户名</th>
										<th class="text-center">手机号码</th>
										<th class="text-center">操作</th>
									</tr>
									<c:forEach var="user" varStatus="userIndex" items="${requestScope.pageInfo.list}">
										<tr>
											<td><img alt="头像" src="${pageContext.request.contextPath}/assets/pages/img/userImages/${user.userImg}"/></td>
											<td>${user.username}</td>
											<td>${user.phone}</td>
											<td>
												<button type="button"class="delete btn btn-danger btn-xs" data-id="${user.id}">
													<span class="glyphicon glyphicon-trash"></span> 删 除
												</button>
											</td>
										</tr>
									</c:forEach>
								</table>
							</c:if>
							<c:if test="${not notNull}">
								<div id="nullImgDiv">
									<img alt="空" id="nullImg" src="${pageContext.request.contextPath}/assets/pages/img/common/Imageddd.png"/>
								</div>
							</c:if>
							</div>
							
							
							<c:if test="${notNull}">
								<div id="pageSizeAndpageNum">
									<ul>
										<c:if test="${!requestScope.pageInfo.isFirstPage}">
											<li data-pagenum="1" class="ulNotClick">&#171;</li>
											<li data-pagenum="${requestScope.pageInfo.prePage}" class="ulNotClick">&#139;</li>
										</c:if>
										<c:forEach var="pagingButton" items="${requestScope.UserInfo_pagingButton}">
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
		</div>
		<script src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<%@ include file="common/dataManagement-script.jsp" %>
		<script src="assets/global/plugins/notice-dist/js/notice.js"></script>
		<script src="assets/global/plugins/lobibox-dist/js/lobibox.min.js"></script>
		<script src="assets/pages/scripts/userInfo.js"></script>
	</body>
</html>

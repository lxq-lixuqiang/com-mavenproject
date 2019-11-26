<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>zhai_用户反馈</title>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<link href="assets/global/plugins/lobibox-dist/css/Lobibox.min.css" type="text/css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/LobiboxClass.css" type="text/css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/animate.css" type="text/css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/noticejs.css" type="text/css" rel="stylesheet">
		<link href="assets/pages/css/userMessageFeedback.css" type="text/css" rel="stylesheet">
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
						document.getElementById("userMessageFeedback").setAttribute("class","click");
					</script>
				</div>
				<div class="section-botton">
					<input type="hidden" value="${requestScope.msg}" id="msg" />
					<p class="findClass">
						<select id="type">
							<option value="-1">全部</option>
							<option ${sessionScope.UserMessageFeedback_type == 0?'selected':''} value="0">问题</option>
							<option ${sessionScope.UserMessageFeedback_type == 1?'selected':''} value="1">建议</option>
						</select>
						<input type="text" autocomplete="off" id="title" value="${sessionScope.UserMessageFeedback_title}" placeholder="搜索标题" maxlength="10" /><img id="select" alt="搜索" src="${pageContext.request.contextPath}/assets/pages/img/common/ImageSelect01.png" />
					</p>
					
					<c:if test="${requestScope.pageInfo.total >0}" var="notNull">
						<ul class="userMessageFeedbackUlClass">
							<c:forEach items="${requestScope.pageInfo.list}" var="userMessageFeedback">
								<li>
									<h4>${userMessageFeedback.title}</h4>
									<div>${userMessageFeedback.content}</div>
									<p><span>发送人：${userMessageFeedback.sender}</span><span>类型：${userMessageFeedback.type==0?'问题':'建议'}</span><span>日期：<fmt:formatDate value='${userMessageFeedback.dateTime}' pattern='yyyy-MM-dd hh:mm:ss'/></span></p>
									<p>
										<button type="button"class="delete btn btn-danger btn-xs" data-id="${userMessageFeedback.id}">
											<span class="glyphicon glyphicon-trash"></span> 删 除
										</button>
									</p>
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
								<c:forEach var="pagingButton" items="${requestScope.UserMessageFeedback_pagingButton}">
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
		<script src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/global/plugins/lobibox-dist/js/lobibox.min.js"></script>
		<script type="text/javascript" src="assets/global/plugins/notice-dist/js/notice.js"></script>
		<script type="text/javascript" src="assets/pages/scripts/userMessageFeedback.js"></script>
	</body>
</html>
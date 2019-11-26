<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>zhai_公共数据-画展</title>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<%@ include file="common/dataManagement-link.jsp" %>
		<link href="assets/global/plugins/notice-dist/css/animate.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/noticejs.css" rel="stylesheet">
		<link href="assets/pages/css/commonIllustrations.css" rel="stylesheet">
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
							$(".sortable-accordion div:nth-of-type(1)").attr("id","showDiv");
							$('.sortable-accordion div:not(#showDiv)').slideToggle(1);
						</script>
					</div>
					<div class="section-botton-right">
						<div id="currentLocation">
							<p><a href="dataManagement">公共数据</a><span>&#155;</span>画展</p>
						</div>
						<input type="hidden" id="msg" value="${requestScope.msg}" />
						
						<c:forEach var="illustration" items="${requestScope.pageInfo.list}">
							<dl class="dlClass text-left">
								<dt class="text-center"><img class="imgClass" src="${pageContext.request.contextPath}/assets/pages/img/illustrations/${illustration.picture}"/></dt>
								<dd>
									<p>标题：${illustration.title}</p>
									<p>主题：${illustration.theme}</p>
									<p>作者：${illustration.author}</p>
									<p>时间：<fmt:formatDate value="${illustration.datetime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
									<p class="text-right">
										<a href="commonIllustrationsUpdate?id=${illustration.id}" class="updateButton btn btn-info">
											<span class="glyphicon glyphicon-pencil"></span> 修 改
										</a>
									</p>
								</dd>
							</dl>
						</c:forEach>
						
						<div id="pageSizeAndpageNum">
							<ul>
								<c:if test="${!requestScope.pageInfo.isFirstPage}">
									<li data-pagenum="1" class="ulNotClick">&#171;</li>
									<li data-pagenum="${requestScope.pageInfo.prePage}" class="ulNotClick">&#139;</li>
								</c:if>
								<c:forEach var="pagingButton" items="${requestScope.CommonIllustrations_pagingButton}">
									<li data-pagenum="${pagingButton}" class="${pagingButton=='...'?'ulreadlyClick':pagingButton==requestScope.pageInfo.pageNum?'ulClick':'ulNotClick'}">${pagingButton}</li>
								</c:forEach>
								<c:if test="${!requestScope.pageInfo.isLastPage}">
									<li data-pagenum="${requestScope.pageInfo.nextPage}" class="ulNotClick">&#155;</li>
									<li data-pagenum="${requestScope.pageInfo.pages}" class="ulNotClick">&#187;</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<%@ include file="common/dataManagement-script.jsp" %>
		<script src="assets/global/plugins/notice-dist/js/notice.js"></script>
		<script>
			$(".ulNotClick").click(function(){
				var pageNum=$(this).data("pagenum");
				location.href="commonIllustrations?pageNum="+pageNum;
			});
			if($("#msg").val().length >0){
				new NoticeJs({
				    text: $("#msg").val(),
				    position: 'middleCenter',
				    animation: {
				        open: 'animated zoomIn',
				        close: 'animated zoomOut'
				  }
				}).show();
			}
		</script>
	</body>
</html>

<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>zhai_公共数据-友情链接</title>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
		<link href="assets/global/plugins/tabularStylePlug-in/css/font-awesome.min.css" rel="stylesheet">
		<link href="assets/global/plugins/tabularStylePlug-in/css/tabularStylePlug-in.css" rel="stylesheet">
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<%@ include file="common/dataManagement-link.jsp" %>
		<link href="assets/global/plugins/lobibox-dist/css/Lobibox.min.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/LobiboxClass.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/noticejs.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/animate.css" rel="stylesheet">
		<link href="assets/pages/css/commonFoot.css" rel="stylesheet">
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
						<div class="heightFixed">
						<div id="currentLocation">
							<p><a href="dataManagement">公共数据</a><span>&#155;</span>友情链接</p>
						</div>
						<input type="hidden" id="msg" value="${requestScope.msg}" />
						<p class="findClass">
							<input type="text" autocomplete="off" id="name" value="${sessionScope.CommonFoot_name}" placeholder="搜索名称" maxlength="10" /><img data-pagesize="${requestScope.pageInfo.pageSize}" id="select" alt="搜索" src="${pageContext.request.contextPath}/assets/pages/img/common/ImageSelect01.png" />
						</p>
						
						<div class="table-responsive">
							<c:if test="${requestScope.pageInfo.total >0}" var="notNull">
								<table class="table table-bordered table-striped" id="mytable">
									<thead>
										<tr>
											<th>名称</th>
											<th>路径</th>
										</tr>
									</thead>
								    <tbody>
								    	<c:forEach var="foot" items="${requestScope.pageInfo.list}">
										    <tr>
										    	<td hidden="hidden">${foot.id}</td>
										    	<td class="position-relative">${foot.name}</td>
										    	<td class="position-relative">${foot.path}</td>
										    </tr>
								    	</c:forEach>
								    </tbody>
								 </table>
							 </c:if>
							 <c:if test="${not notNull}">
							 	<div id="nullImgDiv">
									<img alt="空" id="nullImg" src="${pageContext.request.contextPath}/assets/pages/img/common/Imageddd.png"/>
								</div>
							 </c:if>
						</div>
						</div>
						
						<p class="text-right"><button data-pages="${requestScope.pageInfo.pages}" id="addButton" class="btn btn-info"><i class="glyphicon glyphicon-plus"></i> 添加友情链接</button></p>
						<c:if test="${notNull}">
							<div id="pageSizeAndpageNum">
								<ul>
									<c:if test="${!requestScope.pageInfo.isFirstPage}">
										<li data-pagenum="1" class="ulNotClick">&#171;</li>
										<li data-pagenum="${requestScope.pageInfo.prePage}" class="ulNotClick">&#139;</li>
									</c:if>
									<c:forEach var="pagingButton" items="${requestScope.CommonFoot_pagingButton}">
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
		<script src="assets/global/plugins/bootstrap-validator-0.5.4-dist/js/bootstrapValidator.min.js"></script>
		<script src="assets/global/plugins/tabularStylePlug-in/js/bootstable.js"></script>
		<script src="assets/global/plugins/tabularStylePlug-in/js/tabularStylePlug-in.js"></script>
		<%@ include file="common/dataManagement-script.jsp" %>
		<script src="assets/global/plugins/lobibox-dist/js/lobibox.min.js"></script>
		<script src="assets/global/plugins/notice-dist/js/notice.js"></script>
		<script src="assets/pages/scripts/commonFoot.js"></script>
	</body>
</html>

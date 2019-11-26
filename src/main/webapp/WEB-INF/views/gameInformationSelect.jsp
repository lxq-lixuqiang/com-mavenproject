<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>zhai_游戏数据-游戏资讯内容</title>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<%@ include file="common/dataManagement-link.jsp" %>
		<link href="assets/global/plugins/notice-dist/css/animate.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/noticejs.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/Lobibox.min.css" rel="stylesheet">
		<link href="assets/global/plugins/lobibox-dist/css/LobiboxClass.css" rel="stylesheet">
		<link href="assets/global/plugins/radiobutton-Class/style.css" rel="stylesheet">
		<link href="assets/pages/css/gameInformationSelect.css" rel="stylesheet">
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
							$(".sortable-accordion div:nth-of-type(5)").attr("id","showDiv");
							$('.sortable-accordion div:not(#showDiv)').slideToggle(1);
						</script>
					</div>
					<div class="section-botton-right">
						<div class="heightFixed">
						<div id="currentLocation">
							<p><a href="dataManagement">游戏数据</a><span>&#155;</span><a href="gameInformationTheme">游戏资讯</a><span>&#155;</span>游戏资讯内容</p>
						</div>
						<input type="hidden" id="msg" value="${requestScope.msg}" />
						
						<c:if test="${requestScope.pageInfo.total >0}" var="notNull">
							<ul>
							
								<c:forEach var="gameInformation" items="${requestScope.pageInfo.list}">
									<li class="li">
										<h4 class="text-left">
											类型：<span class="type">${gameInformation.infoOrImgType == 0?'文 字':'图 片'}</span>
											<span class="typeClass">
												<input style="outline:none;" id="text" class="blue" name="typeRadio${gameInformation.id}"  type="radio" value="0" /> 文 字
												<input style="outline:none;" id="img" class="blue" name="typeRadio${gameInformation.id}" type="radio" value="1" /> 图 片
											</span>
										</h4>
										<div class="form-inline text-left divNumberClass">
											<h4 class="form-group">
												编号：<span class="number">${gameInformation.serialNumber}</span>
												<input style="width:100px;" type="number" value="" class="numberClass form-control" />
											</h4>
										</div>
										
										<h4 class="titleH4 text-center"></h4>
										<div ${gameInformation.infoOrImgType == 1?'':'style="display: none;"'} class="position-relative">
											<div class="imgClass imgSize">
												<c:if test="${gameInformation.infoOrImgType == 1}">
													<img src="assets/pages/img/gameInformationImg/${gameInformation.infoOrImg}" />
												</c:if>
											</div>
											<input data-id="${gameInformation.id}" accept="image/*" type="file" class="fileUpload" />
										</div>
										
										<div ${gameInformation.infoOrImgType == 0?'':'style="display: none;"'} class="position-relative02">
										
											<p style="text-indent: 2em;height:150px;border:1px solid #fff;" class="context">${gameInformation.infoOrImgType == 0?gameInformation.infoOrImg:''}</p>
											<textarea style="height:150px;" class="contextTextarea form-control"></textarea>	
											
										</div>
										
										<div class="divClass">
											<button data-id="${gameInformation.id}" type="button" class="btn btn-info update">
												<span class="glyphicon glyphicon-pencil"></span> 修 改
											</button>
											<button data-id="${gameInformation.id}" type="button" class="btn btn-danger delete">
												<span class="glyphicon glyphicon-trash"></span> 删 除
											</button>
											<button data-id="${gameInformation.id}" type="button" class="btn btn-primary save">
												<span class="glyphicon glyphicon-ok"></span> 保 存
											</button>
											<button data-id="${gameInformation.id}" type="button" class="btn btn-default cancel">
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
						</div>
						
						<div style="clear: both;"></div>
						<div class="text-right buttonMarginSize">
							<a href="gameInformationAdd?informationThemeId=${sessionScope.gameInformationSelect_informationThemeId}" class="updateButton btn btn-info">
								<span class="glyphicon glyphicon-plus"></span> 添加资讯内容
							</a>
							<a href="gameInformationTheme" class="btn btn-default">
								<span class="glyphicon glyphicon-chevron-left"></span> 返 回
							</a>
						</div>
						
						<c:if test="${notNull}">
							<div id="pageSizeAndpageNum">
								<ul>
									<c:if test="${!requestScope.pageInfo.isFirstPage}">
										<li data-pagenum="1" class="ulNotClick">&#171;</li>
										<li data-pagenum="${requestScope.pageInfo.prePage}" class="ulNotClick">&#139;</li>
									</c:if>
									<c:forEach var="pagingButton" items="${requestScope.gameInformationSelect_pagingButton}">
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
		<script src="assets/pages/scripts/gameInformationSelect.js"></script>
	</body>
</html>

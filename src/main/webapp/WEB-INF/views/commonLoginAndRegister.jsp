<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>zhai_公共数据-更换背景</title>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<%@ include file="common/dataManagement-link.jsp" %>
		<link href="assets/global/plugins/notice-dist/css/noticejs.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/animate.css" rel="stylesheet">
		<link href="assets/pages/css/commonLoginAndRegister.css" rel="stylesheet">
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
							<p><a href="dataManagement">公共数据</a><span>&#155;</span>更换背景</p>
						</div>
						
						<div>
							<ul>
								<c:forEach var="loginAndRegister" items="${requestScope.loginAndRegister}">
									<li>
										<div class="imgDiv">
											<h4>${loginAndRegister.type == 0?'登录':loginAndRegister.type==1?'忘记密码':'注册'}背景</h4><h4>点击图片更换</h4>
											<input type="hidden" value="${loginAndRegister.id}"/>
											<div class="position-relative">
												<div data-imgnum="${loginAndRegister.type}" class="image-holder">
													<img class="imgClass" src="${pageContext.request.contextPath}/assets/pages/img/loginAndRegister/${loginAndRegister.picture}" />
												</div>
												<input accept="image/*" type="file" name="file" class="fileUpload"/>
											</div>
										</div>
										<div class="buttonDiv">
											<button data-num="${loginAndRegister.type}" type="button" class="btn btn-info update">
												<span class="glyphicon glyphicon-pencil"></span> 修 改
											</button>
											<button type="button" class="btn btn-primary save">
												<span class="glyphicon glyphicon-ok"></span> 保 存
											</button>
											<button type="button" class="btn btn-default cancel">
												<span class="glyphicon glyphicon-remove"></span> 取 消
											</button>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		<script src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<%@ include file="common/dataManagement-script.jsp" %>
		<script src="assets/global/plugins/notice-dist/js/notice.js"></script>
		<script src="assets/pages/scripts/commonLoginAndRegister.js"></script>
	</body>
</html>

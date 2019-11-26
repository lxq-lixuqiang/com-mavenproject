<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>zhai_动漫数据-添加动漫信息</title>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<%@ include file="common/dataManagement-link.jsp" %>
		<link href="assets/global/plugins/bootstrap-validator-0.5.4-dist/css/bootstrapValidator.min.css" rel="stylesheet">
		<link href="assets/global/plugins/bootstrap-float-label-dist/bootstrap-float-label.min.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/animate.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/noticejs.css" rel="stylesheet">
		<link href="assets/global/plugins/radiobutton-Class/style.css" rel="stylesheet">
		<link href="assets/pages/css/animationInfoAdd.css" rel="stylesheet">
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
						<div id="currentLocation">
							<p><a href="dataManagement">动漫数据</a><span>&#155;</span><a href="animationInfo">动漫信息</a><span>&#155;</span>添加动漫信息</p>
						</div>
						<input type="hidden" value="${requestScope.msg}" id="msg"/>
						
						<form:form modelAttribute="animationInfoFormBean" enctype="multipart/form-data" id="form">
							<div class="form-group input-group">
								<div id="wrapper" class="divImgHeader">
									<div id="image-holder" class="text-center divImg">
										
									</div> 
								</div>
							 </div>
							
							<div class="form-group input-group">
								<div id="wrapper has-float-label" class="input-group position-relative">
									<input id="fileUpload" class="position-absolute" accept="image/*" type="file" name="file" />
									<span class="input-group-addon">请选择</span>
									<input disabled id="img" class="form-control" />
								</div>
								<form:errors cssClass="errorInfo" path="animationPicture" />
							 </div>
							
							 <div class="form-group input-group">
							  	<span class="has-float-label inputClass">
									<form:input path="animationName" cssClass="form-control" placeholder="请输入名称"/>
									<label for="animationName">名称</label>
								</span>
								<form:errors cssClass="errorInfo" path="animationName" />
							 </div> 
							
							
							<div class="form-group input-group">
								标签：
								<div id="checkboxesClass">
									<form:checkboxes cssClass="blue" items="${sessionScope.animationTypes}" itemLabel="type" itemValue="type" path="animationType"/>
					       		</div>
					       		<form:errors cssClass="errorInfo" path="animationType" />
							</div>
							
							
							 <div class="form-group input-group radiobuttonClass">
								<span class="has-float-label">
									<form:select cssClass="form-control" path="classificationId.id" items="${sessionScope.animationClassifications}" itemLabel="name" itemValue="id" />
									<label for="classificationId.id">分类</label>
								</span>
								<form:errors cssClass="errorInfo" path="classificationId" />
							</div>
							 
							 <div class="form-group input-group">
								<span class="has-float-label inputClass">
									<form:textarea path="animationContent" cssClass="form-control" placeholder="请输入简介"/>
									<label for="animationContent">简介</label>
								</span>
								<form:errors cssClass="errorInfo" path="animationContent" />
							 </div>
							
							<div class="form-group input-group">
								<div class="has-float-label textCenter">
									<button type="submit" class="btn btn-primary">
										<span class="glyphicon glyphicon-ok"></span> 保 存
									</button>
									<a href="animationInfo" class="btn btn-default">
										<span class="glyphicon glyphicon-chevron-left"></span> 返 回
									</a>
								</div>
							</div>
						</form:form>
						
					</div>
				</div>
			</div>
		</div>
		<script src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script src="assets/global/plugins/bootstrap-validator-0.5.4-dist/js/bootstrapValidator.min.js"></script>
		<%@ include file="common/dataManagement-script.jsp" %>
		<script src="assets/global/plugins/notice-dist/js/notice.js"></script>
		<script src="assets/pages/scripts/animationInfoAdd.js"></script>
	</body>
</html>

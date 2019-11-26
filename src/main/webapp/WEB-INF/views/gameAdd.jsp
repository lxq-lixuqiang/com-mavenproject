<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>zhai_游戏数据-添加游戏信息</title>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<%@ include file="common/dataManagement-link.jsp" %>
		<link href="assets/global/plugins/bootstrap-validator-0.5.4-dist/css/bootstrapValidator.min.css" rel="stylesheet">
		<link href="assets/global/plugins/bootstrap-float-label-dist/bootstrap-float-label.min.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/animate.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/noticejs.css" rel="stylesheet">
		<link href="assets/pages/css/gameAdd.css" rel="stylesheet">
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
						<div id="currentLocation">
							<p><a href="dataManagement">游戏数据</a><span>&#155;</span><a href="gameInfo">游戏信息</a><span>&#155;</span>添加游戏信息</p>
						</div>
						<input type="hidden" value="${requestScope.msg}" id="msg"/>
						
						<form:form modelAttribute="gameFormBean" enctype="multipart/form-data" id="form">
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
								<form:errors cssClass="errorInfo" path="picture" />
							 </div>
							
							 <div class="form-group input-group">
							  	<span class="has-float-label inputClass">
									<form:input path="name" cssClass="form-control" placeholder="请输入名称"/>
									<label for="name">名称</label>
								</span>
								<form:errors cssClass="errorInfo" path="name" />
							 </div> 
							 
							 <div class="form-group input-group">
							  	<span class="has-float-label inputClass">
									<form:input path="issuer" cssClass="form-control" placeholder="请输入发行商"/>
									<label for="issuer">发行商</label>
								</span>
								<form:errors cssClass="errorInfo" path="issuer" />
							 </div> 
							 
							  <div class="form-group input-group">
								<div id="wrapper has-float-label" class="input-group position-relative">
									<input id="fileUpload2" accept=".zip,.rar" class="position-absolute" type="file" name="file2" />
									<span class="input-group-addon">请选择</span>
									<input disabled id="game" class="form-control" />
								</div>
								<form:errors cssClass="errorInfo" path="game" />
							 </div>
							 
							 <div class="form-group input-group">
							  	<span class="has-float-label inputClass">
									<form:input path="gamePath" cssClass="form-control" placeholder="请输入其他资源路径"/>
									<label for="gamePath">其他资源路径</label>
								</span>
								<form:errors cssClass="errorInfo" path="gamePath" />
							 </div> 
							 
							 <div class="form-group input-group radiobuttonClass">
								<span class="has-float-label">
									<form:select cssClass="form-control" path="language" items="${sessionScope.languages.entrySet()}" itemLabel="value" itemValue="key" ></form:select>
									<label for="language">语言</label>
								</span>
								<form:errors cssClass="errorInfo" path="language" />
							 </div> 
							 
							 <div class="form-group input-group radiobuttonClass">
								<span class="has-float-label">
									<form:select cssClass="form-control" path="classificationId.id" items="${sessionScope.gameClassifications}" itemLabel="classificationName" itemValue="id" />
									<label for="classificationId.id">分类</label>
								</span>
								<form:errors cssClass="errorInfo" path="classificationId" />
							 </div>
							 
							 <div class="form-group input-group radiobuttonClass">
								<span class="has-float-label">
									<form:select cssClass="form-control" path="platformId.id" items="${sessionScope.gamePlatforms}" itemLabel="platformName" itemValue="id" />
									<label for="platformId.id">平台</label>
								</span>
								<form:errors cssClass="errorInfo" path="platformId" />
							 </div>
							 
							 <div class="form-group input-group radiobuttonClass">
								<span class="has-float-label">
									<form:select cssClass="form-control" path="typeId.id" items="${sessionScope.gameTypes}" itemLabel="typeName" itemValue="id" />
									<label for="typeId.id">类型</label>
								</span>
								<form:errors cssClass="errorInfo" path="typeId" />
							 </div>
							 
							 <div class="form-group input-group">
								<span class="has-float-label inputClass">
									<form:textarea path="content" cssClass="form-control" placeholder="请输入介绍"/>
									<label for="content">介绍</label>
								</span>
								<form:errors cssClass="errorInfo" path="content" />
							 </div>
							 
							 <div class="form-group input-group">
								<span class="has-float-label inputClass">
									<form:textarea path="gameExplain" cssClass="form-control" placeholder="请输入安装说明"/>
									<label for="gameExplain">安装说明</label>
								</span>
								<form:errors cssClass="errorInfo" path="gameExplain" />
							 </div>
							
							<div class="form-group input-group">
								<div class="has-float-label textCenter">
									<button type="submit" class="btn btn-primary">
										<span class="glyphicon glyphicon-ok"></span> 保 存
									</button>
									<a href="gameInfo" class="btn btn-default">
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
		<script src="assets/pages/scripts/gameAdd.js"></script>
	</body>
</html>

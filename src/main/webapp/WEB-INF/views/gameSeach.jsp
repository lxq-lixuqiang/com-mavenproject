<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_宅游戏</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonFootLink.jsp" %>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/css/jquery-spinkit.min.css" />
		<link href="assets/pages/css/gameSeach.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
			</div>
			<div id="log">
				<div class="seach">
					<span>
						<label>宅</label>
						<span>次</span>
						<em>元</em>
						<span>搜索</span>
					</span>
					<input class="seachInput" type="text" ></input>
					<button style="outline:none;" type="button" class="btn btn-info seachButton btn-lg"> 
						<span class="glyphicon glyphicon-search"></span> 搜 索
					</button>
				</div>
			</div>
			<div class="section">
				<h3><span>游 戏</span></h3>
				<p class="pClass01">
					<label>语言:</label>
					<span>
						<a class="clickA01" data-language="null" href="javascript:;">全部</a>
						<c:forEach items="${requestScope.languages.entrySet()}" var="language">
							<a data-language="${language.key}" href="javascript:;">${language.value}</a>
						</c:forEach>
					</span>
				</p>
				<p class="pClass02">
					<label>类别:</label>
					<span>
						<a class="clickA02" data-gameclassification="-1" href="javascript:;">全部</a>
						<c:forEach items="${requestScope.gameClassifications}" var="gameClassification">
							<a data-gameclassification="${gameClassification.id}" href="javascript:;">${gameClassification.classificationName}</a>
						</c:forEach>
					</span>
				</p>
				<p class="pClass03">
					<label>平台:</label>
					<span>
						<a class="clickA03" data-gameplatform="-1" href="javascript:;">全部</a>
						<c:forEach items="${requestScope.gamePlatforms}" var="gamePlatform">
							<a data-gameplatform="${gamePlatform.id}" href="javascript:;">${gamePlatform.platformName}</a>
						</c:forEach>
					</span>
				</p>
				<p class="pClass04">
					<label>类型:</label>
					<span>
						<a class="clickA04" data-gametype="-1" href="javascript:;">全部</a>
						<c:forEach items="${requestScope.gameTypes}" var="gameType">
							<a data-gametype="${gameType.id}" href="javascript:;">${gameType.typeName}</a>
						</c:forEach>
					</span>
				</p>
				<div class="sectionInfo">
					<ul class="InfoClass">
						<c:if test="${requestScope.pageInfo.total>0}" var="notNull">
							<c:forEach items="${requestScope.pageInfo.list}" var="gameInfo" varStatus="status">
								<li class="gameInfo">
									<a target="_blank" href="gameVisit?gameId=${gameInfo.id}"><img src="${pageContext.request.contextPath}/assets/pages/img/gameTheme/${gameInfo.picture}"/></a>
									<label><a target="_blank" href="gameVisit?gameId=${gameInfo.id}">${gameInfo.name}</a></label>
									<span>类型：${gameInfo.typeId.typeName}</span>
									<span>语言：${gameInfo.language}</span>
									<span>发行：${gameInfo.issuer}</span>
									<span>类别：${gameInfo.classificationId.classificationName}</span>
									<span>平台：${gameInfo.platformId.platformName}</span>
								</li>
							</c:forEach>
						</c:if>
						<c:if test="${not notNull}">
							<li class="nullImg">
								<img src="assets/pages/img/common/Imageddd.png"/>
							</li>
						</c:if>
					</ul>
					
					<div id="pageSizeAndpageNum">
						<c:if test="${notNull}">
							<ul>
								<c:forEach var="pagingButton" items="${requestScope.pageInfo.navigatepageNums}">
									<li data-pagenum="${pagingButton}" class="${pagingButton==requestScope.pageInfo.pageNum?'ulClick':'ulNotClick'}">${pagingButton}</li>
								</c:forEach>
								<li>${requestScope.pageInfo.pageNum}/${requestScope.pageInfo.pages}页 共${requestScope.pageInfo.total}条</li>
							</ul>
						</c:if>
					</div>
					
				</div>
			</div>
		</div>
		<%@ include file="common/foot.jsp" %>
		
		<script id="gameSeachTemplate" type="text/template">
			{{#if total}}
				{{#each list}}
					<li class="gameInfo">
						<a target="_blank" href="gameVisit?gameId={{id}}"><img src="${pageContext.request.contextPath}/assets/pages/img/gameTheme/{{picture}}"/></a>
						<label><a target="_blank" href="gameVisit?gameId={{id}}">{{name}}</a></label>
						<span>类型：{{typeId.typeName}}</span>
						<span>语言：{{language}}</span>
						<span>发行：{{issuer}}</span>
						<span>类别：{{classificationId.classificationName}}</span>
						<span>平台：{{platformId.platformName}}</span>
					</li>
				{{/each}}
			{{else}}
				<li class="nullImg">
					<img src="assets/pages/img/common/Imageddd.png"/>
				</li>
			{{/if}}
		</script>
		<script id="pagingButtonTemplate" type="text/template">
			{{#if total}}
				<ul>
					{{#each navigatepageNums}}
						<li {{#neq this ../pageNum}}data-pagenum="{{this}}"{{/neq}} {{#eq this ../pageNum}}class="ulClick"{{/eq}} {{#neq this ../pageNum}}class="ulNotClick"{{/neq}}>{{this}}</li>
					{{/each}}
					<li>{{pageNum}}/{{pages}}页 共{{total}}条</li>
				</ul>
			{{/if}}
		</script>
		
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/handlebars-v4.0.11-dist/handlebars-v4.0.11.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/handlebars-v4.0.11-dist/handlebars-ext.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/jquery-spinkit.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-cookie-1.4.1-dist/jquery.cookie.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/moment-2.22.2-dist/moment.min.js"></script>
		<script type="text/javascript" src="assets/pages/scripts/gameSeach.js"></script>
	</body>
</html>
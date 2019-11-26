<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_宅动漫</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonFootLink.jsp" %>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/css/jquery-spinkit.min.css" />
		<link href="assets/pages/css/animationSeach.css" type="text/css" rel="stylesheet">
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
				<h3><span>动 漫</span></h3>
				<p>
					<label>标签:</label>
					<span>
						<a class="clickA" data-animationtype="null" href="javascript:;">全部</a>
						
						<c:forEach items="${requestScope.animationTypes}" var="animationType">
							<a data-animationtype="${animationType.type}" href="javascript:;">${animationType.type}</a>
						</c:forEach>
						
					</span>
				</p>
				<div class="sectionInfo">
					<ul class="InfoClass">
						<c:if test="${requestScope.pageInfo.total>0}" var="notNull">
							<c:forEach items="${requestScope.pageInfo.list}" var="animationInfo">
							<li>
								<div>
									<a target="_blank" href="animationInfoVisit?animationInfoId=${animationInfo.id}">
										<img src="assets/pages/img/animationInfoImg/${animationInfo.animationPicture}"/>
									</a>
								</div>
								<div>
									<h2><a target="_blank" href="animationInfoVisit?animationInfoId=${animationInfo.id}">${animationInfo.animationName}</a></h2>
									<p>日期：<fmt:formatDate value="${animationInfo.animationDate}" pattern="yyyy年MM月" /></p>
									<p>标签：${animationInfo.animationType}</p>
									<p>简介：${animationInfo.animationContent}</p>
								</div>
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
		
		<script id="animationSeachTemplate" type="text/template">
			{{#if total}}
				{{#each list}}
					<li>
						<div>
							<a target="_blank" href="animationInfoVisit?{{id}}">
								<img src="assets/pages/img/animationInfoImg/{{animationPicture}}"/>
							</a>
						</div>
						<div>
							<h2><a target="_blank" href="animationInfoVisit?animationInfoId={{id}}">{{animationName}}</a></h2>
							<p>日期：{{year animationDate}}</p>
							<p>标签：{{animationType}}</p>
							<p>简介：{{animationContent}}</p>
						</div>
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
		<script type="text/javascript" src="assets/pages/scripts/animationSeach.js"></script>
	</body>
</html>
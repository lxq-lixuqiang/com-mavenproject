<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_宅游戏</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonFootLink.jsp" %>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/css/jquery-spinkit.min.css" />
		<link href="assets/pages/css/gameInformationThemeSeach.css" type="text/css" rel="stylesheet">
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
				<h3><span>游戏资讯</span></h3>
				<p>
					<label>暂无类型可选</label>
				</p>
				<div class="sectionInfo">
					<ul class="borderH3">
						<c:if test="${requestScope.pageInfo.total>0}" var="notNull">
							<c:forEach items="${requestScope.pageInfo.list}" var="gameInformationTheme">
									<li>
										<div>
											<a target="_blank" href="gameInformationVisit?informationThemeId=${gameInformationTheme.id}">
												<img src="${pageContext.request.contextPath}/assets/pages/img/gameInformationImg/${gameInformationTheme.picture}"/>
												<span>
													<label>${gameInformationTheme.theme}</label>
													<label>日期：<fmt:formatDate value="${gameInformationTheme.date}" pattern="yyyy-MM-dd" /></label>
												</span>
											</a>
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
		
		<script id="gameInformationThemeSeachTemplate" type="text/template">
			{{#if total}}
				{{#each list}}
					<li>
						<div>
							<a target="_blank" href="gameInformationVisit?informationThemeId={{id}}">
								<img src="${pageContext.request.contextPath}/assets/pages/img/gameInformationImg/{{picture}}"/>
								<span>
									<label>{{theme}}</label>
									<label>日期：{{date date}}</label>
								</span>
							</a>
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
		<script type="text/javascript" src="assets/pages/scripts/gameInformationThemeSeach.js"></script>
	</body>
</html>
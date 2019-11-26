<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_问题反馈</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-validator-0.5.4-dist/css/bootstrapValidator.min.css" />
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-float-label-dist/bootstrap-float-label.min.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/notice-dist/css/noticejs.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/notice-dist/css/animate.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/radiobutton-Class/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/questionFeedback.css" />
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
						document.getElementById("questionFeedback").setAttribute("class","click");
					</script>
				</div>
				<div class="section-botton">
					<input type="hidden" value="${requestScope.msg}" id="msg"/>
					<form:form modelAttribute="messageFeedbackFormBean" id="form">
						<form:input type="hidden" path="sender" value="${sessionScope.loginUser.username}" />
						
						<div class="form-group input-group">
							<div class="has-float-label inputClass alert alert-warning alert-dismissible fade in">
									<button type="button" class="close" data-dismiss="alert" aria-label="Close" ><span>&times;</span></button>
									为了更快解决问题，请在发生问题时或再次遇到问题时，立即提交反馈。
							</div>
						</div>
						
						<div class="form-group input-group">
							<span class="has-float-label inputClass">
								<form:input path="title" cssClass="form-control" placeholder="请输入标题"/>
								<label for="title">标题</label>
							</span>
							<form:errors cssClass="errorInfo" path="title" />
						</div>
						 
						<div class="form-group input-group radiobuttonClass">
							<form:radiobutton style="outline:none;" cssClass="blue" path="type" value="0" /> 问 题
							<form:radiobutton style="outline:none;" cssClass="blue" path="type" value="1" /> 建 议
							<form:errors cssClass="errorInfo" path="type" />
						</div>
						 
						 <div class="form-group input-group">
							<span class="has-float-label inputClass">
								<form:textarea path="content" cssClass="form-control" placeholder="请输入内容"/>
								<label for="content">内容</label>
							</span>
							<form:errors cssClass="errorInfo" path="content" />
						 </div>
						 
						 <div class="form-group input-group">
							<div class="has-float-label textCenter">
								<button type="submit" class="btn btn-primary">
									<span class="glyphicon glyphicon-ok"></span> 提 交
								</button>
								<button type="reset" id="reset" class="btn btn-default">
									<span class="glyphicon glyphicon-repeat"></span> 重 置
								</button>
							</div>
						 </div>
						
					</form:form>
				</div>
			</div>
		</div>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-validator-0.5.4-dist/js/bootstrapValidator.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/notice-dist/js/notice.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/questionFeedback.js"></script>
	</body>
</html>
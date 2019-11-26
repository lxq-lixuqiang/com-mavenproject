<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_登 录</title>
		<%@ include file="common/commonLink.jsp" %>
		<link href="${pageContext.request.contextPath}/assets/global/plugins/verification-control/css/verify.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/global/plugins/notice-dist/css/noticejs.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/global/plugins/notice-dist/css/animate.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/global/plugins/jquery-phanimate-dist/css/phanimate.jquery.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/pages/css/login.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div class="backgroundColor"></div>
		<div class="login">
		<%@ include file="common/header.jsp" %>
			<div class="loginserver">
				<input type="hidden" id="success" value="${requestScope.success}" />
				<form:form modelAttribute="loginUserFormBean">
					<table class="tableCommenClass">
						<tr>
							<td class="userImgClass" colspan="2">
								<img alt="头像" id="userImg" class="imgSize" src="${pageContext.request.contextPath}/assets/pages/img/userImages/default.jpg" />
							</td>
						</tr>
						<tr>
							<td class="tdClass" colspan="2">
								<div class="custom-input inputyClass">
								  	<label for="username">用户名</label>
								 	<form:input cssClass="form-control" autocomplete="off" maxlength="10" path="username"/>
									<form:errors path="username" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="tdClass">
								<div class="custom-input inputyClass">
								  	<label for="password">密 码</label>
								 	<form:password cssClass="form-control" maxlength="16" path="password"/>
									<form:errors path="password" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="verification-control">
								<div id="mpanel" >
									<div id="mpanel4" ></div>
								</div>
								<span id="verificationError" class="verificationError"></span>
							</td>
						</tr>
						<tr>
							<td class="buttonHeader" colspan="2"><input class="button" value="登 录" type="submit" /></td>
						</tr>
						<tr>
							<td class="registerClass" colspan="2">没有账号？<a href="register">新用户注册</a>|<a href="forgetPassword">忘记密码?</a></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<img id="bodyImg" src="${pageContext.request.contextPath}/assets/pages/img/loginAndRegister/${sessionScope.loginAndRegister}"/>
		<%@ include file="common/commonTiShiInfo.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/verification-control/js/verify.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/notice-dist/js/notice.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-phanimate-dist/js/phanimate.jquery.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-phanimate-dist/js/phanimate.jquery02.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/login.js"></script>
	</body>
</html>
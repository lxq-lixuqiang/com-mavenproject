<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_注册</title>
		<%@ include file="common/commonLink.jsp" %>
		<link href="${pageContext.request.contextPath}/assets/global/plugins/jquery-phanimate-dist/css/phanimate.jquery.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/pages/css/register.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div class="backgroundColor"></div>
		<div class="section">
			<%@ include file="common/header.jsp" %>
			<div class="sectionTable">
				<form:form modelAttribute="userFormBean">
					<table class="tableCommenClass">
						<tr>
							<td class="tdClass" colspan="2">
								<div class="custom-input inputyClass">
								  	<label for="username">用户名</label>
								 	<form:input cssClass="form-control" autocomplete="off" maxlength="10" path="username"/>
								 	<span id="usernameScope"></span>
									<form:errors path="username" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="tdClass" colspan="2">
								<div class="custom-input inputyClass">
								  	<label for="password">密 码</label>
								 	<form:password cssClass="form-control" maxlength="16" showPassword="true" path="password"/>
									<form:errors path="password" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="tdClass" colspan="2">
								<div class="custom-input inputyClass">
								  	<label for="password02">确认密码</label>
								 	<form:password cssClass="form-control" maxlength="16" showPassword="true" path="password02"/>
									<form:errors path="password02" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="tdClass" colspan="2">
								<div class="custom-input inputyClass">
								  	<label for="phone">手机号码</label>
								 	<form:input cssClass="form-control" maxlength="11" path="phone"/>
									<span id="phoneScope"></span>
									<form:errors path="phone"></form:errors>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="buttonHeader">
								<input class="button" type="submit" value="注 册" />
							</td>
						</tr>
						<tr>
							<td colspan="2" class="registerClass">已有账号？<a href="login">立即登录</a></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<img id="bodyImg" src="${pageContext.request.contextPath}/assets/pages/img/loginAndRegister/${sessionScope.loginAndRegister}"/>
		<%@ include file="common/commonTiShiInfo.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-phanimate-dist/js/phanimate.jquery.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-phanimate-dist/js/phanimate.jquery02.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/register.js"></script>
	</body>
</html>
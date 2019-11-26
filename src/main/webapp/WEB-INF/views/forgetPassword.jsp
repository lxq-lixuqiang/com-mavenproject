<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_忘记密码？</title>
		<%@ include file="common/commonLink.jsp" %>
		<link href="${pageContext.request.contextPath}/assets/pages/css/login.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/css/jquery-spinkit.min.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/global/plugins/jquery-phanimate-dist/css/phanimate.jquery.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/assets/pages/css/forgetPassword.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div class="backgroundColor"></div>
		<div class="login">
			<%@ include file="common/header.jsp" %>
			<div class="loginserver">
				<form:form onkeydown="if(event.keyCode==13){return false;}" modelAttribute="forgetPasswordFormBean">
					<table class="tableCommenClass">
						<tr>
							<td class="tdClass" colspan="2">
								<div class="custom-input inputyClass">
								  	<label for="phone">电 话</label>
								 	<form:input cssClass="form-control" path="phone" autocomplete="off" maxlength="11" />
								 	<span class="errorSpan"></span>
								 	<span class="errorSpan"></span>
									<form:errors path="phone" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="tdClass" colspan="2">
								<div class="custom-input inputyClass">
								  	<label for="verification">短信验证</label>
								 	<form:input cssClass="form-control" path="verification" autocomplete="off" maxlength="6" />
								 	<em id="verificationInfo">更换</em>
								 	<span class="errorSpan"></span>
									<form:errors path="verification" />
								</div>
							</td>
						</tr>
						<tr class="disabled">
							<td class="tdClass" colspan="2">
								<div class="custom-input inputyClass">
								  	<label for="newpassword">新密码</label>
								 	<form:password cssClass="form-control" path="newpassword" maxlength="16" />
								 	<span class="errorSpan"></span>
									<form:errors path="newpassword" />
								</div>
							</td>
						</tr>
						<tr class="disabled">
							<td class="tdClass" colspan="2">
								<div class="custom-input inputyClass">
								  	<label for="newpassword02">确认新密码</label>
								 	<form:password cssClass="form-control" path="newpassword02" maxlength="16" />
								 	<span class="errorSpan"></span>
									<form:errors path="newpassword02" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="buttonHeader" colspan="2"><input id="submit" class="button disabled" value="修 改" type="submit" /> <input id="ok" class="button read-only" value="下一步" type="button" /></td>
						</tr>
						<tr>
							<td class="registerClass" colspan="2">已有账号？<a href="login">立即登陆</a></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<img id="bodyImg" src="${pageContext.request.contextPath}/assets/pages/img/loginAndRegister/${sessionScope.loginAndRegister}"/>
		<%@ include file="common/commonTiShiInfo.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/jquery-spinkit.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-phanimate-dist/js/phanimate.jquery.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-phanimate-dist/js/phanimate.jquery02.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/forgetPassword.js"></script>
	</body>
</html>
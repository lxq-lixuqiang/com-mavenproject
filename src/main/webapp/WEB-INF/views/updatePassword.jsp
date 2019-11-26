<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_修改密码</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/jquery-phanimate-dist/css/phanimate.jquery.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/updatePassword.css" />
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
						document.getElementById("updatePassword").setAttribute("class","click");
					</script>
				</div>
				<div class="section-botton">
					<input type="hidden" value="${requestScope.success}" id="successInfo"/>
					<form:form modelAttribute="updatePasswordFormBean">
					<table class="tableCommenClass">
						<tr>
							<td class="tdClass" colspan="2">
								<div class="custom-input inputyClass">
								  	<label for="oldPassword">原密码</label>
								 	<form:password cssClass="form-control" path="oldPassword" maxlength="16" />
									<form:errors path="oldPassword" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="tdClass" colspan="2">
								<div class="custom-input inputyClass">
								  	<label for="newPassword">新密码</label>
								 	<form:password cssClass="form-control" path="newPassword" maxlength="16" />
									<form:errors path="newPassword" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="tdClass" colspan="2">
								<div class="custom-input inputyClass">
								  	<label for="newPassword02">确认新密码</label>
								 	<form:password cssClass="form-control" path="newPassword02" maxlength="16" />
									<form:errors path="newPassword02" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="buttonHeader buttonTdClass" colspan="2"><input class="button" type="submit" value="修改"/><input class="button" type="reset" value="重置" /></td>
						</tr>
					</table>
					</form:form>
				</div>
			</div>
		</div>
		<%@ include file="common/commonTiShiInfo.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-phanimate-dist/js/phanimate.jquery.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-phanimate-dist/js/phanimate.jquery02.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/updatePassword.js"></script>
	</body>
</html>
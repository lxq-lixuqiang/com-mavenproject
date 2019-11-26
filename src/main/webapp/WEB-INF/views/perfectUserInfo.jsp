<%@page import="com.accp.pojo.User.LoginIdentity"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_完善信息</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/administrationUser.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/perfectUserInfo.css"/>
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
						document.getElementById("administrationUser").setAttribute("class","click");
					</script>
				</div>
				<div class="section-botton">
					<div class="myInfo">
						<form:form modelAttribute="userFormBean" enctype="multipart/form-data">
							<table>
								<tr>
									<th colspan="2">
										<span class="marginRight">点击头像更换</span>
										<form:hidden value="${sessionScope.user.id}" path="id"/>
										<form:hidden value="${sessionScope.user.password}" path="password"/>
										<form:hidden value="${sessionScope.user.loginIdentity=='Administrators'?'Administrators':'User'}" path="loginIdentity"/>
									</th>
								</tr>
								<tr>
									<th id="positions" colspan="2">
										<div id="image-holder">
											<img class="marginRight" id="userHearderImg" alt="头像" src="${pageContext.request.contextPath}/assets/pages/img/userImages/${sessionScope.user.userImg}">
										</div>
										<input accept="image/*" id="file" type="file" name="file" />
									</th>
								</tr>
								<tr>
									<td>用户名：</td>
									<td><form:input path="username" maxlength="10" class="inputClass" value="${sessionScope.user.username}" /><span id="usernameScopeError" class="error"><img alt="错误" class="errorImg" src="${pageContext.request.contextPath}/assets/pages/img/common/errorImg.jpg"/><span id="usernameScopeErrorInfo" class="errorInfo"></span></span></td>
								</tr>
								<tr>
									<td>年龄：</td>
									<td id="Age"></td>
								</tr>
								<tr>
									<td>性别：</td>
									<td>
										<select id="sexSelect">
											<option ${sessionScope.user.sex=="Secrecyt"?'selected':''} value="保密">保密</option>
											<option ${sessionScope.user.sex=="Male"?'selected':''} value="男">男</option>
											<option ${sessionScope.user.sex=="Female"?'selected':''} value="女">女</option>
										</select>
										<form:hidden path="sex"/>
									</td>
								</tr>
								<tr>
									<td>手机号码：</td>
									<td><form:input path="phone" maxlength="11" class="inputClass" value="${sessionScope.user.phone}" /><span id="phoneScopeError" class="error"><img alt="错误" class="errorImg" src="${pageContext.request.contextPath}/assets/pages/img/common/errorImg.jpg"/><span class="errorInfo" id="phoneScopeErrorInfo"></span></span></td>
								</tr>
								<tr>
									<td>出生日期：</td>
									<td>
										<input id="birthDataDate" class="inputClass" value="${sessionScope.user.birthData}" type="date" /><span id="birthDataDateScopeError" class="error"><img alt="错误" class="errorImg" src="${pageContext.request.contextPath}/assets/pages/img/common/errorImg.jpg"/><span class="errorInfo" id="birthDataDateScopeErrorInfo"></span></span>
										<form:hidden path="birthData"/>
									</td>
								</tr>
							</table>
							<table>
								<tr>
									<td>邮箱：</td>
									<td><form:input path="email" maxlength="20" class="inputClass inputyClass02" value="${sessionScope.user.email}" /><span id="emailScopeError" class="error"><img alt="错误" class="errorImg" src="${pageContext.request.contextPath}/assets/pages/img/common/errorImg.jpg"/><span id="emailScopeErrorInfo" class="errorInfo"></span></span></td>
								</tr>
								<tr>
									<td>地址：</td>
									<td><form:input path="address" maxlength="20" class="inputClass inputyClass02" value="${sessionScope.user.address}" /></td>
								</tr>
								<tr>
									<td>兴趣爱好：</td>
									<td>
										<textarea id="hobbyTextarea" maxlength="50" style="resize:none" rows="5" cols="27">${sessionScope.user.hobby}</textarea>
										<form:hidden path="hobby"/>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<input class="button" type="submit" value="保存" />
										<input class="button" type="reset" value="重置"/>
										<input style="margin-right:100px;" id="return" class="button" type="button" value="返回"/>
									</td>
								</tr>
							</table>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="common/commonTiShiInfo.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/administrationUser.js"></script>
	</body>
</html>
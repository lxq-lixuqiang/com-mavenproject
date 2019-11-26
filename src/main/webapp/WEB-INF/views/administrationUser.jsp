<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_个人信息</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/administrationUser.css" />
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
						<script>
							document.getElementById("administrationUser").setAttribute("class","click");
						</script>
					</c:if>
					<c:if test="${not isNo}">
						<%@ include file="common/userSection-top.jsp" %>
						<script>
							document.getElementById("administrationUser").setAttribute("class","click");
						</script>
					</c:if>
					
				</div>
				<div class="section-botton">
					<div class="myInfo">
						<table>
							<tr>
								<th colspan="2"><span class="marginRight">${sessionScope.loginUser.loginIdentity=="Administrators"?"管理员":"用户"}</span></th>
							</tr>
							<tr>
								<th colspan="2"><img class="marginRight" id="userHearderImg" alt="头像" src="${pageContext.request.contextPath}/assets/pages/img/userImages/${sessionScope.loginUser.userImg}"></th>
							</tr>
							<tr>
								<td>用户名：</td>
								<td>${sessionScope.loginUser.username}</td>
							</tr>
							<tr>
								<td>年龄：</td>
								<td id="Age"></td>
							</tr>
							<tr>
								<td>性别：</td>
								<td>${sessionScope.loginUser.sex=="Male"?"男":sessionScope.loginUser.sex=="Female"?"女":"保密"}</td>
							</tr>
							<tr>
								<td>手机号码：</td>
								<td>${sessionScope.loginUser.phone}</td>
							</tr>
							<tr>
								<td>出生日期：</td>
								<td><fmt:formatDate value='${sessionScope.loginUser.birthData}' pattern='yyyy-MM-dd'/><input id="birthDataDate" type="hidden" value="<fmt:formatDate value='${sessionScope.loginUser.birthData}' pattern='yyyy-MM-dd'/>"/></td>
							</tr>
						</table>
						<table>
							<tr>
								<th class="updateInfo" colspan="2">
									<a href="perfectUserInfo"><img alt="修改" id="perfectUserImg" src="${pageContext.request.contextPath}/assets/pages/img/common/Image94507.png" />完善资料</a>
									<a id="logOut" href="javascript:;">退出账号</a>
								</th>
							</tr>
							<tr>
								<td>邮箱：</td>
								<td>${sessionScope.loginUser.email}</td>
							</tr>
							<tr>
								<td>地址：</td>
								<td>${sessionScope.loginUser.address}</td>
							</tr>
							<tr>
								<td>兴趣爱好：</td>
								<td>${sessionScope.loginUser.hobby}</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="common/commonTiShiInfo.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/administrationUser.js"></script>
	</body>
</html>
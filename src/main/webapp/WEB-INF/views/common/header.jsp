<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="header">
	<div id="background"></div>
	<div class="navigation">
		<h1 class="zhai"><label>宅</label><span>次</span><em>元</em></h1>
		<p class="address">www.zhai.com</p>
		<ul class="ulClass">
			<c:forEach var="navigationBar" items="${sessionScope.navigationBars}">
				<li><a href="${navigationBar.path}">${navigationBar.name}</a></li>
			</c:forEach>	
		</ul>
		<a id="userHeaderImg" href="${sessionScope.loginUser == null?'login':'administrationUser'}"><img alt="头像" class="userImg" src="${pageContext.request.contextPath}/assets/pages/img/userImages/${sessionScope.loginUser.userImg == null? 'default.jpg' : sessionScope.loginUser.userImg}"></a>
	</div>
</div>
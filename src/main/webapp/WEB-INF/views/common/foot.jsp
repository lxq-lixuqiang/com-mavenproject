<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="foot">
	<div class="footClass">
		<h3>友情链接：</h3>
		<p>
			<c:forEach var="foot" items="${sessionScope.foots}">
				<a target="_blank" href="${foot.path}">${foot.name}</a>
			</c:forEach>
		</p>
	</div>
</div>
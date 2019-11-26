<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_修改动漫信息</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/updateComic.css" />
	</head>
	<body>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
				<%@ include file="common/userImg.jsp" %>
			</div>
			<div class="section">
				<div class="section-top">
					<%@ include file="common/section-top.jsp" %>
					<script>
						document.getElementById("messages").setAttribute("class","click");
					</script>
				</div>
				<div class="section-botton">
					<div class="section-botton-left">
						<%@ include file="common/section-botton-left.jsp" %>
						<script>
							document.getElementById("messagesComic").setAttribute("class","clickClass");
						</script>
					</div>
					<div class="section-botton-right">
						<div class="session-botton-right-TableUser">
							<form:form enctype="multipart/form-data" modelAttribute="comicFormBean">
								<table>
									<tr style="line-height:40px;">
										<td colspan="2"><img id="HearderImg" src="${pageContext.request.contextPath}/assets/pages/img/comicImg/${sessionScope.comic.imgpath}"/></td>
									</tr>
									<tr>
										<td class="right">动漫图片：</td>
										<td class="positionFile">
											<input accept="image/*" id="uploadImg" name="file" type="file" />
											<input type="text" placeholder="请选择动漫图片" name="imgpath02" id="imgpath" />
										</td>
									</tr>
									<tr>
										<td class="right">动漫名称：</td>
										<td>
											<form:hidden value="${sessionScope.comic.id}" path="id"/>
											<form:input maxlength="30" value="${sessionScope.comic.name}" path="name"/>
											<form:errors path="name"></form:errors>
											<span id="nameScope"></span>
										</td>
									</tr>
									<tr>
										<td class="right">集数：</td>
										<td>
											<form:input maxlength="20" value="${sessionScope.comic.setnumber}" path="setnumber"/>
											<form:errors path="setnumber" />
										</td>
									</tr>
									<tr>
										<td class="right">类型：</td>
										<td>
											<form:input maxlength="30" value="${sessionScope.comic.type}" path="type"/>
											<form:errors path="type" />
										</td>
									</tr>
									<tr>
										<td class="right">开播日期：</td>
										<td>
											<input type="date" name="playDate" id="playDate" value="${sessionScope.comic.date}" />
											<form:errors path="date" />
											<form:hidden value="${sessionScope.comic.date}" path="date"/>
										</td>
									</tr>
									<tr>
										<td class="right">地区：</td>
										<td>
											<form:input maxlength="10" value="${sessionScope.comic.region}" path="region"/>
											<form:errors path="region" />
										</td>
									</tr>
									<tr>
										<td class="right">地址：</td>
										<td>
											<form:input maxlength="100" value="${sessionScope.comic.address}" path="address"/>
											<form:errors path="address" />
										</td>
									</tr>
									<tr>
										<td class="right">备注：</td>
										<td>
											<form:input maxlength="100" value="${sessionScope.comic.remark}" path="remark"/>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<input class="button" type="submit" value="修改"/>
											<input class="button" type="reset" value="重置"/>
											<input id="return" class="button" type="button" value="返回"/>
										</td>
									</tr>
								</table>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="Imgxianshi"></div>
		<%@ include file="common/commonTiShiInfo.jsp" %>
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/updateComic.js"></script>
	</body>
</html>
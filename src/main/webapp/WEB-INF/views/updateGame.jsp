<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_修改游戏信息</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/updateGame.css" />
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
							document.getElementById("messagesGame").setAttribute("class","clickClass");
						</script>
					</div>
					<div class="section-botton-right">
						<div class="session-botton-right-TableUser">
							<form:form enctype="multipart/form-data" modelAttribute="gameFormBean">
								<table>
									<tr>
										<td rowspan="10"><img id="HearderImg" src="${pageContext.request.contextPath}/assets/pages/img/gameImg/${sessionScope.getGame.imgpath}"/></td>
									</tr>
									<tr>
										<td class="right">游戏封面：</td>
										<td class="positionFile">
											<input accept="image/*" id="uploadImg" name="file" type="file" />
											<input type="text" placeholder="请选择游戏封面" name="imgpath02" id="imgpath"/>
										</td>
									</tr>
									<tr>
										<td class="right">游戏名称：</td>
										<td>
											<form:hidden value="${sessionScope.game.id}" path="id"></form:hidden>
											<form:input maxlength="15" value="${sessionScope.getGame.name}" path="name"/><form:errors path="name"></form:errors>
											<span id="usernameScope"></span>
										</td>
									</tr>
									<tr>
										<td class="right">游戏类型：</td>
										<td>
											<select id="gametype">
												<option value="其他类型">其他类型</option>
												<option ${sessionScope.getGame.type=="动作游戏ACT"? 'selected':''} value="动作游戏ACT">动作游戏ACT</option>
												<option ${sessionScope.getGame.type=="冒险游戏AVG"? 'selected':''} value="冒险游戏AVG">冒险游戏AVG</option>
												<option ${sessionScope.getGame.type=="角色扮演RPG"? 'selected':''} value="角色扮演RPG">角色扮演RPG</option>
												<option ${sessionScope.getGame.type=="体育竞技SPG"? 'selected':''} value="体育竞技SPG">体育竞技SPG</option>
												<option ${sessionScope.getGame.type=="第一人称射击FPS"? 'selected':''} value="第一人称射击FPS">第一人称射击FPS</option>
												<option ${sessionScope.getGame.type=="第三人称射击TPS"? 'selected':''} value="第三人称射击TPS">第三人称射击TPS</option>
												<option ${sessionScope.getGame.type=="策略战棋SLG"? 'selected':''} value="策略战棋SLG">策略战棋SLG</option>
												<option ${sessionScope.getGame.type=="赛车竞速RAC"? 'selected':''} value="赛车竞速RAC">赛车竞速RAC</option>
												<option ${sessionScope.getGame.type=="模拟经营SIM"? 'selected':''} value="模拟经营SIM">模拟经营SIM</option>
												<option ${sessionScope.getGame.type=="即时战略RTS"? 'selected':''} value="即时战略RTS">即时战略RTS</option>
												<option ${sessionScope.getGame.type=="格斗游戏FTG"? 'selected':''} value="格斗游戏FTG">格斗游戏FTG</option>
											</select>
											<form:hidden path="type"></form:hidden>
										</td>
									</tr>
									<tr>
										<td class="right">发行商：</td>
										<td><form:input maxlength="20" value="${sessionScope.getGame.issue}" path="issue"/><form:errors path="issue"></form:errors><span id="resolutionSizeScope"></span></td>
									</tr>
									<tr>
										<td class="right">游戏平台：</td>
										<td><form:input maxlength="15" value="${sessionScope.getGame.platform}" path="platform"/><form:errors path="platform"></form:errors><span id="resolutionSizeScope"></span></td>
									</tr>
									<tr>
										<td class="right">专题地址：</td>
										<td><form:input maxlength="100" value="${sessionScope.getGame.special}" path="special"/><form:errors path="special"></form:errors></td>
									</tr>
									<tr>
										<td class="right">论坛地址：</td>
										<td><form:input maxlength="100" value="${sessionScope.getGame.forum}" path="forum"/><form:errors path="forum"></form:errors></td>
									</tr>
									<tr>
										<td class="right">备注：</td>
										<td><form:input maxlength="100" value="${sessionScope.getGame.remark}" path="remark"/><form:errors path="remark"></form:errors></td>
									</tr>
									<tr>
										<td colspan="2">
											<input class="updateGameButton" type="submit" value="修改"/>
											<input class="updateGameButton" type="reset" value="重置"/>
											<input id="return" class="updateGameButton" type="button" value="返回"/>
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
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/updateGame.js"></script>
	</body>
</html>
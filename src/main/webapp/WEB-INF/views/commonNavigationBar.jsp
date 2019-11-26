<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>zhai_公共数据-导航栏</title>
		<link href="assets/global/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
		<link href="assets/global/plugins/tabularStylePlug-in/css/font-awesome.min.css" rel="stylesheet">
		<link href="assets/global/plugins/tabularStylePlug-in/css/tabularStylePlug-in.css" rel="stylesheet">
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonSection-topLink.jsp" %>
		<%@ include file="common/section-botton-leftClass.jsp" %>
		<%@ include file="common/dataManagement-link.jsp" %>
		<link href="assets/global/plugins/notice-dist/css/noticejs.css" rel="stylesheet">
		<link href="assets/global/plugins/notice-dist/css/animate.css" rel="stylesheet">
		<link href="assets/pages/css/commonNavigationBar.css" rel="stylesheet">
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
						document.getElementById("dataManagement").setAttribute("class","click");
					</script>
				</div>
				<div class="section-botton">
					<div class="section-botton-left">
						<%@ include file="common/dataManagement-leftMenu.jsp" %>
						<script> 
							$(".sortable-accordion div:nth-of-type(1)").attr("id","showDiv");
							$('.sortable-accordion div:not(#showDiv)').slideToggle(1);
						</script>
					</div>
					<div class="section-botton-right">
						<div id="currentLocation">
							<p><a href="dataManagement">公共数据</a><span>&#155;</span>导航栏</p>
						</div>
						
						<div class="table-responsive">
							<table class="table table-bordered table-striped" id="mytable">
								<thead>
									<tr>
										<th>编号</th>
										<th>名称</th>
										<th>路径</th>
									</tr>
								</thead>
							    	<tbody>
								        <c:forEach var="navigationBar" items="${requestScope.navigationBars}">
											<tr>
												<td class="position-relative">${navigationBar.id}</td>
												<td class="position-relative">${navigationBar.name}</td>
												<td class="position-relative">${navigationBar.path}</td>
											</tr>
										</c:forEach>
							    	</tbody>
							 </table>
						</div> 
						<p id="pClass" style="text-align:right;">编号可以改变顺序，点击 <i class="glyphicon glyphicon-pencil btn-xs box"></i> 按钮可以对表格进行修改。</p>

					</div>
				</div>
			</div>
		</div>
		<script src="assets/global/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<script src="assets/global/plugins/bootstrap-validator-0.5.4-dist/js/bootstrapValidator.min.js"></script>
		<script src="assets/global/plugins/tabularStylePlug-in/js/bootstable.js"></script>
		<script src="assets/global/plugins/tabularStylePlug-in/js/tabularStylePlug-in.js"></script>
		<%@ include file="common/dataManagement-script.jsp" %>
		<script src="assets/global/plugins/notice-dist/js/notice.js"></script>
		<script src="assets/pages/scripts/commonNavigationBar.js"></script>
	</body>
</html>

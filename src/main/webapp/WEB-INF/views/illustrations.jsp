<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<title>zhai_画展</title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/pages/img/common/zhai.ico" />
		<link rel="stylesheet" type="text/css" href="assets/global/plugins/wallgallery-component2/css/default.css" />
		<link rel="stylesheet" type="text/css" href="assets/global/plugins/wallgallery-component2/css/component2.css" />
		<script src="assets/global/plugins/wallgallery-component2/js/modernizr.custom.js"></script>
		<style type="text/css">
			dl>dd{border:1px solid #fff;}
		</style>
	</head>
	<body>
		<div class="container">	
			<h1>3D 画展 <a href="home">返回</a></h1>
			<div id="gr-gallery" class="gr-gallery">
				<div class="gr-main">
					<c:forEach var="illustration" items="${requestScope.illustrations}">
						<figure>
							<div>
								<img src="assets/pages/img/illustrations/${illustration.picture}" alt="${illustration.title}" />
							</div>
							<figcaption>
								<h2><span>${illustration.title}</span></h2>
								<div>
									<dl>
										<dt>主题：</dt><dd>${illustration.theme}</dd>
										<dt>作者：</dt><dd>${illustration.author}</dd>
										<dt>上传时间：</dt><dd><fmt:formatDate value="${illustration.datetime}" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
										<dt>内容：</dt><dd>${illustration.content}</dd>
									</dl>
								</div>
							</figcaption>
						</figure>
					</c:forEach>
				</div>
			</div>
		</div>
		<script src="assets/global/plugins/jquery-3.1.1-dist/jquery-3.1.1.min.js"></script>
		<script src="assets/global/plugins/wallgallery-component2/js/wallgallery.js"></script>
		<script>
			$(function() {
				Gallery.init( {
					layout : [3,3,3,3]
				} );

			});
		</script>
	</body>
</html>
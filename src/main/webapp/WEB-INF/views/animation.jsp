<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>zhai_动漫</title>
		<%@ include file="common/commonLink.jsp" %>
		<%@ include file="common/commonFootLink.jsp" %>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/css/jquery-spinkit.min.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/pages/css/animation.css" />
	</head>
	<body>
		<div>
		<div class="cntrolFormat">
			<div class="headerFormat">
				<%@ include file="common/header.jsp" %>
				<div class="logcomic">
					<img alt="bilibili" src="${pageContext.request.contextPath}/assets/pages/img/animationTop/${requestScope.animationNavigationBar.animationWallpaper}"  />
				</div>
			</div>
			<div class="section">
				<div class="section-header01">
					<div class="section-header01-HeaderImg">
						<div>
							<c:forEach items="${requestScope.newAnimationEntertainmentAnimation1}" var="newAnimationEntertainmentAnimation">
								<a target="_blank" href="animationEntertainmentAnimationVisit?id=${newAnimationEntertainmentAnimation.id}">
									<img src="assets/pages/img/animationEntertainmentAnimationImg/${newAnimationEntertainmentAnimation.picture}"/>
									<span>${newAnimationEntertainmentAnimation.name}</span>
								</a>
							</c:forEach>
						</div>
						<ul class="section-header01-Ul01">
							<li><a class="on" data-comicimg="0" href="javascript:;"></a></li>
							<li><a data-comicimg="1" href="javascript:;"></a></li>
							<li><a data-comicimg="2" href="javascript:;"></a></li>
							<li><a data-comicimg="3" href="javascript:;"></a></li>
							<li><a data-comicimg="4" href="javascript:;"></a></li>
						</ul>
					</div>
					<div class="section-header01-HeaderRight">
						<ul>
							<c:forEach items="${requestScope.newAnimationEntertainmentAnimation2}" var="newAnimationEntertainmentAnimation">
								<li>
									<a target="_blank" href="animationEntertainmentAnimationVisit?id=${newAnimationEntertainmentAnimation.id}">
										<img src="${pageContext.request.contextPath}/assets/pages/img/animationEntertainmentAnimationImg/${newAnimationEntertainmentAnimation.picture}"/>
										<span></span>
										<span>
											<span>${newAnimationEntertainmentAnimation.name}</span>
											<label>
												<fmt:formatDate value="${newAnimationEntertainmentAnimation.dateTime}" pattern="MM.dd" />
												<img src="${pageContext.request.contextPath}/assets/pages/img/common/time.png"/>
											</label>
										</span>
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="section-header02 tuiguang">
					<h1>
						<img src="${pageContext.request.contextPath}/assets/pages/img/common/tuigang.png"/>
						推广
					</h1>
					<ul>
						<c:forEach items="${requestScope.animationEntertainmentAnimation01}" var="animationEntertainmentAnimation">
							<li>
								<a target="_blank" href="animationEntertainmentAnimationVisit?id=${animationEntertainmentAnimation.id}">
									<span>
										<img src="${pageContext.request.contextPath}/assets/pages/img/animationEntertainmentAnimationImg/${animationEntertainmentAnimation.picture}"/>
										<span></span>
										<span>
											<span>
												<fmt:formatDate value="${animationEntertainmentAnimation.dateTime}" pattern="MM.dd" />
												<img src="${pageContext.request.contextPath}/assets/pages/img/common/time.png">
											</span>
										</span>
									</span>
									${animationEntertainmentAnimation.name}
								</a>
							</li>
						</c:forEach>
						
					</ul>
				</div>
				<div class="section-header02 borderSize">
					<h1>
						<img src="${pageContext.request.contextPath}/assets/pages/img/common/donghua.png"/>
						动画
					</h1>
					<ul>
						<c:forEach items="${requestScope.animationEntertainmentAnimation02}" var="animationEntertainmentAnimation" >
							<li>
								<a target="_blank" href="animationEntertainmentAnimationVisit?id=${animationEntertainmentAnimation.id}">
									<span>
										<img src="${pageContext.request.contextPath}/assets/pages/img/animationEntertainmentAnimationImg/${animationEntertainmentAnimation.picture}"/>
										<span></span>
										<span>
											<span>
											<fmt:formatDate value="${animationEntertainmentAnimation.dateTime}" pattern="MM.dd" />
											<img src="${pageContext.request.contextPath}/assets/pages/img/common/time.png">
											</span>
										</span>
									</span>
									${animationEntertainmentAnimation.name}
								</a>
								<span>
									<img src="${pageContext.request.contextPath}/assets/pages/img/common/2018-07-12(18_54_55).png">
									<span><img src="${pageContext.request.contextPath}/assets/pages/img/common/2018-07-12(18_55_15).png"></span>
								</span>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="borderSizeRight">
					<h1>最新</h1>
					<ul>
						<c:forEach items="${requestScope.newAnimationEntertainmentAnimation02}" var="newAnimationEntertainmentAnimation">
							<li>
								<a target="_blank" href="animationEntertainmentAnimationVisit?id=${newAnimationEntertainmentAnimation.id}">
									${newAnimationEntertainmentAnimation.name}
								</a>
							</li>
						</c:forEach>
						<li class="has">
							<a href="animationEntertainmentAnimationSeach">
								查看更多
								<span>></span>
							</a>
						</li>
					</ul>
				</div>
				<div class="section-header03 opera">
					<h1>
						<span>
							<img src="${pageContext.request.contextPath}/assets/pages/img/common/opera.png"/>
							新番
						</span>
						<span id="opera">
							<a data-datetime="0" href="javascript:;">一</a>
							<a data-datetime="1" href="javascript:;">二</a>
							<a data-datetime="2" href="javascript:;">三</a>
							<a data-datetime="3" href="javascript:;">四</a>
							<a data-datetime="4" href="javascript:;">五</a>
							<a data-datetime="5" href="javascript:;">六</a>
							<a data-datetime="6" href="javascript:;">日</a>
						</span>
					</h1>
					
					<div class="section-header03-divClass">
						<ul>
							<c:forEach items="${requestScope.animationInfo1}" var="animationInfo">
								<li>
									<a target="_blank" href="animationInfoVisit?animationInfoId=${animationInfo.id}">
										<label><img src="${pageContext.request.contextPath}/assets/pages/img/animationInfoImg/${animationInfo.animationPicture}"/></label>
										<span>${animationInfo.animationName}</span>
										<span>${animationInfo.animationType}</span>
									</a>
								</li>
							</c:forEach>
						</ul>
						<ul>
							<c:forEach items="${requestScope.animationInfo2}" var="animationInfo">
								<li>
									<a target="_blank" href="animationInfoVisit?animationInfoId=${animationInfo.id}">
										<label><img src="${pageContext.request.contextPath}/assets/pages/img/animationInfoImg/${animationInfo.animationPicture}"/></label>
										<span>${animationInfo.animationName}</span>
										<span>${animationInfo.animationType}</span>
									</a>
								</li>
							</c:forEach>
						</ul>
						<ul>
							<c:forEach items="${requestScope.animationInfo3}" var="animationInfo">
								<li>
									<a target="_blank" href="animationInfoVisit?animationInfoId=${animationInfo.id}">
										<label><img src="${pageContext.request.contextPath}/assets/pages/img/animationInfoImg/${animationInfo.animationPicture}"/></label>
										<span>${animationInfo.animationName}</span>
										<span>${animationInfo.animationType}</span>
									</a>
								</li>
							</c:forEach>
						</ul>
						<ul>
							<c:forEach items="${requestScope.animationInfo4}" var="animationInfo">
								<li>
									<a target="_blank" href="animationInfoVisit?animationInfoId=${animationInfo.id}">
										<label><img src="${pageContext.request.contextPath}/assets/pages/img/animationInfoImg/${animationInfo.animationPicture}"/></label>
										<span>${animationInfo.animationName}</span>
										<span>${animationInfo.animationType}</span>
									</a>
								</li>
							</c:forEach>
						</ul>
						<ul>
							<c:forEach items="${requestScope.animationInfo5}" var="animationInfo">
								<li>
									<a target="_blank" href="animationInfoVisit?animationInfoId=${animationInfo.id}">
										<label><img src="${pageContext.request.contextPath}/assets/pages/img/animationInfoImg/${animationInfo.animationPicture}"/></label>
										<span>${animationInfo.animationName}</span>
										<span>${animationInfo.animationType}</span>
									</a>
								</li>
							</c:forEach>
						</ul>
						<ul>
							<c:forEach items="${requestScope.animationInfo6}" var="animationInfo">
								<li>
									<a target="_blank" href="animationInfoVisit?animationInfoId=${animationInfo.id}">
										<label><img src="${pageContext.request.contextPath}/assets/pages/img/animationInfoImg/${animationInfo.animationPicture}"/></label>
										<span>${animationInfo.animationName}</span>
										<span>${animationInfo.animationType}</span>
									</a>
								</li>
							</c:forEach>
						</ul>
						<ul>
							<c:forEach items="${requestScope.animationInfo7}" var="animationInfo">
								<li>
									<a target="_blank" href="animationInfoVisit?animationInfoId=${animationInfo.id}">
										<label><img src="${pageContext.request.contextPath}/assets/pages/img/animationInfoImg/${animationInfo.animationPicture}"/></label>
										<span>${animationInfo.animationName}</span>
										<span>${animationInfo.animationType}</span>
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				
				<div class="section-header02 borderSize">
					<h1>
						<img src="${pageContext.request.contextPath}/assets/pages/img/common/music.png"/>
						音乐
					</h1>
					<ul>
						<c:forEach items="${requestScope.animationEntertainmentAnimation03}" var="animationEntertainmentAnimation" >
							<li>
								<a target="_blank" href="animationEntertainmentAnimationVisit?id=${animationEntertainmentAnimation.id}">
									<span>
										<img src="${pageContext.request.contextPath}/assets/pages/img/animationEntertainmentAnimationImg/${animationEntertainmentAnimation.picture}"/>
										<span></span>
										<span>
											<span>
											<fmt:formatDate value="${animationEntertainmentAnimation.dateTime}" pattern="MM.dd" />
											<img src="${pageContext.request.contextPath}/assets/pages/img/common/time.png">
											</span>
										</span>
									</span>
									${animationEntertainmentAnimation.name}
								</a>
								<span>
									<img src="${pageContext.request.contextPath}/assets/pages/img/common/2018-07-12(18_54_55).png">
									<span><img src="${pageContext.request.contextPath}/assets/pages/img/common/2018-07-12(18_55_15).png"></span>
								</span>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="borderSizeRight">
					<h1>最新</h1>
					<ul>
						<c:forEach items="${requestScope.newAnimationEntertainmentAnimation03}" var="newAnimationEntertainmentAnimation">
							<li>
								<a target="_blank" href="animationEntertainmentAnimationVisit?id=${newAnimationEntertainmentAnimation.id}">
									${newAnimationEntertainmentAnimation.name}
								</a>
							</li>
						</c:forEach>
						<li class="has">
							<a href="animationEntertainmentAnimationSeach">
								查看更多
								<span>></span>
							</a>
						</li>
					</ul>
				</div>
				<div class="section-header02 borderSize">
					<h1>
						<img src="${pageContext.request.contextPath}/assets/pages/img/common/gueichu.png"/>
						娱乐
					</h1>
					<ul>
						<c:forEach items="${requestScope.animationEntertainmentAnimation04}" var="animationEntertainmentAnimation" >
							<li>
								<a target="_blank" href="animationEntertainmentAnimationVisit?id=${animationEntertainmentAnimation.id}">
									<span>
										<img src="${pageContext.request.contextPath}/assets/pages/img/animationEntertainmentAnimationImg/${animationEntertainmentAnimation.picture}"/>
										<span></span>
										<span>
											<span>
											<fmt:formatDate value="${animationEntertainmentAnimation.dateTime}" pattern="MM.dd" />
											<img src="${pageContext.request.contextPath}/assets/pages/img/common/time.png">
											</span>
										</span>
									</span>
									${animationEntertainmentAnimation.name}
								</a>
								<span>
									<img src="${pageContext.request.contextPath}/assets/pages/img/common/2018-07-12(18_54_55).png">
									<span><img src="${pageContext.request.contextPath}/assets/pages/img/common/2018-07-12(18_55_15).png"></span>
								</span>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="borderSizeRight">
					<h1>最新</h1>
					<ul>
						<c:forEach items="${requestScope.newAnimationEntertainmentAnimation04}" var="newAnimationEntertainmentAnimation">
							<li>
								<a target="_blank" href="animationEntertainmentAnimationVisit?id=${newAnimationEntertainmentAnimation.id}">
									${newAnimationEntertainmentAnimation.name}
								</a>
							</li>
						</c:forEach>
						<li class="has">
							<a href="animationEntertainmentAnimationSeach">
								查看更多
								<span>></span>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		</div>
		<div class="clear"></div>
		<div class="animationSelect">
			<div class="animationBackground"></div>
			<div class="animationInfo">
				<div>
					<p id="yearSelect">
						<label>按年份<span></span></label>
						<c:forEach items="${requestScope.years}" var="year">
							<span><label>${year}</label>年</span>
						</c:forEach>
					</p>
					<p id="animationQuarterSelect">
						<label>按季度<span></span></label>
						<c:forEach items="${requestScope.quarters}" var="quarter" >
							<span><label>${quarter}</label>季</span>
						</c:forEach>
					</p>
					<p id="animationTypeSelect">
						<label>按标签<span></span></label>
						<span>全部</span>
						<c:forEach items="${requestScope.animationTypes}" var="animationType">
							<span>${animationType.type}</span>
						</c:forEach>
						<a href="animationSeach">更多</a>
					</p>
				</div>
			</div>
		</div>
		<div class="animationInfoClass">
			<div>
				<h1>
					<img src="${pageContext.request.contextPath}/assets/pages/img/common/animationInfoSelect.png"/>
					<span id="title" data-text="全部" data-num="3">全部</span>动漫
				</h1>
				<div class="animationInfoSelectClass">
					<div class="pageSizeAndpageNum">
						<ul>
							<c:if test="${!requestScope.pageInfo.isFirstPage}">
								<li data-pagenum="1" class="ulNotClick">&#171;</li>
								<li data-pagenum="${requestScope.pageInfo.prePage}" class="ulNotClick">&#139;</li>
							</c:if>
							<c:forEach var="pagingButton" items="${requestScope.pageInfo.navigatepageNums}">
								<li data-pagenum="${pagingButton}" class="${pagingButton==requestScope.pageInfo.pageNum?'ulClick':'ulNotClick'}">${pagingButton}</li>
							</c:forEach>
							<c:if test="${!requestScope.pageInfo.isLastPage}">
								<li data-pagenum="${requestScope.pageInfo.nextPage}" class="ulNotClick">&#155;</li>
								<li data-pagenum="${requestScope.pageInfo.pages}" class="ulNotClick">&#187;</li>
							</c:if>
								<li>${requestScope.pageInfo.pageNum}/${requestScope.pageInfo.pages}页 共${requestScope.pageInfo.total}条</li>
						</ul>
					</div>
					<ul class="InfoClass">
					
						<c:forEach items="${requestScope.pageInfo.list}" var="animationInfo">
							<li>
								<div>
									<a target="_blank" href="animationInfoVisit?animationInfoId=${animationInfo.id}">
										<img src="assets/pages/img/animationInfoImg/${animationInfo.animationPicture}"/>
									</a>
								</div>
								<div>
									<h2><a target="_blank" href="animationInfoVisit?animationInfoId=${animationInfo.id}">${animationInfo.animationName}</a></h2>
									<p>日期：<fmt:formatDate value="${animationInfo.animationDate}" pattern="yyyy年MM月" /></p>
									<p>标签：${animationInfo.animationType}</p>
									<p>简介：${animationInfo.animationContent}</p>
								</div>
							</li>
						</c:forEach>
						
					</ul>
					<div class="pageSizeAndpageNum">
						<ul>
							<c:if test="${!requestScope.pageInfo.isFirstPage}">
								<li data-pagenum="1" class="ulNotClick">&#171;</li>
								<li data-pagenum="${requestScope.pageInfo.prePage}" class="ulNotClick">&#139;</li>
							</c:if>
							<c:forEach var="pagingButton" items="${requestScope.pageInfo.navigatepageNums}">
								<li data-pagenum="${pagingButton}" class="${pagingButton==requestScope.pageInfo.pageNum?'ulClick':'ulNotClick'}">${pagingButton}</li>
							</c:forEach>
							<c:if test="${!requestScope.pageInfo.isLastPage}">
								<li data-pagenum="${requestScope.pageInfo.nextPage}" class="ulNotClick">&#155;</li>
								<li data-pagenum="${requestScope.pageInfo.pages}" class="ulNotClick">&#187;</li>
							</c:if>
								<li>${requestScope.pageInfo.pageNum}/${requestScope.pageInfo.pages}页 共${requestScope.pageInfo.total}条</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		
		<img id="bodyImg" src="${pageContext.request.contextPath}/assets/pages/img/common/bg1.jpg"/>
		<%@ include file="common/foot.jsp" %>
		
		<script type="text/template" id="animationInfoTemplate">
			{{#if total}}
				<div class="pageSizeAndpageNum">
					<ul>
						{{#if isFirstPage}}
						{{else}}
							<li data-pagenum="1" class="ulNotClick">&#171;</li>
							<li data-pagenum="{{prePage}}" class="ulNotClick">&#139;</li>
						{{/if}}
						{{#each navigatepageNums}}
							<li data-pagenum="{{this}}" class="{{#eq this ../pageNum}}ulClick{{/eq}}{{#neq this ../pageNum}}ulNotClick{{/neq}}">{{this}}</li>
						{{/each}}
						{{#if isLastPage}}
						{{else}}
							<li data-pagenum="{{nextPage}}" class="ulNotClick">&#155;</li>
							<li data-pagenum="{{pages}}" class="ulNotClick">&#187;</li>
						{{/if}}
						<li>{{pageNum}}/{{pages}}页 共{{total}}条</li>
					</ul>
				</div>
				<ul class="InfoClass">

					{{#each list}}
						<li>
							<div>
								<a target="_blank" href="animationInfoVisit?animationInfoId={{id}}">
									<img src="assets/pages/img/animationInfoImg/{{animationPicture}}"/>
								</a>
							</div>
							<div>
								<h2><a target="_blank" href="animationInfoVisit?animationInfoId={{id}}">{{animationName}}</a></h2>
								<p>日期：{{year animationDate}}</p>
								<p>标签：{{animationType}}</p>
								<p>简介：{{animationContent}}</p>
							</div>
						</li>
					{{/each}}

				</ul>
				<div class="pageSizeAndpageNum">
					<ul>
						{{#if isFirstPage}}
						{{else}}
							<li data-pagenum="1" class="ulNotClick">&#171;</li>
							<li data-pagenum="{{prePage}}" class="ulNotClick">&#139;</li>
						{{/if}}
						{{#each navigatepageNums}}
							<li data-pagenum="{{this}}" class="{{#eq this ../pageNum}}ulClick{{/eq}}{{#neq this ../pageNum}}ulNotClick{{/neq}}">{{this}}</li>
						{{/each}}
						{{#if isLastPage}}
						{{else}}
							<li data-pagenum="{{nextPage}}" class="ulNotClick">&#155;</li>
							<li data-pagenum="{{pages}}" class="ulNotClick">&#187;</li>
						{{/if}}
						<li>{{pageNum}}/{{pages}}页 共{{total}}条</li>
					</ul>
				</div>
			{{/if}}
		</script>
		
		<%@ include file="common/jquery.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/moment-2.22.2-dist/moment.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/handlebars-v4.0.11-dist/handlebars-v4.0.11.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/handlebars-v4.0.11-dist/handlebars-ext.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/jquery-spinkit-1.2.5-dist/jquery-spinkit.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/pages/scripts/animation.js"></script>
	</body>
</html>
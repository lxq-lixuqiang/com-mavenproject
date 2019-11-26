<%@ page pageEncoding="UTF-8"%>
<div id="backgroundMsg"></div>
<div id="InfoMsg">
	<p>温馨提示：</p>
	<div>
		<img src="${pageContext.request.contextPath}/assets/pages/img/common/TiShi.png" alt="提示">
		<span id="InfoMsgSpan">${requestScope.msg}</span>
	</div>
	<button id="CloseButton">确 定</button>
	<button id="NoDelete">取 消</button>
	<button id="Delete">确 定</button>
</div>
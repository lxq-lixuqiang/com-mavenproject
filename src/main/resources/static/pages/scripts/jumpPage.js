var JumpPage=function(){
	var auto=null;
	var num=$("#sClass").html();
	function setIntervalJumpPage(){
		num--;
		if(num == 0){
			clearInterval(auto);
			location.href="login";
		}else{
			$("#sClass").html(num);
		}
	}
	
	return {
		init:function(){
			auto=setInterval(setIntervalJumpPage,1000);
		}
	}
}();
$(function(){
	JumpPage.init();
})
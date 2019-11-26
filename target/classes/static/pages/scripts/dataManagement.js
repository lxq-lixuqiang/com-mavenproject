var MessagesUser=function(){
	var initialization=function(){
		$("#dataManagement>a").attr("href","javascript:;");
		$(".sectionTopMover>li").mouseenter(function(){
			$(this).attr("class","mover");
			$("#dataManagement").attr("class","click");
		}).mouseleave(function(){
			$(this).attr("class","noClick");
			$("#dataManagement").attr("class","click");
		});
		
	}
	
	return {
		init:function(){
			initialization();
		}
	}
}();
$(function(){
	MessagesUser.init();
})

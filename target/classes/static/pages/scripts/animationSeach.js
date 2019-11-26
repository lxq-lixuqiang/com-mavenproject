var AnimationSeach=function(){
	var typeButton=function(){
		$(".section>p>span>a").click(function(){
			if(!$(this).hasClass("clickA")){
				$(".seachInput").val($.cookie("animationSeach_name"));
				$(".clickA").removeClass("clickA");
				$(this).addClass("clickA");
				var animationType=$(this).data("animationtype");
				var name=$.cookie("animationSeach_name") || null;
				ajax(1,name,animationType);
			}
		});
	}
	
	
	var animationSeachTemplate=Handlebars.compile($("#animationSeachTemplate").html());
	var pagingButtonTemplate=Handlebars.compile($("#pagingButtonTemplate").html());
	var InfoClass=$(".InfoClass");
	var pageSizeAndpageNum=$("#pageSizeAndpageNum");
	var body=$("body");
	function ajax(pageNum,animationName,animationType){
		var data={pageNum:pageNum,animationName:animationName,animationType:animationType};
		body.busyLoad("show");
		$.getJSON("animationSeach02",data,function(data){
			var newAnimationSeachHtml=animationSeachTemplate(data);
			var newPagingButtonHtml=pagingButtonTemplate(data);
			
			InfoClass.html(newAnimationSeachHtml);
			pageSizeAndpageNum.html(newPagingButtonHtml);
			body.busyLoad("hide");
		});
	}
	
	var seachButton=function(){
		$(".seachButton").click(function(){
			var name=$(".seachInput").val();
			$.cookie("animationSeach_name",name);
			var animationType=$(".clickA").data("animationtype");
			ajax(1,name,animationType);
		});
		
		$(".seachInput").keyup(function(e){
			if(e.which==13){
				var name=$(".seachInput").val();
				$.cookie("animationSeach_name",name);
				var animationType=$(".clickA").data("animationtype");
				ajax(1,name,animationType);
			}
		});
	}
	
	var pagingButton=function(){
		pageSizeAndpageNum.on("click",".ulNotClick",function(){
			$(".seachInput").val($.cookie("animationSeach_name"));
			var pageNum=$(this).data("pagenum");
			var name=$.cookie("animationSeach_name") || null;
			var animationType=$(".clickA").data("animationtype");
			ajax(pageNum,name,animationType);
		});
	}
	
	return{
		init:function(){
			typeButton();
			seachButton();
			pagingButton();
		}
	}
}();
$(function(){
	AnimationSeach.init();
});

var AnimationEntertainmentAnimationSeach=function(){
	var typeButton=function(){
		$(".section>p>span>a").click(function(){
			if(!$(this).hasClass("clickA")){
				$(".seachInput").val($.cookie("animationEntertainmentType_name"));
				$(".clickA").removeClass("clickA");
				$(this).addClass("clickA");
				var entertainmentTypeName=$(this).data("animationentertainmenttype");
				var name=$.cookie("animationEntertainmentType_name") || null;
				ajax(1,name,entertainmentTypeName);
			}
		});
	}
	
	var animationEntertainmentAnimationSeachTemplate=Handlebars.compile($("#animationEntertainmentAnimationSeachTemplate").html());
	var pagingButtonTemplate=Handlebars.compile($("#pagingButtonTemplate").html());
	var borderSize=$(".borderSize");
	var pageSizeAndpageNum=$("#pageSizeAndpageNum");
	var body=$("body");
	function ajax(pageNum,name,entertainmentTypeName){
		var data={pageNum:pageNum,name:name,entertainmentTypeName:entertainmentTypeName};
		body.busyLoad("show");
		$.getJSON("animationEntertainmentAnimationSeach02",data,function(data){
			var newAnimationEntertainmentAnimationSeachHtml=animationEntertainmentAnimationSeachTemplate(data);
			var newPagingButtonHtml=pagingButtonTemplate(data);
			
			borderSize.html(newAnimationEntertainmentAnimationSeachHtml);
			pageSizeAndpageNum.html(newPagingButtonHtml);
			body.busyLoad("hide");
		});
	}
	
	var seachButton=function(){
		$(".seachButton").click(function(){
			var name=$(".seachInput").val();
			$.cookie("animationEntertainmentType_name",name);
			var entertainmentTypeName=$(".clickA").data("animationentertainmenttype");
			ajax(1,name,entertainmentTypeName);
		});
		
		$(".seachInput").keyup(function(e){
			if(e.which==13){
				var name=$(".seachInput").val();
				$.cookie("animationEntertainmentType_name",name);
				var entertainmentTypeName=$(".clickA").data("animationentertainmenttype");
				ajax(1,name,entertainmentTypeName);
			}
		});
	}
	
	var pagingButton=function(){
		pageSizeAndpageNum.on("click",".ulNotClick",function(){
			$(".seachInput").val($.cookie("animationEntertainmentType_name"));
			var pageNum=$(this).data("pagenum");
			var name=$.cookie("animationEntertainmentType_name") || null;
			var entertainmentTypeName=$(".clickA").data("animationentertainmenttype");
			ajax(pageNum,name,entertainmentTypeName);
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
	AnimationEntertainmentAnimationSeach.init();
});

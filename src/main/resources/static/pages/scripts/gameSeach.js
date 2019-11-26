var AnimationSeach=function(){
	var type01Button=function(){
		$(".section>.pClass01>span>a").click(function(){
			if(!$(this).hasClass("clickA01")){
				$(".seachInput").val($.cookie("gameSeach_name"));
				$(".clickA01").removeClass("clickA01");
				$(this).addClass("clickA01");
				
				var language=$(this).data("language");
				var name=$.cookie("gameSeach_name") || null;
				var classificationId=$(".clickA02").data("gameclassification");
				var platformId=$(".clickA03").data("gameplatform");
				var typeId=$(".clickA04").data("gametype");
				ajax(1,classificationId,platformId,typeId,language,name);
			}
		});
	}
	var type02Button=function(){
		$(".section>.pClass02>span>a").click(function(){
			if(!$(this).hasClass("clickA02")){
				$(".seachInput").val($.cookie("gameSeach_name"));
				$(".clickA02").removeClass("clickA02");
				$(this).addClass("clickA02");
				
				var classificationId=$(this).data("gameclassification");
				var name=$.cookie("gameSeach_name") || null;
				var language=$(".clickA01").data("language");
				var platformId=$(".clickA03").data("gameplatform");
				var typeId=$(".clickA04").data("gametype");
				ajax(1,classificationId,platformId,typeId,language,name);
			}
		});
	}
	var type03Button=function(){
		$(".section>.pClass03>span>a").click(function(){
			if(!$(this).hasClass("clickA03")){
				$(".seachInput").val($.cookie("gameSeach_name"));
				$(".clickA03").removeClass("clickA03");
				$(this).addClass("clickA03");

				var platformId=$(this).data("gameplatform");
				var name=$.cookie("gameSeach_name") || null;
				var language=$(".clickA01").data("language");
				var classificationId=$(".clickA02").data("gameclassification");
				var typeId=$(".clickA04").data("gametype");
				ajax(1,classificationId,platformId,typeId,language,name);
			}
		});
	}
	var type04Button=function(){
		$(".section>.pClass04>span>a").click(function(){
			if(!$(this).hasClass("clickA04")){
				$(".seachInput").val($.cookie("gameSeach_name"));
				$(".clickA04").removeClass("clickA04");
				$(this).addClass("clickA04");

				var typeId=$(this).data("gametype");
				var name=$.cookie("gameSeach_name") || null;
				var language=$(".clickA01").data("language");
				var classificationId=$(".clickA02").data("gameclassification");
				var platformId=$(".clickA03").data("gameplatform");
				ajax(1,classificationId,platformId,typeId,language,name);
			}
		});
	}
	
	
	var gameSeachTemplate=Handlebars.compile($("#gameSeachTemplate").html());
	var pagingButtonTemplate=Handlebars.compile($("#pagingButtonTemplate").html());
	var InfoClass=$(".InfoClass");
	var pageSizeAndpageNum=$("#pageSizeAndpageNum");
	var body=$("body");
	function ajax(pageNum,classificationId,platformId,typeId,language,name){
		var data={pageNum:pageNum,classificationId:classificationId,platformId:platformId,typeId:typeId,language:language,name:name};
		body.busyLoad("show");
		$.getJSON("gameSeach02",data,function(data){
			var newGameSeachHtml=gameSeachTemplate(data);
			var newPagingButtonHtml=pagingButtonTemplate(data);
			
			InfoClass.html(newGameSeachHtml);
			pageSizeAndpageNum.html(newPagingButtonHtml);
			body.busyLoad("hide");
		});
	}
	
	var seachButton=function(){
		$(".seachButton").click(function(){
			var name=$(".seachInput").val();
			$.cookie("gameSeach_name",name);
			var typeId=$(this).data("gametype");
			var language=$(".clickA01").data("language");
			var classificationId=$(".clickA02").data("gameclassification");
			var platformId=$(".clickA03").data("gameplatform");
			ajax(1,classificationId,platformId,typeId,language,name);
		});
		
		$(".seachInput").keyup(function(e){
			if(e.which==13){
				var name=$(".seachInput").val();
				$.cookie("gameSeach_name",name);
				var typeId=$(this).data("gametype");
				var language=$(".clickA01").data("language");
				var classificationId=$(".clickA02").data("gameclassification");
				var platformId=$(".clickA03").data("gameplatform");
				ajax(1,classificationId,platformId,typeId,language,name);
			}
		});
	}
	
	var pagingButton=function(){
		pageSizeAndpageNum.on("click",".ulNotClick",function(){
			$(".seachInput").val($.cookie("gameSeach_name"));
			var pageNum=$(this).data("pagenum");
			var name=$.cookie("gameSeach_name") || null;
			var language=$(".clickA01").data("language");
			var classificationId=$(".clickA02").data("gameclassification");
			var platformId=$(".clickA03").data("gameplatform");
			var typeId=$(".clickA04").data("gametype");
			ajax(pageNum,classificationId,platformId,typeId,language,name);
		});
	}
	
	return{
		init:function(){
			type01Button();
			type02Button();
			type03Button();
			type04Button();
			seachButton();
			pagingButton();
		}
	}
}();
$(function(){
	AnimationSeach.init();
});

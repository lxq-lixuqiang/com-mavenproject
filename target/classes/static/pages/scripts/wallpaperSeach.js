var WallpaperSeach=function(){
	var typeButton=function(){
		$(".section>p>span>a").click(function(){
			if(!$(this).hasClass("clickA")){
				$(".seachInput").val($.cookie("wallpaperSeach_theme"));
				$(".clickA").removeClass("clickA");
				$(this).addClass("clickA");
				var typeId=$(this).data("typeid");
				var theme=$.cookie("wallpaperSeach_theme") || null;
				ajax(1,theme,typeId);
			}
		});
	}
	
	
	var wallpaperSeachTemplate=Handlebars.compile($("#wallpaperSeachTemplate").html());
	var pagingButtonTemplate=Handlebars.compile($("#pagingButtonTemplate").html());
	var wallpaperThemeUl=$(".wallpaperThemeUl");
	var pageSizeAndpageNum=$("#pageSizeAndpageNum");
	var body=$("body");
	function ajax(pageNum,theme,typeId){
		var data={pageNum:pageNum,theme:theme,typeId:typeId};
		body.busyLoad("show");
		$.getJSON("wallpaperSearch02",data,function(data){
			var newWallpaperSeachHtml=wallpaperSeachTemplate(data);
			var newPagingButtonHtml=pagingButtonTemplate(data);
			
			wallpaperThemeUl.html(newWallpaperSeachHtml);
			pageSizeAndpageNum.html(newPagingButtonHtml);
			body.busyLoad("hide");
		});
	}
	
	var seachButton=function(){
		$(".seachButton").click(function(){
			var theme=$(".seachInput").val();
			$.cookie("wallpaperSeach_theme",theme);
			var typeId=$(".clickA").data("typeid");
			ajax(1,theme,typeId);
		});
		
		$(".seachInput").keyup(function(e){
			if(e.which==13){
				var theme=$(".seachInput").val();
				$.cookie("wallpaperSeach_theme",theme);
				var typeId=$(".clickA").data("typeid");
				ajax(1,theme,typeId);
			}
		});
	}
	
	var pagingButton=function(){
		pageSizeAndpageNum.on("click",".ulNotClick",function(){
			$(".seachInput").val($.cookie("wallpaperSeach_theme"));
			var pageNum=$(this).data("pagenum");
			var theme=$.cookie("wallpaperSeach_theme") || null;
			var typeId=$(".clickA").data("typeid");
			ajax(pageNum,theme,typeId);
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
	WallpaperSeach.init();
});

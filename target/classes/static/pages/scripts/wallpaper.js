var Wallpaper=function(){
	var AutoMover=setInterval(autoMover,4000);
	var HeadLogin=function(){
		$("a[href=wallpaper]").attr("href","javescript:;");
		$(".sectonHeader-right>ul li a").mouseenter(function(){
			clearInterval(AutoMover);
			$(".sectonHeader-right>ul li a").removeClass("moveClass");
			$(this).addClass("moveClass");
			var imgId=$(this).data("imgid");
			$(".sectonHeader-left a").removeClass("notDisplay");
			$(".sectonHeader-left a:nth-of-type("+imgId+")").addClass("notDisplay")
		}).mouseleave(function(){
			clearInterval(AutoMover);
			AutoMover=setInterval(autoMover,4000);
		});
		$(".sectionClass>fieldset>legend span").mouseenter(function(){
			$(".sectionClass>fieldset>legend span").removeAttr("class");
			$(this).attr("class","select");
			var num=$(this).data("num");
			$(".sectionClass div").removeAttr("class");
			$(".sectionClass div:nth-of-type("+num+")").attr("class","block");
		});
	}
	
	function autoMover(){
		var imgId=$(".moveClass").data("imgid");
		imgId++;
		if(imgId > 6) imgId=1;
		$(".sectonHeader-right>ul li a").removeClass("moveClass");
		$("a[data-imgid="+imgId+"]").addClass("moveClass");
		$(".sectonHeader-left>a").removeClass("notDisplay");
		$(".sectonHeader-left>a:nth-of-type("+imgId+")").addClass("notDisplay");
		$(".sectonHeader-left>a:nth-of-type("+imgId+")").animate({"opacity":"0.1"},1);
		$(".sectonHeader-left>a:nth-of-type("+imgId+")").animate({"opacity":"1"},1000);
	}
	
	var wallpaperThemeTemplate=Handlebars.compile($("#wallpaperThemeTemplate").html());
	var wallpaperPagingTemplate=Handlebars.compile($("#wallpaperPagingTemplate").html());
	var wallpaperThemeUl=$("#wallpaperThemeUl");
	var wallpaperThemeOl=$("#wallpaperThemeOl");
	var body=$("body");
	function ajax(pageSize,pageNum,date,typeId){
		pageSize = pageSize || 15;
		pageNum = pageNum || 1;
		
		var data={pageSize:pageSize,pageNum:pageNum,date:date,typeId:typeId};
		body.busyLoad("show");
		$.getJSON("wallpaperThemeSearch",data,function(data){
			var newWallpaperThemeHtml=wallpaperThemeTemplate(data);
			var newWallpaperPagingHtml=wallpaperPagingTemplate(data);
			
			wallpaperThemeUl.html(newWallpaperThemeHtml);
			wallpaperThemeOl.html(newWallpaperPagingHtml);
			body.busyLoad("hide");
		});
	}
	
	var pageingButton=function(){
		wallpaperThemeOl.on("click",".pageNum[data-pagenum][data-pagesize]",function(){
			var typeId=$("#title").data("typeid");
			var date=$("#title").data("date");
			var pageNum=$(this).data("pagenum");
			var pageSize=$(this).data("pagesize");
			ajax(pageSize,pageNum,date,typeId);
		});
	}
	
	var wallpaperTypeButton=function(){
		$(".wallpaperType").click(function(){
			var typeId=$(this).data("typeid");
			$("#title").text($(this).text());
			$("#title").attr("data-typeid",typeId);
			$("#title").attr("data-date","null");
			ajax(null,null,null,typeId);
		});
	}
	
	var wallpaperDateButton=function(){
		$(".wallpaperDate").click(function(){
			var date=$(this).data("date");
			$("#title").text($(this).text());
			$("#title").attr("data-date",date);
			$("#title").attr("data-typeid","null");
			ajax(null,null,date,null);
		});
	}
	
	return{
		init:function(){
			HeadLogin();
			pageingButton();
			wallpaperTypeButton();
			wallpaperDateButton();
		}
	}
}();
$(function(){
	Wallpaper.init();
});

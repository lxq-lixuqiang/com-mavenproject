var musicMVSeach=function(){
	
	var gameInformationThemeSeachTemplate=Handlebars.compile($("#gameInformationThemeSeachTemplate").html());
	var pagingButtonTemplate=Handlebars.compile($("#pagingButtonTemplate").html());
	var borderH3=$(".borderH3");
	var pageSizeAndpageNum=$("#pageSizeAndpageNum");
	var body=$("body");
	function ajax(pageNum,theme){
		var data={pageNum:pageNum,theme:theme};
		body.busyLoad("show");
		$.getJSON("gameInformationThemeSeach02",data,function(data){
			var newGameInformationThemeSeachHtml=gameInformationThemeSeachTemplate(data);
			var newPagingButtonHtml=pagingButtonTemplate(data);
			
			borderH3.html(newGameInformationThemeSeachHtml);
			pageSizeAndpageNum.html(newPagingButtonHtml);
			body.busyLoad("hide");
		});
	}
	
	var seachButton=function(){
		$(".seachButton").click(function(){
			var theme=$(".seachInput").val();
			$.cookie("gameInformationThemeSeach_theme",theme);
			ajax(1,theme);
		});
		
		$(".seachInput").keyup(function(e){
			if(e.which==13){
				var theme=$(".seachInput").val();
				$.cookie("gameInformationThemeSeach_theme",theme);
				ajax(1,theme);
			}
		});
	}
	
	var pagingButton=function(){
		pageSizeAndpageNum.on("click",".ulNotClick",function(){
			$(".seachInput").val($.cookie("gameInformationThemeSeach_theme"));
			var pageNum=$(this).data("pagenum");
			var theme=$.cookie("gameInformationThemeSeach_theme") || null;
			ajax(pageNum,theme);
		});
	}
	
	return{
		init:function(){
			seachButton();
			pagingButton();
		}
	}
}();
$(function(){
	musicMVSeach.init();
});

var musicMVSeach=function(){
	
	var musicMVSeachTemplate=Handlebars.compile($("#musicMVSeachTemplate").html());
	var pagingButtonTemplate=Handlebars.compile($("#pagingButtonTemplate").html());
	var musicSongsheetClassRight=$(".musicSongsheetClassRight");
	var pageSizeAndpageNum=$("#pageSizeAndpageNum");
	var body=$("body");
	function ajax(pageNum,themeName){
		var data={pageNum:pageNum,themeName:themeName};
		body.busyLoad("show");
		$.getJSON("musicMVSeach02",data,function(data){
			var newMusicMVSeachHtml=musicMVSeachTemplate(data);
			var newPagingButtonHtml=pagingButtonTemplate(data);
			
			musicSongsheetClassRight.html(newMusicMVSeachHtml);
			pageSizeAndpageNum.html(newPagingButtonHtml);
			body.busyLoad("hide");
		});
	}
	
	var seachButton=function(){
		$(".seachButton").click(function(){
			var themeName=$(".seachInput").val();
			$.cookie("musicMVSeach_themeName",themeName);
			ajax(1,themeName);
		});
		
		$(".seachInput").keyup(function(e){
			if(e.which==13){
				var themeName=$(".seachInput").val();
				$.cookie("musicMVSeach_themeName",themeName);
				ajax(1,themeName);
			}
		});
	}
	
	var pagingButton=function(){
		pageSizeAndpageNum.on("click",".ulNotClick",function(){
			$(".seachInput").val($.cookie("musicMVSeach_themeName"));
			var pageNum=$(this).data("pagenum");
			var themeName=$.cookie("musicMVSeach_themeName") || null;
			ajax(pageNum,themeName);
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

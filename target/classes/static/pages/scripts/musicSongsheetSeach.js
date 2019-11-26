var musicMVSeach=function(){
	
	var musicSongsheetSeachTemplate=Handlebars.compile($("#musicSongsheetSeachTemplate").html());
	var pagingButtonTemplate=Handlebars.compile($("#pagingButtonTemplate").html());
	var borderH3=$(".borderH3");
	var pageSizeAndpageNum=$("#pageSizeAndpageNum");
	var body=$("body");
	function ajax(pageNum,name){
		var data={pageNum:pageNum,name:name};
		body.busyLoad("show");
		$.getJSON("musicSongsheetSeach02",data,function(data){
			var newMusicSongsheetSeachHtml=musicSongsheetSeachTemplate(data);
			var newPagingButtonHtml=pagingButtonTemplate(data);
			
			borderH3.html(newMusicSongsheetSeachHtml);
			pageSizeAndpageNum.html(newPagingButtonHtml);
			body.busyLoad("hide");
		});
	}
	
	var seachButton=function(){
		$(".seachButton").click(function(){
			var name=$(".seachInput").val();
			$.cookie("musicSongsheetSeach_name",name);
			ajax(1,name);
		});
		
		$(".seachInput").keyup(function(e){
			if(e.which==13){
				var name=$(".seachInput").val();
				$.cookie("musicSongsheetSeach_name",name);
				ajax(1,name);
			}
		});
	}
	
	var pagingButton=function(){
		pageSizeAndpageNum.on("click",".ulNotClick",function(){
			$(".seachInput").val($.cookie("musicSongsheetSeach_name"));
			var pageNum=$(this).data("pagenum");
			var name=$.cookie("musicSongsheetSeach_name") || null;
			ajax(pageNum,name);
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

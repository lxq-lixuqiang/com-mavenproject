var Music=function(){
	var headInit=function(){
		$("a[href=music]").attr("href","javescript:;");
		$(document).ready(function(){
			$("#player").vpplayer({
			    src: "",
			    trackName: "sample audio",
			    view: "minimal",
			    playerColor: "#fff,#D8EAEC,#59DEE9"
			  });
		});
	}
	
	var musicSingerTemplate=Handlebars.compile($("#musicSingerTemplate").html());
	var musicSingerDiv=$("#musicSingerDiv");
	function musicSingerAjax(pageNum,pageSize){
		musicSingerDiv.busyLoad("show");
		var musicSingerName=$("#musicSingerName").val() || null;
		var data={pageNum:pageNum,pageSize:pageSize,name:musicSingerName};
		$.getJSON("musicSingerSelect",data,function(data){
			var newMusicSingerHtml=musicSingerTemplate(data);
			musicSingerDiv.html(newMusicSingerHtml);
			musicSingerDiv.busyLoad("hide");
		});
	}
	
	var musicSingerPagingButton=function(){
		musicSingerDiv.on("click","a[data-pagenum][data-pagesize]",function(){
			var pageNum=$(this).data("pagenum");
			var pageSize=$(this).data("pagesize");
			$("#musicSingerName").val($.cookie("musicSingerName"));
			musicSingerAjax(pageNum,pageSize);
		});
	}
	
	var selectButton=function(){
		$("#select").click(function(){
			var pageSize=$(this).data("pagesize");
			$.cookie("musicSingerName",$("#musicSingerName").val());
			musicSingerAjax(1,pageSize);
		});
	}
	
	var musicSingerNameKeyUp=function(){
		$("#musicSingerName").keyup(function(e){
			if(e.which == 13){ 
				$.cookie("musicSingerName",$("#musicSingerName").val());
				var pageSize=$("#select").data("pagesize");
				musicSingerAjax(1,pageSize);
			}
		});
	}
	
	var musicId=null;
	var liClick=function(){
		musicClass.on("click","span[class=playMusic][data-src]",function(){
			var SingerName=$(this).parent().prev().text();
			if($("#SingerName").text() == SingerName){
				var pause=$(".pause")[0];
				if(pause != undefined){
					$(".pause").click();
				}else{
					$(".play").click();
				}
			}else{
				$("#SingerName").text(SingerName);
				var src=$(this).data("src");
				$("#player").vpplayer({
					src: src,
					trackName: "sample audio",
					view: "minimal",
					playerColor: "#fff,#D8EAEC,#59DEE9"
				});
				$(".playLoad").html("");
				var img=$("<img>").attr("src","assets/pages/img/common/loading.gif");
				$(this).parent().prev().prev().html(img);
				musicId=$(this).parent().prev().prev().data("musicid");
				setTimeout(musicPlay,500);
			}
		});
		function musicPlay(){
			$(".play").click();
		}
	}
	
	var musicTemplate=Handlebars.compile($("#musicTemplate").html());
	var musicTable=$("#musicTable");
	var musicClass=$(".musicClass");
	function musicAjax(pageNum,pageSize){
		musicTable.busyLoad("show");
		var musicName=$.cookie("musicName") || null;
		var data={pageNum:pageNum,pageSize:pageSize,songName:musicName,singerName:musicName};
		$.getJSON("musicAndSonger",data,function(data){
			var newMusicHtml=musicTemplate(data);
			musicTable.html(newMusicHtml);
			if(musicId != null){
				var img=$("<img>").attr("src","assets/pages/img/common/loading.gif");
				$("td[data-musicid="+musicId+"]").html(img);
			}
			musicTable.busyLoad("hide");
		});
	}
	var musicPagingButton=function(){
		musicTable.on("click","label[data-musicpagesize][data-musicpagenum]",function(){
			var pageSize=$(this).data("musicpagesize");
			var pageNum=$(this).data("musicpagenum");
			$("#musicName").val($.cookie("musicName"));
			musicAjax(pageNum,pageSize);
		});
	}
	
	var selectMusicButton=function(){
		$("#selectMusic").click(function(){
			$.cookie("musicName",$("#musicName").val());
			var pageSize=$(this).data("pagesize");
			musicAjax(1,pageSize);
		});
	}
	
	var musicNameKeyUp=function(){
		$("#musicName").keyup(function(e){
			if(e.which == 13){ 
				$.cookie("musicName",$("#musicName").val());
				var pageSize=$("#selectMusic").data("pagesize");
				musicAjax(1,pageSize);
			}
		});
	}
	
	return {
		init:function(){
			headInit();
			musicSingerPagingButton();
			selectButton();
			musicSingerNameKeyUp();
			liClick();
			musicPagingButton();
			selectMusicButton();
			musicNameKeyUp();
		}
	}
}();
$(function(){
	Music.init();
});

var MusicSingerVisit=function(){
	var headInit=function(){
		$(document).ready(function(){
			$("#player").vpplayer({
			    src: "",
			    trackName: "sample audio",
			    view: "minimal",
			    playerColor: "#fff,#D8EAEC,#59DEE9"
			  });
		});
	}
	
	var classChange=function(){
		$(".musicUlClass>li").click(function(){
			var clickLiInfo=$(".clickLi").data("info");
			$("."+clickLiInfo).hide();
			$(".clickLi").removeAttr("style").removeClass("clickLi");
			
			var info=$(this).data("info");
			$("."+info).show();
			$(this).attr("style","color:#9DCEF4;").addClass("clickLi");
		});
	}
	
	var musicId=null;
	var liClick=function(){
		
		
		
		musicClass.on("click","span[class=playMusic][data-src]",function(){
			var SingerName=$(this).prev().text();
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
				$(this).prev().prev().html(img);
				musicId=$(this).prev().prev().data("musicid");
				setTimeout(musicPlay,500);
			}
		});
		function musicPlay(){
			$(".play").click();
		}
	}
	
	var musicTemplate=Handlebars.compile($("#musicTemplate").html());
	var musicClass=$(".musicClass");
	function musicAjax(pageNum,pageSize){
		musicClass.busyLoad("show"); 
		var singerId=$("#singerId").val();
		var data={pageNum:pageNum,pageSize:pageSize,singerId:singerId};
		$.getJSON("musicAjax",data,function(data){
			var newMusicHtml=musicTemplate(data);
			musicClass.html(newMusicHtml);
			if(musicId != null){
				var img=$("<img>").attr("src","assets/pages/img/common/loading.gif");
				$("span[data-musicid="+musicId+"]").html(img);
			}
			musicClass.busyLoad("hide"); 
		});
	}
	var musicPaging=function(){
		musicClass.on("click","label[data-musicpagesize][data-musicpagenum]",function(){
			var pageSize=$(this).data("musicpagesize");
			var pageNum=$(this).data("musicpagenum");
			musicAjax(pageNum,pageSize);
		});
	}
	
	var musicMVTemplate=Handlebars.compile($("#musicMVTemplate").html());
	var musicMVClass=$(".musicMVClass");
	function musicMVAjax(pageNum,pageSize){
		musicMVClass.busyLoad("show"); 
		var singerId=$("#singerId").val();
		var data={pageNum:pageNum,pageSize:pageSize,singerId:singerId};
		$.getJSON("musicMVAjax",data,function(data){
			var newMusicMVHtml=musicMVTemplate(data);
			musicMVClass.html(newMusicMVHtml);
			musicMVClass.busyLoad("hide"); 
		});
	}
	var musicMVPaging=function(){
		musicMVClass.on("click","label[data-musicmvpagesize][data-musicmvpagenum]",function(){
			var pageSize=$(this).data("musicmvpagesize");
			var pageNum=$(this).data("musicmvpagenum");
			musicMVAjax(pageNum,pageSize);
		});
	}
	
	return {
		init:function(){
			headInit();
			classChange();
			liClick();
			musicPaging();
			musicMVPaging();
		}
	}
}();
$(function(){
	MusicSingerVisit.init();
});

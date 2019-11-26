var MusicSongsheetInfoVisit=function(){
	var headInit=function(){
		$(document).ready(function(){
			$("#player").vpplayer({
			    src: "",
			    trackName: "sample audio",
			    view: "minimal",
			    playerColor: "#fff,#D8EAEC,#59DEE9"
			  });
		});
		var width=$("#musicTable").height();
		if(width <522){
			$("#musicTable").height(522);
		}
	}
	
	var musicId=null;
	var liClick=function(){
		$(".musicClass").on("click","span[class=playMusic][data-src]",function(){
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
				$(this).parent().prev().prev().prev().html(img);
				musicId=$(this).parent().prev().prev().data("musicid");
				setTimeout(musicPlay,500);
			}
		});
		function musicPlay(){
			$(".play").click();
		}
	}
	
	return {
		init:function(){
			headInit();
			liClick();
		}
	}
}();
$(function(){
	MusicSongsheetInfoVisit.init();
});

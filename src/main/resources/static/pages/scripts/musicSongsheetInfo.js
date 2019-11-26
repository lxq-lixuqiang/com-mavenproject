var UserInfo=function(){
	var headInit=function(){
		$("#dataManagement>a").attr("href","javascript:;");
		$(".sectionTopMover>li").mouseenter(function(){
			$(this).attr("class","mover");
			$("#dataManagement").attr("class","click");
		}).mouseleave(function(){
			$(this).attr("class","noClick");
			$("#dataManagement").attr("class","click");
		});
		$("#userHeaderImg").attr("href","javascript:;");
		
		if($("#msg").val().length>0){
			new NoticeJs({
			    text: $("#msg").val(),
			    position: 'middleCenter',
			    animation: {
			        open: 'animated zoomIn',
			        close: 'animated zoomOut'
			  }
			}).show();
		}
	}
	
	var pagingButton=function(){
		$(".ulNotClick").click(function(){
			var pageNum=$(this).data("pagenum");
			var name=$("#name").val();
			location.href="musicSongsheetInfo?pageNum="+pageNum+"&name="+name;
		});
	}
	
	var seachMove=function(){
		$("#select").mouseenter(function(){
			$(this).attr("src","assets/pages/img/common/ImageSelect02.png");
		}).mouseleave(function(){
			$(this).attr("src","assets/pages/img/common/ImageSelect01.png");
		});
	}
	
	function selectUser(){
		var name=$("#name").val();
		location.href="musicSongsheetInfo?name="+name+"&pageNum=1";
	}
	var selectButton=function(){
		$("#select").click(function(){
			selectUser();
		});
		$("#name").keyup(function(e){
			if(e.which == 13){
				selectUser();
			}
		});
	}
	
	return{
		init:function(){
			headInit();
			pagingButton();
			seachMove();
			selectButton();
		}
	}
}();
$(function(){
	UserInfo.init();
});
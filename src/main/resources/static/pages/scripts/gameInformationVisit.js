var GameInformationVisit=function(){
	var headInit=function(){
		var imgSize=[];
		for(var i=0,len=$(".imgSize").length;i<len;i++){
			imgSize.push($(".imgSize")[i]);
		}
		for(var i=0,len=imgSize.length;i<len;i++){
			if($(imgSize[i]).height()>500){
				$(imgSize[i]).height(500);
			}
			if($(imgSize[i]).width() >900){
				$(imgSize[i]).width(900);
			}
			$(imgSize[i]).show();
		}
	}
	
	var pagingButton=function(){
		$(".ulNotClick").click(function(){
			var pageNum=$(this).data("pagenum");
			location.href="gameInformationVisit?pageNum="+pageNum;
		});
	}
	
	return {
		init:function(){
			headInit();
			pagingButton();
		}
	}
}();
$(function(){
	GameInformationVisit.init();
});

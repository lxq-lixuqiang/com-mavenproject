var WallpaperVisit=function(){
	var headInit=function(){
		var width=$("#ImgClass").width();
		console.info(width);
		if(width >1450 || width == 0){
			$("#ImgClass").attr("style","opacity:1;width:1450px;");
		}else{
			$("#ImgClass").attr("style","opacity:1;");
		}
	}
	return{
		init:function(){
			headInit();
		}
	}
}();
$(function(){
	WallpaperVisit.init();
});
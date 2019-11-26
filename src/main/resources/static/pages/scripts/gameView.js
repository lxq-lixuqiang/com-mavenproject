var GameView=function(){
	var clickImg=function(){
		$("#Img>img").click(function(){
			$(".clickClass").removeClass("clickClass");
			$(this).addClass("clickClass");
			var src=$(this).attr("src");
			$("#imgClass").attr("src",src);
		});
	}
	return{
		init:function(){
			clickImg();
		}
	}
}();

$(function(){
	GameView.init();
});

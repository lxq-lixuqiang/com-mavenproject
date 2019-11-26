var Home=function(){
	var homeInit=function(){
		$("a[href=home]").attr("href","javescript:;");
	}
	
	var moverImgColor=function(){
		setInterval(auto,500);
		function auto(){
			var backgroundColor=$(".active").data("backgroundcolor");
			$(".home").css("background-color",backgroundColor);
		}
	}
	
	
	var clickMoverImg=function(){
		var num=0;
		$("#rightImg").click(function(){
			if(num<3){
				num++;
				$(".newcomicUlLi").animate({"left":""+(-520*num)+"px"},350);
			}
		});
		$("#leftImg").click(function(){
			if(num>0){
				num--;
				$(".newcomicUlLi").animate({"left":""+(-520*num)+"px"},350);
			}
		});
	}
	
	var navigation=function(){	
		$(window).scroll(function(){
			
			if($(document).scrollTop()>600){
				if($("#top").hasClass("none")){
					$("#top").removeClass("none");
				}
			}else if($(document).scrollTop()<600){
				if(!$("#top").hasClass("none")){
					$("#top").addClass("none");
				}
			}
			//$("#borderTop").offset({ top: ($(document).scrollTop()+300) });
			
			if($(document).scrollTop()<810){
				if(!$("#comic").hasClass("moverAClass")){
					$(".moverAClass").removeClass("moverAClass");
					$("#comic").addClass("moverAClass");
				}
			}else if($(document).scrollTop()>=810 && $(document).scrollTop()<1870){
				if(!$("#game").hasClass("moverAClass")){
					$(".moverAClass").removeClass("moverAClass");
					$("#game").addClass("moverAClass");
				}
			}else if($(document).scrollTop()>=1870 && $(document).scrollTop()<3020){
				if(!$("#music").hasClass("moverAClass")){
					$(".moverAClass").removeClass("moverAClass");
					$("#music").addClass("moverAClass");
				}
			}else if($(document).scrollTop()>=3020){
				if(!$("#wallpaper").hasClass("moverAClass")){
					$(".moverAClass").removeClass("moverAClass");
					$("#wallpaper").addClass("moverAClass");
				}
			}
		});
	
		
	}
	
	var moverGameUpdateImg=function(){
		$(".game01_Span03>a").mouseenter(function(){
			$(".showGameAAndImg").removeClass("showGameAAndImg");
			$(this).addClass("showGameAAndImg");
			var id=$(this).data("aid");
			$(".showGameImg").removeClass("showGameImg");
			$(".game01_Span01>a:nth-of-type("+id+")").addClass("showGameImg");
			$(".showGameA").removeClass("showGameA");
			$(".game01_Span02>a:nth-of-type("+id+")").addClass("showGameA");
		});
	}
	
	var moverComic=function(){
		$("#comic").click(function(){
			$(".moverAClass").removeClass("moverAClass");
			$(this).addClass("moverAClass");
			$('body').filter(':not(:animated)').animate({scrollTop:'0'},500);
			$('html').filter(':not(:animated)').animate({scrollTop:'0'},500);
		});
	}
	
	var moverGame=function(){
		$("#game").click(function(){
			$(".moverAClass").removeClass("moverAClass");
			$(this).addClass("moverAClass");
			$('body').filter(':not(:animated)').animate({scrollTop:'820'},500);
			$('html').filter(':not(:animated)').animate({scrollTop:'820'},500);
		})
	}
	
	var moverMusic=function(){
		$("#music").click(function(){
			$(".moverAClass").removeClass("moverAClass");
			$(this).addClass("moverAClass");
			$('body').filter(':not(:animated)').animate({scrollTop:'1880'},500);
			$('html').filter(':not(:animated)').animate({scrollTop:'1880'},500);
		})
	}
	
	var moverWallpaper=function(){
		$("#wallpaper").click(function(){
			$(".moverAClass").removeClass("moverAClass");
			$(this).addClass("moverAClass");
			$('body').filter(':not(:animated)').animate({scrollTop:'3100'},500);
			$('html').filter(':not(:animated)').animate({scrollTop:'3100'},500);
		})
	}
	
	var moverTop=function(){
		$("#top>a").click(function(){
			$('body').filter(':not(:animated)').animate({scrollTop:'0'},500);
			$('html').filter(':not(:animated)').animate({scrollTop:'0'},500);
		});
	}
	return{
		init:function(){
			homeInit();
			moverImgColor();
			clickMoverImg();
			navigation();
			moverGameUpdateImg();
			moverComic();
			moverGame();
			moverMusic();
			moverWallpaper();
			moverTop();
		}
	}
}();
$(function(){
	Home.init();
})


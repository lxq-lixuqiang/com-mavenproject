var Game=function(){
	var clearAutoMover=setInterval(auto,5000);
	var HeaderLogin=function(){
		$("a[href=game]").attr("href","javescript:;");
		$("a[data-imgid]").mouseenter(function(){
			clearInterval(clearAutoMover);
			$("a[data-imgid]").removeClass("moverClass");
			$(this).addClass("moverClass");
			var imgId=$(this).data("imgid");
			$(".moveLi").removeClass("moveLi");
			$(".gameHeadMaxImg>li:nth-of-type("+imgId+")").addClass("moveLi");
		});
		
		$(".loggame").mouseenter(function(){
			clearInterval(clearAutoMover);
		}).mouseleave(function(){
			clearInterval(clearAutoMover);
			clearAutoMover=setInterval(auto,5000);
		});;
	}
	var LeftAndRightMover=function(){
		$("#left").click(function(){
			var imgId=$(".moverClass").data("imgid");
			imgId--;
			if(imgId < 1) imgId=9;
			$("a[data-imgid]").removeClass("moverClass");
			$("a[data-imgid="+imgId+"]").addClass("moverClass");
			$(".moveLi").removeClass("moveLi");
			$(".gameHeadMaxImg>li:nth-of-type("+imgId+")").addClass("moveLi");
		});
		$("#right").click(function(){
			var imgId=$(".moverClass").data("imgid");
			imgId++;
			if(imgId > 9) imgId=1;
			$("a[data-imgid]").removeClass("moverClass");
			$("a[data-imgid="+imgId+"]").addClass("moverClass");
			$(".moveLi").removeClass("moveLi");
			$(".gameHeadMaxImg>li:nth-of-type("+imgId+")").addClass("moveLi");
		});
	}
	
	function auto(){
			var imgId=$(".moverClass").data("imgid");
			imgId++;
			if(imgId > 9) imgId=1;
			$("a[data-imgid]").removeClass("moverClass");
			$("a[data-imgid="+imgId+"]").addClass("moverClass");
			$(".moveLi").removeClass("moveLi");
			$(".gameHeadMaxImg>li:nth-of-type("+imgId+")").addClass("moveLi");
		}
	
	var ClickSectionCenterTitle=function(){
		$(".center-ul01>li").click(function(){
			var title=$(this).data("title");
			$(".center-ul01>li").removeClass("clickHourLiClass");
			$(this).addClass("clickHourLiClass");
			$(".title>div").hide()
			$(".title>.title0"+title).show();
		});
	}
	
	var NewGameMover=function(){
		$("label[data-showimg]").mouseenter(function(){
			var showImg=$(this).data("showimg");
			$(".liMoveClass").removeClass("liMoveClass");
			$(".LeftUl01>li:nth-of-type("+showImg+")").addClass("liMoveClass");
			$(".liShow").removeClass("liShow");
			$(".RightUl01>li:nth-of-type("+showImg+")").addClass("liShow");
		});
	}
	var TabelMove=function(){
		$("#flootDiv01Table01 tbody .gameInfo:nth-of-type(2)").show();
		$("#flootDiv01Table02 tbody .gameInfo:nth-of-type(2)").show();
		$("#flootDiv01Table03 tbody .gameInfo:nth-of-type(2)").show();
		
		$("#flootDiv01Table01 tbody tr:not(.gameInfo)").mouseenter(function(){
			$("#flootDiv01Table01 tbody .gameInfo").hide();
			$(this).next(".gameInfo").show();
		});
		$("#flootDiv01Table02 tbody tr:not(.gameInfo)").mouseenter(function(){
			$("#flootDiv01Table02 tbody .gameInfo").hide();
			$(this).next(".gameInfo").show();
		});
		$("#flootDiv01Table03 tbody tr:not(.gameInfo)").mouseenter(function(){
			$("#flootDiv01Table03 tbody .gameInfo").hide();
			$(this).next(".gameInfo").show();
		});
	}
	return{
		init:function(){
			HeaderLogin();
			LeftAndRightMover();
			ClickSectionCenterTitle();
			NewGameMover();
			TabelMove();
		}
	}
}();

$(function(){
	Game.init();
});

var Comic=function(){
	$("a[href=animation]").attr("href","javescript:;");
	$(".on").animate({"background-position-y":"-726px"},1);
	var MoveImg=function(){
		$(".section-header01-Ul01>li>a").mouseenter(function(){
			if($(this).hasClass("on")){
				$(".on").animate({"background-position-y":"-726px"},1);
				$(".on").animate({"background-position-x":"-854px"},1);
			}else{
				$(this).animate({"background-position-x":"-918px"},1);
			}
		}).mouseleave(function(){
			if($(this).hasClass("on")){
				$(".on").animate({"background-position-y":"-726px"},1);
				$(".on").animate({"background-position-x":"-854px"},1);
			}else{
				$(this).animate({"background-position-x":"-854px"},1);
			}
		});
	}
	var ClickImg=function(){
		$(".section-header01-Ul01>li>a").click(function(){
			if(!$(this).hasClass("on")){
				$(".on").animate({"background-position-y":"-790px"},1);
				$(".on").removeClass("on");
				$(this).addClass("on");
				$(this).animate({"background-position-y":"-726px"},1);
				$(this).animate({"background-position-x":"-854px"},1);
				var comicImg=$(this).data("comicimg");
				$(".section-header01-HeaderImg>div").attr("style","left:"+(comicImg*-440)+"px");
			}
		});
	}
	var AutoImg=function(){
		var clearAutoImgMover=setInterval(autoimgmover,3000);
		$(".section-header01-HeaderImg>div>a").mouseenter(function(){
			clearInterval(clearAutoImgMover);
		}).mouseleave(function(){
			clearInterval(clearAutoImgMover);
			clearAutoImgMover=setInterval(autoimgmover,3000);
		});
		$(".section-header01-Ul01>li>a").mouseenter(function(){
			clearInterval(clearAutoImgMover);
		});
		function autoimgmover(){
			$(".on").animate({"background-position-y":"-790px"},1);
				var comicImg=$(".on").data("comicimg");
				$(".on").removeClass("on");
				comicImg++;
				if(comicImg >4){
					comicImg=0;
				}
				$("a[data-comicimg="+comicImg+"]").addClass("on");
				$("a[data-comicimg="+comicImg+"]").animate({"background-position-y":"-726px"},1);
				$("a[data-comicimg="+comicImg+"]").animate({"background-position-x":"-854px"},1);
				$(".section-header01-HeaderImg>div").attr("style","left:"+(comicImg*-440)+"px");
		}
	}
	
	var operaMoverA=function(){
		
		var date=new Date();
		var day=date.getDay();
		if( day== 0){
			day=7;
		}
		var nowDate1=$("#opera>a[data-datetime="+(day-1)+"]");
		nowDate1.addClass("moverA");
		nowDate1.text("周"+nowDate1.text());
		$(".section-header03-divClass").attr("style","left:"+((day-1)*-1000)+"px");
		
		$("#opera>a").click(function(){
			var moverA=$("#opera>.moverA");
			moverA.text(moverA.text().substring(1));
			moverA.removeClass("moverA");
			$(this).addClass("moverA");
			$(this).text("周"+$(this).text());
			$(".opera>.showUl").removeClass("showUl");
			var datetime=$(this).data("datetime");
			$(".section-header03-divClass").attr("style","left:"+(datetime*-1000)+"px");
		});
		
	}
	
	var animationInfoTemplate=Handlebars.compile($("#animationInfoTemplate").html());
	var animationInfoSelectClass=$(".animationInfoSelectClass");
	var animationInfoClass=$(".animationInfoClass");
	var animationSelect=$(".animationSelect");
	function ajax(pageNum,year,animationType,animationQuarter){
		var data={pageNum:pageNum,year:year,animationType:animationType,animationQuarter:animationQuarter};
		animationInfoClass.busyLoad("show");
		animationSelect.busyLoad("show");
		$.getJSON("animationInfoFind",data,function(data){
			var newAnimationInfoHtml=animationInfoTemplate(data);
			animationInfoSelectClass.html(newAnimationInfoHtml);
			animationInfoClass.busyLoad("hide");
			animationSelect.busyLoad("hide");
		});
	}
	
	pagingButton=function(){
		animationInfoSelectClass.on("click",".ulNotClick[data-pagenum]",function(){
			var pageNum=$(this).data("pagenum");
			var num=$("#title").attr("data-num");
			var text=$("#title").attr("data-text");
			if(num == "1"){
				ajax(pageNum,text,null,null);
			}else if(num == "2"){
				ajax(pageNum,null,null,text);
			}else{
				ajax(pageNum,null,text,null);
			}
		});
	}
	
	var yearSelect=function(){
		$("#yearSelect>span").click(function(){
			var year=$(this).find(">label").text();
			$("#title").text($(this).text());
			$("#title").attr("data-num",1);
			$("#title").attr("data-text",year);
			ajax(1,year,null,null);
		});
	}
	
	var animationQuarterSelect=function(){
		$("#animationQuarterSelect>span").click(function(){
			var animationQuarter=$(this).find(">label").text();
			$("#title").text($(this).text());
			$("#title").attr("data-num",2);
			$("#title").attr("data-text",animationQuarter);
			ajax(1,null,null,animationQuarter);
		});
	}
	
	var animationTypeSelect=function(){
		$("#animationTypeSelect>span").click(function(){
			var animationType=$(this).text();
			$("#title").text(animationType);
			$("#title").attr("data-num",3);
			$("#title").attr("data-text",animationType);
			ajax(1,null,animationType,null);
		});
	}
	return{
		init:function(){
			MoveImg();
			ClickImg();
			AutoImg();
			operaMoverA();
			pagingButton();
			yearSelect();
			animationQuarterSelect();
			animationTypeSelect();
		}
	}
}();

$(function(){
	Comic.init();
});

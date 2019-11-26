var UpdateUserHeadPictureId=function(){
	var headInit=function(){
		$("#updateUserHeadPictureId>a").attr("href","javascript:;");
		$(".sectionTopMover>li").mouseenter(function(){
			$(this).attr("class","mover");
			$("#updateUserHeadPictureId").attr("class","click");
		}).mouseleave(function(){
			$(this).attr("class","noClick");
			$("#updateUserHeadPictureId").attr("class","click");
		});
		$("#userHeaderImg").attr("href","javascript:;");
	}
	
	var pagingButton=function(){
		$(".ulNotClick").click(function(){
			var pageNum=$(this).data("pagenum");
			location.href="updateUserHeadPictureId?pageNum="+pageNum;
		});
	}
	
	var mouseDiv=function(){
		$(".position-relative").mouseenter(function(){
			var oldId=$("#oldId").val();
			var p=$(this).find(" p[data-id]");
			var newId=p.data("id");
			if(oldId == newId){
				p.attr("class","spinkitPClick");
				p.text("已使用");
			}else{
				p.attr("class","spinkitP");
				p.text("使用此图");
			}
		});
	}
	
	var spinkitPButton=function(){
		$(".pictureClass").on("click",".spinkitP",function(){
			var textcontent;
			var $this=$(this);
			var imgIdSrc=$this.prev().prev().attr("src");
			var userHeadPictureId=$this.data("id");
			var headPicture=$this.data("headpicture");
			var userId=$("#userId").val();
			var data={userHeadPictureId:userHeadPictureId,userId:userId,headPicture:headPicture};
			$.getJSON("updatePictureId",data,function(data){
				if(data.isOk){
					textcontent="使用成功！";
					$(".loginHeader>img").attr("src",imgIdSrc);
					$("#oldId").val(userHeadPictureId);
					$this.attr("class","spinkitPClick");
					$this.text("已使用");
				}else{
					textcontent="使用失败，请稍后再试！";
				}
				new NoticeJs({
				    text: textcontent,
				    position: 'middleCenter',
				    animation: {
				        open: 'animated zoomIn',
				        close: 'animated zoomOut'
				  }
				}).show();
			});
		});
	}
	return {
		init:function(){
			headInit();
			pagingButton();
			mouseDiv();
			spinkitPButton();
		}
	}
}();
$(function(){
	UpdateUserHeadPictureId.init();
})
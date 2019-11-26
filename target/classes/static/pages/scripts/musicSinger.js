var WallpaperTheme=function(){
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
		
		$("#select").mouseenter(function(){
			$(this).attr("src","assets/pages/img/common/ImageSelect02.png");
		}).mouseleave(function(){
			$(this).attr("src","assets/pages/img/common/ImageSelect01.png");
		});
		
		if($("#msg").val().length >0){
			new NoticeJs({
			    text: $("#msg").val(),
			    position: 'middleCenter',
			    animation: {
			        open: 'animated zoomIn',
			        close: 'animated zoomOut'
			  }
			}).show();
		}
		
		$('[data-toggle="tooltip"]').tooltip();
	}
	
	var pagingButton=function(){
		$(".ulNotClick").click(function(){
			var pageNum=$(this).data("pagenum");
			location.href="musicSinger?pageNum="+pageNum;
		});
	}
	
	function seach(){
		var name=$("#name").val();
		var sex=$("#sex").val();
		location.href="musicSinger?pageNum=1&name="+name+"&sex="+sex;
	}
	var selectButton=function(){
		$("#select").click(function(){
			seach();
		});
		$("#name").keyup(function(e){
			if(e.which == 13){
				seach();
			}
		});
	}
	
	var deleteButton=function(){
		$(".delete").click(function(){
			var id=$(this).data("id");
			Lobibox.confirm({
				title: '温馨提示：',
			    msg: "确定要删除此歌手及歌曲和mv吗？",
			    width: 300,
			    buttons: {
			    	yes: {
			    	    'class': 'lobibox-btn lobibox-btn-no',
			    	    text: '确 定',
			    	    closeOnClick: true
			    	  },
			    	  no: {
			    	    'class': 'lobibox-btn lobibox-btn-default',
			    	    text: '取 消',
			    	    closeOnClick: true
			    	  }
			    },
			    callback: function ($this, type, ev) {
			        if(type == 'yes'){
			    		location.href="musicSingerDelete?id="+id;
			        }
			    }
			});
		});
	}
	
	return {
		init:function(){
			headInit();
			pagingButton();
			selectButton();
			deleteButton();
		}
	}
}();
$(function(){
	WallpaperTheme.init();
});
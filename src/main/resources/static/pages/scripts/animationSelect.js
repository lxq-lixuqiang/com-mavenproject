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
			var setName=$("#setName").val();
			location.href="animationSelect?pageNum="+pageNum+"&setName="+setName;
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
		var setName=$("#setName").val();
		location.href="animationSelect?setName="+setName+"&pageNum=1";
	}
	var selectButton=function(){
		$("#select").click(function(){
			selectUser();
		});
		$("#setName").keyup(function(e){
			if(e.which == 13){
				selectUser();
			}
		});
	}
	
	var deleteButton=function(){
		$(".delete").click(function(){
			var id=$(this).data("id");
			Lobibox.confirm({
				title: '温馨提示：',
			    msg: "确定删除此歌曲的所有信息吗？",
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
			    		location.href="animationDelete?id="+id;
			        }
			    }
			});
		});
	}
	
	return{
		init:function(){
			headInit();
			pagingButton();
			seachMove();
			selectButton();
			deleteButton();
		}
	}
}();
$(function(){
	UserInfo.init();
});
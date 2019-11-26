var UserInfo=function(){
	//把字符串转成数组
	var musicIds=[];
	var findMusicIds=$("#musicIds").val().substring(1,$("#musicIds").val().length-1);
	if(findMusicIds.length >0){
		musicIds=findMusicIds.split(",");
	}
	//转数字
	for(var i=0,len=musicIds.length;i<len;i++){
		musicIds[i]=parseInt(musicIds[i]);
	}
	//判断是否选中
	for(var i=0,len=$(".blue").length;i<len;i++){
		var num=$($(".blue")[i]).data("musicid");
		for(var j=0,leg=musicIds.length;j<leg;j++){
			if(musicIds[j] == num){
				$("[class=blue][data-musicid="+num+"]").attr("checked","checked");
				break;
			}
		}
	}
	
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
		pageSizeAndpageNum.on("click","[class=ulNotClick][data-pagesize][data-pagenum]",function(){
			var pageNum=$(this).data("pagenum");
			var pageSize=$(this).data("pagesize");
			ajax(pageNum,pageSize);
		});
	}
	
	var seachMove=function(){
		$("#select").mouseenter(function(){
			$(this).attr("src","assets/pages/img/common/ImageSelect02.png");
		}).mouseleave(function(){
			$(this).attr("src","assets/pages/img/common/ImageSelect01.png");
		});
	}
	
	var musicInfoTemplate=Handlebars.compile($("#musicInfoTemplate").html());
	var musicPagingTemplate=Handlebars.compile($("#musicPagingTemplate").html());
	var musicInfo=$("#musicInfo");
	var pageSizeAndpageNum=$("#pageSizeAndpageNum");
	var sectionBottonRight=$(".section-botton-right");
	function ajax(pageNum,pageSize){
		sectionBottonRight.busyLoad("show");
		var name=$("#name").val();
		var data={pageNum:pageNum,pageSize:pageSize,name:name};
		$.getJSON("musicSongsheetInfoUpdateSelect",data,function(data){
			var newMusicHtml=musicInfoTemplate(data);
			var newMusicPagingHtml=musicPagingTemplate(data);
			
			musicInfo.html(newMusicHtml);
			pageSizeAndpageNum.html(newMusicPagingHtml);
			
			for(var i=0,len=$(".blue").length;i<len;i++){
				var num=$($(".blue")[i]).data("musicid");
				for(var j=0,leg=musicIds.length;j<leg;j++){
					if(musicIds[j] == num){
						$("[class=blue][data-musicid="+num+"]").attr("checked","checked");
						break;
					}
				}
			}
			sectionBottonRight.busyLoad("hide");
		});
	}
	
	var selectButton=function(){
		$("#select").click(function(){
			var pageSize=$(this).data("pagesize");
			ajax(1,pageSize);
		});
		$("#name").keyup(function(e){
			if(e.which == 13){
				var pageSize=$("#select").data("pagesize");
				ajax(1,pageSize);
			}
		});
	}
	
	var checkboxButton=function(){
		sectionBottonRight.on("click","[type=checkbox][data-musicid]",function(){
			var num=$(this).data("musicid");
			if($(this).get(0).checked){
			}else{
				musicIds.splice($.inArray(num,musicIds),1);
			}
			if(musicIds.length < 30){
				if($(this).get(0).checked){
					musicIds.push(num);
				}
			}else{
				$(this).prop("checked",false);
				Lobibox.confirm({
					title: '温馨提示：',
				    msg: "歌单最多只能有30首歌曲！",
				    width: 300,
				    buttons: {
				    	yes: {
				    	    'class': 'lobibox-btn lobibox-btn-no',
				    	    text: '确 定',
				    	    closeOnClick: true
				    	  }
				    }
				});
			}
		});
	}
	
	var UpdateButton=function(){
		$("#musicSongsheetInfoUpdate").click(function(){
			var songsheetId=$(this).data("songsheetid");
			location.href="musicSongsheetInfoUpdateMusicId?musicIds="+musicIds+"&songsheetId="+songsheetId;
		});
	}
	
	return{
		init:function(){
			headInit();
			pagingButton();
			seachMove();
			selectButton();
			checkboxButton();
			UpdateButton();
		}
	}
}();
$(function(){
	UserInfo.init();
});
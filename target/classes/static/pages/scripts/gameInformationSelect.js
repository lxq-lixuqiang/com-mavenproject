var GameInformationSelect=function(){
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
	}
	
	var deleteButton=function(){
		$(".delete").click(function(){
			var id=$(this).data("id");
			Lobibox.confirm({
				title: '温馨提示：',
			    msg: "确定要删除此导航栏信息吗？",
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
			    		location.href="gameInformationDelete?id="+id;
			        }
			    }
			});
		});
	}
	
	var numArray=[];
	var typeClick="";
	var updateButton=function(){
		$(".update").click(function(){
			var typeName=$(this).closest(".li").find(" .type").text();
			typeClick=typeName;
			if(typeName == "图 片"){
				var li=$(this).closest(".li");
				var number=li.find(" .number").text();
				li.find(" .numberClass").val(number);
				li.find(" #img").prop("checked",true);
				li.find(" .position-relative02").hide();
				li.attr("class","liUpdate");
				$(this).closest(".liUpdate").find(".titleH4").text("点击图片更换");
				var num=$(this).data("id");
				numArray[num]=$(this).closest(".liUpdate").find(" .imgClass>img").attr("src");
			}else if(typeName == "文 字"){
				var li=$(this).closest(".li");
				var number=li.find(" .number").text();
				li.find(" .numberClass").val(number);
				li.find(" #text").prop("checked",true);
				li.find(" .position-relative").hide();
				li.find(" .contextTextarea").val(li.find(" .context").text());
				li.attr("class","liUpdate");
				var num=$(this).data("id");
				numArray[num]=$(this).closest(".liUpdate").find(" .context").text();
			}
		});
	}
	
	var cancelButton=function(){
		$(".cancel").click(function(){
			var liUpdate=$(this).closest(".liUpdate");
			liUpdate.find(".titleH4").text("");
			var num=$(this).data("id");
			if(typeClick=="图 片"){
				liUpdate.find(" .position-relative").show();
				liUpdate.find(" .position-relative02").hide();
				liUpdate.find(" .imgClass>img").attr("src",numArray[num]);
			}else if(typeClick=="文 字"){
				liUpdate.find(" .position-relative02").show();
				liUpdate.find(" .position-relative").hide();
				liUpdate.find(" .context").text(numArray[num]);
			}
			$(this).closest(".liUpdate").attr("class","li");
		});
	}
	
	var typeClassButton=function(){
		$(".typeClass>.blue").click(function(){
			var value=$(this).attr("value");
			if(value == 0){
				var liUpdate=$(this).closest(".liUpdate");
				liUpdate.find(".titleH4").text("");
				liUpdate.find(" .position-relative02").show();
				liUpdate.find(" .position-relative").hide();
			}else if(value == 1){
				var liUpdate=$(this).closest(".liUpdate");
				liUpdate.find(".titleH4").text("点击图片更换");
				liUpdate.find(" .position-relative").show();
				liUpdate.find(" .position-relative02").hide();
			}
		});
	}
	
	var fileUpload=function(){
		$(".fileUpload").on('change', function () {
			var ImgPath=$(this);
			var num=ImgPath.data("id");
			var isOk=false;
			var context;
		    if (typeof (FileReader) != "undefined") {
		    	if(ImgPath != null && ImgPath.val().length >0){
		    		var ImgTypeIndex=ImgPath.val().lastIndexOf(".");
			    	if(ImgPath.val().substring(ImgTypeIndex) == ".png" ||
						ImgPath.val().substring(ImgTypeIndex) == ".jpg"){
			    		var files=ImgPath[0].files;
			    		if(files[0].size <= 2097152){
			    			var image_holder = $(this).prev();
					        image_holder.empty();
						    var reader = new FileReader();
						    reader.onload = function (e) {
						    	$("<img />", {
						            "src": e.target.result,
						            "class": "thumb-image"
						        }).appendTo(image_holder);
						    }
						    image_holder.show();
						    reader.readAsDataURL($(this)[0].files[0]);
			    		}else{
			    			context="图片大小不能超过2M！";
			    			isOk=true;
			    		}
			    	}else{
			    		context="只支持jpg和png类型的图片！";
			    		isOk=true;
			    	}
			    }else{
			    	if(numArray[num] == undefined){
			    		$(this).prev().find(" img").remove();
			    	}else{
			    		$(this).prev().find(" img").attr("src",numArray[num]);
			    	}
			    }
		    } else {
		    	context="你的浏览器不支持FileReader.";
		        isOk=true;
		    }
		    if(isOk){
		    	ImgPath.val("");
		    	if(numArray[num] == undefined){
		    		$(this).prev().find(" img").remove();
		    	}else{
		    		$(this).prev().find(" img").attr("src",numArray[num]);
		    	}
		    	 new NoticeJs({
					    text: context,
					    position: 'middleCenter',
					    animation: {
					        open: 'animated zoomIn',
					        close: 'animated zoomOut'
					  }
				}).show();
		    }
		});
	}
	
	var saveButton=function(){
		$(".save").click(function(){
			var isOk=true;
			var img=$(this).closest(".liUpdate").find(" #img").prop("checked");
			var text=$(this).closest(".liUpdate").find(" #text").prop("checked");
			var formData=new FormData();
			formData.append("id",$(this).data("id"));
			if(img){
				ImgPath=$(this).closest(".liUpdate").find(" .fileUpload");
				formData.append("file",ImgPath[0].files[0]);
				formData.append("infoOrImgType",1);
			}else if(text){
				var context=$(this).closest(".liUpdate").find(" .contextTextarea").val();
				if(context.length>1000){
					new NoticeJs({
					    text: "文本字数不能超过1000个字！",
					    position: 'middleCenter',
					    animation: {
					        open: 'animated zoomIn',
					        close: 'animated zoomOut'
					  }
					}).show();
					isOk=false;
				}
				formData.append("context",context);
				formData.append("infoOrImgType",0);
			}
			if(isOk){
				formData.append("serialNumber",$(this).closest(".liUpdate").find(" .numberClass").val());
				$.ajax({
					async:false,
					url:"fileUploadGameInformation",
					type: "post" ,
					data: formData ,
					processData: false ,
					contentType: false ,
					cache: false ,
					dataType: "json" ,
					success: function(data){
						location.href="gameInformationSelect";
					}
				});
			}
		});
	}
	
	var pagingButton=function(){
		$(".ulNotClick").click(function(){
			var pageNum=$(this).data("pagenum");
			location.href="gameInformationSelect?pageNum="+pageNum;
		});
	}
	return {
		init:function(){
			headInit();
			deleteButton();
			updateButton();
			cancelButton();
			typeClassButton();
			fileUpload();
			saveButton();
			pagingButton();
		}
	}
}();
$(function(){
	GameInformationSelect.init();
});
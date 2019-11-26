var CommonUserHeadPicture=function(){
	var numArray=[];
	var headInit=function(){
		if($("#errorInfo").val().length>0){
			new NoticeJs({
			    text: $("#errorInfo").val(),
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
			location.href="userHeadPicture?pageNum="+pageNum;
		});
	}
	
	var addButton=function(){
		$("#add").click(function(){
			location.href="addUserHeadPicture";
		});
	}
	
	var deleteButton=function(){
		$(".delete").click(function(){
			var id=$(this).data("id");
			Lobibox.confirm({
				title: '温馨提示：',
			    msg: "确定删除此图片吗？",
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
						location.href="deleteUserHeadPicture?id="+id;
			        }
			    }
			});
		});
	}
	
	var updateButton=function(){
		$(".update").click(function(){
			$(this).closest(".li").attr("class","liUpdate");
			$(this).closest(".liUpdate").find(" h4").text("点击图片更换");
			var num=$(this).data("id");
			numArray[num]=$(this).closest(".liUpdate").find(" .imgClass").attr("src");
		});
	}
	
	var cancelButton=function(){
		$(".cancel").click(function(){
			$(this).closest(".liUpdate").attr("class","li");
			$(this).closest(".li").find(" h4").text("");
			var num=$(this).data("id");
			if(numArray[num] == undefined){
				$(this).closest(".li").find(" .imgClass").remove();
			}else{
				$(this).closest(".li").find(" .imgClass").attr("src",numArray[num]);
			}
		});
	}
	
	var saveButton=function(){
		$(".save").click(function(){
			var textContent;
			var flag=true;
			ImgPath=$(this).closest(".liUpdate").find(" .fileUpload");
			if(ImgPath != null && ImgPath.val().length >0){
				var id=ImgPath.data("id");
				var formData=new FormData();
				formData.append("file",ImgPath[0].files[0]);
				formData.append("id",id);
				$.ajax({
					async:false,
					url:"fileUploadUserHeadPicture",
					type: "post" ,
					data: formData ,
					processData: false ,
					contentType: false ,
					cache: false ,
					dataType: "json" ,
					success: function(data){
						if(data.isOk){
							textContent="修改成功！";
						}else{
							textContent="修改失败，请稍后再试";
							flag=false;
						}
					}
				});
			}else{
				textContent="修改成功！";
			}
			new NoticeJs({
			    text: textContent,
			    position: 'middleCenter',
			    animation: {
			        open: 'animated zoomIn',
			        close: 'animated zoomOut'
			  }
			}).show();
			if(flag){
				$(this).closest(".liUpdate").attr("class","li");
				$(this).closest(".li").find(" h4").text("");
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
						            "class": "thumb-image imgClass"
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
			    		$(this).prev().find(" .imgClass").remove();
			    	}else{
			    		$(this).prev().find(" .imgClass").attr("src",numArray[num]);
			    	}
			    }
		    } else {
		    	context="你的浏览器不支持FileReader.";
		        isOk=true;
		    }
		    if(isOk){
		    	ImgPath.val("");
		    	if(numArray[num] == undefined){
		    		$(this).prev().find(" .imgClass").remove();
		    	}else{
		    		$(this).prev().find(" .imgClass").attr("src",numArray[num]);
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
	return {
		init:function(){
			headInit();
			pagingButton();
			addButton();
			deleteButton();
			updateButton();
			cancelButton();
			saveButton();
			fileUpload();
		}
	}
}();
$(function(){
	CommonUserHeadPicture.init();
});
var WallpaperNavigationBar=function(){
	var numArray=[];
	var updateButton=function(){
		$(".update").click(function(){
			var num=$(this).data("num");
			numArray[num]=$(this).parent().prev().find(" .imgClass").attr("src");
			$(this).parent().prev().attr("class","imgDivUpdate");
			$(this).parent().attr("class","buttonDivUpdate");
		});
	}
	
	var cancelButton=function(){
		$(".cancel").click(function(){
			var num=$(this).prev().prev().data("num");
			$(this).parent().prev().find(" .imgClass").attr("src",numArray[num]);
			$(this).parent().prev().attr("class","imgDiv");
			$(this).parent().attr("class","buttonDiv");
		});
	}
	
	var saveButton=function(){
		$(".save").click(function(){
			var textContent;
			var flag=true;
			ImgPath=$(this).parent().prev().find(" .fileUpload");
			if(ImgPath != null && ImgPath.val().length >0){
				var id=ImgPath.prev().data("id");
				var formData=new FormData();
				formData.append("file",ImgPath[0].files[0]);
				formData.append("id",id);
				$.ajax({
					async:false,
					url:"wallpaperNavigationBarFileUpload",
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
				$(this).parent().prev().attr("class","imgDiv");
				$(this).parent().attr("class","buttonDiv");
			}
		});
	}
	
	var fileUpload=function(){
		$(".fileUpload").on('change', function () {
			var ImgPath=$(this);
			var num=ImgPath.prev().data("id");
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
			    	$(this).prev().find(" .imgClass").attr("src",numArray[num]);
			    }
		    } else {
		    	context="你的浏览器不支持FileReader.";
		        isOk=true;
		    }
		    if(isOk){
		    	ImgPath.val("");
		    	ImgPath.prev().find(" .imgClass").attr("src",numArray[num]);
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
	
	return{
		init:function(){
			updateButton();
			cancelButton();
			saveButton();
			fileUpload();
		}
	}
}();
$(function(){
	WallpaperNavigationBar.init();
});
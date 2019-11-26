var AnimationEntertainmentAnimationUpdate=function(){
	var oldImgPath=$(".imgClass").attr("src");
	
	var headInit=function(){
		$("#img").val("");
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
	
	var fileUpload=function(){
		$("#fileUpload").on('change', function () {
			var isOk=false;
			var context;
		    if (typeof (FileReader) != "undefined") {
		    	var ImgPath=$(this);
		    	var image_holder = $("#image-holder");
		        $("#img").val("");
		    	var ImgTypeIndex=ImgPath.val().lastIndexOf(".");
		    	if(ImgPath != null && ImgPath.val().length >0){
			    	if(ImgPath.val().substring(ImgTypeIndex) == ".png" ||
						ImgPath.val().substring(ImgTypeIndex) == ".jpg"){
			    		var files=ImgPath[0].files;
			    		if(files[0].size <= 2097152){
					        image_holder.empty();
				    		var name=ImgPath.val().lastIndexOf("\\");
						    $("#img").val(ImgPath.val().substring(name+1));
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
			    	$("#fileUpload").val("");
			    	$(".imgClass").attr("src",oldImgPath);
			    }
		    } else {
		    	context="你的浏览器不支持FileReader.";
		        isOk=true;
		    }
		    if(isOk){
		    	 $("#fileUpload").val("");
		    	 $(".imgClass").attr("src",oldImgPath);
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
	
	var fileUpload2=function(){
		$("#fileUpload2").on('change', function () {
			var isOk=false;
			var context;
		    if (typeof (FileReader) != "undefined") {
		    	var ImgPath=$(this);
		    	$("#entertainmentTypeId").val("");
		    	var ImgTypeIndex=ImgPath.val().lastIndexOf(".");
		    	if(ImgPath != null && ImgPath.val().length >0){
			    	if(ImgPath.val().substring(ImgTypeIndex) == ".mp4"){
			    		var files=ImgPath[0].files;
			    		if(files[0].size <= 52428800){
				    		var name=ImgPath.val().lastIndexOf("\\");
						    $("#entertainmentTypeId").val(ImgPath.val().substring(name+1));
			    		}else{
			    			context="视频大小不能超过50M！";
			    			isOk=true;
			    		}
			    	}else{
			    		context="只支持mp4类型的视频！";
			    		isOk=true;
			    	}
			    }else{
			    	$("#fileUpload2").val("");
			    }
		    } else {
		    	context="你的浏览器不支持FileReader.";
		        isOk=true;
		    }
		    if(isOk){
		    	$("#fileUpload2").val("");
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
	
	var bootstrapValidator=function(){
		$("#form").bootstrapValidator({
			feedbackIcons: {	
	             valid: "glyphicon glyphicon-ok",
	             invalid: "glyphicon glyphicon-remove",
	             validating: "glyphicon glyphicon-refresh"
	        },
	        fields: {
	        	name:{
	        		validators:{
	        			notEmpty: {
	        				message:"名称不能为空！"
	        			},
	        			stringLength:{
	        				max:30,
	        				message:"名称长度不能超过%s位！"
	        			}
	        		}
	        	},
	        	entertainmentAnimationPath:{
	        		validators:{
	        			stringLength:{
	        				max:1000,
	        				message:"其他资源路径长度不能超过%s位！"
	        			}
	        		}
	        	},
	        	"entertainmentTypeId.id":{
	        		validators:{
	        			notEmpty: {
	        				message:"请选择类型！"
	        			}
	        		}
	        	}
	        }
		});
	}
	
	return {
		init:function(){
			headInit();
			fileUpload();
			fileUpload2();
			bootstrapValidator();
		}
	}
}();
$(function(){
	AnimationEntertainmentAnimationUpdate.init();
})
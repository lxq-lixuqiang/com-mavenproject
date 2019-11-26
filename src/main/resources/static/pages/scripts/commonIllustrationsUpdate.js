var CommonIllustrationsUpdate=function(){
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
			var ImgPath=$(this);
			var isOk=false;
			var context;
		    if (typeof (FileReader) != "undefined") {
		    	var image_holder = $("#image-holder");
		        image_holder.empty();
		        $("#img").val("");
		    	var ImgTypeIndex=ImgPath.val().lastIndexOf(".");
		    	if(ImgPath != null && ImgPath.val().length >0){
			    	if(ImgPath.val().substring(ImgTypeIndex) == ".png" ||
						ImgPath.val().substring(ImgTypeIndex) == ".jpg"){
			    		var files=ImgPath[0].files;
			    		if(files[0].size <= 2097152){
				    		var name=ImgPath.val().lastIndexOf("\\");
						    $("#img").val(ImgPath.val().substring(name+1));
						    var reader = new FileReader();
						    reader.onload = function (e) {
						    	imgLabel(e.target.result,image_holder);
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
			    	imgLabel(oldImgPath,image_holder);
			    }
		    } else {
		    	context="你的浏览器不支持FileReader.";
		        isOk=true;
		    }
		    if(isOk){
		    	ImgPath.val("");
		    	imgLabel(oldImgPath,image_holder);
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
	
	function imgLabel(src,container){
		$("<img />", {
            "src": src,
            "class": "thumb-image imgClass"
        }).appendTo(container);
	}
	
	var bootstrapValidator=function(){
		$("#form").bootstrapValidator({
			feedbackIcons: {	
	            valid: "glyphicon glyphicon-ok",
	            invalid: "glyphicon glyphicon-remove",
	            validating: "glyphicon glyphicon-refresh"
	        },
	        fields: {
	        	title:{
	        		validators:{
	        			notEmpty: {
	        				message:"标题不能为空！"
	        			},
	        			stringLength:{
	        				max:20,
	        				message:"标题长度不能超过%s位！"
	        			}
	        		}
	        	},
	        	theme:{
	        		validators:{
	        			notEmpty: {
	        				message:"主题不能为空！"
	        			},
	        			stringLength:{
	        				max:30,
	        				message:"主题长度不能超过%s位！"
	        			}
	        		}
	        	},
	        	author:{
	        		validators:{
	        			notEmpty: {
	        				message:"作者不能为空！"
	        			},
	        			stringLength:{
	        				max:20,
	        				message:"作者长度不能超过%s位！"
	        			}
	        		}
	        	},
	        	content:{
	        		validators:{
	        			stringLength:{
	        				max:200,
	        				message:"内容长度不能超过%s位！"
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
			bootstrapValidator();
		}
	}
}();
$(function(){
	CommonIllustrationsUpdate.init();
})
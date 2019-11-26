var WallpaperThemeAdd=function(){
	
	var headInit=function(){
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
		    	$("#animation").val("");
		    	var ImgTypeIndex=ImgPath.val().lastIndexOf(".");
		    	if(ImgPath != null && ImgPath.val().length >0){
			    	if(ImgPath.val().substring(ImgTypeIndex) == ".mp4"){
			    		var files=ImgPath[0].files;
			    		if(files[0].size <= 209715200){
				    		var name=ImgPath.val().lastIndexOf("\\");
						    $("#animation").val(ImgPath.val().substring(name+1));
			    		}else{
			    			context="视频大小不能超过200M！";
			    			isOk=true;
			    		}
			    	}else{
			    		context="只支持mp4类型的视频！";
			    		isOk=true;
			    	}
			    }else{
			    	$("#fileUpload").val("");
			    }
		    } else {
		    	context="你的浏览器不支持FileReader.";
		        isOk=true;
		    }
		    if(isOk){
		    	$("#fileUpload").val("");
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
	        	setName:{
	        		validators:{
	        			notEmpty: {
	        				message:"名称不能为空！"
	        			},
	        			stringLength:{
	        				max:20,
	        				message:"名称长度不能超过%s位！"
	        			}
	        		}
	        	},
	            animationPath:{
	        	  validators:{
	        			stringLength:{
	        				max:1000,
	        				message:"其他路径资源长度不能超过%s位！"
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
	WallpaperThemeAdd.init();
})
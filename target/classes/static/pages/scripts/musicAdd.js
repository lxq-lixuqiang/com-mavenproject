var WallpaperThemeAdd=function(){
	
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
		    	$("#music").val("");
		    	var ImgTypeIndex=ImgPath.val().lastIndexOf(".");
		    	if(ImgPath != null && ImgPath.val().length >0){
			    	if(ImgPath.val().substring(ImgTypeIndex) == ".m4a" ||
			    		ImgPath.val().substring(ImgTypeIndex) == ".mp3"){
			    		var files=ImgPath[0].files;
			    		if(files[0].size <= 10485760){
				    		var name=ImgPath.val().lastIndexOf("\\");
						    $("#music").val(ImgPath.val().substring(name+1));
			    		}else{
			    			context="音频大小不能超过10M！";
			    			isOk=true;
			    		}
			    	}else{
			    		context="只支持m4a或mp3类型的音频！";
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
	        	file:{
	        		validators:{
	        			notEmpty: {
	        				message:"请选择音乐！"
	        			}
	        		}
	        	},
	        	songName:{
	        		validators:{
	        			notEmpty: {
	        				message:"名称不能为空！"
	        			},
	        			stringLength:{
	        				max:50,
	        				message:"名称长度不能超过%s位！"
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
var AnimationEntertainmentAnimationAdd=function(){
	
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
	
	var fileUpload2=function(){
		$("#fileUpload2").on('change', function () {
			var isOk=false;
			var context;
		    if (typeof (FileReader) != "undefined") {
		    	var ImgPath=$(this);
		    	$("#game").val("");
		    	var ImgTypeIndex=ImgPath.val().lastIndexOf(".");
		    	if(ImgPath != null && ImgPath.val().length >0){
		    		if(ImgPath.val().substring(ImgTypeIndex) == ".zip" ||
						ImgPath.val().substring(ImgTypeIndex) == ".rar"){
		    			var files=ImgPath[0].files;
		    			if(files[0].size <= 314572800){
		    				var name=ImgPath.val().lastIndexOf("\\");
		    				$("#game").val(ImgPath.val().substring(name+1));
		    			}else{
		    				context="游戏大小不能超过300M！";
		    				isOk=true;
		    			}
		    		}else{
		    			context="只支持zip和rar类型的压缩文件！";
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
	        	file:{
	        		validators:{
	        			notEmpty: {
	        				message:"请选择封面！"
	        			}
	        		}
	        	},
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
	        	issuer:{
	        		validators:{
	        			notEmpty: {
	        				message:"发行商不能为空！"
	        			},
	        			stringLength:{
	        				max:50,
	        				message:"发行商长度不能超过%s位！"
	        			}
	        		}
	        	},
	        	gamePath:{
	        		validators:{
	        			stringLength:{
	        				max:1000,
	        				message:"其他资源路径长度不能超过%s位！"
	        			}
	        		}
	        	},
	        	language:{
	        		validators:{
	        			notEmpty: {
	        				message:"请选择语言！"
	        			}
	        		}
	        	},
	        	"platformId.id":{
	        		validators:{
	        			notEmpty: {
	        				message:"请选择平台！"
	        			}
	        		}
	        	},
	        	"typeId.id":{
	        		validators:{
	        			notEmpty: {
	        				message:"请选择类型！"
	        			}
	        		}
	        	},
	        	"classificationId.id":{
	        		validators:{
	        			notEmpty: {
	        				message:"请选择分类！"
	        			}
	        		}
	        	},
	        	content:{
	        		validators:{
	        			notEmpty: {
	        				message:"介绍不能为空！"
	        			},
	        			stringLength:{
	        				max:300,
	        				message:"介绍长度不能超过%s位！"
	        			}
	        		}
	        	},
	        	gameExplain:{
	        		validators:{
	        			notEmpty: {
	        				message:"安装说明不能为空！"
	        			},
	        			stringLength:{
	        				max:200,
	        				message:"安装说明不能超过%s位！"
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
	AnimationEntertainmentAnimationAdd.init();
})
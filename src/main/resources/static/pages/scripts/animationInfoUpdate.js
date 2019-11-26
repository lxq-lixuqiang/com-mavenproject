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
		oldImg=$(".imgClass").attr("src");
		//让复选框选中
		var animationInfos=[];
		var findAnimationInfos=$("#animationInfoTypes").val();
		if(findAnimationInfos.length >0){
			animationInfos=findAnimationInfos.split(" ");
		}
		for(var i=1,len=$("#checkboxesClass .blue").length;i<=len;i++){
			var value=$("#checkboxesClass span:nth-of-type("+i+")>.blue").val();
			for(var j=0,leg=animationInfos.length;j<leg;j++){
				if(animationInfos[j] == value){
					$("#checkboxesClass span:nth-of-type("+i+")>.blue").attr("checked","checked");
					break;
				}
			}
		}
		
	}
	
	var oldImg="";
	var fileUpload=function(){
		$("#fileUpload").on('change', function () {
			var isOk=false;
			var context;
		    if (typeof (FileReader) != "undefined") {
 		        $("#img").val("");
		    	var ImgPath=$(this);
		    	var image_holder = $("#image-holder");
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
			    	$(".imgClass").attr("src",oldImg);
			    	$("#fileUpload").val("");
			    }
		    } else {
		    	context="你的浏览器不支持FileReader.";
		        isOk=true;
		    }
		    if(isOk){
		    	 $(".imgClass").attr("src",oldImg);
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
	        	animationName:{
	        		validators:{
	        			notEmpty: {
	        				message:"名称不能为空！"
	        			},
	        			stringLength:{
	        				max:40,
	        				message:"名称长度不能超过%s位！"
	        			}
	        		}
	        	},
	        	animationType:{
	        		validators:{
	        			notEmpty: {
	        				message:"标签不能为空！"
	        			},
	        			stringLength:{
	        				max:100,
	        				message:"标签长度不能超过%s位！"
	        			}
	        		}
	        	},
	        	"classificationId.id":{
	        		validators:{
	        			notEmpty: {
	        				message:"分类不能为空！"
	        			}
	        		}
	        	},
	        	animationContent:{
	        		validators:{
	        			notEmpty: {
	        				message:"介绍不能为空！"
	        			},
	        			stringLength:{
	        				max:200,
	        				message:"介绍长度不能超过%s位！"
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
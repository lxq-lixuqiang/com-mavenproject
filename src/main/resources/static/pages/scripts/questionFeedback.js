var QuestionFeedback=function(){
	var headInit=function(){
		$("#questionFeedback>a").attr("href","javascript:;");
		$(".sectionTopMover>li").mouseenter(function(){
			$(this).attr("class","mover");
			$("#questionFeedback").attr("class","click");
		}).mouseleave(function(){
			$(this).attr("class","noClick");
			$("#questionFeedback").attr("class","click");
		});
		$("#userHeaderImg").attr("href","javascript:;");
		
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
	
	var bootstrapValidate=function(){
		$("#form").bootstrapValidator({
	        fields: {
	        	title:{
	        		validators:{
	        			notEmpty: {
	        				message:"标题不能为空！"
	        			},
	        			stringLength:{
	        				max:30,
	        				message:"标题长度不能超过%s位！"
	        			}
	        		}
	        	},
	        	type:{
	        		validators:{
	        			notEmpty: {
	        				message:"请选择类型！"
	        			}
	        		}
	        	},
	        	content:{
	        		validators:{
	        			notEmpty: {
	        				message:"内容不能为空！"
	        			},
	        			stringLength:{
	        				max:500,
	        				message:"内容长度不能超过%s位！"
	        			}
	        		}
	        	}
	        }
		});
	}
	
	var resetButton=function(){
		$("#reset").click(function(){
			$("#form").bootstrapValidator("resetForm");
		});
	}
	
	return {
		init:function(){
			headInit();
			bootstrapValidate();
			resetButton();
		}
	}
}();
$(function(){
	QuestionFeedback.init();
});
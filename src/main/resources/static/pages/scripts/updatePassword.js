var UpdatePassword=function(){
	var updatePasswordInit=function(){
		$("#updatePassword>a").attr("href","javascript:;");
		$(".sectionTopMover>li").mouseenter(function(){
			$(this).attr("class","mover");
			$("#updatePassword").attr("class","click");
		}).mouseleave(function(){
			$(this).attr("class","noClick");
			$("#updatePassword").attr("class","click");
		});
		$("#userHeaderImg").attr("href","javascript:;");
		
		if($("#successInfo").val().length >0){
			$("#InfoMsg>div>img").attr("src","assets/pages/img/common/IsOn.png");
			$("#InfoMsgSpan").text($("#successInfo").val());
			$("#backgroundMsg").show();
			$("#InfoMsg").show();
			$("#CloseButton").hide();
			$("#NoDelete").show();
			$("#Delete").show();
		}
		
		$("#Delete").click(function(){
			location.href="logInAgain";
		});
		
		$("#NoDelete").click(function(){
			$("#backgroundMsg").hide();
			$("#InfoMsg").hide();
		})
	}
	
	var validate=function(){
		$("form").validate({
			errorElement:"span",
			rules:{
				oldPassword:{
					required:true,
					rangelength:[6,16]
				},
				newPassword:{
					required:true,
					rangelength:[6,16]
				},
				newPassword02:{
					required:true,
					equalTo:"#newPassword"
				}
			},
			messages:{
				oldPassword:{
					required:"原密码不能为空！",
					rangelength:"原密码必须在6-16位！"
				},
				newPassword:{
					required:"新密码不能为空！",
					rangelength:"新密码必须在6-16位！"
				},
				newPassword02:{
					required:"确认新密码不能为空！",
					equalTo:"两次输入的密码不一致！"
				}
			}
		});
	}
	
	var resetButton=function(){
		$("input[type=reset]").click(function(){
			$("#oldPassword").next().empty();
			$("#newPassword").next().empty();
			$("#newPassword02").next().empty();
		});
	}
	return {
		init:function(){
			updatePasswordInit();
			validate();
			resetButton();
		}
	}
}();
$(function(){
	UpdatePassword.init();
});
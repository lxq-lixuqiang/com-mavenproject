var Login=function(){
	var isOk=false;
	
	var loginInit=function(){
		$("#username").val("");
		$("#userHeaderImg").attr("href","javascript:;");
		$("#mpanel").on("mouseenter",".verify-move-block",function(){
			$(".verify-sub-block").fadeIn(200);
			$(".verify-img-panel").fadeIn(200);
		});
		
		$("#mpanel").on("mouseleave",".verify-bar-area",function(){
			$(".verify-sub-block").fadeOut(500);
			$(".verify-img-panel").fadeOut(500);
		});
		
		if($("#success").val().length>0){
			new NoticeJs({
			    text: $("#success").val(),
			    position: 'middleCenter',
			    animation: {
			        open: 'animated zoomIn',
			        close: 'animated zoomOut'
			  }
			}).show();
		}
	}
	
	var validate=function(){
		$("form").validate({
			//把错误提示的元素由默认的label改为span
			errorElement:"span",
			//验证规则
			rules:{
				username:{
					required:true,
					maxlength:10
				},
				password:{
					required:true,
					rangelength:[6,16]
				}
			},
			messages:{
				username:{
					required:"用户名不能为空！",
					maxlength:"用户名不能超过{0}个！"
				},
				password:{
					required:"密码不能为空！",
					rangelength:"密码必须在{0}-{1}个之间！"
				}
			}
		});
		
		$("form").submit(function(){
			username=$("#username");
			password=$("#password");
			if(isOk==false){
				$("#verificationError").html("拖动方块到空白处完成验证");
			}
				return isOk;
		});
	}
	
	var usernameBlur=function(){
		$("#username").blur(function(){
			$.ajax({
				url:"findUserImg",
				type:"post",
				data:"username="+$(this).val(),
				dataType:"json",
				success:function(data){
					$("#userImg").attr("src",data.userImg);
				}
			});
		});
	}
	
	var verificationControl=function(){
		$('#mpanel4').slideVerify({
		    type : 2,   //类型
		    vOffset : 5,  //误差量，根据需求自行调整
		    vSpace : 5, //间隔
		    imgName : ['306000.jpg', '308650.jpg','301083.jpg','304558.jpg','304985.jpg','313742.jpg','313261.jpg','306234.jpg'],
		    imgSize : {
		      width: '217px',
		      height: '125px',
		    },
		    blockSize : {
		      width: '30px',
		      height: '30px',
		    },
		    barSize : {
		      width : '217px',
		      height : '30px',
		    },
		    ready : function() {
		    },
		    success : function() {
		    	//......后续操作
		    	$("#verificationError").html("");
		    	isOk=true;
		    },
		    error : function() {
		    //              alert('验证失败！');
		    }
		 
		});
	}
	
	return{
		init:function(){
			loginInit();
			validate();
			usernameBlur();
			verificationControl();
		}
	}
}();

$(function(){
	Login.init();
});
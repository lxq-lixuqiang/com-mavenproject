$(function(){
	$("form").validate({
		errorElement:"span",
		rules:{
			username:{
				required:true,
				maxlength:10
			},
			password:{
				required:true,
				rangelength:[6,16]
			},
			password02:{
				required:true,
				equalTo:"#password"
			},
			phone:{
				required:true,
				number:true,
				rangelength:[11,11]
			}
		},
		messages:{
			username:{
				required:"用户名不能为空！",
				maxlength:"用户名不能超过10位！"
			},
			password:{
				required:"密码不能为空！",
				rangelength:"密码必须在6-16位！"
			},
			password02:{
				required:"确认密码不能为空！",
				equalTo:"两次输入的密码不一致"
			},
			phone:{
				required:"手机号码不能为空！",
				number:"手机号码必须都是数字！",
				rangelength:"手机号码必须是11位！"
			}
		}
	});
	
	$("#username").focus(function(){
		$("#usernameScope").html("");
	});
	
	$("#username").blur(usernameBlur);
	
	function usernameBlur(){
		var isNo=false;
		var username=$("#username").val();
		if($.trim(username).length!=0){
			$.ajax({
				async:false,
				url:"findUsername",
				type:"post",
				data:"username="+username,
				dataType:"json",
				success:function(data){
					isNo=data.isOk;
					if(isNo){
						$("#usernameScope").html("此用户名已存在！");
					}
				}
			});
		}
		return isNo;
	};
	
	$("#phone").focus(function(){
		$("#phoneScope").html("");
	});
	
	$("#phone").blur(phoneBlur);
	
	function phoneBlur(){
		var isNo=false;
		var phone=$("#phone").val();
		if($.trim(phone).length!=0 &&
		   $.trim(phone).length==11 &&
		   !isNaN(phone) 
			){
			if(!/^1[3,5,8]\d{9}$/.test(phone)){
				isNo=true;
				$("#phoneScope").html("只支持以13、15、18开头！");
			}else{
				$.ajax({
					async:false,
					url:"findPhone",
					type:"post",
					data:"phone="+$("#phone").val(),
					dataType:"json",
					success:function(data){
					 isNo=data.isOk;
					 if(isNo){
						 $("#phoneScope").html("不能使用重复的手机号码！");
					 }
					}
				});
			}	
		}
		return isNo;
	};
	
	$("form").submit(function(){
		var isOk=true;
		if(usernameBlur()!=false) isOk=false;
		if(phoneBlur()!=false) isOk=false;
		return isOk;
	});
	
});
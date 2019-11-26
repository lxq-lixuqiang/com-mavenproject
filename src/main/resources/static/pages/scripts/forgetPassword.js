$(function(){
	var isOk=false;
	var randNum="";
	var x = 61,y = 0;
	var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
	$("#userHeaderImg").attr("href","javascript:;");
	$("#phone").val("");
	$("#verification").val("");
	$("#verificationInfo").hide();
	
	$("form").validate({
		errorElement:"span",
		rules:{
			phone:{
				number:true,
				rangelength:[11,11]
			},
			newpassword:{
				required:true,
				rangelength:[6,16]
			},
			newpassword02:{
				required:true,
				equalTo:"#newpassword"
			}
		},
		messages:{
			phone:{
				number:"电话必须是11位数字！",
				rangelength:"电话必须是11位数字！"
			},
			newpassword:{
				required:"新密码不能为空！",
				rangelength:"新密码必须在6-16位！"
			},
			newpassword02:{
				required:"确认新密码不能为空！",
				equalTo:"两次输入的密码不一致！"
			}
		}
	});
	
	$("#phone").focus(function(){
		$(this).next().next().empty(); 	   
	});
	
	$("#phone").blur(function(){
		var phone=$(this);
		var phoneSpan=phone.next().next();
		if($.trim(phone.val()).length==11){
			if(!isNaN(phone.val())){
				if(/^1[3,5,8]\d{9}$/.test(phone.val())){
					if($("#verification").val().length == 0){
						$(".tableCommenClass").busyLoad("show");
						$.ajax({
							async:true,
							url:"findPhone",
							type:"post",
							data:"phone="+phone.val(),
							dataType:"json",
							success:function(data){
								$(".tableCommenClass").busyLoad("hide");
								if(data.isOk){
									if(randNum.length <= 0){
										phone.attr("readonly","readonly");
										$("#return").removeClass("classReturn");
										isOk=true;
										$("#verificationInfo").show();
										$("#ok").removeClass("read-only");
										randNum="";
										for(var i=0;i<6;i++){
											var rand = parseInt(Math.random() * (x - y + 1) + y);
											randNum+=chars[rand];
										}
										if(confirm("是否填入验证码:："+randNum)){
											$("#verification").val(randNum);
											$("#verification").focus();
										}
									}
									
								}else{
									phoneSpan.text("没有此电话号码！");
								}
							}
						});
					}
				}else{
					phoneSpan.text("只支持以13、15、18开头的电话！");
				}
			}
		}
	});
	
	$("#verificationInfo").click(function(){
		randNum="";
		for(var i=0;i<6;i++){
			var rand = parseInt(Math.random() * (x - y + 1) + y);
			randNum+=chars[rand];
		}
		if(confirm("是否填入验证码："+randNum)){
			$("#verification").val(randNum);
			$("#verification").focus();
		}
	});
	
	$("#verification").focus(function(){
		$(this).next().next().empty();
	});
	
	$("#ok").click(function(){
		if(isOk==true){
			var verification=$("#verification");
			if(verification.val()==randNum){
				verification.attr("readonly","readonly");
				$("#verificationInfo").hide();
				$("tr[class=disabled]").removeClass("disabled");
				$("#submit").removeClass("disabled");
				$("#ok").hide();
				$("#verification").next().next().empty();
			}else{
				verification.next().next().text("错误，请检查大小写是否正确!");
			}
		}
	});
});
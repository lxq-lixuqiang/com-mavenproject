$(function(){
	$("#administrationUser>a").attr("href","javascript:;");
	$(".sectionTopMover>li").mouseenter(function(){
		$(this).attr("class","mover");
		$("#administrationUser").attr("class","click");
	}).mouseleave(function(){
		$(this).attr("class","noClick");
		$("#administrationUser").attr("class","click");
	});
	
	var userHearderImg=$("#userHearderImg").attr("src");
	var oldusername=$("#username").val();
	var oldphone=$("#phone").val();
	$("#userHeaderImg").attr("href","javascript:;");
	
	//年龄计算
	if($("#birthDataDate").val().length >0){
		var nowDate=new Date();
		var nowYear=nowDate.getFullYear();
		var nowMonth=nowDate.getMonth()+1;
		var nowDate=nowDate.getDate();
		var birthDataDate=$("#birthDataDate").val().split("-");
		var Age=nowYear-birthDataDate[0];
		if(nowMonth == birthDataDate[1]){
			if(nowDate < birthDataDate[2]){
				Age--;
			}
		}else if(nowMonth < birthDataDate[1]){
			Age--;
		}
		$("#Age").text(Age);
	}
	
	$("#logOut").click(function(){
		$("#CloseButton").hide();
		$("#InfoMsg>div>img").attr("src","assets/pages/img/common/IsOn.png");
		$("#InfoMsgSpan").text("确定要退出账号吗？");
		$("#backgroundMsg").show();
		$("#NoDelete").show();
		$("#Delete").show();
		$("#InfoMsg").show();
	});
	
	$("#NoDelete").click(function(){
		$("#InfoMsg").hide();
		$("#backgroundMsg").hide();
	});
	
	$("#Delete").click(function(){
		location.href="logOut";
	});
	
	$("#return").click(function(){
		location.href="administrationUser";
	});
	
	$("input[type=reset]").click(function(){
		$("#userHearderImg").attr("src",userHearderImg);
		$("#phoneScopeError").attr("style","display: none;");
		$("#usernameScopeError").attr("style","display: none;");
		$("#emailScopeError").attr("style","display: none;");
	});
	
	$("form").submit(function(){
		var isOk=true;
		if(usernameBlur()!=false) isOk=false;
		if(phoneBlur()!=false) isOk=false;
		if(emailBlur()!=false) isOk=false;
		if(birthDataDateBlur()!=false) isOk=false;
		if(isOk){
			$("#birthData").val($("#birthDataDate").val());
			$("#hobby").val($("#hobbyTextarea").val());
			$("#sex").val($("#sexSelect").val()=="男"?"Male":$("#sexSelect").val()=="女"?"Female":"Secrecyt");
		}
		return isOk;
	});
	
	$("#birthDataDate").focus(function(){
		$("#birthDataDateScopeError").attr("style","display: none;");
	});
	
	$("#birthDataDate").blur(birthDataDateBlur);
	
	function birthDataDateBlur(){
		var isNo=false;
		var birthDataDate=$("#birthDataDate").val();
		var birthDataDateScopeErrorInfo=null;
		var birthDataDateScopeErrorInfoSize=null;
		var birthDataDateScopeError=null;
		if($.trim(birthDataDate).length==0){
			isNo=false;
		}else if(!/^\d{4}-\d{2}-\d{2}$/.test(birthDataDate)){
			isNo=true;
			birthDataDateScopeErrorInfo="出生日期不正确！";
			birthDataDateScopeErrorInfoSize="font-size:19px;";
			birthDataDateScopeError="display: inline-block;";
		}
		if(isNo){
			$("#birthDataDateScopeErrorInfo").html(birthDataDateScopeErrorInfo);
			if(birthDataDateScopeErrorInfoSize != null){
				$("#birthDataDateScopeErrorInfo").attr("style",birthDataDateScopeErrorInfoSize);
			}else{
				$("#birthDataDateScopeErrorInfo").removeAttr("style");
			}
			$("#birthDataDateScopeError").attr("style",birthDataDateScopeError);
		}
		return isNo;
	}
	
	$("#email").focus(function(){
		$("#emailScopeError").attr("style","display: none;");
	});
	
	$("#email").blur(emailBlur);
	
	function emailBlur(){
		var isNo=false;
		var email=$("#email").val();
		var emailScopeErrorInfo=null;
		var emailScopeErrorInfoSize=null;
		var emailScopeError=null;
		if($.trim(email).length==0){
			isNo=false;
		}else if(!/^\w+@\w+\.(\w{3}|\w{3}\.\w{2})$/.test(email)){
			isNo=true;
			emailScopeErrorInfo="邮箱格式不正确！";
			emailScopeErrorInfoSize="font-size:19px;";
			emailScopeError="display: inline-block;";
		}
		if(isNo){
			$("#emailScopeErrorInfo").html(emailScopeErrorInfo);
			if(emailScopeErrorInfoSize != null){
				$("#emailScopeErrorInfo").attr("style",emailScopeErrorInfoSize);
			}else{
				$("#emailScopeErrorInfo").removeAttr("style");
			}
			$("#emailScopeError").attr("style",emailScopeError);
		}
		return isNo;
	}
	
	$("#username").focus(function(){
		$("#usernameScopeError").attr("style","display: none;");
	});
	
	$("#username").blur(usernameBlur);
	
	function usernameBlur(){
		var isNo=false;
		var username=$("#username").val();
		var usernameScopeErrorInfo=null;
		var usernameScopeErrorInfoSize=null;
		var usernameScopeError=null;
		if(oldusername==username){
			isNo=false;
		}else{
			if($.trim(username).length==0){
				isNo=true;
				usernameScopeErrorInfo="不能为空！";
				usernameScopeError="display: inline-block;";
			}else if($.trim(username).length!=0){
				$.ajax({
					async:false,
					url:"findUsername",
					type:"post",
					data:"username="+username,
					dataType:"json",
					success:function(data){
						isNo=data.isOk;
						if(isNo){
							usernameScopeErrorInfo="此用户名已存在！";
							usernameScopeErrorInfoSize="font-size:19px;";
							usernameScopeError="display: inline-block;";
						}
					}
				});
			}
		}
		if(isNo){
			$("#usernameScopeErrorInfo").html(usernameScopeErrorInfo);
			if(usernameScopeErrorInfoSize != null){
				$("#usernameScopeErrorInfo").attr("style",usernameScopeErrorInfoSize);
			}else{
				$("#usernameScopeErrorInfo").removeAttr("style");
			}
			$("#usernameScopeError").attr("style",usernameScopeError);
		}
		return isNo;
	};
	
	$("#phone").focus(function(){
		$("#phoneScopeError").attr("style","display: none;");
	});
	
	$("#phone").blur(phoneBlur);
	
	function phoneBlur(){
		var isNo=false;
		var phoneScopeErrorInfo=null;
		var phoneScopeErrorInfoSize=null;
		var phoneScopeError=null;
		var phone=$("#phone").val();
		if(oldphone==phone){
			isNo=false;
		}else{
			if($.trim(phone).length==0){
				phoneScopeErrorInfo="不能为空！";
				phoneScopeError="display: inline-block;";
				isNo=true;
			}else if($.trim(phone).length!=11){
				phoneScopeErrorInfo="必须是11位！";
				phoneScopeError="display: inline-block;";
				isNo=true;
			}else if(isNaN(phone)){
				phoneScopeErrorInfo="必须都是数字！";
				phoneScopeError="display: inline-block;";
				isNo=true;
			}else if(!/^1[3,5,8]\d{9}$/.test(phone)){
				isNo=true;
				phoneScopeErrorInfo="只支持以13、15、18开头！";
				phoneScopeErrorInfoSize="font-size:15px;width:200px;";
				phoneScopeError="display: inline-block;";
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
							 phoneScopeErrorInfo="手机号码已存在！";
							 phoneScopeErrorInfoSize="font-size:19px;";
							 phoneScopeError="display: inline-block;";
						}
					}
				});
			}	
		}
		if(isNo){
			$("#phoneScopeErrorInfo").html(phoneScopeErrorInfo);
			if(phoneScopeErrorInfoSize != null){
				$("#phoneScopeErrorInfo").attr("style",phoneScopeErrorInfoSize);
			}else{
				$("#phoneScopeErrorInfo").removeAttr("style");
			}
			$("#phoneScopeError").attr("style",phoneScopeError);
		}
		return isNo;
	};
	
	var oldImgPath=$("#userHearderImg").attr("src");
	$("#file").on('change', function () {
		var ImgPath=$(this);
		var isOk=false;
		var context;
	    if (typeof (FileReader) != "undefined") {
	    	var image_holder = $("#image-holder");
	        image_holder.empty();
	    	var ImgTypeIndex=ImgPath.val().lastIndexOf(".");
	    	if(ImgPath != null && ImgPath.val().length >0){
		    	if(ImgPath.val().substring(ImgTypeIndex) == ".png" ||
					ImgPath.val().substring(ImgTypeIndex) == ".jpg"){
		    		var files=ImgPath[0].files;
		    		if(files[0].size <= 2097152){
			    		var name=ImgPath.val().lastIndexOf("\\");
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
		    		context="只支持jpg和png图片！";
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
	    	$("#InfoMsgSpan").text(context);
			$("#backgroundMsg").show();
			$("#InfoMsg").show();
	    }
	});
	
	function imgLabel(src,container){
		$("<img />", {
            "src": src,
            "class": "thumb-image marginRight",
            "id":"userHearderImg"
        }).appendTo(container);
	}
	
});
function updateButton(but){
	var isOk=true;
	var input=$(but).closest("tr").find(" input");
	if(!idValidator(input[0])) isOk=false;
	if(!nameValidator(input[1])) isOk=false;
	if(!pathValidator(input[2])) isOk=false;
	if(isOk){
		$.ajaxSettings.async=false;
		var data={"newId":input[0].value,"name":input[1].value,"path":input[2].value,"oldId":$(but).closest("tr").find(" div")[0].innerText};
		$.getJSON("updateNavigationBar",data,function(data){
			isOk=data.isOk;
			var msg;
			if(data.isOk){
				msg="修改成功！";
			}else{
				msg="修改失败，请稍后再试！";
			}
			new NoticeJs({
			    text: msg,
			    position: 'middleCenter',
			    animation: {
			        open: 'animated zoomIn',
			        close: 'animated zoomOut'
			    }
			}).show();
		});
		$.ajaxSettings.async=true;
	}
	return isOk;
}

function isError(isOk,errorInfo,span,input){
	if(!isOk){
		span.html(errorInfo);
		$(input).attr("style","border:1px solid red;")
	}else{
		span.html("");
		$(input).attr("style","border:1px solid #CCCCCC;")
	}
}

function idValidator(id){
	var isOk=true;
	var errorInfo="";
	var value=id.value;
	var span=$(id).find("+.error");
	if(value == null || value == ""){
		errorInfo="编号不能为空！";
		isOk= false;
	}else if(value == 0){
		errorInfo="编号不能为0！";
		isOk= false;
	}else if(!/^\d+$/.test(value)){
		errorInfo="编号必须是正整数！";
		isOk= false;
	}else{
		$.ajaxSettings.async=false;
		var data={"findId":value,"nowId":$(id).closest("tr").find(" div")[0].innerText};
		$.getJSON("isExistsId",data,function(data){
			if(data.isOk){
				errorInfo="编号不能重复！";
				isOk=false;
			}
		});
		$.ajaxSettings.async=true;
	}
	isError(isOk,errorInfo,span,id);
	return isOk;
}

function nameValidator(name){
	var isOk=true;
	var errorInfo="";
	var value=name.value;
	var span=$(name).find("+.error");
	if(value == null || value.length == 0){
		errorInfo="名称不能为空！";
		isOk= false;
	}else if(value.length > 10){
		errorInfo="名称长度不能超过10位！";
		isOk= false;
	}
	isError(isOk,errorInfo,span,name);
	return isOk;
}

function pathValidator(path){
	var isOk=true;
	var errorInfo="";
	var value=path.value;
	var span=$(path).find("+.error");
	if(value == null || value.length == 0){
		errorInfo="路径不能为空！";
		isOk= false;
	}else if(value.length > 20){
		errorInfo="路径长度不能超过20位！";
		isOk= false;
	}
	isError(isOk,errorInfo,span,path);
	return isOk;
}

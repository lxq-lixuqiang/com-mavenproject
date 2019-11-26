if($("#msg").val() != null && $("#msg").val().length !=0 ){
	new NoticeJs({
	    text: $("#msg").val(),
	    position: 'middleCenter',
	    animation: {
	        open: 'animated zoomIn',
	        close: 'animated zoomOut'
	    }
	}).show();
}

function updateButton(but){
	var isOk=true;
	var input=$(but).closest("tr").find(" input");
	if(!nameValidator(input[1])) isOk=false;
	if(!pathValidator(input[2])) isOk=false;
	if(isOk){
		$.ajaxSettings.async=false;
		var data={"id":input[0].value,"name":input[1].value,"path":input[2].value};
		$.getJSON("updateFoot",data,function(data){
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

function nameValidator(name){
	var isOk=true;
	var errorInfo="";
	var value=name.value;
	var span=$(name).find("+.error");
	if(value == null || value.length == 0){
		errorInfo="名称不能为空！";
		isOk= false;
	}else if(value.length > 20){
		errorInfo="名称长度不能超过20位！";
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
	}else if(value.length > 50){
		errorInfo="路径长度不能超过50位！";
		isOk= false;
	}
	isError(isOk,errorInfo,span,path);
	return isOk;
}

function removeButton(but){
	Lobibox.confirm({
		title: '温馨提示：',
	    msg: "确定删除此链接吗？",
	    width: 300,
	    buttons: {
	    	yes: {
	    	    'class': 'lobibox-btn lobibox-btn-no',
	    	    text: '确 定',
	    	    closeOnClick: true
	    	  },
	    	  no: {
	    	    'class': 'lobibox-btn lobibox-btn-default',
	    	    text: '取 消',
	    	    closeOnClick: true
	    	  }
	    },
	    callback: function ($this, type, ev) {
	        if(type == 'yes'){
	        	var id=$(but).closest("tr").find(" td")[0].innerText;
	    		location.href="deleteFoot?id="+id;
	    		return true;
	        }else{
	        	return false;
	        }
	    }
	});
}

var CommonFoot=function(){
	var homeInit=function(){
		$("button[disabled]").removeAttr("disabled");
		$("#select").mouseenter(function(){
			$(this).attr("src","assets/pages/img/common/ImageSelect02.png");
		}).mouseleave(function(){
			$(this).attr("src","assets/pages/img/common/ImageSelect01.png");
		});
	}
	
	var addButton=function(){
		$("#addButton").click(function(){
			location.href="addFoot";
		});
	}
	
	function selectName(){
		var pageSize=$("#select").data("pagesize");
		var name=$("#name").val();
		location.href="commonFoot?pageNum=1&pageSize="+pageSize+"&name="+name;
	}
	
	var selectButton=function(){
		$("#select").click(function(){
			selectName();
		});
	}
	
	var keyUp=function(){
		$("#name").keyup(function(e){
			if(e.which == 13){
				selectName()
			}
		});
	}
	
	var pagingButton=function(){
		$(".ulNotClick").click(function(){
			var pageNum=$(this).data("pagenum");
			location.href="commonFoot?pageNum="+pageNum;
		});
	}
	
	return{
		init:function(){
			homeInit();
			addButton();
			selectButton();
			keyUp();
			pagingButton();
		}
	}
}();
$(function(){
	CommonFoot.init();
});
var UpdateGame=function(){
	var LoginInit=function(){
		$("#messages>a").attr("href","javascript:;");
		$(".sectionTopMover>li").mouseenter(function(){
			$(this).attr("class","mover");
			$("#messages").attr("class","click");
		}).mouseleave(function(){
			$(this).attr("class","noClick");
			$("#messages").attr("class","click");
		});
		$("a[class=clickClass]").attr("href","javascript:;");
		$("#userHeaderImg").attr("href","javascript:;");
		$("#imgpath").attr("readonly","readonly");
		$("#imgpath").val("");
	}
	
	var ReturnButton=function(){
		$("#return").click(function(){
			location.href="messagesGame";
		});
	}
	
	var ResetButton=function(){
		var Img=$("#HearderImg").attr("src");
		$("input[type=reset]").click(function(){
			$("#name").next().empty();
			$("#issue").next().empty();
			$("#platform").next().empty();
			$("#special").next().empty();
			$("#forum").next().empty();
			$("#remark").next().empty();
			$("#HearderImg").attr("src",Img);
		});
	}
	
	var Validate=function(){
		$("form").validate({
			errorElement:"span",
			rules:{
				name:{
					required:true,
					maxlength:15
				},
				issue:{
					required:true,
					maxlength:20
				},
				platform:{
					required:true,
					maxlength:15
				},
				special:{
					required:true,
					maxlength:100
				},
				forum:{
					maxlength:100
				},
				remark:{
					maxlength:100
				}
			},
			messages:{
				name:{
					required:"游戏名称不能为空！",
					maxlength:"游戏名称长度不能超过{0}位！"
				},
				issue:{
					required:"发行商不能为空！",
					maxlength:"发行商长度不能超过{0}位！"
				},
				platform:{
					required:"游戏平台不能为空！",
					maxlength:"游戏平台长度不能超过{0}位！"
				},
				special:{
					required:"专题地址不能为空！",
					maxlength:"游戏专题长度不能超过{0}位！"
				},
				forum:{
					maxlength:"论坛地址长度不能超过{0}位！"
				},
				remark:{
					maxlength:"备注长度不能超过{0}位！"
				}
			}
		});
		
		$("form").submit(function(){
			var gametype=$("#gametype").val();
			$("#type").val(gametype);
			return true;
		});
	}
	
	var UploadImg=function(){
		$("#uploadImg").change(function(){
			var ImgPath=$(this);
			if(ImgPath.val().length > 0 && ImgPath.val() != null){
				var ImgTypeIndex=ImgPath.val().lastIndexOf(".");
				if(ImgPath.val().substring(ImgTypeIndex) == ".png" ||
					ImgPath.val().substring(ImgTypeIndex) == ".jpg"){
					var files=ImgPath[0].files;
					if(files[0].size <= 2097152){
						var formData=new FormData();
						for(var i=0,len=files.length;i<len;i++){
							formData.append("fileImg",files[i])
						}
						$.ajax({
							url:"gameImgPath",
							type: "post" ,
							data: formData ,
							processData: false ,
							contentType: false ,
							cache: false ,
							dataType: "json" ,
							success: function(data){
								$.each(data, function(index, path) {
									$("#HearderImg").attr("src",path);
									var ImgNameIndex=path.lastIndexOf("/");
									$("#imgpath").val(path.substring(ImgNameIndex+1));
								});
							}
						});
					}else{
						$("#InfoMsgSpan").text("图片大小不能超过2M！");
						$("#backgroundMsg").show();
						$("#InfoMsg").show();
					}
				}else{
					$("#InfoMsgSpan").text("只支持jpg和png类型的图片！");
					$("#backgroundMsg").show();
					$("#InfoMsg").show();
				}
			}
		});
	}
	return{
		init:function(){
			LoginInit();
			ReturnButton();
			ResetButton();
			Validate();
			UploadImg();
		}
	}
}();
$(function(){
	UpdateGame.init();
});
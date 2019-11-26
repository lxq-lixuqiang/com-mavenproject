var UpdateComic=function(){
	var HeadLogin=function(){
		$("#messages>a").attr("href","javascript:;");
		$(".sectionTopMover>li").mouseenter(function(){
			$(this).attr("class","mover");
			$("#messages").attr("class","click");
		}).mouseleave(function(){
			$(this).attr("class","noClick");
			$("#messages").attr("class","click");
		});
		$("a[class=clickClass]").attr("href","javascript:;");
		$("#imgpath").val("");
		$("#imgpath").attr("readonly","readonly");
		$("#userHeaderImg").attr("href","javascript:;");
	}
	
	var ReturnPath=function(){
		$("#return").click(function(){
			location.href="messagesComic";
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
							url:"comicImgPath",
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
	
	var submitButton=function(){
		$("form").submit(function(){
			$("#date").val($("#playDate").val());
			return true;
		});
	}	
	
	var Reset=function(){
		var HearderImg=$("#HearderImg").attr("src");
		$("input[type=reset]").click(function(){
			$("#name").next().empty();
			$("#address").next().empty();
			$("#imgpath").next().empty();
			$("#setnumber").next().empty();
			$("#type").next().empty();
			$("#playDate").next().empty();
			$("#region").next().empty();
			$("#address").next().empty();
			$("#HearderImg").attr("src",HearderImg);
		});
	}
	
	var validateText=function(){
		$("form").validate({
			errorElement:"span",
			rules:{
				imgpath:{
					required:true,
				},
				name:{
					required:true,
					maxlength:30
				},
				setnumber:{
					required:true,
					digits:true,
					min:1
				},
				type:{
					required:true,
					maxlength:30
				},
				playDate:{
					required:true,
					dateISO:true
				},
				region:{
					required:true,
					maxlength:10
				},
				address:{
					required:true,
					maxlength:100
				}
			},
			messages:{
				imgpath:{
					required:"请选择动漫图片！",
				},
				name:{
					required:"动漫名称不能为空！",
					maxlength:"动漫名称长度不能超过{0}位！"
				},
				setnumber:{
					required:"集数不能为空！",
					digits:"集数只能为正整数！",
					min:"集数最小只能为{0}！"
				},
				type:{
					required:"类型不能为空！",
					maxlength:"类型不能超过{0}位！"
				},
				playDate:{
					required:"开播日期不能为空！",
					dateISO:"开播日期格式不正确！"
				},
				region:{
					required:"地区不能为空！",
					maxlength:"地区长度不能超过{0}位！"
				},
				address:{
					required:"地址不能为空！",
					maxlength:"地址不能超过{0}位！"
				}
			}
		});
	}
	return {
		init:function(){
			HeadLogin();
			ReturnPath();
			UploadImg();
			Reset();
			submitButton();
			validateText();
		}
	}
}();
$(function(){
	UpdateComic.init();
});
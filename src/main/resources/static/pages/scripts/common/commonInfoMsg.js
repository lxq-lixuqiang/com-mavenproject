$(function(){
	if($("#InfoMsgSpan").text().length >0){
		$("#backgroundMsg").show();
		$("#InfoMsg").show();
	}
	
	$("#CloseButton").click(function(){
		$("#backgroundMsg").hide();
		$("#InfoMsg").hide();
	});
});
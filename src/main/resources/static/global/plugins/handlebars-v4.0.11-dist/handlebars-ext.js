Handlebars.registerHelper("dateFormat", function(v) {
	return moment(v).format("YYYY-MM-DD HH:mm:ss");
});

Handlebars.registerHelper("date", function(v) {
	return moment(v).format("YYYY-MM-DD");
});

Handlebars.registerHelper("year", function(v) {
	return moment(v).format("YYYY年MM月");
});

Handlebars.registerHelper("MMAndDD", function(v) {
	return moment(v).format("MM.DD");
});

Handlebars.registerHelper("severityFormat",function(v){
	if(v == 10){
		return "文字错误";
	}else if(v == 20){
		return "次要错误";
	}else{
		return "严重错误";
	}
});

Handlebars.registerHelper("eq",function(v1,v2,options){
	if(v1 == v2){
		return options.fn(this);
	}else{
		return options.inverse(this);
	}
});

Handlebars.registerHelper("neq",function(v1,v2,options){
	if(v1 != v2){
		return options.fn(this);
	}else{
		return options.inverse(this);
	}
});
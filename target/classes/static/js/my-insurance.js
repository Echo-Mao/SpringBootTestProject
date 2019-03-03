$(function(){
	$("#myinsuerans").click(function(){
		location.href="/foreground/my-insurance.html";
	});
	$("#myfile").click(function(){
		location.href="/foreground/my-information.html";
	});
	$("#myorder").click(function(){
		$(this).css({"backgroundColor": "#B8EECF", "fontSize": "19px"});
		$("#mypolicy").css({"backgroundColor": "#E0ECFF", "fontSize": "18px"});
		$(".content1").css("display","block");
		$(".content2").css("display","none");
	});
	$("#mypolicy").click(function(){
		$(this).css({"backgroundColor": "#B8EECF", "fontSize": "19px"});
		$("#myorder").css({"backgroundColor": "#E0ECFF", "fontSize": "18px"});
		$(".content2").css("display","block");
		$(".content1").css("display","none");
	});
	
});

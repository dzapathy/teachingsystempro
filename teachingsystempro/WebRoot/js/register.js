$(function(){
	$("#in3").bind("click",function(){
		if($("#password").val()!=$("#password1").val()){
			alert("两次输入密码不一致.");
			return false;
		}		
	});
});
$(function(){
	$("#rep").bind("click",function(){
		$.ajax({
			url: "savereply",
			type : "post",
			data : {"pid":$("#pid").attr("data-pid"),"content":UM.getEditor('myEditor').getContent()},
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data){
				if(data =="true"){
					location.reload();
				}else{
					alert("回复失败");
				}
			} 
		});
	});
});
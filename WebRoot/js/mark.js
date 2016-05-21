$(function(){
	//每道题判分
	$(".check").bind("click",function(){
		var $btn = $(this);
		var pid = $btn.attr("data-pid");
		var score = $btn.parent().prev().val();
		if(score.trim() == ""){
			alert("请输入分数.");
			return false;
		}
		$.get("changescore",{"pid":pid,"score":score},function(data){
			if(data == "true"){
				$btn.attr("disabled","true");
				$btn.parent().prev().attr("disabled","true");
			}
		});
	});
	
	//最后合分
	$("#setgrade").bind("click",function(){
		var $btn = $(this);
		var stid = $btn.attr("data-stid");
		var pfid = $btn.attr("data-pfid");
		$.post("setgrade",{"stid":stid,"pfid":pfid},function(data){
			$btn.attr("disabled","true");
			$btn.text(data);
		});
	});
});
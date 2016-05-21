$(function(){
	//点击发布
	$(".isIssued").bind("click",function(){
		var tag = confirm("确认发布吗?");
		if(tag){
			var $td = $(this).parent();
			var $tr = $(this).parent().parent();
			$.get("issuepaper",{"paperform.pfid":$tr.find("a").attr("data-pfid"),"paperform.pfstatus":1},function(data){
				if(data == "true"){
					$td.html("<a href='showgrade?pfid="+$tr.find("a").attr("data-pfid")+"' class='btn btn-primary-outline btn-block'>查看成绩</a>");
				}else{
					alert("发布失败");
				}
			});
		}
	});
});
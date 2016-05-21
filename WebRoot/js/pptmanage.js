$(function(){
	//删除PPT
	$(".delppt").bind("click",function(){
		var tag = confirm("确认删除?");
		if(tag){
			var $td = $(this).parent("td");
			var $tr = $(this).parent().parent();
			//alert(""+$td.attr("data-pchapter")+" "+$td.attr("data-pid"));
			$.post("mdelppt",{"ppt.id.pchapter":$td.attr("data-pchapter"),
				"ppt.id.pid":$td.attr("data-pid")},function(data){
				if(data == "true"){
					$tr.remove();
					location.reload();
				}else{
					alert("删除失败");
				}
			});
		}
	});
	
	//管理PPT:公开,非公开
	$(".manageppt").bind("click",function(){
		var $tr = $(this).parent().parent(); //tr
		var $btn = $(this); //btn
		if($btn.text() == "公布"){
			var $td = $btn.parent("td");
			$.post("mupdppt",{"ppt.id.pchapter":$td.attr("data-pchapter"),
				"ppt.id.pid":$td.attr("data-pid"),"ppt.pstatus":1},function(data){
				if(data == "true"){
					$btn.text("关闭");
					$btn.removeClass("btn-success-outline").addClass("btn-warning-outline");
					$tr.children("td:eq(2)").text("公开");
				}else{
					alert("更新失败");
				}
			});
		}else{
			var $td = $btn.parent("td");
			$.post("mupdppt",{"ppt.id.pchapter":$td.attr("data-pchapter"),
				"ppt.id.pid":$td.attr("data-pid"),"ppt.pstatus":0},function(data){
				if(data == "true"){
					$btn.text("公布");
					$btn.removeClass("btn-warning-outline").addClass("btn-success-outline");
					$tr.children("td:eq(2)").text("非公开");
				}else{
					alert("更新失败");
				}
			});			
		}
	});	
});
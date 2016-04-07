$(function(){
	//删除视频
	$(".delvideo").bind("click",function(){
		var tag = confirm("确认删除?");
		if(tag){
			var $td = $(this).parent("td");
			var $tr = $(this).parent().parent();
			$.post("mdelvideo",{"media.id.mchapter":$td.attr("data-mchapter"),
				"media.id.mid":$td.attr("data-mid")},function(data){
				if(data == "true"){
					$tr.remove();
					location.reload();
				}else{
					alert("删除失败");
				}
			});			
		}
	});
	
	//管理视频:公开,非公开
	$(".managevideo").bind("click",function(){
		var $tr = $(this).parent().parent(); //tr
		var $btn = $(this); //btn
		if($btn.text() == "公布"){
			var $td = $btn.parent("td");
			$.post("mupdvideo",{"media.id.mchapter":$td.attr("data-mchapter"),
				"media.id.mid":$td.attr("data-mid"),"media.mstatus":1},function(data){
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
			$.post("mupdvideo",{"media.id.mchapter":$td.attr("data-mchapter"),
				"media.id.mid":$td.attr("data-mid"),"media.mstatus":0},function(data){
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
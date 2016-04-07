$(function(){
	var page = 1; //默认初始化第一页
	var $list = $("#nrlist");
	var t = setTimeout(function(){
		$list.hide();
		$.get("getnoreply",{"page":1},function(data){
			var posts = JSON.parse(data);
			$("#nrcount").text(posts.count);
			$list.empty();			
			$.each(posts.list,function(i,item){
				$list.append("<a href='getreply?pid="+item.pid+"' class='list-group-item'>"+item.ptitle+"</a>");
			});
			$list.slideDown("fast");
		});
	},100);
	
	$("#getno").bind("click",function(){
		var total = $("#nrcount").text();
		var cc = (total%5 == 0) ? (total/5) : parseInt(total/5+1);
		page = page % cc +1;
		$list.hide();
		$.get("getnoreply",{"page":page},function(data){
			var posts = JSON.parse(data);
			$("#nrcount").text(posts.count);
			$list.empty();
			$.each(posts.list,function(i,item){
				$list.append("<a href='getreply?pid="+item.pid+"' class='list-group-item'>"+item.ptitle+"</a>");
			});
			$list.slideDown("fast");
		});
	});
});
$(function(){	
	//学生删除按钮
	$(".removeS").bind("click",function(){
		var btn = $(this);
		var tag = confirm("确认删除?");
		if(tag){
			var stid = btn.parent().parent("tr").children("td:eq(1)").text();
			var seid = btn.parent().parent("tr").children("td:eq(4)").text();
			$.get("deleteStudent",{"takesId.stid":stid,"takesId.seid":seid},function(data){
				if(data == "true"){
					btn.parent().parent("tr").remove();
					var total = $("#ptotal").text() -1;
					var str = changeURLPar(location.href,"pageTotal",total);
					location.href=str;
				}else{
					alert("删除失败");
				}
			});
			
		}		
	});
	
	
	//修改URL
	function changeURLPar(destiny, par, par_value) { 
		var pattern = par+'=([^&]*)'; 
		var replaceText = par+'='+par_value; 
		if (destiny.match(pattern)) { 
			var tmp = '/\\'+par+'=[^&]*/'; 
			tmp = destiny.replace(eval(tmp), replaceText); 
			return (tmp); 
		}else{ 
			if (destiny.match('[\?]')){ 
				return destiny+'&'+ replaceText; 
			}else 
			{ 
				return destiny+'?'+replaceText; 
			} 
		} 
		return destiny+'\n'+par+'\n'+par_value; 
	} 
});
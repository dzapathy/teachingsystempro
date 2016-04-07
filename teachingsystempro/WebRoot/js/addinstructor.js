$(function(){
	$("#addins").click(function(){
		var $last = $("#tab tbody tr:last");
		var cid = $last.children("td:eq(0)").text();
		var count = $last.index()+1;
		var $tr = "<tr>"
				+"<td>"+cid+"<input type='text' name='courseIns["+count+"].cid' hidden='true' value='"+cid+"'/></td>"
				+"<td>"+(count+1)+"<input type='text' name='courseIns["+count+"].seid' hidden='true' value='"+(count+1)+"'/></td>"
				+"<td><input type='text' name='courseIns["+count+"].iid' class='form-control check ' required/></td>"
				+"<td>"
					+"<label class='checkbox-inline'>"
						+"<input type='radio' name='courseIns["+count+"].tcharge' value='1'> 是"
					+"</label>"
					+"<label class='checkbox-inline'>"
						+"<input type='radio' name='courseIns["+count+"].tcharge' value='0' checked> 否"
					+"</label>"
				+"</td>"
				+"<td><input type='date' name='courseIns["+count+"].sestart_time' required/></td>"
				+"<td><input type='date' name='courseIns["+count+"].seend_time' required/></td>";
		$("#tab tbody").append($tr);
	});
	
	//检测是否有instructor
	$("tbody").on("blur",".check",function(){
		var t = $(this);
		t.parent("td").removeClass("has-warning");
		t.removeClass("form-control-warning");
		t.parent("td").removeClass("has-success");
		t.removeClass("form-control-success");
		if(t.val()==""){
			t.parent("td").addClass("has-warning");
			t.addClass("form-control-warning");		
		}else{
			$.get("checkins",{iid:t.val()},function(data){
				if(data =="true"){
					t.parent("td").addClass("has-success");
					t.addClass("form-control-success");
				}else{
					t.parent("td").addClass("has-warning");
					t.addClass("form-control-warning");
				}
			});
		}
	});
});
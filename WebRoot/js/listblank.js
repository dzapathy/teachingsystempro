$(function(){
    	$(".del").click(function(){
			if (window.confirm("您确认要删除吗?")) {
				var a = $(this);
				var cid1 = a.parent().parent().children("li:first").children("input").val();
				var bchapter1 = a.parent().parent().children("li:eq(1)").children("input").val();
				var bid1 = a.parent().parent().children("li:eq(2)").children("input").val();
				$.post("blank_delete", {
					cid:cid1,
					bchapter:bchapter1,
					bid:bid1
				}, function(data){
					a.parent().parent().remove();
				});
			}
		});
    });	  
    
    function toBlankAction(){
		var select = document.getElementById("chapter");
		var chapter = select.value;
		var f1 = document.forms[0];
		f1.action="blank_list?"+chapter;
	}
	
	function toChoiceAction(){
		var select = document.getElementById("chapter");
		var chapter = select.value;
		var f1 = document.forms[0];
		f1.action="choice_list?"+chapter;
	}
	
	function toSubquesAction(){
		var select = document.getElementById("chapter");
		var chapter = select.value;
		var f1 = document.forms[0];
		f1.action="subques_list?"+chapter;
	}
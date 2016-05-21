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
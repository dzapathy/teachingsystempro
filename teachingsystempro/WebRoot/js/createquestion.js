$(document).ready(function(){
    $("#con2").hide();
    $("#con3").hide();
    $("#btn1").click(function(){
      $("#con1").fadeIn();
      $("#con2").hide();
      $("#con3").hide();
    });
    $("#btn2").click(function(){
      $("#con2").fadeIn();
      $("#con1").hide();
      $("#con3").hide();
    });
    $("#btn3").click(function(){
      $("#con3").fadeIn();
      $("#con2").hide();
      $("#con1").hide();
    });
	window.onload= function(){
		var msg = document.getElementById("msg");
		if(msg.value.length>0){
			alert(msg.value);
		}
	};
    $("#tj").click(function() {      
       var num = $("#cho input").length;
       var str = String.fromCharCode(65+num+1);
       var ap = "<input type='text' name='chchoices' class='form-control' />";
       $("#cho").append(ap);
       $("#cho input:last").val(str+":");
    });    
  });
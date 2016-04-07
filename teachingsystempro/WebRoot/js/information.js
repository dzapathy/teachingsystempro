$(function(){
	//修改基本信息
	$("#form2").submit(function(ev){
		ev.preventDefault();
        var form = $(this);
        $.ajax({
			url: "changeBasicInfo",
			type : "post",
			data : form.serialize(),
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data){
				if(data == "true"){
					alert("修改成功");
				}else{
					alert("修改失败");
				}
			} 
		});
	});
	
	//修改密码
	$("#form1").submit(function(ev){
		ev.preventDefault();
		var password1 = $("#new").val();
        var passwordSure1 = $("#confirm").val();
        if(password1 != passwordSure1){
            alert('两次输入密码不一致');
            return false;
        }
        var form = $(this);
        $.ajax({
			url: "changePassInfo",
			type : "post",
			data : form.serialize(),
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data){
				if(data == "true"){
					alert("修改成功");
				}else{
					alert("原密码错误");
				}
			} 
		});
	});
	
	//修改头像
	$("button#pic").bind("click",function(){
		$.ajax({
			url: "changePicInfo",
			type : "post",
			data : {"instructor.iid":"201393123","instructor.iurl":"http://img0.imgtn.bdimg.com/it/u=2356599480,1579948032&fm=23&gp=0.jpg"},
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data){
				if(data == "true"){
					alert("修改成功");
				}else{
					alert("修改失败");
				}
			} 
		});
	});
});

$(function(){
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
	
	$("#a").click(function(){	     
	      $("#con1img").attr("src",$("#in1").val());
	});
});
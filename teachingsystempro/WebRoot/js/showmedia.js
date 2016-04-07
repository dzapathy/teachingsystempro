$(function(){
	//播放媒体
	$(".showmedia").bind("click",function(){
		var url = $(this).attr('data-url');
		var $left = $(this).offset().left+200;
		window.open("showmedia.jsp?url="+url,"media","height=250,width=450,location=no,left="+$left+",titlebar=no");
	});
});
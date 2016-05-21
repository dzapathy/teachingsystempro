$(function(){ 
	var count = 2;
	//select改变事件
	$("#chap").change(function(){
		count = $(this).val();	
	});
	
    $("#uploadify").uploadify({
    	//'formData'       : {'chapter' : count}, //不能传递动态参数
        'debug'          : false, //开启调试  
        'auto'           : false, //是否自动上传     
        'swf'            : 'uploadify/uploadify.swf',  //引入uploadify.swf    
        'uploader'       : 'uploadvideo',//请求路径    
        'queueID'        : 'fileQueue',//队列id,用来展示上传进度的         
        'queueSizeLimit' : 5,  //同时上传文件的个数    
        'fileTypeDesc'   : '视频文件',    //可选择文件类型说明  
        'fileTypeExts'   : '*.mp4;*.wmv', //控制可上传文件的扩展名    
        'multi'          : true,  //允许多文件上传    
        'buttonText'     : '选择视频',//按钮上的文字  
        'buttonImage'	 : 'uploadify/btn.png',  
        'fileSizeLimit'  : '200MB', //设置单个文件大小限制     
        'fileObjName'    : 'uploadify',  //<input type="file"/>的name    
        'method'         : 'post',    
        'removeCompleted' : true, //上传完成后自动删除队列    
        'onFallback':function(){      
            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");      
        },   
        'onUploadSuccess' : function(file, data, response){//单个文件上传成功触发    
            //data就是action中返回来的数据    
        },
        'onQueueComplete' : function(){//所有文件上传完成    
            //alert("视频上传成功!");  
        },
        'onUploadStart': function (file) {  
            $("#uploadify").uploadify("settings", "formData", { 'chapter': count });  
            //在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。  
        },
        'onSelectError': function (file, errorCode, errorMsg) {  
            switch (errorCode) {  
                case -100:  
                    alert("上传的文件数量已经超出系统限制的" + jQuery('#file_upload').uploadify('settings', 'queueSizeLimit') + "个文件！");  
                    break;  
                case -110:  
                    alert("文件 [" + file.name + "] 大小超出系统限制的" + jQuery('#file_upload').uploadify('settings', 'fileSizeLimit') + "大小！");  
                    break;  
                case -120:  
                    alert("文件 [" + file.name + "] 大小异常！");  
                    break;  
                case -130:  
                    alert("文件 [" + file.name + "] 类型不正确！");  
                    break;  
            }  
        },  
    });  
}); 
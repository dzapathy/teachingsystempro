package com.util;

import java.io.File;
import java.util.UUID;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
/**
 * 七牛云上传
 * @author lliangx
 *
 */
public class FileUploadYun {
	static String ACCESS_KEY = "EZY7Q09WkZCpwbfBAk-y-kCh4r1zgu9wCebq6XkZ";
	static String SECRET_KEY = "pQhjyOc8obV4lzzFndfsIU15paM3GFK6DbmS75Lg";	
	static String bucketname = "mediastore";//要上传的空间
	static String yuming = "http://7xsq2n.com2.z0.glb.clouddn.com/";//域名	
	static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);//密钥配置		
	static UploadManager uploadManager = new UploadManager();//创建上传对象
		
	public static String getUpToken(){
	    return auth.uploadToken(bucketname);
	}
	
	//上传视频
	public static String uploadVideo(File file , String fileName){
		String newName = getRandomFileName(fileName);
		return upload(file.getPath() ,newName );
	}
	
	//上传音频
	public static String uploadAudio(File file , String fileName){
		String newName = getRandomFileName(fileName);
		return upload(file.getPath() ,newName);
	}
	
	//上传图片
	public static String uploadPicture(File file , String fileName){
		String newName = getRandomFileName(fileName);
		return upload(file.getPath() ,newName);
	}
		
	//上传PPT
	public static String uploadPpt(File file ,String fileName){
		String newName = getRandomFileName(fileName);
		return upload(file.getPath() ,newName);
	}
	
	
	public static String dealUpload(String mediaType,File upload,String uploadFileName) throws Exception{
		String mediaUrl = "";
		if(upload != null && uploadFileName != null){
			if("photo".equals(mediaType)){
				mediaUrl = uploadPicture(upload, uploadFileName);
			}else if("music".equals(mediaType)){
				mediaUrl = uploadAudio(upload, uploadFileName);
			}else if("vidio".equals(mediaType)){
				mediaUrl = uploadVideo(upload, uploadFileName);
			}else{
				throw new Exception("上传错误");
			}
		}
		return mediaUrl;
	}
	
	//upload函数
	public static String upload(String filepath , String filename){
		String path = null;
		try {
	      Response res = uploadManager.put(filepath, filename, getUpToken());	     
	      path = yuming + res.bodyString().subSequence(res.bodyString().lastIndexOf(":")+2
	    		  , res.bodyString().lastIndexOf("\""));
		} catch (QiniuException e) {
	          Response r = e.response;
	          System.out.println(r.toString());
	          try {
	            System.out.println(r.bodyString());
	          } catch (QiniuException e1) {
	        	  
	          }
	    }
		return path;
	}
	
	//获取随机文件名
	public static String getRandomFileName(String fileName){
		UUID uuid = UUID.randomUUID();
		return uuid.toString()+fileName.substring(fileName.lastIndexOf("."));
	}
	
	public static void main(String[] args) {
		File file = new File("F:/CloudMusic/BIGBANG - Let's not fall in love .mp3");
//		File file = new File("H:/picture/3.jpg");
		String path = FileUploadYun.uploadPicture(file, "BIGBANG - Let's not fall in love .mp3");
		System.out.println(path);
	}
}

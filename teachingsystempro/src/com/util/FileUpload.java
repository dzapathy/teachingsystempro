package com.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class FileUpload {
	private static String savePath="uploadfile"; //配置文件上传根目录目录
	private static String PICTURE ="/picture"; //配置图片目录
	private static String AUDIO = "/audio"; //配置音频目录
	private static String VIDEO = "/video"; //配置视频目录
	private static String PPT = "/ppt"; //配置ppt目录
	
	//上传视频
	public static String uploadVideo(File file , String fileName){
		String newName = getRandomFileName(fileName);
		saveFile(file, VIDEO, newName);
		return savePath+VIDEO+"/"+newName;
	}
	//上传音频
	public static String uploadAudio(File file , String fileName){
		String newName = getRandomFileName(fileName);
		saveFile(file, AUDIO, newName);
		return savePath+AUDIO+"/"+newName;
	}
	//上传图片
	public static String uploadPicture(File file , String fileName){
		String newName = getRandomFileName(fileName);
		saveFile(file, PICTURE, newName);
		return savePath + PICTURE+"/"+newName;
	}
	
	//上传PPT
	public static String uploadPpt(File file ,String fileName){
		String newName = getRandomFileName(fileName);
		saveFile(file, PPT, newName);
		return savePath + PPT+"/"+newName;
	}
	
	public static String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	
	//获取随机文件名
	public static String getRandomFileName(String fileName){
		UUID uuid = UUID.randomUUID();
		return uuid.toString()+fileName.substring(fileName.lastIndexOf("."));
	}
	
	//存储文件
	public static void saveFile(File file , String path ,String fileName){
		if(file!= null){
			File dir = new File(getSavePath()+path);
			if(!dir.exists()){
				dir.mkdir();
			}
			File descFile = new File(getSavePath()+path+"/"+fileName);
			try {
				FileUtils.copyFile(file, descFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
}

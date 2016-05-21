package com.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MediaPlayAction extends ActionSupport{
	private String media = new String("");

	public void setMedia(String media) {
		this.media = media;
	}

	@Override
	public String execute() throws Exception {
		if("".equals(media)){
			ActionContext.getContext().getValueStack().set("mediaType", "4");
			return this.SUCCESS;
		}else{
			System.out.println(media);
			ActionContext.getContext().getValueStack().set("media", media);
			System.out.println(media.substring(media.lastIndexOf(".")));
			String str = media.substring(media.lastIndexOf(".")+1);
			
			if("jpg,jpeg,gif,png,bmp".contains(str) || "jpg,jpeg,gif,png,bmp".contains(str.toLowerCase())){
				ActionContext.getContext().getValueStack().set("mediaType", "1");
				return this.SUCCESS;
			}else if("cd,mp3,wma,realaudio,ape".contains(str) || "cd,mp3,wma,realaudio,ape".contains(str.toLowerCase())){
				ActionContext.getContext().getValueStack().set("mediaType", "2");
				return this.SUCCESS;
			}else //if("mkv,avi,rmvb,mp4,3gp,flv".contains(str) || "mkv,avi,rmvb,mp4,3gp,flv".contains(str.toLowerCase())){
				ActionContext.getContext().getValueStack().set("mediaType", "3");
				return this.SUCCESS;
			}
		
		}

	
}

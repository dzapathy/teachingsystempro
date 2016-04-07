package com.action;

import java.io.File;
import java.util.List;

import com.bean.Choice;
import com.bean.Course;
import com.bean.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.ChoiceService;
import com.util.FileUploadYun;

public class ChoiceAction extends ActionSupport implements ModelDriven<Choice>{

	private Choice choice = new Choice();
	private ChoiceService choiceService;
	private Course course;
	private short chchapter;
	
	//媒体信息类型
	private String mediaType;
	
	@Override
	public Choice getModel() {
		return choice;
	}
	public void setChoiceService(ChoiceService choiceService) {
		this.choiceService = choiceService;
	}
	public void setChchapter(short chchapter) {
		this.chchapter = chchapter;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	public String add(){
		try {
			//用于测试在session中手动存入course
			ActionContext.getContext().getSession().put("COURSE_ID", new Integer(1));
			Integer cid = (Integer) ActionContext.getContext().getSession().get("COURSE_ID");
			System.out.println("cid = " + cid + "chchapter = " + chchapter);
			
			String str = FileUploadYun.dealUpload(mediaType, upload, uploadFileName);
			choice.setChmediaurl(str);
			
			choiceService.addChoice(choice,cid,chchapter);
			this.addActionMessage("添加成功");
			return "add_success";
			
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("添加失败");
			return "add_input";
		}
	}
		
		
		//列出全部题目
		private Short chapter;
		public void setChapter(Short chapter) {
			this.chapter = chapter;
		}
		//用于分页的当前页码
		private Integer currentPage = 1;
		public void setCurrentPage(Integer currentPage) {
			this.currentPage = currentPage;
		}

		public String list(){
			PageBean pageBean = new PageBean();
			pageBean.setPagesize(3);
			pageBean.setCurrentPage(currentPage);
			//用于测试在session中手动存入course
			ActionContext.getContext().getSession().put("COURSE_ID", new Integer(1));
	//		chapter = 2;
			/*if(chapter != null){
				ActionContext.getContext().getSession().put("chapter", chapter);
			}else{
				chapter = (Short) ActionContext.getContext().getSession().get("chapter");
			}*/
			
			//将课程号存入session
	//		ActionContext.getContext().getSession().put("chapter", chapter);
			Integer cid = (Integer) ActionContext.getContext().getSession().get("COURSE_ID");
			System.out.println("cid = " + cid + "chapter = " + chapter);
			System.out.println("currentPage" + currentPage);
			
			List list = choiceService.findAllChoice(cid,chapter,pageBean); 
			ActionContext.getContext().getValueStack().set("list", list);
			return "findAll_success";
		}
		
		//删除题目
		private Integer cid;
//		private short chchapter;     
		private Integer chid;
		
		public void setCid(Integer cid) {
			this.cid = cid;
		}

		public void setChid(Integer chid) {
			this.chid = chid;
		}
		
		public String delete(){
			System.out.println("cid = "+ cid + "chchapter = "+ chchapter + "chid = " + chid);
	//		ActionContext.getContext().getSession().put("chapter", );
			choiceService.deleteById(cid,chchapter,chid);
	//		return "delete_success";
			return this.NONE;
		}

		//按id查找
		public String findById(){
			System.out.println("cid = "+ cid + "chchapter = "+ chchapter + "chid = " + chid);
			Object[] choice2 = choiceService.findById(cid,chchapter,chid);
			ActionContext.getContext().getValueStack().set("choice", choice2);
			return "findById_success";
		}
		
		//修改
		public String update(){
			try {
				System.out.println("cid = " + cid + "chchapter = " + chchapter
						+ "chid = " + chid);
				System.out.println(choice);
				String str = FileUploadYun.dealUpload( mediaType, upload,
						uploadFileName);
				
				if(str == null || "".equals(str)){
					System.out.println("没上传媒体，用原来的了");
					Object[] choice2 = choiceService.findById(cid, chchapter, cid);
					choice.setChmediaurl((String) choice2[8]);
				}else{
					System.out.println("上传媒体了，替换原来的");
					choice.setChmediaurl(str);
				}
				
				choiceService.updateById(cid, chchapter, chid, choice);
				this.addActionMessage("修改成功");
				return "update_success";
			} catch (Exception e) {
				e.printStackTrace();
				this.addActionMessage("修改失败");
				return "update_input";
			}
		}
		
		// 上传操作
			private File upload;
			private String uploadContentType;
			private String uploadFileName;

			public File getUpload() {
				return upload;
			}

			public void setUpload(File upload) {
				this.upload = upload;
			}

			public String getUploadContentType() {
				return uploadContentType;
			}

			public void setUploadContentType(String uploadContentType) {
				this.uploadContentType = uploadContentType;
			}

			public String getUploadFileName() {
				return uploadFileName;
			}

			public void setUploadFileName(String uploadFileName) {
				this.uploadFileName = uploadFileName;
			}

}

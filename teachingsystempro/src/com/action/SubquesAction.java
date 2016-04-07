package com.action;

import java.io.File;
import java.util.List;

import com.bean.Course;
import com.bean.PageBean;
import com.bean.Subques;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.SubquesService;
import com.util.FileUpload;
import com.util.FileUploadYun;

public class SubquesAction extends ActionSupport implements ModelDriven<Subques>{
	private Subques subques = new Subques();
	private Course course;
	private SubquesService subquesService;
	private short suchapter;
	
	//媒体信息类型
	private String mediaType;
	@Override
	public Subques getModel() {
		return subques;
	}
	public void setSubques(Subques subques) {
		this.subques = subques;
	}
	public void setSubquesService(SubquesService subquesService) {
		this.subquesService = subquesService;
	}
	public void setSuchapter(short suchapter) {
		this.suchapter = suchapter;
	}
	
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	

		public String add(){
			try {
				//用于测试在session中手动存入course
				ActionContext.getContext().getSession().put("COURSE_ID", new Integer(1));
				Integer cid = (Integer) ActionContext.getContext().getSession().get("COURSE_ID");
				System.out.println("cid = " + cid + "suchapter = " + suchapter);
				
				String str = FileUploadYun.dealUpload( mediaType, upload, uploadFileName);
				subques.setSumediaurl(str);
				
				subquesService.addSubques(subques,cid,suchapter);
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
		//	chapter = 2;
			Integer cid = (Integer) ActionContext.getContext().getSession().get("COURSE_ID");
			System.out.println("cid = " + cid + "chapter = " + chapter);
			System.out.println("currentPage" + currentPage);
			
			List list = subquesService.findAllChoice(cid,chapter,pageBean); 
			ActionContext.getContext().getValueStack().set("list", list);
			return "findAll_success";
		}
		
		
		//删除题目
		private Integer cid;
//		private short suchapter;     
		private Integer suid;
		
		public void setCid(Integer cid) {
			this.cid = cid;
		}

		public void setSuid(Integer suid) {
			this.suid = suid;
		}
		
		public String delete(){
			System.out.println("cid = "+ cid + "suchapter = "+ suchapter + "suid = " + suid);
			subquesService.deleteById(cid,suchapter,suid);
			return this.NONE;
		}
		
		//按id查找
		public String findById(){
			System.out.println("cid = "+ cid + "suchapter = "+ suchapter + "suid = " + suid);
			Object[] subques2 = subquesService.findById(cid,suchapter,suid);
			ActionContext.getContext().getValueStack().set("subques", subques2);
			return "findById_success";
		}
		
		//修改
		public String update(){
			try {
				System.out.println("cid = " + cid + "suchapter = " + suchapter
						+ "suid = " + suid);
				System.out.println(subques);
				String str = FileUploadYun.dealUpload(mediaType, upload,uploadFileName);
				
				if(str == null || "".equals(str)){
					System.out.println("没上传媒体，用原来的了");
					Object[] choice2 = subquesService.findById(cid, suchapter, suid);
					subques.setSumediaurl((String) choice2[7]);
				}else{
					System.out.println("上传媒体了，替换原来的");
					subques.setSumediaurl(str);
				}
				
				subquesService.updateById(cid, suchapter, suid, subques);
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

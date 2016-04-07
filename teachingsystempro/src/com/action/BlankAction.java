package com.action;

import java.io.File;
import java.util.List;

import com.bean.Blank;
import com.bean.Course;
import com.bean.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.BlankService;
import com.util.FileUploadYun;

public class BlankAction extends ActionSupport implements ModelDriven<Blank>{
	
	private Blank blank = new Blank();
	private Course course;
	private BlankService blankService;
	private short bchapter;

	//媒体信息类型
	private String mediaType;
	
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	


	public void setBchapter(short bchapter) {
		this.bchapter = bchapter;
	}

	public void setBlankService(BlankService blankService) {
		this.blankService = blankService;
	}

	@Override
	public Blank getModel() {
		return blank;
	}

	
	//添加题目
	public String add(){
		try {
			//用于测试在session中手动存入course
			ActionContext.getContext().getSession().put("COURSE_ID", new Integer(1));
			Integer cid = (Integer) ActionContext.getContext().getSession().get("COURSE_ID");
			System.out.println("cid = " + cid + "bchapter = " + bchapter);
			System.out.println(blank.getBanswer());
			System.out.println(blank.getBexplain());
			System.out.println(blank.getBquestion());
			System.out.println(blank.getBscore());
			String str = FileUploadYun.dealUpload( mediaType, upload, uploadFileName);
			blank.setBmediaurl(str);
			
			blankService.addBlank(blank,cid,bchapter);
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
	//	chapter = 1;
		Integer cid = (Integer) ActionContext.getContext().getSession().get("COURSE_ID");
		System.out.println("cid = " + cid + "chapter = " + chapter);
		System.out.println("currentPage" + currentPage);
		
		List list = blankService.findAllBlank(cid,chapter,pageBean); 
		ActionContext.getContext().getValueStack().set("list", list);
		return "findAll_success";
	}
	
	//删除题目
	private Integer cid;
//	private short bchapter;     
	private Integer bid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}
	
	public String delete(){
		System.out.println("cid = "+ cid + "bchapter = "+ bchapter + "bid = " + bid);
		blankService.deleteById(cid,bchapter,bid);
		return this.NONE;
	}
	
	//按id查找
	public String findById(){
		System.out.println("cid = "+ cid + "bchapter = "+ bchapter + "bid = " + bid);
		Object[] blank2 = blankService.findById(cid,bchapter,bid);
		ActionContext.getContext().getValueStack().set("blank", blank2);
		return "findById_success";
	}
	
	//修改
	public String update(){
		try {
			System.out.println("cid = " + cid + "bchapter = " + bchapter
					+ "bid = " + bid);
			System.out.println(blank);
			String str = FileUploadYun.dealUpload(mediaType, upload,uploadFileName);
			if(str == null || "".equals(str)){
				System.out.println("没上传媒体，用原来的了");
				Object[] blank2 = blankService.findById(cid, bchapter, bid);
				blank.setBmediaurl((String) blank2[7]);
			}else{
				System.out.println("上传媒体了，替换原来的");
				blank.setBmediaurl(str);
			}
			
			blankService.updateById(cid, bchapter, bid, blank);
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
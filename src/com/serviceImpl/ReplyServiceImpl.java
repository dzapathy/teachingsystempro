package com.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.Posts;
import com.bean.Reply;
import com.dao.PostsDAO;
import com.dao.ReplyDAO;
import com.service.ReplyService;

public class ReplyServiceImpl implements ReplyService {
	private ReplyDAO replyDAO;
	private PostsDAO postsDAO;
	
	@Override
	public List reply(String iid, Integer pid, String content) {
		Reply reply = new Reply();
		Posts posts = postsDAO.findById(pid);
		reply.setPosts(posts);
		reply.setRauthorid(iid);
		reply.setRcontent(content);
		reply.setRcreatetime(new Date());
		replyDAO.save(reply);
		return null;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List getReplys(Integer pid) { 
		//list[0]存储post,list[1]存储instructor对应,list[2]存储student对应
		List list = new ArrayList();
		list.add(postsDAO.findSender(pid)); //list[0]
		list.add(replyDAO.findInstructorReply(pid));
		list.add(replyDAO.findStudentReply(pid));
		return list;
	}

	public ReplyDAO getReplyDAO() {
		return replyDAO;
	}

	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}

	public PostsDAO getPostsDAO() {
		return postsDAO;
	}

	public void setPostsDAO(PostsDAO postsDAO) {
		this.postsDAO = postsDAO;
	}

}

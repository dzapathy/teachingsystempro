package com.serviceImpl;

import java.util.List;

import com.bean.Posts;
import com.dao.PostsDAO;
import com.service.PostService;

public class PostServiceImpl implements PostService {
	private PostsDAO postsDAO;
	//获取posts总数
	@Override
	public Long getPostsCount(Integer cid) {
		return postsDAO.getPostsCount(cid);
	}
	//获取每页post
	@Override
	public List getPosts(Integer cid, Integer page, Integer pageSize) {
		return postsDAO.getPostsList(cid, page, pageSize);
	}
	
	//获取无人回复的帖子
	@Override
	public List getNoreplyPosts(Integer cid, Integer page, Integer pageSize) {
		return postsDAO.getNoReplyList(cid, page, pageSize);
	}

	//获取无人回复post总数
	@Override
	public Long getNoReplyCount(Integer cid) {
		return postsDAO.getNoReplyPosts(cid);
	}
	
	//创建话题
	@Override
	public void createPost(Posts posts) {
	
	}

	public PostsDAO getPostsDAO() {
		return postsDAO;
	}

	public void setPostsDAO(PostsDAO postsDAO) {
		this.postsDAO = postsDAO;
	}
}

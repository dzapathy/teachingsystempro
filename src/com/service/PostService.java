package com.service;

import java.util.List;

import com.bean.Posts;

public interface PostService {
	//获取话题
	public List getPosts(Integer cid ,Integer page ,Integer pageSize);
	//获取话题总数
	public Long getPostsCount(Integer cid);	
	//创建话题
	public void createPost(Posts posts);
	//获取无人回答话题
	public List getNoreplyPosts(Integer cid ,Integer page ,Integer pageSize);
	//获取无人回复总数
	public Long getNoReplyCount(Integer cid);
	
}

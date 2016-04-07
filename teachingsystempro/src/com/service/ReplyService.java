package com.service;

import java.util.List;

public interface ReplyService {
	//回复
	public List reply(String iid , Integer pid , String content);
	//获取回复信息
	public List getReplys(Integer pid);//参数:帖子ID
}

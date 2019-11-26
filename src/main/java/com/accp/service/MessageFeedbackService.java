package com.accp.service;

import com.accp.pojo.MessageFeedback;
import com.github.pagehelper.PageInfo;

public interface MessageFeedbackService {
	PageInfo<MessageFeedback> getMessageFeedbackByType(Integer type,String title,Integer pageNum,Integer pageSize);

	boolean deleteUserMessageFeed(Integer id);

	boolean addMessageFeedback(MessageFeedback messageFeedback);
}

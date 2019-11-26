package com.accp.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.MessageFeedbackMapper;
import com.accp.pojo.MessageFeedback;
import com.accp.service.MessageFeedbackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class MessageFeedbackServiceImpl implements MessageFeedbackService {
	@Autowired
	private MessageFeedbackMapper messageFeedbackMapper;

	@Override
	public PageInfo<MessageFeedback> getMessageFeedbackByType(Integer type,String title,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(messageFeedbackMapper.findMessageFeedbackByType(type,title));
	}

	@Override
	public boolean deleteUserMessageFeed(Integer id) {
		return messageFeedbackMapper.delete(id) == 1;
	}

	@Override
	public boolean addMessageFeedback(MessageFeedback messageFeedback) {
		messageFeedback.setDateTime(new Date());
		return messageFeedbackMapper.add(messageFeedback) == 1;
	}

}

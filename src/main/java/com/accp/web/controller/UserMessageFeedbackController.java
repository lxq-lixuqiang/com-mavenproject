package com.accp.web.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accp.pojo.MessageFeedback;
import com.accp.service.MessageFeedbackService;
import com.accp.web.formbean.MessageFeedbackFormBean;
import com.accp.web.formbean.MessageFeedbackFormBean.MessageFeedbackBeanGroupSequence;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class UserMessageFeedbackController {

	@Autowired
	private MessageFeedbackService messageFeedbackService;
	@Autowired
	private MessageSource messageSource;
	private Integer size=6;
	private Integer num=1;
	
	@GetMapping("userMessageFeedback")
	public String userMessageFeedback(Integer pageSize,Integer pageNum,Integer type,String title,Model model) {
		pageSize=WebTools.memory(pageSize,"UserMessageFeedback_pageSize",size);
		pageNum=WebTools.memory(pageNum,"UserMessageFeedback_pageNum",num);
		type=WebTools.memory(type,"UserMessageFeedback_type",null);
		title=WebTools.memory(title,"UserMessageFeedback_title",null);
		
		PageInfo<MessageFeedback> messageFeedbacks=messageFeedbackService.getMessageFeedbackByType(type, title, pageNum, pageSize);
		model.addAttribute("pageInfo", messageFeedbacks);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(messageFeedbacks.getPageNum(),messageFeedbacks.getPages());
		model.addAttribute("UserMessageFeedback_pagingButton", pagingButton);
		
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession("UserMessageFeedback_pageSize", pageSize);
		WebTools.setSession("UserMessageFeedback_pageNum", pageNum);
		WebTools.setSession("UserMessageFeedback_type", type);
		WebTools.setSession("UserMessageFeedback_title", title);
		return "userMessageFeedback";
	}
	
	@GetMapping("deleteUserMessageFeed")
	public String deleteUserMessageFeed(@RequestParam Integer id) {
		boolean isOk=messageFeedbackService.deleteUserMessageFeed(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
					messageSource.getMessage("UserMessageFeedbackController.deleteUserMessageFeed.Success", null, "删除成功！", locale) :
						messageSource.getMessage("UserMessageFeedbackController.deleteUserMessageFeed.Fail", null, "删除失败，请稍后再试！", locale));
		return "redirect:userMessageFeedback";
	}
	
	@GetMapping("questionFeedback")
	public String questionFeedback(MessageFeedbackFormBean messageFeedbackBean) {
		WebTools.sessionToRequest(Common.MSG);
		return "questionFeedback";
	}
	
	@PostMapping("questionFeedback")
	public String questionFeedback(@Validated(MessageFeedbackBeanGroupSequence.class)MessageFeedbackFormBean messageFeedbackBean,Errors errors,Model model) {
		if(errors.hasErrors()) {
			return "questionFeedback";
		}
		MessageFeedback messageFeedback=new MessageFeedback();
		messageFeedback.setSender(messageFeedbackBean.getSender());
		messageFeedback.setTitle(messageFeedbackBean.getTitle());
		messageFeedback.setType(messageFeedbackBean.getType());
		messageFeedback.setContent(messageFeedbackBean.getContent());
		
		boolean isOk=messageFeedbackService.addMessageFeedback(messageFeedback);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG,isOk?
							messageSource.getMessage("UserMessageFeedbackController.questionFeedback.Success",null, "已提交成功，谢谢对我们的支持！", locale) :
								messageSource.getMessage("UserMessageFeedbackController.questionFeedback.Fail",null, "提交失败，请稍后再试！", locale));
		return "redirect:questionFeedback";
	}
}

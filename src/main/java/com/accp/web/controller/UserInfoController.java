package com.accp.web.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accp.pojo.User;
import com.accp.service.UserService;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

/**
 * 管理用户控制器
 * @author Y2项目:李旭强
 *
 */
@Controller
public class UserInfoController {
	@Autowired
	private UserService userService;
	@Autowired
	private MessageSource messageSource;
	private Integer size=8;
	private Integer num=1;

	@GetMapping("userInfo")
	public String userMessages(String username,Integer pageSize,Integer pageNum,Model model) {
		username=WebTools.memory(username, "UserInfo_username", username);
		pageSize=WebTools.memory(pageSize, "UserInfo_pageSize", size);
		pageNum=WebTools.memory(pageNum, "UserInfo_pageNum", num);
		PageInfo<User> users=userService.getAllUser(username, pageSize, pageNum);
		model.addAttribute("pageInfo", users);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(users.getPageNum(),users.getPages());
		model.addAttribute("UserInfo_pagingButton", pagingButton);
		
		WebTools.setSession(Common.DATA_MANAGEMENT,"userInfo");
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession("UserInfo_username", username);
		WebTools.setSession("UserInfo_pageSize", pageSize);
		WebTools.setSession("UserInfo_pageNum", pageNum);
		WebTools.setSession("UserInfo_pagingButton", pagingButton);
		return "userInfo";
	}
	
	@GetMapping("deleteUserInfo")
	public String deleteUser(@RequestParam Integer id) {
		boolean isOk=userService.deleteById(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?messageSource.getMessage("UserInfoController.deleteUser.Success", null, "删除成功！", locale):messageSource.getMessage("UserInfoController.deleteUser.Fail", null, "删除失败，稍后再试！", locale));
		return "redirect:userInfo";
	}
	
}

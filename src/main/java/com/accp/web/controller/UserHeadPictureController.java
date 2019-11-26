package com.accp.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.accp.pojo.User;
import com.accp.pojo.UserHeadPicture;
import com.accp.service.UserHeadPictureService;
import com.accp.service.UserService;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;


@Controller
public class UserHeadPictureController {
	@Autowired
	private UserHeadPictureService userHeadPictureService;
	@Autowired
	private UserService userService;
	@Autowired
	private MessageSource messageSource;
	private Integer size=6;
	private Integer num=1;

	@GetMapping("userHeadPicture")
	public String commonUserHeadPicture(Integer pageSize,Integer pageNum,Model model) {
		pageSize=WebTools.memory(pageSize, "userHeadPicture_pageSize", size);
		pageNum=WebTools.memory(pageNum, "userHeadPicture_pageNum", num);
		PageInfo<UserHeadPicture> userHeadPictures=userHeadPictureService.getAll(pageSize, pageNum);
		model.addAttribute("pageInfo", userHeadPictures);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(userHeadPictures.getPageNum(),userHeadPictures.getPages());
		model.addAttribute("userHeadPicture_pagingButton", pagingButton);
		
		WebTools.setSession(Common.DATA_MANAGEMENT, "userHeadPicture");
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession("userHeadPicture_pageSize",pageSize);
		WebTools.setSession("userHeadPicture_pageNum",pageNum);
		WebTools.setSession("userHeadPicture_pagingButton", pagingButton);
		return "userHeadPicture";
	}
	
	@GetMapping("addUserHeadPicture")
	public String addUserHeadPicture() {
		UserHeadPicture userHeadPicture=new UserHeadPicture();
		userHeadPicture.setHeadPicture("");
		userHeadPictureService.addUserHeadPicture(userHeadPicture);
		PageInfo<UserHeadPicture> userHeadPictures=userHeadPictureService.getAll(size, num);
		WebTools.setSession("userHeadPicture_pageNum",userHeadPictures.getPages());
		return "redirect:userHeadPicture";
	}
	
	@GetMapping("deleteUserHeadPicture")
	public String deleteUserHeadPicture(@RequestParam Integer id) {
		int isExists=userService.isExistsUserHeadPicture(id);
		Locale locale=LocaleContextHolder.getLocale();
		if(isExists >0) {
			WebTools.setSession(Common.MSG, messageSource.getMessage("UserHeadPictureController.deleteUserHeadPicture.isExists", null, "无法删除，"+isExists+"位用户正在使用此图片！", locale));
		}else {
			boolean isOk=userHeadPictureService.deleteUserHeadPicture(id);
			WebTools.setSession(Common.MSG,isOk?messageSource.getMessage("UserHeadPictureController.deleteUserHeadPicture.Success", null, "删除成功！", locale):messageSource.getMessage("UserHeadPictureController.deleteUserHeadPicture.Fail", null, "删除失败，请稍后再试！", locale));
		}
		return "redirect:userHeadPicture";
	}
	
	@PostMapping("fileUploadUserHeadPicture")
	@ResponseBody
	public String fileUploadUserHeadPicture(MultipartFile file,Integer id, HttpServletRequest request) throws IllegalStateException, IOException {
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/headPicture");
		String absPath = Common.UPLOAD_FILE+"img/headPicture";
		//随机生成文件名加原文件后缀
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		//上传文件
		file.transferTo(new File(absPath,pictureName+suffix));
		boolean isOk=userHeadPictureService.updateUserHeadPicture(id,pictureName+suffix);
		return "{\"isOk\":"+isOk+"}";
	}
	
	@GetMapping("updateUserHeadPictureId")
	public String updateUserHeadPicture(Integer pageSize,Integer pageNum,Model model) {
		pageSize=WebTools.memory(pageSize, "userHeadPictureId_pageSize", size);
		pageNum=WebTools.memory(pageNum, "userHeadPictureId_pageNum", num);
		PageInfo<UserHeadPicture> userHeadPictures=userHeadPictureService.getAll(pageSize, pageNum);
		model.addAttribute("pageInfo", userHeadPictures);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(userHeadPictures.getPageNum(),userHeadPictures.getPages());
		model.addAttribute("userHeadPictureId_pagingButton", pagingButton);
		
		WebTools.setSession("userHeadPictureId_pageSize",pageSize);
		WebTools.setSession("userHeadPictureId_pageNum",pageNum);
		WebTools.setSession("userHeadPictureId_pagingButton", pagingButton);
		return "updateUserHeadPictureId";
	}
	
	@GetMapping("updatePictureId")
	@ResponseBody
	public String updatePictureId(Integer userHeadPictureId,Integer userId,String headPicture) {
		boolean isOk=userService.updatePictureId(userHeadPictureId,userId);
		if(isOk) {
			User user=WebTools.getSession(Common.LOGIN_USER);
			UserHeadPicture userHeadPicture=new UserHeadPicture();
			userHeadPicture.setId(userHeadPictureId);
			userHeadPicture.setHeadPicture(headPicture);
			user.setUserHeadPicture(userHeadPicture);
			WebTools.setSession(Common.LOGIN_USER, user);
		}
		return "{\"isOk\":"+isOk+"}";
	}
}

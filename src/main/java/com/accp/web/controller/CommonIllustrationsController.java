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
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.accp.pojo.Illustrations;
import com.accp.service.IllustrationsService;
import com.accp.web.formbean.IllustrationsFormBean;
import com.accp.web.formbean.IllustrationsFormBean.updateIllustrationsBeanGroupSequence;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class CommonIllustrationsController {
	@Autowired
	private IllustrationsService illustrationsService;
	@Autowired
	private MessageSource messageSource;
	private Integer size=6;
	private Integer num=1;

	@GetMapping("commonIllustrations")
	public String commonIllustrations(Integer pageSize,Integer pageNum,Model model) {
		pageSize=WebTools.memory(pageSize, "CommonIllustrations_pageSize", size);
		pageNum=WebTools.memory(pageNum, "CommonIllustrations_pageNum", num);
		PageInfo<Illustrations> Illustrations=illustrationsService.getAllIllustrationsByPage(pageSize,pageNum);
		model.addAttribute("pageInfo", Illustrations);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(Illustrations.getPageNum(),Illustrations.getPages());
		model.addAttribute("CommonIllustrations_pagingButton", pagingButton);
		
		WebTools.setSession(Common.DATA_MANAGEMENT,"commonIllustrations");
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession("CommonIllustrations_pageSize", pageSize);
		WebTools.setSession("CommonIllustrations_pageNum", pageNum);
		WebTools.setSession("CommonIllustrations_pagingButton", pagingButton);
		return "commonIllustrations";
	}
	
	@GetMapping("commonIllustrationsUpdate")
	public String commonIllustrationsUpdate(@RequestParam Integer id,Model model,IllustrationsFormBean illustrationsBean) {
		Illustrations illustration=illustrationsService.getIllustrationsById(id);
		model.addAttribute("illustrationsFormBean",illustration);
		WebTools.setSession("picture",illustration.getPicture());
		return "commonIllustrationsUpdate";
	}
	
	@PostMapping("commonIllustrationsUpdate")
	public String commonIllustrationsUpdate(@Validated(updateIllustrationsBeanGroupSequence.class)IllustrationsFormBean illustrationsBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "commonIllustrationsUpdate";
		}
		if(file != null && file.getSize()>0) {
			//存储位置
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/illustrations");
			String absPath = Common.UPLOAD_FILE+"img/illustrations";
			//随机生成文件名加原文件后缀
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			//上传文件
			file.transferTo(new File(absPath,pictureName+suffix));
			illustrationsBean.setPicture(pictureName+suffix);
		}
		Illustrations illustrations=new Illustrations();
		illustrations.setId(illustrationsBean.getId());
		illustrations.setAuthor(illustrationsBean.getAuthor());
		illustrations.setTitle(illustrationsBean.getTitle());
		illustrations.setTheme(illustrationsBean.getTheme());
		illustrations.setContent(illustrationsBean.getContent());
		illustrations.setPicture(illustrationsBean.getPicture());
		
		boolean isOk=illustrationsService.updateIllustrations(illustrations);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("commonIllustrationsController.commonIllustrationsUpdate.Fail", null, "修改失败，请稍后再试！", locale));
			return "commonIllustrationsUpdate";
		}
		WebTools.removeSession("picture");
		WebTools.setSession(Common.MSG,messageSource.getMessage("commonIllustrationsController.commonIllustrationsUpdate.Success", null,"修改成功！", locale));
		return "redirect:commonIllustrations";
	}
}

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

import com.accp.pojo.HomeNavigationBar;
import com.accp.service.HomeNavigationBarService;
import com.accp.web.formbean.HomeNavigationBarFormBean;
import com.accp.web.formbean.HomeNavigationBarFormBean.HomeNavigationBarFormBeanGroupSequence;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;


@Controller
public class HomeNavigationBarController {
	@Autowired
	private HomeNavigationBarService homeNavigationBarService;
	private Integer size=5;
	private Integer num=1;
	@Autowired
	private MessageSource messageSource;

	@GetMapping("homeNavigationBar")
	public String homeNavigationBar(Integer pageSize,Integer pageNum,String title,Model model) {
		pageSize=WebTools.memory(pageSize, "homeNavigationBar_pageSize", size);
		pageNum=WebTools.memory(pageNum, "homeNavigationBar_pageNum", num);
		title=WebTools.memory(title, "homeNavigationBar_title", null);
		PageInfo<HomeNavigationBar> homeNavigationBar=homeNavigationBarService.getHomeNavigationBarByTitle(pageNum,pageSize,title);
		model.addAttribute("pageInfo", homeNavigationBar);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(homeNavigationBar.getPageNum(),homeNavigationBar.getPages());
		model.addAttribute("homeNavigationBar_pagingButton", pagingButton);
		
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession(Common.DATA_MANAGEMENT,"homeNavigationBar");
		WebTools.setSession("homeNavigationBar_pageSize", pageSize);
		WebTools.setSession("homeNavigationBar_pageNum", pageNum);
		WebTools.setSession("homeNavigationBar_title", title);
		return "homeNavigationBar";
	}
	
	@GetMapping("homeNavigationBarVisit")
	public String homeNavigationBarVisit(@RequestParam Integer id,Model model) {
		model.addAttribute("homeNavigationBar", homeNavigationBarService.getHomeNavigationBarById(id));
		return "homeNavigationBarVisit";
	}
	
	@GetMapping("homeNavigationBarDelete")
	public String homeNavigationBarDelete(@RequestParam Integer id) {
		boolean isOk=homeNavigationBarService.homeNavigationBarDelete(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
									messageSource.getMessage("HomeNavigationBarController.homeNavigationBarDelete.Success", null, "删除成功！", locale) :
									messageSource.getMessage("HomeNavigationBarController.homeNavigationBarDelete.Fail", null, "删除失败，请稍后再试！", locale));	
		return "redirect:homeNavigationBar";
	}
	
	@GetMapping("homeNavigationBarUpdate")
	public String homeNavigationBarUpdate(@RequestParam Integer id,HomeNavigationBarFormBean homeNavigationBarFormBean,Model model) {
		HomeNavigationBar homeNavigationBar=homeNavigationBarService.getHomeNavigationBarById(id);
		model.addAttribute("homeNavigationBarFormBean", homeNavigationBar);
		WebTools.setSession("picture", homeNavigationBar.getPicture());
		return "homeNavigationBarUpdate";
	}
	
	@PostMapping("homeNavigationBarUpdate")
	public String homeNavigationBarUpdate(@Validated(HomeNavigationBarFormBeanGroupSequence.class)HomeNavigationBarFormBean homeNavigationBarFormBean,Errors errors,MultipartFile file,MultipartFile file2,HttpServletRequest request,Model model) throws IllegalStateException, IOException{
		if(errors.hasErrors()) {
			return "homeNavigationBarUpdate";
		}
		//导航栏图片
		if(file.getSize() > 0) {
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/homeNavigationBarImg");
			String absPath = Common.UPLOAD_FILE+"img/homeNavigationBarImg";
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			file.transferTo(new File(absPath,pictureName+suffix));
			homeNavigationBarFormBean.setPicture(pictureName+suffix);
		}
		//导航栏视频
		if(file2.getSize() > 0) {
			//String absPath2 = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"video/homeNavigationBar");
			String absPath2 = Common.UPLOAD_FILE+"video/homeNavigationBar";
			String suffix2=file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf("."));
			String pictureName2=UUID.randomUUID().toString();
			file2.transferTo(new File(absPath2,pictureName2+suffix2));
			homeNavigationBarFormBean.setHomeNavigationBar(pictureName2+suffix2);
		}
		
		HomeNavigationBar homeNavigationBar=new HomeNavigationBar();
		homeNavigationBar.setId(homeNavigationBarFormBean.getId());
		homeNavigationBar.setBackgroundColor(homeNavigationBarFormBean.getBackgroundColor());
		homeNavigationBar.setHomeNavigationBar(homeNavigationBarFormBean.getHomeNavigationBar());
		homeNavigationBar.setHomeNavigationBarPath(homeNavigationBarFormBean.getHomeNavigationBarPath());
		homeNavigationBar.setPicture(homeNavigationBarFormBean.getPicture());
		homeNavigationBar.setTitle(homeNavigationBarFormBean.getTitle());

		boolean isOk=homeNavigationBarService.homeNavigationBarUpdate(homeNavigationBar);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("HomeNavigationBarController.homeNavigationBarUpdate.Fail", null, "修改失败，请稍后再试！", locale));
			return "homeNavigationBarUpdate";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("HomeNavigationBarController.homeNavigationBarUpdate.Success", null, "修改成功！", locale));
		WebTools.removeSession("picture");
		return "redirect:homeNavigationBar";
	}
	
	@GetMapping("homeNavigationBarAdd")
	public String homeNavigationBarAdd(HomeNavigationBarFormBean homeNavigationBarFormBean) {
		return "homeNavigationBarAdd";
	}
	
	@PostMapping("homeNavigationBarAdd")
	public String homeNavigationBarAdd(@Validated(HomeNavigationBarFormBeanGroupSequence.class)HomeNavigationBarFormBean homeNavigationBarFormBean,Errors errors,MultipartFile file,MultipartFile file2,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "homeNavigationBarAdd";
		}
		//导航栏图片
		if(file.getSize() == 0) {
			errors.rejectValue("picture","HomeNavigationBarController.picture.NotBlank","请选择图片！");
			return "homeNavigationBarAdd";
		}
		//导航栏视频
		if(file2.getSize() > 0) {
			//String absPath2 = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"video/homeNavigationBar");
			String absPath2 = Common.UPLOAD_FILE+"video/homeNavigationBar";
			String suffix2=file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf("."));
			String pictureName2=UUID.randomUUID().toString();
			file2.transferTo(new File(absPath2,pictureName2+suffix2));
			homeNavigationBarFormBean.setHomeNavigationBar(pictureName2+suffix2);
		}
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/homeNavigationBarImg");
		String absPath = Common.UPLOAD_FILE+"img/homeNavigationBarImg";
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		file.transferTo(new File(absPath,pictureName+suffix));
		homeNavigationBarFormBean.setPicture(pictureName+suffix);
		
		HomeNavigationBar homeNavigationBar=new HomeNavigationBar();
		homeNavigationBar.setBackgroundColor(homeNavigationBarFormBean.getBackgroundColor());
		homeNavigationBar.setHomeNavigationBar(homeNavigationBarFormBean.getHomeNavigationBar());
		homeNavigationBar.setHomeNavigationBarPath(homeNavigationBarFormBean.getHomeNavigationBarPath());
		homeNavigationBar.setPicture(homeNavigationBarFormBean.getPicture());
		homeNavigationBar.setTitle(homeNavigationBarFormBean.getTitle());
				

		boolean isOk=homeNavigationBarService.homeNavigationBarAdd(homeNavigationBar);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("HomeNavigationBarController.homeNavigationBarAdd.Fail", null, "保存失败，请稍后再试！", locale));
			return "homeNavigationBarAdd";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("HomeNavigationBarController.homeNavigationBarAdd.Success", null, "保存成功！", locale));
		return "redirect:homeNavigationBar";
	}
}

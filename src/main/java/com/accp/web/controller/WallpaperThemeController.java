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

import com.accp.pojo.WallpaperTheme;
import com.accp.pojo.WallpaperType;
import com.accp.service.WallpaperThemeService;
import com.accp.service.WallpaperTypeService;
import com.accp.web.formbean.WallpaperThemeFormBean;
import com.accp.web.formbean.WallpaperThemeFormBean.WallpaperThemeBeanGroupSequence;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;


@Controller
public class WallpaperThemeController {
	@Autowired
	private WallpaperThemeService wallpaperThemeService;
	private Integer size=8;
	private Integer num=1;
	@Autowired
	private WallpaperTypeService wallpaperTypeService;
	@Autowired
	private MessageSource messageSource;

	@GetMapping("wallpaperTheme")
	public String wallpaperTheme(Integer pageSize,Integer pageNum,String theme,Integer typeId,Model model) {
		pageSize=WebTools.memory(pageSize, "wallpaperTheme_pageSize", size);
		pageNum=WebTools.memory(pageNum, "wallpaperTheme_pageNum", num);
		theme=WebTools.memory(theme, "wallpaperTheme_theme", null);
		typeId=WebTools.memory(typeId, "wallpaperTheme_typeId", null);
		PageInfo<WallpaperTheme> wallpaperThemes=wallpaperThemeService.getAllWallpaperTheme(pageSize,pageNum,theme,typeId,null);
		model.addAttribute("pageInfo", wallpaperThemes);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(wallpaperThemes.getPageNum(),wallpaperThemes.getPages());
		model.addAttribute("wallpaperTheme_pagingButton", pagingButton);
		//类型
		model.addAttribute("types", wallpaperTypeService.getAllWallpaperType());
		
		//防止重复第一次进入不是第一页
		WebTools.removeSession("wallpaperSelect_pageNum");
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession(Common.DATA_MANAGEMENT,"wallpaperTheme");
		WebTools.setSession("wallpaperTheme_pageSize", pageSize);
		WebTools.setSession("wallpaperTheme_pageNum", pageNum);
		WebTools.setSession("wallpaperTheme_theme", theme);
		WebTools.setSession("wallpaperTheme_typeId", typeId);
		return "wallpaperTheme";
	}
	
	@GetMapping("wallpaperThemeUpdate")
	public String wallpaperThemeUpdate(@RequestParam Integer id,WallpaperThemeFormBean wallpaperThemeBean,Model model) {
		WallpaperTheme wallpaperTheme=wallpaperThemeService.getWallpaperThemeById(id);
		model.addAttribute("wallpaperThemeFormBean",wallpaperTheme);
		
		List<WallpaperType> wallpaperTypes= wallpaperTypeService.getAllWallpaperType();
		WallpaperType wallpaperType=new WallpaperType();
		wallpaperType.setId(null);
		wallpaperType.setName("请选择");
		wallpaperTypes.add(0, wallpaperType);
		WebTools.setSession("wallpaperType", wallpaperTypes);
		
		WebTools.setSession("wallpaper", wallpaperTheme.getWallpaper());
		return "wallpaperThemeUpdate";
	}
	
	@PostMapping("wallpaperThemeUpdate")
	public String wallpaperThemeUpdate(@Validated(WallpaperThemeBeanGroupSequence.class)WallpaperThemeFormBean wallpaperThemeBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "wallpaperThemeUpdate";
		}
		if(wallpaperThemeBean.getWallpaperType().getId() == null) {
			errors.rejectValue("wallpaperType","WallpaperThemeController.wallpaperType.id.NotNull","请选择类型！");
			return "wallpaperThemeUpdate";
		}
		
		if(file != null && file.getSize()>0) {
			//存储位置
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/wallpaperImg");
			String absPath = Common.UPLOAD_FILE+"img/wallpaperImg";
			//随机生成文件名加原文件后缀
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			//上传文件
			file.transferTo(new File(absPath,pictureName+suffix));
			wallpaperThemeBean.setWallpaper(pictureName+suffix);
		}
		
		WallpaperTheme wallpaperTheme=new WallpaperTheme();
		wallpaperTheme.setId(wallpaperThemeBean.getId());
		wallpaperTheme.setTheme(wallpaperThemeBean.getTheme());
		wallpaperTheme.setWallpaper(wallpaperThemeBean.getWallpaper());
		wallpaperTheme.setWallpaperType(wallpaperThemeBean.getWallpaperType());
		
		boolean isOk=wallpaperThemeService.updateWallpaperTheme(wallpaperTheme);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("WallpaperThemeController.wallpaperThemeUpdate.Fail", null,"修改失败，请稍后再试！", locale));
			return "wallpaperThemeUpdate";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("WallpaperThemeController.wallpaperThemeUpdate.Success", null,"修改成功！", locale));
		WebTools.removeSession("wallpaper");
		WebTools.removeSession("wallpaperTypes");
		return "redirect:wallpaperTheme";
	}
	
	@GetMapping("wallpaperThemeAdd")
	public String wallpaperThemeAdd(WallpaperThemeFormBean wallpaperThemeBean) {
		List<WallpaperType> wallpaperTypes= wallpaperTypeService.getAllWallpaperType();
		WallpaperType wallpaperType=new WallpaperType();
		wallpaperType.setId(null);
		wallpaperType.setName("请选择");
		wallpaperTypes.add(0, wallpaperType);
		WebTools.setSession("wallpaperType", wallpaperTypes);
		return "wallpaperThemeAdd";
	}
	
	@PostMapping("wallpaperThemeAdd")
	public String wallpaperThemeAdd(@Validated(WallpaperThemeBeanGroupSequence.class)WallpaperThemeFormBean wallpaperThemeBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "wallpaperThemeAdd";
		}
		if(wallpaperThemeBean.getWallpaperType().getId() == null) {
			errors.rejectValue("wallpaperType","WallpaperThemeController.wallpaperType.id.NotNull","请选择类型！");
			return "wallpaperThemeAdd";
		}
		if(file.getSize() == 0) {
			errors.rejectValue("wallpaper","WallpaperThemeController.wallpaper.NotBlank","请选择壁纸！");
			return "wallpaperThemeAdd";
		}
		
		//存储位置
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/wallpaperImg");
		String absPath = Common.UPLOAD_FILE+"img/wallpaperImg";
		//随机生成文件名加原文件后缀
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		//上传文件
		file.transferTo(new File(absPath,pictureName+suffix));
		wallpaperThemeBean.setWallpaper(pictureName+suffix);
		
		WallpaperTheme wallpaperTheme=new WallpaperTheme();
		wallpaperTheme.setId(wallpaperThemeBean.getId());
		wallpaperTheme.setTheme(wallpaperThemeBean.getTheme());
		wallpaperTheme.setWallpaper(wallpaperThemeBean.getWallpaper());
		wallpaperTheme.setWallpaperType(wallpaperThemeBean.getWallpaperType());
		
		boolean isOk=wallpaperThemeService.addWallpaperTheme(wallpaperTheme);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("WallpaperThemeController.wallpaperThemeAdd.Fail", null,"保存失败，请稍后再试！", locale));
			return "wallpaperThemeAdd";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("WallpaperThemeController.wallpaperThemeAdd.Success", null,"保存成功！", locale));
		WebTools.removeSession("wallpaperType");
		return "redirect:wallpaperTheme";
	}
	
	@GetMapping("wallpaperThemeDelete")
	public String wallpaperThemeDelete(@RequestParam Integer id) {
		boolean isOk=wallpaperThemeService.deleteWallpaperThemeById(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
							messageSource.getMessage("WallpaperThemeController.wallpaperThemeDelete.Success", null,"删除成功！", locale):
							messageSource.getMessage("WallpaperThemeController.wallpaperThemeDelete.Success", null,"删除失败，请稍后再试！", locale));
		return "redirect:wallpaperTheme";
	}
}

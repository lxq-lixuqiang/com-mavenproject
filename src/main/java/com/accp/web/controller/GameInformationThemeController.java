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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.accp.pojo.GameInformationTheme;
import com.accp.service.GameInformationThemeService;
import com.accp.web.formbean.GameInformationThemeFormBean;
import com.accp.web.formbean.GameInformationThemeFormBean.GameInfomationThemeFormBeanGroupSequence;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class GameInformationThemeController {
	@Autowired
	private GameInformationThemeService gameInformationThemeService;
	private Integer size=8;
	private Integer num=1;
	@Autowired
	private MessageSource messageSource;

	@GetMapping("gameInformationTheme")
	public String gameInfomationTheme(Integer pageSize,Integer pageNum,String theme,Model model) {
		pageSize=WebTools.memory(pageSize, "gameInformationTheme_pageSize", size);
		pageNum=WebTools.memory(pageNum, "gameInformationTheme_pageNum", num);
		theme=WebTools.memory(theme, "gameInformationTheme_theme", null);
		PageInfo<GameInformationTheme> gameInfomationThemes=gameInformationThemeService.getGameInfomationThemeByTheme(pageNum,pageSize,theme);
		model.addAttribute("pageInfo", gameInfomationThemes);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(gameInfomationThemes.getPageNum(),gameInfomationThemes.getPages());
		model.addAttribute("gameInformationTheme_pagingButton", pagingButton);
		
		//防止重复第一次进入不是第一页
		WebTools.removeSession("gameInformationSelect_pageNum");
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession(Common.DATA_MANAGEMENT,"gameInformationTheme");
		WebTools.setSession("gameInformationTheme_pageSize", pageSize);
		WebTools.setSession("gameInformationTheme_pageNum", pageNum);
		WebTools.setSession("gameInformationTheme_theme", theme);
		return "gameInformationTheme";
	}
	
	@GetMapping("gameInformationThemeSeach")
	public String gameInformationThemeSeach(Model model) {
		model.addAttribute("pageInfo",gameInformationThemeService.getGameInfomationThemeByTheme(1, 10, null));
		return "gameInformationThemeSeach";
	}
	
	@GetMapping("gameInformationThemeSeach02")
	@ResponseBody
	public PageInfo<GameInformationTheme> gameInformationThemeSeach02(Integer pageNum,String theme){
		return gameInformationThemeService.getGameInfomationThemeByTheme(pageNum, 10, theme);
	}
	
	@GetMapping("gameInformationThemeDelete")
	public String gameInformationThemeDelete(@RequestParam Integer id) {
		boolean isOk =gameInformationThemeService.gameInformationThemeDelete(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
							messageSource.getMessage("GameInfomationThemeController.gameInformationThemeDelete.Success", null, "删除成功！", locale) :
							messageSource.getMessage("GameInfomationThemeController.gameInformationThemeDelete.Fail", null, "删除失败，请稍后再试！", locale));
		return "redirect:gameInformationTheme";
	}
	
	@GetMapping("gameInformationThemeAdd")
	public String gameInfomationThemeAdd(GameInformationThemeFormBean gameInformationThemeFormBean) {
		return "gameInformationThemeAdd";
	}
	
	@PostMapping("gameInformationThemeAdd")
	public String gameInfomationThemeAdd(@Validated(GameInfomationThemeFormBeanGroupSequence.class)GameInformationThemeFormBean gameInformationThemeFormBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "gameInformationThemeAdd";
		}
		if(file.getSize() == 0) {
			errors.rejectValue("picture","GameInfomationThemeController.picture.NotBlank","请选择封面！");
			return "gameInformationThemeAdd";
		}
		//存储位置
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/gameInformationImg");
		String absPath = Common.UPLOAD_FILE+"img/gameInformationImg";
		//随机生成文件名加原文件后缀
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		//上传文件
		file.transferTo(new File(absPath,pictureName+suffix));
		gameInformationThemeFormBean.setPicture(pictureName+suffix);
		
		GameInformationTheme gameInformationTheme = new GameInformationTheme();
		gameInformationTheme.setPicture(gameInformationThemeFormBean.getPicture());
		gameInformationTheme.setTheme(gameInformationThemeFormBean.getTheme());
		
		boolean isOk=gameInformationThemeService.gameInformationThemeAdd(gameInformationTheme);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("GameInformationThemeController.GameInformationThemeAdd.Fail", null,"保存失败，请稍后再试！", locale));
			return "gameInformationThemeAdd";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("GameInformationThemeController.GameInformationThemeAdd.Success", null,"保存成功！", locale));
		return "redirect:gameInformationTheme";
	}
	
	@GetMapping("gameInformationThemeUpdate")
	public String gameInformationThemeUpdate(@RequestParam Integer id,Model model,GameInformationThemeFormBean gameInformationThemeFormBean) {
		GameInformationTheme gameInformationTheme=gameInformationThemeService.getGameInformationThemeBy(id);
		model.addAttribute("gameInformationThemeFormBean",gameInformationTheme);
		WebTools.setSession("picture", gameInformationTheme.getPicture());
		return "gameInformationThemeUpdate";
	}
	
	@PostMapping("gameInformationThemeUpdate")
	public String gameInformationThemeUpdate(@Validated(GameInfomationThemeFormBeanGroupSequence.class)GameInformationThemeFormBean gameInformationThemeFormBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "gameInformationThemeUpdate";
		}
		if(file.getSize() > 0) {
			//存储位置
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/gameInformationImg");
			String absPath = Common.UPLOAD_FILE+"img/gameInformationImg";
			//随机生成文件名加原文件后缀
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			//上传文件
			file.transferTo(new File(absPath,pictureName+suffix));
			gameInformationThemeFormBean.setPicture(pictureName+suffix);
		}
		
		GameInformationTheme gameInformationTheme = new GameInformationTheme();
		gameInformationTheme.setId(gameInformationThemeFormBean.getId());
		gameInformationTheme.setPicture(gameInformationThemeFormBean.getPicture());
		gameInformationTheme.setTheme(gameInformationThemeFormBean.getTheme());
		
		boolean isOk=gameInformationThemeService.gameInformationThemeUpdate(gameInformationTheme);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("GameInformationThemeController.gameInformationThemeUpdate.Fail", null,"修改失败，请稍后再试！", locale));
			return "gameInformationThemeUpdate";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("GameInformationThemeController.gameInformationThemeUpdate.Success", null,"修改成功！", locale));
		WebTools.removeSession("picture");
		return "redirect:gameInformationTheme";
	}
}

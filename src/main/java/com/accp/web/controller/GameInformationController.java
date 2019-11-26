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

import com.accp.pojo.GameInformation;
import com.accp.pojo.GameInformationTheme;
import com.accp.service.GameInformationService;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class GameInformationController {
	@Autowired
	private GameInformationService gameInformationService;
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("gameInformationSelect")
	public String GameInformationSelect(@RequestParam(defaultValue="5",required=false) Integer pageSize,
										Integer pageNum,Integer informationThemeId,Model model) {
		informationThemeId=WebTools.memory(informationThemeId, "gameInformationSelect_informationThemeId", null);
		if(informationThemeId == null) {
			return "gameInformationTheme";
		}
		pageNum=WebTools.memory(pageNum, "gameInformationSelect_pageNum", 1);
		
		PageInfo<GameInformation> gameInformations=gameInformationService.getGameInformationByInformationThemeId(pageSize,pageNum,informationThemeId);
		model.addAttribute("pageInfo",gameInformations);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(gameInformations.getPageNum(),gameInformations.getPages());
		model.addAttribute("gameInformationSelect_pagingButton", pagingButton);
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession("gameInformationSelect_informationThemeId", informationThemeId);
		WebTools.setSession("gameInformationSelect_pageNum", pageNum);
		return "gameInformationSelect";
	}
	
	@GetMapping("gameInformationVisit")
	public String gameInformationVisit(@RequestParam(defaultValue="10",required=false) Integer pageSize,
									@RequestParam(defaultValue="1",required=false) Integer pageNum,
									Integer informationThemeId,Model model) {
		informationThemeId=WebTools.memory(informationThemeId, "gameInformationVisit_informationThemeId", null);
		if(informationThemeId == null) {
			return "game";
		}
		PageInfo<GameInformation> gameInformations=gameInformationService.getGameInformationByInformationThemeId(pageSize,pageNum,informationThemeId);
		model.addAttribute("pageInfo",gameInformations);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(gameInformations.getPageNum(),gameInformations.getPages());
		model.addAttribute("gameInformationVisit_pagingButton", pagingButton);
		WebTools.setSession("gameInformationVisit_informationThemeId", informationThemeId);
		return "gameInformationVisit";
	}
	
	@GetMapping("gameInformationDelete")
	public String gameInformationDelete(@RequestParam Integer id) {
		boolean isOk=gameInformationService.gameInformationDelete(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
				messageSource.getMessage("GameInformationController.gameInformationDelete.Success", null, "删除成功！", locale) :
				messageSource.getMessage("GameInformationController.gameInformationDelete.Fail", null, "删除失败，请稍后再试！", locale));	
		return "redirect:gameInformationSelect";
	}
	
	@GetMapping("gameInformationAdd")
	public String gameInformationAdd(@RequestParam Integer informationThemeId) {
		GameInformation gameInformation=new GameInformation();
		gameInformation.setInfoOrImg("");
		gameInformation.setInfoOrImgType(0);
		
		GameInformationTheme gameInformationTheme=new GameInformationTheme();
		gameInformationTheme.setId(informationThemeId);
		gameInformation.setInformationThemeId(gameInformationTheme);
		gameInformation.setSerialNumber(gameInformationService.getSerialNumberByInformationThemeId(informationThemeId));
		gameInformationService.gameInformationAdd(gameInformation);
		PageInfo<GameInformation> gameInformations=gameInformationService.getGameInformationByInformationThemeId(5,1,informationThemeId);
		WebTools.setSession("gameInformationSelect_pageNum",gameInformations.getPages());
		return "redirect:gameInformationSelect";
	}
	
	@PostMapping("fileUploadGameInformation")
	@ResponseBody
	public String fileUploadGameInformation(MultipartFile file,Integer infoOrImgType,Integer id,String context,Integer serialNumber, HttpServletRequest request) throws IllegalStateException, IOException {
		GameInformation gameInformation=new GameInformation();
		gameInformation.setId(id);
		gameInformation.setSerialNumber(serialNumber);
		gameInformation.setInfoOrImgType(infoOrImgType);
		
		if(file!=null && file.getSize()>0) {
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/gameInformationImg");
			String absPath = Common.UPLOAD_FILE+"img/gameInformationImg";
			//随机生成文件名加原文件后缀
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			//上传文件
			file.transferTo(new File(absPath,pictureName+suffix));
			gameInformation.setInfoOrImg(pictureName+suffix);
		}else if(context != null && context.length()>0) {
			gameInformation.setInfoOrImg(context);
		}
		
		boolean isOk=gameInformationService.gameInformationUpdate(gameInformation);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
				messageSource.getMessage("GameInformationController.gameInformationUpdate.Success", null, "修改成功！", locale) :
				messageSource.getMessage("GameInformationController.gameInformationUpdate.Fail", null, "修改失败，请稍后再试！", locale));	
		return "{\"isOk\":"+isOk+"}";
	}
}

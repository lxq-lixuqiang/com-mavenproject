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

import com.accp.pojo.MusicSongsheet;
import com.accp.service.MusicSongsheetService;
import com.accp.web.formbean.MusicSongsheetFormBean;
import com.accp.web.formbean.MusicSongsheetFormBean.MusicSongSheetFormBeanGroupSequence;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class MusicSongsheetController {
	@Autowired
	private MusicSongsheetService musicSongsheetService;
	private Integer size=9;
	private Integer num=1;
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("musicSongsheet")
	public String musicSongsheet(Integer pageSize,Integer pageNum,String name,Model model) {
		name=WebTools.memory(name, "MusicSongsheet_name", null);
		pageSize=WebTools.memory(pageSize, "MusicSongsheet_pageSize", size);
		pageNum=WebTools.memory(pageNum, "MusicSongsheet_pageNum", num);
		PageInfo<MusicSongsheet> musicSongsheets=musicSongsheetService.getMusicSongsheetByName(pageSize, pageNum, name);
		model.addAttribute("pageInfo", musicSongsheets);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(musicSongsheets.getPageNum(),musicSongsheets.getPages());
		model.addAttribute("MusicSongsheet_pagingButton", pagingButton);
		
		WebTools.setSession(Common.DATA_MANAGEMENT,"musicSongsheet");
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession("MusicSongsheet_name", name);
		WebTools.setSession("MusicSongsheet_pageSize", pageSize);
		WebTools.setSession("MusicSongsheet_pageNum", pageNum);
		WebTools.setSession("MusicSongsheet_pagingButton", pagingButton);
		return "musicSongsheet";
	}
	
	@GetMapping("musicSongsheetSeach")
	public String musicSongsheetSeach(Model model) {
		model.addAttribute("pageInfo",musicSongsheetService.getMusicSongsheetByName(10, 1, null));
		return "musicSongsheetSeach";
	}
	
	@GetMapping("musicSongsheetSeach02")
	@ResponseBody
	public PageInfo<MusicSongsheet> musicSongsheetSeach02(Integer pageNum,String name){
		return musicSongsheetService.getMusicSongsheetByName(10, pageNum, name);
	}
	
	@GetMapping("musicSongsheetDelete")
	public String musicSongsheetDelete(@RequestParam Integer id) {
		boolean isOk = musicSongsheetService.musicSongsheetDelete(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
					messageSource.getMessage("MusicSongsheetController.musicSongsheetDelete.Success", null, "删除成功！", locale) :
					messageSource.getMessage("MusicSongsheetController.musicSongsheetDelete.Fail", null, "删除失败，请稍后再试！", locale));
		return "redirect:musicSongsheet";
	}
	
	@GetMapping("musicSongsheetUpdate")
	public String musicSongsheetUpdate(@RequestParam Integer id,MusicSongsheetFormBean musicSongsheetFormBean,Model model) {
		MusicSongsheet msuicSongsheet=musicSongsheetService.getMusicSongsheetById(id);
		model.addAttribute("musicSongsheetFormBean",msuicSongsheet);
		WebTools.setSession("picture", msuicSongsheet.getPicture());
		return "musicSongsheetUpdate";
	}
	
	@PostMapping("musicSongsheetUpdate")
	public String musicSongsheetUpdate(@Validated(MusicSongSheetFormBeanGroupSequence.class)MusicSongsheetFormBean musicSongsheetFormBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "musicSongsheetUpdate";
		}
		if(file.getSize() > 0) {
			//存储位置
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/musicSongsheetImg");
			String absPath = Common.UPLOAD_FILE+"img/musicSongsheetImg";
			//随机生成文件名加原文件后缀
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			//上传文件
			file.transferTo(new File(absPath,pictureName+suffix));
			musicSongsheetFormBean.setPicture(pictureName+suffix);
		}
		
		MusicSongsheet musicSongsheet=new MusicSongsheet();
		musicSongsheet.setId(musicSongsheetFormBean.getId());
		musicSongsheet.setPicture(musicSongsheetFormBean.getPicture());
		musicSongsheet.setName(musicSongsheetFormBean.getName());
		boolean isOk=musicSongsheetService.MusicSongsheetUpdate(musicSongsheet);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("MusicSongsheetController.musicSongsheetUpdate.Fail", null, "修改失败，请稍后再试！", locale));
			return "musicSongsheetUpdate";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("MusicSongsheetController.musicSongsheetUpdate.Success", null, "修改成功！", locale));
		WebTools.removeSession("picture");
		return "redirect:musicSongsheet";
	}
	
	@GetMapping("musicSongsheetAdd")
	public String musicSongsheetAdd(MusicSongsheetFormBean musicSongsheetFormBean) {
		return "musicSongsheetAdd";
	}
	
	@PostMapping("musicSongsheetAdd")
	public String musicSongsheetAdd(@Validated(MusicSongSheetFormBeanGroupSequence.class)MusicSongsheetFormBean musicSongsheetFormBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "musicSongsheetAdd";
		}
		if(file.getSize() == 0) {
			errors.rejectValue("picture","MusicSongsheetController.musicSongsheetAdd.NotBlank","请选择图片！");
			return "musicSongsheetAdd";
		}
		
		//存储位置
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/musicSongsheetImg");
		String absPath = Common.UPLOAD_FILE+"img/musicSongsheetImg";
		//随机生成文件名加原文件后缀
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		//上传文件
		file.transferTo(new File(absPath,pictureName+suffix));
		musicSongsheetFormBean.setPicture(pictureName+suffix);
		
		MusicSongsheet musicSongsheet=new MusicSongsheet();
		musicSongsheet.setPicture(musicSongsheetFormBean.getPicture());
		musicSongsheet.setName(musicSongsheetFormBean.getName());
		boolean isOk=musicSongsheetService.MusicSongsheetAdd(musicSongsheet);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("MusicSongsheetController.musicSongsheetAdd.Fail", null, "保存失败，请稍后再试！", locale));
			return "musicSongsheetAdd";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("MusicSongsheetController.musicSongsheetAdd.Success", null, "保存成功！", locale));
		return "redirect:musicSongsheet";
	}
}

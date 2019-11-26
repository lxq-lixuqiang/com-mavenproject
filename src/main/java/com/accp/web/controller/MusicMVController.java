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

import com.accp.pojo.MusicMV;
import com.accp.pojo.MusicSinger;
import com.accp.service.MusicMVService;
import com.accp.web.formbean.MusicMVFormBean;
import com.accp.web.formbean.MusicMVFormBean.MusicMVFormBeanGroupSequence;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class MusicMVController {
	@Autowired
	private MusicMVService musicMVService;
	@Autowired
	private MessageSource messageSource;

	@GetMapping("musicMV")
	public String musicMV(@RequestParam(defaultValue="1",required=false)Integer pageSize,
			@RequestParam(defaultValue="6",required=false)Integer pageNum,
			Integer musicSingerId,
			String themeName,Model model) {
		musicSingerId=WebTools.memory(musicSingerId, "musicMV_musicSingerId", null);
		if(musicSingerId == null) {
			return "redirect:musicSinger";
		}
		PageInfo<MusicMV> musicMVs=musicMVService.getAllMusicMV(pageSize, pageNum, themeName, musicSingerId);
		model.addAttribute("pageInfo", musicMVs);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(musicMVs.getPageNum(),musicMVs.getPages());
		model.addAttribute("musicMV_pagingButton", pagingButton);
		
		WebTools.sessionToRequest(Common.MSG);
		model.addAttribute("musicMV_themeName", themeName);
		WebTools.setSession("musicMV_musicSingerId", musicSingerId);
		return "musicMV";
	}
	
	@GetMapping("musicMVSeach")
	public String musicMVSeach(Model model) {
		model.addAttribute("pageInfo",musicMVService.getAllMusicMV(12, 1, null, null));
		return "musicMVSeach";
	}
	
	@GetMapping("musicMVSeach02")
	@ResponseBody
	public PageInfo<MusicMV> musicMVSeach02(Integer pageNum,String themeName){
		return musicMVService.getAllMusicMV(12,pageNum, themeName, null);
	}
	
	@GetMapping("musicMVDelete")
	public String musicMVDelete(@RequestParam Integer id) {
		boolean isOk=musicMVService.musicMVDelete(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
					messageSource.getMessage("MusicMVController.musicMVDelete.Success", null, "删除成功！", locale):
					messageSource.getMessage("MusicMVController.musicMVDelete.Fail", null, "删除失败，请稍后再试！", locale));
		return "redirect:musicMV";
	}
	
	@GetMapping("musicMVUpdate")
	public String musicMVUpdate(@RequestParam Integer id,MusicMVFormBean musicMVFormBean,Model model) {
		MusicMV musicMV=musicMVService.getMusicMVById(id);
		model.addAttribute("musicMVFormBean",musicMV);
		WebTools.setSession("themePicture", musicMV.getThemePicture());
		return "musicMVUpdate";
	}
	
	@PostMapping("musicMVUpdate")
	public String musicMVUpdate(@Validated(MusicMVFormBeanGroupSequence.class)MusicMVFormBean musicMVFormBean,Errors errors,MultipartFile file,MultipartFile file2,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "musicMVUpdate";
		}
		//mv图片
		if(file.getSize() > 0) {
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/musicMVImg");
			String absPath = Common.UPLOAD_FILE+"img/musicMVImg";
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			file.transferTo(new File(absPath,pictureName+suffix));
			musicMVFormBean.setThemePicture(pictureName+suffix);
		}
		//mv视频
		if(file2.getSize() > 0) {
			//String absPath2 = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"video/musicMV");
			String absPath2 = Common.UPLOAD_FILE+"video/musicMV";
			String suffix2=file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf("."));
			String pictureName2=UUID.randomUUID().toString();
			file2.transferTo(new File(absPath2,pictureName2+suffix2));
			musicMVFormBean.setMv(pictureName2+suffix2);
		}
		
		MusicMV musicMV=new MusicMV();
		musicMV.setId(musicMVFormBean.getId());
		musicMV.setThemePicture(musicMVFormBean.getThemePicture());
		musicMV.setThemeName(musicMVFormBean.getThemeName());
		musicMV.setMv(musicMVFormBean.getMv());
		
		MusicSinger musicSinger=new MusicSinger();
		musicSinger.setId(musicMVFormBean.getMusicSinger().getId());
		musicMV.setMusicSinger(musicSinger);
		
		boolean isOk=musicMVService.musicMVUpdate(musicMV);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("MusicMVController.musicMVUpdate.Fail", null, "修改失败，请稍后再试！", locale));
			return "musicMVUpdate";
		}
		WebTools.removeSession("themePicture");
		WebTools.setSession(Common.MSG, messageSource.getMessage("MusicMVController.musicMVUpdate.Success", null, "修改成功！", locale));
		return "redirect:musicMV";
	}
	
	@GetMapping("musicMVAdd")
	public String musicMVAdd(MusicMVFormBean musicMVFormBean) {
		return "musicMVAdd";
	}
	
	@PostMapping("musicMVAdd")
	public String musicMVAdd(@Validated(MusicMVFormBeanGroupSequence.class)MusicMVFormBean musicMVFormBean,Errors errors,MultipartFile file,MultipartFile file2,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "musicMVAdd";
		}
		if(file.getSize() == 0) {
			errors.rejectValue("themePicture","MusicMVController.themePicture.NotBlank","请选择图片！");
			return "musicMVAdd";
		}
		if(file2.getSize() == 0) {
			errors.rejectValue("mv","MusicMVController.mv.NotBlank","请选择mv！");
			return "musicMVAdd";
		}
		//mv图片
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/musicMVImg");
		String absPath = Common.UPLOAD_FILE+"img/musicMVImg";
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		file.transferTo(new File(absPath,pictureName+suffix));
		musicMVFormBean.setThemePicture(pictureName+suffix);
		//mv视频
		//String absPath2 = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"video/musicMV");
		String absPath2 = Common.UPLOAD_FILE+"video/musicMV";
		String suffix2=file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf("."));
		String pictureName2=UUID.randomUUID().toString();
		file2.transferTo(new File(absPath2,pictureName2+suffix2));
		musicMVFormBean.setMv(pictureName2+suffix2);
		
		MusicMV musicMV=new MusicMV();
		musicMV.setThemePicture(musicMVFormBean.getThemePicture());
		musicMV.setThemeName(musicMVFormBean.getThemeName());
		musicMV.setMv(musicMVFormBean.getMv());
		
		MusicSinger musicSinger=new MusicSinger();
		musicSinger.setId(musicMVFormBean.getMusicSinger().getId());
		musicMV.setMusicSinger(musicSinger);
		
		boolean isOk=musicMVService.musicMVAdd(musicMV);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("MusicMVController.musicMVAdd.Fail", null, "保存失败，请稍后再试！", locale));
			return "musicMVAdd";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("MusicMVController.musicMVAdd.Success", null, "保存成功！", locale));
		return "redirect:musicMV";
	}
	
	@GetMapping("musicMVVisit")
	public String musicMVVisit(@RequestParam Integer id,Model model) {
		model.addAttribute("musicMV",musicMVService.getMusicMVById(id));
		return "musicMVVisit";
	}
}

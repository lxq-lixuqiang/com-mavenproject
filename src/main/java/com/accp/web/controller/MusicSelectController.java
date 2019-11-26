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

import com.accp.pojo.Music;
import com.accp.pojo.MusicSinger;
import com.accp.service.MusicService;
import com.accp.web.formbean.MusicFormBean;
import com.accp.web.formbean.MusicFormBean.musicFormBeanGroupSequence;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class MusicSelectController {
	@Autowired
	private MusicService musicService;
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("musicSelect")
	public String musicSelect(Integer musicSingerId,
			@RequestParam(defaultValue="1",required=false)Integer pageNum,
			@RequestParam(defaultValue="8",required=false)Integer pageSize,
			String songName,Model model) {
		musicSingerId=WebTools.memory(musicSingerId, "musicSelect_musicSingerId", null);
		if(musicSingerId == null) {
			return "musicSinger";
		}
		PageInfo<Music> musics=musicService.getMusicBySingerId(musicSingerId, pageNum, pageSize,songName);
		model.addAttribute("pageInfo",musics);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(musics.getPageNum(),musics.getPages());
		model.addAttribute("musicSelect_pagingButton", pagingButton);
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession("musicSelect_musicSingerId", musicSingerId);
		model.addAttribute("musicSelect_songName", songName);
		return "musicSelect";
	}
	
	@GetMapping("musicDelete")
	public String musicDelete(@RequestParam Integer id) {
		boolean isOk=musicService.deleteMusicById(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
					messageSource.getMessage("MusicSelectController.musicDelete.Success", null, "删除成功！", locale) :
					messageSource.getMessage("MusicSelectController.musicDelete.Fail", null, "删除失败，请稍后再试！", locale));
		return "redirect:musicSelect";
	}
	
	@GetMapping("musicAdd")
	public String musicAdd(MusicFormBean musicFormBean) {
		return "musicAdd";
	}
	
	@PostMapping("musicAdd")
	public String musicAdd(@Validated(musicFormBeanGroupSequence.class)MusicFormBean musicFormBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "musicAdd";
		}
		if(file.getSize() == 0) {
			errors.rejectValue("music","MusicSelectController.musicAdd.NotBlank","请选择歌曲！");
			return "musicAdd";
		}
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"audio/music");
		String absPath = Common.UPLOAD_FILE+"audio/music";
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		file.transferTo(new File(absPath,pictureName+suffix));
		musicFormBean.setMusic(pictureName+suffix);
		
		Music music=new Music();
		music.setMusic(musicFormBean.getMusic());
		music.setSongName(musicFormBean.getSongName());
		MusicSinger musicSinger=new MusicSinger();
		musicSinger.setId(musicFormBean.getMusicSinger().getId());
		music.setMusicSinger(musicSinger);
		boolean isOk=musicService.musicAdd(music);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("MusicSelectController.musicAdd.Fail", null, "保存失败，请稍后再试！", locale));
			return "musicAdd";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("MusicSelectController.musicAdd.Success", null, "保存成功！", locale));
		return "redirect:musicSelect";
	}
	
	@GetMapping("musicUpdate")
	public String musicUpdate(@RequestParam Integer id,MusicFormBean musicFormBean,Model model) {
		model.addAttribute("musicFormBean",musicService.getMusicById(id));
		return "musicUpdate";
	}
	
	@PostMapping("musicUpdate")
	public String musicUpdate(@Validated(musicFormBeanGroupSequence.class)MusicFormBean musicFormBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "musicUpdate";
		}
		if(file.getSize() > 0) {
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"audio/music");
			String absPath = Common.UPLOAD_FILE+"audio/music";
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			file.transferTo(new File(absPath,pictureName+suffix));
			musicFormBean.setMusic(pictureName+suffix);
		}
		
		Music music=new Music();
		music.setId(musicFormBean.getId());
		music.setMusic(musicFormBean.getMusic());
		music.setSongName(musicFormBean.getSongName());
		boolean isOk=musicService.musicUpdate(music);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("MusicSelectController.musicUpdate.Fail", null, "修改失败，请稍后再试！", locale));
			return "musicUpdate";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("MusicSelectController.musicUpdate.Success", null, "修改成功！", locale));
		return "redirect:musicSelect";
	}
}

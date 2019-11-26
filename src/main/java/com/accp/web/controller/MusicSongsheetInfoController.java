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
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.pojo.Music;
import com.accp.service.MusicService;
import com.accp.service.MusicSongsheetInfoService;
import com.accp.service.MusicSongsheetService;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class MusicSongsheetInfoController {
	@Autowired
	private MusicSongsheetInfoService musicSongsheetInfoService;
	@Autowired 
	private MusicService musicService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private MusicSongsheetService musicSongsheetService;

	@GetMapping("musicSongsheetInfo")
	public String musicSongsheetInfo(Integer songsheetId,
								@RequestParam(defaultValue="1",required=false)Integer pageNum,
								@RequestParam(defaultValue="10",required=false)Integer pageSize,
								String name,Model model) {
		songsheetId=WebTools.memory(songsheetId, "musicSongsheetInfo_songsheetId", null);
		if(songsheetId == null) {
			return "musicSongsheet";
		}
		PageInfo<Music> musics=musicSongsheetInfoService.getMusicSongsheetInfoBySongsheetId(pageNum,pageSize,songsheetId,name);
		model.addAttribute("pageInfo",musics);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(musics.getPageNum(),musics.getPages());
		model.addAttribute("musicSongsheetInfo_pagingButton", pagingButton);
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession("musicSongsheetInfo_songsheetId", songsheetId);
		model.addAttribute("musicSongsheetInfo_name", name);
		return "musicSongsheetInfo";
	}
	
	@GetMapping("musicSongsheetInfoUpdate")
	public String musicSongsheetInfoUpdate(Integer songsheetId,
			@RequestParam(defaultValue="1",required=false)Integer pageNum,
			@RequestParam(defaultValue="10",required=false)Integer pageSize,
			String name,Model model) {
		songsheetId=WebTools.memory(songsheetId, "musicSongsheetInfoUpdate_songsheetId", null);
		if(songsheetId == null) {
			return "musicSongsheet";
		}
		model.addAttribute("musicIds",musicSongsheetInfoService.getMusicIdByMusicSongsheetId(songsheetId));
		model.addAttribute("pageInfo",musicService.getMusicBySongNameAndSingerName(pageSize, pageNum, name, name));
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession("musicSongsheetInfoUpdate_songsheetId", songsheetId);
		model.addAttribute("musicSongsheetInfoUpdate_name", name);
		return "musicSongsheetInfoUpdate";
	}
	
	@GetMapping("musicSongsheetInfoUpdateSelect")
	@ResponseBody
	public PageInfo<Music> musicSongsheetInfoUpdateSelect(Integer pageSize,Integer pageNum,String name) {
		return musicService.getMusicBySongNameAndSingerName(pageSize, pageNum, name, name);
	}
	
	@GetMapping("musicSongsheetInfoUpdateMusicId")
	public String musicSongsheetInfoUpdateMusicId(Integer[] musicIds,Integer songsheetId) {
		boolean isOk=musicSongsheetInfoService.musicSongsheetInfoAdd(musicIds,songsheetId);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
								messageSource.getMessage("MusicSongsheetInfoController.musicSongsheetInfoUpdateMusicId.Success", null, "修改成功！", locale) :
									messageSource.getMessage("MusicSongsheetInfoController.musicSongsheetInfoUpdateMusicId.Fail", null, "修改失败，请稍后再试！", locale));
		return "redirect:musicSongsheetInfo";
	}
	
	@GetMapping("musicSongsheetInfoVisit")
	public String musicSongsheetInfoVisit(@RequestParam Integer musicSongsheetId,Model model) {
		model.addAttribute("musicSongsheet", musicSongsheetService.getMusicSongsheetById(musicSongsheetId));
		model.addAttribute("pageInfo", musicSongsheetInfoService.getMusicSongsheetInfoBySongsheetId(0, 30, musicSongsheetId, null));
		return "musicSongsheetInfoVisit";
	}
}

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

import com.accp.pojo.Game;
import com.accp.pojo.GameWallpaper;
import com.accp.service.GameWallpaperService;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class GameWallpaperController {
	@Autowired
	private GameWallpaperService gameWallpaperService;
	@Autowired
	private MessageSource messageSource;

	@GetMapping("gameWallpaper")
	public String gameWallpaper(Integer gameId,
					@RequestParam(required=false,defaultValue="8") Integer pageSize,
					Integer pageNum,Model model){
		gameId=WebTools.memory(gameId, "gameWallpaper_gameId", null);
		if(gameId == null) {
			return "redirect:gameInfo";
		}
		pageNum=WebTools.memory(pageNum, "gameWallpaper_pageNum", 1);
		
		PageInfo<GameWallpaper> gameWallpaper=gameWallpaperService.getGameWallpaperByGameId(pageSize,pageNum,gameId);
		model.addAttribute("pageInfo", gameWallpaper);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(gameWallpaper.getPageNum(),gameWallpaper.getPages());
		model.addAttribute("gameWallpaper_pagingButton", pagingButton);
		WebTools.setSession("gameWallpaper_gameId", gameId);
		WebTools.setSession("gameWallpaper_pageNum", pageNum);
		WebTools.sessionToRequest(Common.MSG);
		return "gameWallpaper";
	}
	
	@GetMapping("gameWallpaperAdd")
	public String gameWallpaperAdd(@RequestParam Integer gameId) {
		GameWallpaper gameWallpaper=new GameWallpaper();
		gameWallpaper.setWallpaper("");
		Game game=new Game();
		game.setId(gameId);
		gameWallpaper.setGameId(game);
		gameWallpaperService.addGameWallpaper(gameWallpaper);
		
		PageInfo<GameWallpaper> gameWallpapers=gameWallpaperService.getGameWallpaperByGameId(1,8,gameId);
		WebTools.setSession("gameWallpaper_pageNum", gameWallpapers.getPages());
		return "redirect:gameWallpaper";
	}
	
	@GetMapping("gameWallpaperDelete")
	public String gameWallpaperDelete(@RequestParam Integer id) {
		boolean isOk=gameWallpaperService.gameWallpaperDelete(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
								messageSource.getMessage("GameWallpaperController.gameWallpaperDelete.Success", null, "删除成功！", locale) :
								messageSource.getMessage("GameWallpaperController.gameWallpaperDelete.Fail", null, "删除失败，请稍后再试！", locale));	
		return "redirect:gameWallpaper";
	}
	
	@PostMapping("gameWallpaperUpdate")
	@ResponseBody
	public String gameWallpaperUpdate(MultipartFile file,Integer id, HttpServletRequest request) throws IllegalStateException, IOException {
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/gameImg");
		String absPath = Common.UPLOAD_FILE+"img/gameImg";
		//随机生成文件名加原文件后缀
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		//上传文件
		file.transferTo(new File(absPath,pictureName+suffix));
		
		GameWallpaper gameWallpaper=new GameWallpaper();
		gameWallpaper.setId(id);
		gameWallpaper.setWallpaper(pictureName+suffix);
		boolean isOk=gameWallpaperService.gameWallpaperUpdate(gameWallpaper);
		return "{\"isOk\":"+isOk+"}";
	}
}

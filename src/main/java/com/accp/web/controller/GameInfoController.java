package com.accp.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

import com.accp.pojo.Game;
import com.accp.pojo.GameClassification;
import com.accp.pojo.GamePlatform;
import com.accp.pojo.GameType;
import com.accp.service.GameClassificationService;
import com.accp.service.GamePlatformService;
import com.accp.service.GameService;
import com.accp.service.GameTypeService;
import com.accp.web.formbean.GameFormBean;
import com.accp.web.formbean.GameFormBean.GameFormBeanGroupSequence;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class GameInfoController {
	@Autowired
	private GameService gameService;
	private Integer size=9;
	private Integer num=1;
	@Autowired
	private GameTypeService gameTypeService;
	@Autowired
	private GamePlatformService gamePlatformService;
	@Autowired
	private GameClassificationService gameClassificationService;
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("gameInfo")
	public String gameInfo(Integer pageSize,Integer pageNum,String nameAndType,Model model) {
		pageSize=WebTools.memory(pageSize, "gameInfo_pageSize", size);
		pageNum=WebTools.memory(pageNum, "gameInfo_pageNum", num);
		nameAndType=WebTools.memory(nameAndType, "gameInfo_nameAndType", null);
		PageInfo<Game> gameInfo=gameService.getGameByNameAndType(pageNum,pageSize,nameAndType);
		model.addAttribute("pageInfo", gameInfo);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(gameInfo.getPageNum(),gameInfo.getPages());
		model.addAttribute("gameInfo_pagingButton", pagingButton);
		
		//防止重复第一次进入不是第一页
		WebTools.removeSession("gameWallpaper_pageNum");
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession(Common.DATA_MANAGEMENT,"gameInfo");
		WebTools.setSession("gameInfo_pageSize", pageSize);
		WebTools.setSession("gameInfo_pageNum", pageNum);
		WebTools.setSession("gameInfo_nameAndType", nameAndType);
		return "gameInfo";
	}

	@GetMapping("gameUpdate")
	public String gameUpdate(GameFormBean gameFormBean,@RequestParam Integer id,Model model) {
		//游戏类型
		List<GameType> gameTypes=gameTypeService.getAll();
		GameType gameType=new GameType();
		gameType.setId(null);
		gameType.setTypeName("请选择");
		gameTypes.add(0, gameType);
		WebTools.setSession("gameTypes",gameTypes);
		//游戏平台
		List<GamePlatform> gamePlatforms=gamePlatformService.getAll();
		GamePlatform gamePlatform = new GamePlatform();
		gamePlatform.setId(null);
		gamePlatform.setPlatformName("请选择");
		gamePlatforms.add(0, gamePlatform);
		WebTools.setSession("gamePlatforms", gamePlatforms);
		//游戏分类
		List<GameClassification> gameClassifications=gameClassificationService.getAll();
		GameClassification gameClassification=new GameClassification();
		gameClassification.setId(null);
		gameClassification.setClassificationName("请选择");
		gameClassifications.add(0, gameClassification);
		WebTools.setSession("gameClassifications", gameClassifications);
		//游戏语言
		Map<String, String> languages=new HashMap<>();
		languages.put(null, "请选择");
		languages.put("中文", "中文");
		languages.put("英文", "英文");
		languages.put("日文", "日文");
		WebTools.setSession("language", languages);
		
		Game game=gameService.getGameById(id);
		model.addAttribute("gameFormBean", game);
		WebTools.setSession("picture",game.getPicture());
		return "gameUpdate";
	}
	
	@PostMapping("gameUpdate")
	public String gameUpdate(@Validated(GameFormBeanGroupSequence.class)GameFormBean gameFormBean,Errors errors,MultipartFile file,MultipartFile file2,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "gameUpdate";
		}
		if(gameFormBean.getClassificationId().getId() ==null) {
			errors.rejectValue("classificationId","GameInfoController.gameAdd.classification.NotBlank","请选择分类！");
			return "gameUpdate";
		}
		if(gameFormBean.getTypeId().getId() ==null) {
			errors.rejectValue("typeId","GameInfoController.gameAdd.typeId.NotBlank","请选择类型！");
			return "gameUpdate";
		}
		if(gameFormBean.getPlatformId().getId()==null) {
			errors.rejectValue("platformId","GameInfoController.gameAdd.platformId.NotBlank","请选择平台！");
			return "gameUpdate";
		}
		//游戏封面
		if(file.getSize() > 0) {
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/gameTheme");
			String absPath = Common.UPLOAD_FILE+"img/gameTheme";
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			file.transferTo(new File(absPath,pictureName+suffix));
			gameFormBean.setPicture(pictureName+suffix);
		}
		//游戏视频
		if(file2.getSize() > 0) {
			//String absPath2 = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"file/game");
			String absPath2 = Common.UPLOAD_FILE+"file/game";
			String suffix2=file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf("."));
			String pictureName2=UUID.randomUUID().toString();
			file2.transferTo(new File(absPath2,pictureName2+suffix2));
			gameFormBean.setGame(pictureName2+suffix2);
		}
		
		Game game=new Game();
		game.setId(gameFormBean.getId());
		game.setPicture(gameFormBean.getPicture());
		game.setName(gameFormBean.getName());
		game.setLanguage(gameFormBean.getLanguage());
		game.setIssuer(gameFormBean.getIssuer());
		game.setGame(gameFormBean.getGame());
		game.setGamePath(gameFormBean.getGamePath());
		game.setContent(gameFormBean.getContent());
		game.setGameExplain(gameFormBean.getGameExplain());
		
		GameClassification gameClassification=new GameClassification();
		gameClassification.setId(gameFormBean.getClassificationId().getId());
		game.setClassificationId(gameClassification);
		
		GameType gameType=new GameType();
		gameType.setId(gameFormBean.getTypeId().getId());
		game.setTypeId(gameType);
		
		GamePlatform gamePlatform=new GamePlatform();
		gamePlatform.setId(gameFormBean.getPlatformId().getId());
		game.setPlatformId(gamePlatform);
		
		boolean isOk=gameService.gameUpdate(game);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("GameInfoController.gameUpdate.Fail", null, "修改失败，请稍后再试！", locale));
			return "gameUpdate";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("GameInfoController.gameUpdate.Success", null, "修改成功！", locale));
		WebTools.removeSession("gameTypes");
		WebTools.removeSession("gamePlatforms");
		WebTools.removeSession("gameClassifications");
		WebTools.removeSession("languages");
		WebTools.removeSession("picture");
		return "redirect:gameInfo";
	}
	
	@GetMapping("gameDelete")
	public String gameDelete(@RequestParam Integer id) {
		boolean isOk=gameService.gameDelete(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
						messageSource.getMessage("GameInfoController.gameDelete.Success", null, "删除成功！", locale) :
						messageSource.getMessage("GameInfoController.gameDelete.Fail", null, "删除失败，请稍后再试！", locale));
		return "redirect:gameInfo";
	}
	
	@GetMapping("gameAdd")
	public String gameAdd(GameFormBean gameFormBean) {
		//游戏类型
		List<GameType> gameTypes=gameTypeService.getAll();
		GameType gameType=new GameType();
		gameType.setId(null);
		gameType.setTypeName("请选择");
		gameTypes.add(0, gameType);
		WebTools.setSession("gameTypes",gameTypes);
		//游戏平台
		List<GamePlatform> gamePlatforms=gamePlatformService.getAll();
		GamePlatform gamePlatform = new GamePlatform();
		gamePlatform.setId(null);
		gamePlatform.setPlatformName("请选择");
		gamePlatforms.add(0, gamePlatform);
		WebTools.setSession("gamePlatforms", gamePlatforms);
		//游戏分类
		List<GameClassification> gameClassifications=gameClassificationService.getAll();
		GameClassification gameClassification=new GameClassification();
		gameClassification.setId(null);
		gameClassification.setClassificationName("请选择");
		gameClassifications.add(0, gameClassification);
		WebTools.setSession("gameClassifications", gameClassifications);
		//游戏语言
		Map<String, String> languages=new HashMap<>();
		languages.put(null, "请选择");
		languages.put("中文", "中文");
		languages.put("英文", "英文");
		languages.put("日文", "日文");
		WebTools.setSession("languages", languages);
		
		return "gameAdd";
	}
	
	@PostMapping("gameAdd")
	public String gameAdd(@Validated(GameFormBeanGroupSequence.class)GameFormBean gameFormBean,Errors errors,MultipartFile file,MultipartFile file2,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "gameAdd";
		}
		if(gameFormBean.getClassificationId().getId() ==null) {
			errors.rejectValue("classificationId","GameInfoController.gameAdd.classification.NotBlank","请选择分类！");
			return "gameAdd";
		}
		if(gameFormBean.getTypeId().getId() ==null) {
			errors.rejectValue("typeId","GameInfoController.gameAdd.typeId.NotBlank","请选择类型！");
			return "gameAdd";
		}
		if(gameFormBean.getPlatformId().getId()==null) {
			errors.rejectValue("platformId","GameInfoController.gameAdd.platformId.NotBlank","请选择平台！");
			return "gameAdd";
		}
		//游戏封面
		if(file.getSize() == 0) {
			errors.rejectValue("picture","GameInfoController.gameAdd.picture.NotBlank","请选择封面！");
			return "gameAdd";
		}
		//游戏视频
		if(file2.getSize() > 0) {
			//String absPath2 = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"file/game");
			String absPath2 = Common.UPLOAD_FILE+"file/game";
			String suffix2=file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf("."));
			String pictureName2=UUID.randomUUID().toString();
			file2.transferTo(new File(absPath2,pictureName2+suffix2));
			gameFormBean.setGame(pictureName2+suffix2);
		}
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/gameTheme");
		String absPath = Common.UPLOAD_FILE+"img/gameTheme";
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		file.transferTo(new File(absPath,pictureName+suffix));
		gameFormBean.setPicture(pictureName+suffix);
		
		Game game=new Game();
		game.setPicture(gameFormBean.getPicture());
		game.setName(gameFormBean.getName());
		game.setLanguage(gameFormBean.getLanguage());
		game.setIssuer(gameFormBean.getIssuer());
		game.setGame(gameFormBean.getGame());
		game.setGamePath(gameFormBean.getGamePath());
		game.setContent(gameFormBean.getContent());
		game.setGameExplain(gameFormBean.getGameExplain());
		
		GameClassification gameClassification=new GameClassification();
		gameClassification.setId(gameFormBean.getClassificationId().getId());
		game.setClassificationId(gameClassification);
		
		GameType gameType=new GameType();
		gameType.setId(gameFormBean.getTypeId().getId());
		game.setTypeId(gameType);
		
		GamePlatform gamePlatform=new GamePlatform();
		gamePlatform.setId(gameFormBean.getPlatformId().getId());
		game.setPlatformId(gamePlatform);
		
		boolean isOk=gameService.gameAdd(game);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("GameInfoController.gameAdd.Fail", null, "保存失败，请稍后再试！", locale));
			return "gameAdd";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("GameInfoController.gameAdd.Success", null, "保存成功！", locale));
		WebTools.removeSession("gameTypes");
		WebTools.removeSession("gamePlatforms");
		WebTools.removeSession("gameClassifications");
		WebTools.removeSession("languages");
		return "redirect:gameInfo";
	}
}

package com.accp.web.controller;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.pojo.Game;
import com.accp.service.GameClassificationService;
import com.accp.service.GameInformationThemeService;
import com.accp.service.GamePlatformService;
import com.accp.service.GameService;
import com.accp.service.GameTypeService;
import com.accp.service.GameWallpaperService;
import com.accp.service.WallpaperThemeService;
import com.accp.web.util.Common;
import com.github.pagehelper.PageInfo;

/**
 * 游戏主页控制器
 * @author Y2项目:李旭强
 *
 */
@Controller
public class GameController {
	
	@Autowired
	private GameService gameService;
	@Autowired
	private GameWallpaperService gameWallpaperService;
	@Autowired
	private WallpaperThemeService wallpaperThemeService;
	@Autowired
	private GameInformationThemeService gameInformationThemeService;
	@Autowired
	private GamePlatformService gamePlatformService;
	@Autowired
	private GameTypeService gameTypeService;
	@Autowired
	private GameClassificationService gameClassificationService;

	@GetMapping("game")
	public String game(Model model) {
		//轮播
		model.addAttribute("newGameInformationThemePageInfo",gameInformationThemeService.getGameInfomationThemeByTheme(1, 9, null));
		//最新资讯
		model.addAttribute("newGameInformationThemePageInfo02",gameInformationThemeService.getGameInfomationThemeByTheme(2, 9, null));
		//宣传
		model.addAttribute("InformationTheme01",gameInformationThemeService.getGameInfomationThemeByTheme(1, 21, "宣传"));
		//cosplay
		model.addAttribute("InformationTheme02",gameInformationThemeService.getGameInfomationThemeByTheme(1, 4, "cos"));
		//手办
		model.addAttribute("InformationTheme03",gameInformationThemeService.getGameInfomationThemeByTheme(1, 4, "手办"));
		//专访
		model.addAttribute("InformationTheme04",gameInformationThemeService.getGameInfomationThemeByTheme(1, 21, "专访"));
		//最新单机游戏下载
		model.addAttribute("newPCGames",gameService.getGameByPlatformId(10,1));
		//近期大作
		model.addAttribute("newMaxGames",gameService.getGameByClassificationId(10,4));
		//即将上市
		model.addAttribute("ComingNewMaxGames",gameService.getGameByClassificationId(12,2));
		//最新上市
		model.addAttribute("NewGameInfos",gameService.getGameByClassificationId(10,3));
		//美图壁纸
		model.addAttribute("WallpaperGames",wallpaperThemeService.getWallpaperThemeByType(4, 7));
		//即将上市游戏
		model.addAttribute("ComingNewMaxGameInfos02",gameService.getGameByClassificationId(20,2));
		//近期大作游戏
		model.addAttribute("newMaxGameInfos02",gameService.getGameByClassificationId(20,4));
		//最新上市游戏
		model.addAttribute("NewGameInfos02",gameService.getGameByClassificationId(20,3));
		return "game";
	}
	
	@GetMapping("gameSeach")
	public String gameSeach(Model model) {
		//平台
		model.addAttribute("gamePlatforms",gamePlatformService.getAll());
		//类型
		model.addAttribute("gameTypes",gameTypeService.getAll());
		//类别
		model.addAttribute("gameClassifications",gameClassificationService.getAll());
		//语言
		Map<String, String> languages=new HashMap<>();
		languages.put("中文", "中文");
		languages.put("英文", "英文");
		languages.put("日文", "日文");
		model.addAttribute("languages", languages);
		
		model.addAttribute("pageInfo",gameService.getGameByClassificationIdAndPlatformIdAndTypeIdAndLanguage(1, 18, null, null, null, null,null));
		return "gameSeach";
	}
	
	
	@GetMapping("gameSeach02")
	@ResponseBody
	public PageInfo<Game> gameSeach02(Integer pageNum,Integer classificationId,Integer platformId,Integer typeId,String language,String name){
		return gameService.getGameByClassificationIdAndPlatformIdAndTypeIdAndLanguage(pageNum, 18, classificationId, platformId, typeId, language,name);
	}
	
	@GetMapping("gameVisit")
	public String gameVisit(@RequestParam Integer gameId,Model model) {
		model.addAttribute("games", gameService.getGameById(gameId));
		model.addAttribute("gameWallpapers", gameWallpaperService.getGameWallpaperByGameId(gameId));
		return "gameVisit";
	}
	
	@GetMapping("downLoadGame")
	public HttpEntity<byte[]> downLoadGame(String game,HttpServletRequest request) throws IOException{
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"file/game");
		String absPath = Common.UPLOAD_FILE+"file/game";
		File file= new File(absPath, game);
		byte[] body=FileUtils.readFileToByteArray(file);
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentDispositionFormData("attachment", game);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
	}
}

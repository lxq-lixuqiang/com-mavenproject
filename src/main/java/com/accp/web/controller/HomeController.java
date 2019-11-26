package com.accp.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.accp.service.AnimationEntertainmentAnimationService;
import com.accp.service.AnimationInfoService;
import com.accp.service.GameInformationThemeService;
import com.accp.service.GameService;
import com.accp.service.HomeNavigationBarService;
import com.accp.service.MusicMVService;
import com.accp.service.MusicSingerService;
import com.accp.service.MusicSongsheetService;
import com.accp.service.WallpaperThemeService;

/**
 * 首页控制器
 * @author Y2项目:李旭强
 *
 */
@Controller
public class HomeController {

	@Autowired
	private GameService gameService;
	@Autowired
	private MusicSingerService musicSingerService;
	@Autowired
	private WallpaperThemeService wallpaperThemeService;
	@Autowired
	private MusicMVService musicMVService;
	@Autowired
	private MusicSongsheetService musicSongsheetService;
	@Autowired
	private AnimationInfoService animationInfoService;
	@Autowired
	private AnimationEntertainmentAnimationService animationEntertainmentAnimationService;
	@Autowired
	private HomeNavigationBarService homeNavigationBarService;
	@Autowired
	private GameInformationThemeService gameInformationThemeService;
	
	//导航到首页页面
	@GetMapping("home")
	public String home(Model model) {
		//轮播
		model.addAttribute("homeNavigationBars",homeNavigationBarService.getAll());
		//新番导视
		model.addAttribute("animationEntertainmentAnimation",animationEntertainmentAnimationService.getAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(null,0,8));
		//最新导视
		model.addAttribute("newAnimationEntertainmentAnimation",animationEntertainmentAnimationService.getAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(null,8,10));
		//新番推荐
		model.addAttribute("animationInfos",animationInfoService.getAnimationInfoByNotClassificationId(8,19));
		//近期大作
		model.addAttribute("newMaxGames",gameService.getGameByClassificationId(7,4));
		//cosplay
		model.addAttribute("InformationThemeCosPlay",gameInformationThemeService.getGameInfomationThemeByTheme(1, 6, "cos"));
		//最新上市
		model.addAttribute("newGameInfos",gameService.getGameByClassificationId(4,3));
		//游戏资讯
		model.addAttribute("newGameInformationThemePageInfo",gameInformationThemeService.getGameInfomationThemeByTheme(1,7, null));
		//歌单推荐
		model.addAttribute("pageInfotopMusicSongsheets",musicSongsheetService.getMusicSongsheetByName(8,1,null));
		//每日精选
		model.addAttribute("pageInfobottomMusicSongsheets",musicSongsheetService.getMusicSongsheetByName(5,10, null));
		//歌手推荐
		model.addAttribute("pageInfoMusicSinger", musicSingerService.getAllMusicSinger(1, 14, null, null));
		//热门MV
		model.addAttribute("musicMVs", musicMVService.getTopMusicMV(0,8));
		//热门明星
		model.addAttribute("wallpaperMusicThtemes",wallpaperThemeService.getWallpaperThemeByType(5,3));
		//壁纸推荐
		model.addAttribute("pageInfo",wallpaperThemeService.getAllWallpaperTheme(9, 1, null, null, null));
		//最新图片
		model.addAttribute("newWallpaperThemes",wallpaperThemeService.getNewWallpaperTheme(1,9));
		//最新壁纸
		model.addAttribute("TopWallpaperThemes", wallpaperThemeService.getTopWallpaperTheme(8));
		return "home";
	}
	
	
}

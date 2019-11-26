package com.accp.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.accp.pojo.Wallpaper;
import com.accp.pojo.WallpaperTheme;
import com.accp.service.WallpaperNavigationBarService;
import com.accp.service.WallpaperService;
import com.accp.service.WallpaperThemeService;
import com.accp.service.WallpaperTypeService;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

/**
 * 壁纸主页控制器
 * @author Y2项目:李旭强
 *
 */
@Controller
public class WallpaperController {
	@Autowired
	private WallpaperService wallpaperService;
	@Autowired
	private WallpaperNavigationBarService wallpaperNavigationBarService;
	private Integer wallpaperNavigationBarId = 1;
	@Autowired
	private WallpaperThemeService wallpaperThemeService;
	private Integer topNum=6;
	private Integer topDate=12;
	private Integer newNum=13;
	@Autowired
	private WallpaperTypeService wallpaperTypeService;
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("wallpaper")
	public String wallpaper(Model model) {
		//导航栏壁纸
		model.addAttribute("wallpaperNavigationBar",wallpaperNavigationBarService.getWallpaperNavigationBarMapperById(wallpaperNavigationBarId));
		//轮播壁纸
		model.addAttribute("topWallpaperThemes", wallpaperThemeService.getTopWallpaperTheme(topNum));
		//壁纸分类
		model.addAttribute("wallpaperTypes", wallpaperTypeService.getAllWallpaperType());
		//日期选择
		model.addAttribute("wallpaperThemeDates", wallpaperThemeService.getWallpaperThemeTopDate(topDate));
		//全部壁纸
		model.addAttribute("pageInfo",wallpaperThemeService.getAllWallpaperTheme(15, 1, null, null,null));
		//最新壁纸推荐
		model.addAttribute("newWallpaperThemes",wallpaperThemeService.getNewWallpaperTheme(topNum,newNum));
		return "wallpaper";
	}
	
	@GetMapping("wallpaperSeach")
	public String wallpaperSeach(Model model) {
		model.addAttribute("wallpaperTypes",wallpaperTypeService.getAllWallpaperType());
		model.addAttribute("pageInfo",wallpaperThemeService.getAllWallpaperTheme(16, 1, null, null,null));
		return "wallpaperSeach";
	}
	
	@GetMapping("wallpaperSearch02")
	@ResponseBody
	public PageInfo<WallpaperTheme> wallpaperThemeSearch02(Integer pageNum,String theme,Integer typeId){
		return wallpaperThemeService.getAllWallpaperTheme(16, pageNum, theme, typeId,null);
	}
	
	@GetMapping("wallpaperThemeSearch")
	@ResponseBody
	public PageInfo<WallpaperTheme> wallpaperThemeSearch(Integer pageSize,Integer pageNum,@DateTimeFormat(pattern="yyyy-MM-dd")Date date,Integer typeId){
		return wallpaperThemeService.getAllWallpaperTheme(pageSize, pageNum, null, typeId,date);
	}
	
	@GetMapping("wallpaperVisit")
	public String wallpaperVisit(Integer themeId,@RequestParam(required=false,defaultValue="1")Integer pageNum,Model model) {
		themeId=WebTools.memory(themeId, "WallpaperSelect_wallpaperThemeId", null);
		if(themeId == null) {
			return "redirect:wallpaper";
		}
		PageInfo<Wallpaper> pageInfo=wallpaperService.getWallpaperByWallpaperTheme(pageNum, 1, themeId);
		model.addAttribute("pageInfo",pageInfo);
		WebTools.setSession("WallpaperSelect_wallpaperThemeId", themeId);
		return "wallpaperVisit";
	}

	//文件下载
	@GetMapping("download")
	public HttpEntity<byte[]> download(String downloadName,HttpServletRequest request) throws IOException{
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/wallpaperImg");
		String absPath = Common.UPLOAD_FILE+"img/wallpaperImg";
		File file= new File(absPath, downloadName);
		byte[] body=FileUtils.readFileToByteArray(file);
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentDispositionFormData("attachment", downloadName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
	}
	
	@GetMapping("wallpaperSelect")
	public String wallpaperSelect(Integer wallpaperThemeId,Integer pageNum,
									@RequestParam(required=false,defaultValue="8")Integer pageSize,
									Model model) {
		wallpaperThemeId=WebTools.memory(wallpaperThemeId, "WallpaperSelect_wallpaperThemeId", null);
		if(wallpaperThemeId == null) {
			return "redirect:wallpaperTheme";
		}
		pageNum=WebTools.memory(pageNum, "wallpaperSelect_pageNum", 1);
		
		PageInfo<Wallpaper> wallpapers=wallpaperService.getWallpaperByWallpaperTheme(pageNum,pageSize,wallpaperThemeId);
		model.addAttribute("pageInfo", wallpapers);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(wallpapers.getPageNum(),wallpapers.getPages());
		model.addAttribute("WallpaperSelect_pagingButton", pagingButton);
		WebTools.setSession("WallpaperSelect_wallpaperThemeId", wallpaperThemeId);
		WebTools.setSession("wallpaperSelect_pageNum", pageNum);
		WebTools.sessionToRequest(Common.MSG);
		return "wallpaperSelect";
	}
	
	@GetMapping("wallpaperAdd")
	public String wallpaperUpdate(@RequestParam(required=true)Integer wallpaperThemeId) {
		Wallpaper wallpaper=new Wallpaper();
		wallpaper.setWallpaper("");
		WallpaperTheme wallpaperTheme=new WallpaperTheme();
		wallpaperTheme.setId(wallpaperThemeId);
		wallpaper.setWallpaperTheme(wallpaperTheme);
		wallpaperService.addWallpaper(wallpaper);
		
		PageInfo<Wallpaper> wallpapers=wallpaperService.getWallpaperByWallpaperTheme(1,8,wallpaperThemeId);
		WebTools.setSession("wallpaperSelect_pageNum", wallpapers.getPages());
		return "redirect:wallpaperSelect";
	}
	
	@GetMapping("wallpaperDelete")
	public String wallpaperDelete(@RequestParam Integer id) {
		boolean isOk=wallpaperService.deleteWallpaperById(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG,isOk?
						messageSource.getMessage("WallpaperController.wallpaperDelete.Success", null, "删除成功！", locale) :
						messageSource.getMessage("WallpaperController.wallpaperDelete.Fail", null, "删除失败，请稍后再试！", locale));
		return "redirect:wallpaperSelect";
	}
	
	@PostMapping("fileUploadWallpaper")
	@ResponseBody
	public String fileUploadWallpaper(MultipartFile file,Integer id, HttpServletRequest request) throws IllegalStateException, IOException {
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/wallpaperImg");
		String absPath = Common.UPLOAD_FILE+"img/wallpaperImg";
		//随机生成文件名加原文件后缀
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		//上传文件
		file.transferTo(new File(absPath,pictureName+suffix));
		
		Wallpaper wallpaper=new Wallpaper();
		wallpaper.setId(id);
		wallpaper.setWallpaper(pictureName+suffix);
		boolean isOk=wallpaperService.updateWallpaper(wallpaper);
		return "{\"isOk\":"+isOk+"}";
	}
	
}

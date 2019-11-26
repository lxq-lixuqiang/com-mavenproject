package com.accp.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.accp.service.WallpaperNavigationBarService;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;


@Controller
public class WallpaperNavigationBarController {
	@Autowired
	private WallpaperNavigationBarService wallpaperNavigationBarService;

	@GetMapping("wallpaperNavigationBar")
	public String wallpaperNavigationBar(Model model) {
		model.addAttribute("wallpaperNavigationBar",wallpaperNavigationBarService.getWallpaperNavigationBarMapperById(1));
		WebTools.setSession(Common.DATA_MANAGEMENT,"wallpaperNavigationBar");
		return "wallpaperNavigationBar";
	}
	
	@PostMapping("wallpaperNavigationBarFileUpload")
	@ResponseBody
	public String wallpaperNavigationBarFileUpload(MultipartFile file,Integer id, HttpServletRequest request) throws IllegalStateException, IOException {
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/wallpaperTop");
		String absPath = Common.UPLOAD_FILE+"img/wallpaperTop";
		//随机生成文件名加原文件后缀
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		//上传文件
		file.transferTo(new File(absPath,pictureName+suffix));
		boolean isOk=wallpaperNavigationBarService.updateWallpaperNavigationBar(id,pictureName+suffix);
		return "{\"isOk\":"+isOk+"}";
	}
}

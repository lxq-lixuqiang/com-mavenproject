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

import com.accp.service.LoginAndRegisterService;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;

@Controller
public class CommonLoginAndRegisterController {
	@Autowired
	private LoginAndRegisterService loginAndRegisterService;

	@GetMapping("commonLoginAndRegister")
	public String commonLoginAndRegister(Model model) {
		model.addAttribute("loginAndRegister", loginAndRegisterService.getAllLoginAndRegister());
		WebTools.setSession(Common.DATA_MANAGEMENT, "commonLoginAndRegister");
		return "commonLoginAndRegister";
	}
	
	@PostMapping("commonLoginAndRegisterFileUpload")
	@ResponseBody
	public String commonLoginAndRegisterFileUpload(MultipartFile file,Integer id, HttpServletRequest request) throws IllegalStateException, IOException {
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/loginAndRegister");
		String absPath = Common.UPLOAD_FILE+"img/loginAndRegister";
		//随机生成文件名加原文件后缀
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		//上传文件
		file.transferTo(new File(absPath,pictureName+suffix));
		boolean isOk=loginAndRegisterService.updateLoginAndRegisterById(id,pictureName+suffix);
		return "{\"isOk\":"+isOk+"}";
	}
}

package com.accp.web.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.accp.pojo.User;
import com.accp.service.UserService;
import com.accp.web.formbean.UserFormBean;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;


/**
 * 管理员和用户个人信息控制器
 * @author Y2项目:李旭强
 *
 */
@Controller
public class AdministrationUserController {

	@Autowired
	private UserService userService;
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("administrationUser")
	public String AdministrationUser() {
		return "administrationUser";
	}
	
	@GetMapping("perfectUserInfo")
	public String perfectUserInfo(UserFormBean userBean) {
		User user=WebTools.getSession(Common.LOGIN_USER);
		if(user.getBirthData() != null) {
			user.setBirthData(new java.sql.Date(user.getBirthData().getTime()));
		}
		WebTools.setSession("user", user);
		return "perfectUserInfo";
	}
	
	@PostMapping("perfectUserInfo")
	public String perfectUserInfo(UserFormBean userBean,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(file.getSize()>0) {
			//存储位置
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/userImages");
			String absPath = Common.UPLOAD_FILE+"img/userImages";
			//随机生成文件名加原文件后缀
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			//上传文件
			file.transferTo(new File(absPath,pictureName+suffix));
			userBean.setUserImg(pictureName+suffix);
		}
		
		User user=new User();
		user.setAddress(userBean.getAddress());
		user.setBirthData(userBean.getBirthData());
		user.setEmail(userBean.getEmail());
		user.setHobby(userBean.getHobby());
		user.setId(userBean.getId());
		user.setPassword(userBean.getPassword());
		user.setPhone(userBean.getPhone());
		user.setSex(userBean.getSex());
		user.setUserImg(userBean.getUserImg());
		user.setLoginIdentity(userBean.getLoginIdentity());
		user.setUsername(userBean.getUsername());
		
		boolean isOk=userService.updateUserAllInfo(user);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG,messageSource.getMessage("AdministrationUserController.perfectUserInfo.Fail", null, "保存失败，请稍后再试！", locale));
			return "perfectUserInfo";
		}
		User userInfo=userService.getUserByname(userBean.getUsername());
		WebTools.removeSession("user");
		WebTools.setSession(Common.LOGIN_USER, userInfo);
		return "redirect:administrationUser";
	}

}

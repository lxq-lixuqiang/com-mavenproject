package com.accp.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.accp.pojo.User;
import com.accp.pojo.User.LoginIdentity;
import com.accp.service.LoginAndRegisterService;
import com.accp.service.UserService;
import com.accp.web.formbean.LoginUserFormBean;
import com.accp.web.formbean.LoginUserFormBean.GroupSequenceLoginUser;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;

/**
 * 登录控制器
 * @author Y2项目:李旭强
 *
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private LoginAndRegisterService loginAndRegisterService;
	@Autowired
	private MessageSource messageSource;
	
	//导航到登录页面
	@GetMapping("login")
	public String login(LoginUserFormBean loginUserBean,Model model) {
		WebTools.setSession("loginAndRegister", loginAndRegisterService.getLoginAndRegisterById(0));
		WebTools.sessionToRequest("success");
		return "login";
	}
	
	//验证登录用户，重定向到首页页面
	@PostMapping("login")
	public String login(@Validated(GroupSequenceLoginUser.class) LoginUserFormBean loginUserBean,Errors errors,Model model,HttpSession session) {
		if(errors.hasErrors()) {
			return "login";
		}
		User user=userService.getUserByname(loginUserBean.getUsername());
		Locale locale=LocaleContextHolder.getLocale();
		if(user == null) {
			model.addAttribute(Common.MSG,messageSource.getMessage("LoginController.login.username.exists", null,"用户名不存在！",locale));
			return "login";
		}else if(!user.getPassword().equals(loginUserBean.getPassword())){
			model.addAttribute(Common.MSG,messageSource.getMessage("LoginController.login.password.error", null,"密码错误！",locale));
			return "login";
		}
		if(user.getLoginIdentity()==LoginIdentity.Administrators) {
			WebTools.setSession(Common.DATA_MANAGEMENT,"dataManagement");
		}
		WebTools.setSession(Common.LOGIN_USER, user);
		WebTools.removeSession("loginAndRegister");
		
		return "redirect:administrationUser";
	}
	
	@PostMapping("findUserImg")
	public void findUserImg(String username,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String userImg=userService.getUserImg(username);
		String path="assets/pages/img/userImages/";
		try(PrintWriter out=response.getWriter();){
			if(userImg == null) {
				path+="default.jpg";
			}else {
				path+=userImg;
			}
			out.print("{\"userImg\":\""+path+"\"}");
		}
	}
}

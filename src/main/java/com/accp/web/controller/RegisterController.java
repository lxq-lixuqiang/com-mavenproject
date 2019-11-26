package com.accp.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

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
import com.accp.pojo.User.Sex;
import com.accp.pojo.UserHeadPicture;
import com.accp.service.LoginAndRegisterService;
import com.accp.service.UserService;
import com.accp.web.formbean.UserFormBean;
import com.accp.web.formbean.UserFormBean.GroupSequenceRegisterUser;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;

/**
 * 注册控制器
 * @author Y2项目:李旭强
 *
 */
@Controller
public class RegisterController {

	@Autowired
	private UserService userService;
	@Autowired
	private LoginAndRegisterService loginAndRegisterService;
	@Autowired
	private MessageSource messageSource;
	
	//导航到注册页面
	@GetMapping("register")
	public String register(UserFormBean user,Model model) {
		WebTools.setSession("loginAndRegister", loginAndRegisterService.getLoginAndRegisterById(2));
		return "register";
	}
	
	//注册用户操作，重定向到登录页面
	@PostMapping("register")
	public String register(@Validated(GroupSequenceRegisterUser.class) UserFormBean registerUser,Errors errors,Model model) throws IOException{
		if(errors.hasErrors()) {
			return "register";
		}
		registerUser.setUserImg("default.jpg");
		registerUser.setLoginIdentity(LoginIdentity.User);
		registerUser.setSex(Sex.Secrecyt);
		
		User user=new User();
		user.setUsername(registerUser.getUsername());
		user.setAddress(registerUser.getAddress());
		user.setBirthData(registerUser.getBirthData());
		user.setEmail(registerUser.getEmail());
		user.setHobby(registerUser.getHobby());
		user.setId(registerUser.getId());
		user.setLoginIdentity(registerUser.getLoginIdentity());
		user.setPassword(registerUser.getPassword());
		user.setPhone(registerUser.getPhone());
		user.setSex(registerUser.getSex());
		user.setUserImg(registerUser.getUserImg());
		UserHeadPicture userHeadPicture=new UserHeadPicture();
		userHeadPicture.setId(1);
		user.setUserHeadPicture(userHeadPicture);
		
		boolean isOk=userService.addUser(user);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG,messageSource.getMessage("RegisterController.register.Fail", null, "注册失败，请稍后再试！", locale));
			return "register";
		}
		WebTools.setSession("success", messageSource.getMessage("RegisterController.register.Success", null, "注册成功！", locale));
		WebTools.removeSession("loginAndRegister");
		return "redirect:login";
	}
	
	//ajax查询是否用户名重复
	@PostMapping("findUsername")
	public void findUsername(String username,HttpServletResponse response) throws IOException {
		boolean isOk=userService.getUserNameIsNo(username);
		try(PrintWriter out=response.getWriter();){
			out.print("{\"isOk\":"+isOk+"}");
		}
	}
	
	//ajax查询是否手机号码重复
	@PostMapping("findPhone")
	public void findPhone(String phone,HttpServletResponse response) throws IOException {
		boolean isOk=userService.getPhoneIsNo(phone);
		try(PrintWriter out=response.getWriter();){
			out.print("{\"isOk\":"+isOk+"}");
		}
	}
}

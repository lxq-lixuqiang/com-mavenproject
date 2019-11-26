package com.accp.web.controller;

import java.util.Locale;

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
import com.accp.service.LoginAndRegisterService;
import com.accp.service.UserService;
import com.accp.web.formbean.ForgetPasswordFormBean;
import com.accp.web.formbean.ForgetPasswordFormBean.groupSerquenceForgetPassword;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;

/**
 * 忘记密码控制器
 * @author Y2项目:李旭强
 *
 */
@Controller
public class ForgetPasswordController {
	@Autowired
	private UserService userService;
	@Autowired
	private LoginAndRegisterService loginAndRegisterService;
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("forgetPassword")
	public String forgetPassword(ForgetPasswordFormBean forgetPassword,Model model) {
		WebTools.setSession("loginAndRegister", loginAndRegisterService.getLoginAndRegisterById(1));
		return "forgetPassword";
	}
	
	@PostMapping("forgetPassword")
	public String forgetPassword(@Validated(groupSerquenceForgetPassword.class) ForgetPasswordFormBean forgetPasswordBean,Errors errors,Model model) {
		if(errors.hasErrors()) {
			return "forgetPassword";
		}
		
		User user=new User();
		user.setId(forgetPasswordBean.getId());
		user.setPhone(forgetPasswordBean.getPhone());
		user.setPassword(forgetPasswordBean.getNewpassword());
		
		boolean isOk=userService.updatePassword(user);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("ForgetPasswordController.forgetPassword.Fail", null, "修改失败，请稍后再试！", locale));
			return "forgetPassword";
		}
		WebTools.setSession("success", messageSource.getMessage("ForgetPasswordController.forgetPassword.Success", null, "修改成功！", locale));
		WebTools.removeSession("loginAndRegister");
		return "redirect:login";
	}
}

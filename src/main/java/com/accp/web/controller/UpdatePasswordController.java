package com.accp.web.controller;

import java.util.Locale;

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
import com.accp.service.UserService;
import com.accp.web.formbean.ForgetPasswordFormBean;
import com.accp.web.formbean.UpdatePasswordFormBean;
import com.accp.web.formbean.UpdatePasswordFormBean.updatePasswordGroupSequence;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;

/**
 * 更改密码控制器
 * @author Y2项目:李旭强
 *
 */
@Controller
public class UpdatePasswordController {

	@Autowired
	private UserService userService;
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("updatePassword")
	public String updatePassword(UpdatePasswordFormBean updatePasswordBean) {
		return "updatePassword";
	}
	
	@PostMapping("updatePassword")
	public String updatePassword(@Validated(updatePasswordGroupSequence.class) UpdatePasswordFormBean updatePasswordBean,Errors errors,HttpSession session,Model model) {
		if(errors.hasErrors()) {
			return "updatePassword";
		}
		User userBean=WebTools.getSession(Common.LOGIN_USER);
		Locale locale=LocaleContextHolder.getLocale();
		if(!userBean.getPassword().equals(updatePasswordBean.getOldPassword())) {
			model.addAttribute(Common.MSG, messageSource.getMessage("UpdatePasswordController.updatePassword.password.error", null, "原密码错误！", locale));
			return "updatePassword";
		}
		ForgetPasswordFormBean forgetPassword=new ForgetPasswordFormBean();
		forgetPassword.setPhone(userBean.getPhone());
		forgetPassword.setNewpassword(updatePasswordBean.getNewPassword());
		
		User user=new User();
		user.setId(forgetPassword.getId());
		user.setPassword(forgetPassword.getNewpassword());
		user.setPhone(forgetPassword.getPhone());

		boolean isOk=userService.updatePassword(user);
		if(!isOk) {
			model.addAttribute(Common.MSG,messageSource.getMessage("UpdatePasswordController.updatePassword.Fail", null, "修改失败，请稍后再试！", locale));
		}
		model.addAttribute("success",messageSource.getMessage("UpdatePasswordController.updatePassword.Success", null, "修改成功，是否重新登录！", locale));
		return "updatePassword";
	}
}

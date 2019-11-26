package com.accp.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 退出登录控制器
 * @author Y2项目:李旭强
 *
 */
@Controller
public class LogOutController {
	
	@GetMapping("logOut")
	public String loginOut(HttpSession session) {
		session.invalidate();
		return "redirect:home";
	}
	
	@GetMapping("logInAgain")
	public String logInAgain(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
}

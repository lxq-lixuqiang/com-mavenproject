package com.accp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * 自动跳转页面
 * @author Y2项目:李旭强
 *
 */
@Controller
public class JumpPageController {

	@GetMapping("jumpPage")
	public String jumpPage() {
		return "jumpPage";
	}
}

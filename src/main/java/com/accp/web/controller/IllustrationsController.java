package com.accp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.accp.service.IllustrationsService;

/**
 * 画展控制器
 * @author Y2项目:李旭强
 *
 */
@Controller
public class IllustrationsController {
	@Autowired
	private IllustrationsService illustrationsService;

	@GetMapping("illustrations")
	public String illustrations(Model model) {
		model.addAttribute("illustrations", illustrationsService.getAllIllustrations());
		return "illustrations";
	}
}

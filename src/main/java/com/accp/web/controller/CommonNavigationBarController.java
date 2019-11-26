package com.accp.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.pojo.NavigationBar;
import com.accp.service.NavigationBarService;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;

@Controller
public class CommonNavigationBarController {
	@Autowired
	private NavigationBarService navigationBarService;

	@GetMapping("commonNavigationBar")
	public String commonNavigationBar(Model model) {
		model.addAttribute(Common.NAVIGATIONBARS, navigationBarService.getAllNavigationBar());
		WebTools.setSession(Common.DATA_MANAGEMENT,"commonNavigationBar");
		return "commonNavigationBar";
	}
	
	@GetMapping("updateNavigationBar")
	@ResponseBody
	public String updateNavigationBar(Integer oldId,String name,String path,Integer newId) {
		NavigationBar navigationBar=new NavigationBar();
		navigationBar.setId(newId);
		navigationBar.setName(name);
		navigationBar.setPath(path);
		boolean isOk=navigationBarService.updateNavigationBar(navigationBar,oldId);
		if(isOk) {
			WebTools.setSession(Common.NAVIGATIONBARS, null);
		}
		return "{\"isOk\":"+isOk+"}";
	}
	
	@GetMapping("isExistsId")
	@ResponseBody
	public String isExistsId(Integer nowId,Integer findId) {
		boolean isOk=navigationBarService.isExistsId(nowId,findId);
		return "{\"isOk\":"+isOk+"}";
	}
}

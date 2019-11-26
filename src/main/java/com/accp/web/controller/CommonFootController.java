package com.accp.web.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.pojo.Foot;
import com.accp.service.FootService;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class CommonFootController {
	@Autowired
	private FootService footService;
	@Autowired
	private MessageSource messageSource;
	private Integer size=5;
	private Integer num=1;

	@GetMapping("commonFoot")
	public String commonFoot(Integer pageSize,Integer pageNum,String name,Model model) {
		pageSize=WebTools.memory(pageSize,"CommonFoot_pageSize",size);
		pageNum=WebTools.memory(pageNum,"CommonFoot_pageNum",num);
		name=WebTools.memory(name,"CommonFoot_name",null);
		
		PageInfo<Foot> foots=footService.getFootByName(pageSize,pageNum,name);
		model.addAttribute("pageInfo", foots);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(foots.getPageNum(),foots.getPages());
		model.addAttribute("CommonFoot_pagingButton", pagingButton);
		
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession(Common.DATA_MANAGEMENT,"commonFoot");
		WebTools.setSession("CommonFoot_pageSize", pageSize);
		WebTools.setSession("CommonFoot_pageNum", pageNum);
		WebTools.setSession("CommonFoot_name", name);
		return "commonFoot";
	}
	
	@GetMapping("updateFoot")
	@ResponseBody
	public String updateFoot(Integer id,String name,String path) {
		Foot foot=new Foot();
		foot.setId(id);
		foot.setName(name);
		foot.setPath(path);
		boolean isOk=footService.updateFoot(foot);
		WebTools.setSession(Common.FOOTS, null);
		return "{\"isOk\":"+isOk+"}";
	}
	
	@GetMapping("addFoot")
	public String addFoot() {
		Foot foot=new Foot();
		foot.setName("请输入名称");
		foot.setPath("请输入路径");
		footService.addFoot(foot);
		PageInfo<Foot> foots=footService.getFootByName(size,num,null);
		WebTools.setSession("CommonFoot_pageNum", foots.getPages());
		WebTools.setSession("CommonFoot_name", null);
		WebTools.setSession(Common.FOOTS, null);
		return "redirect:commonFoot";
	}
	
	@GetMapping("deleteFoot")
	public String deleteFoot(Integer id) {
		boolean isOk=footService.deleteFoot(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
					messageSource.getMessage("CommonFootController.deleteFoot.Success", null, "删除成功！", locale):
					messageSource.getMessage("CommonFootController.deleteFoot.Fail", null, "删除失败，请稍后再试！", locale));
		WebTools.setSession(Common.FOOTS, null);
		return "redirect:commonFoot";
	}
	
}

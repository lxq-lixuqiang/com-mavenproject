package com.accp.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.accp.pojo.Animation;
import com.accp.pojo.AnimationInfo;
import com.accp.service.AnimationService;
import com.accp.web.formbean.AnimationFormBean;
import com.accp.web.formbean.AnimationFormBean.AnimationFormBeanGroupSequence;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class AnimationSelectController {
	@Autowired
	private AnimationService animationService;
	@Autowired
	private MessageSource messageSource;

	@GetMapping("animationSelect")
	public String animationSelect(Integer animationInfoId,
									@RequestParam(defaultValue="8",required=false)Integer pageSize,
									@RequestParam(defaultValue="1",required=false)Integer pageNum,
									String setName,Model model) {
		animationInfoId=WebTools.memory(animationInfoId, "animationSelect_animationInfoId",null);
		if(animationInfoId == null) {
			return "animationInfo";
		}
		PageInfo<Animation> animations=animationService.getAnimationBySetName(pageNum,pageSize,animationInfoId,setName);
		model.addAttribute("pageInfo", animations);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(animations.getPageNum(),animations.getPages());
		model.addAttribute("animationSelect_pagingButton", pagingButton);
		
		WebTools.sessionToRequest(Common.MSG);
		model.addAttribute("animationSelect_setName", setName);
		WebTools.setSession("animationSelect_animationInfoId", animationInfoId);
		return "animationSelect";
	}
	
	@GetMapping("animationAdd")
	public String animationAdd(AnimationFormBean animationFormBean) {
		Integer animationInfoId=WebTools.memory(null, "animationSelect_animationInfoId",null);
		if(animationInfoId == null) {
			return "animationInfo";
		}
		return "animationAdd";
	}
	
	@PostMapping("animationAdd")
	public String animationAdd(@Validated(AnimationFormBeanGroupSequence.class)AnimationFormBean animationFormBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "animationAdd";
		}
		if(file.getSize() > 0) {
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"video/animation");
			String absPath = Common.UPLOAD_FILE+"video/animation";
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			file.transferTo(new File(absPath,pictureName+suffix));
			animationFormBean.setAnimation(pictureName+suffix);
		}
		Animation animation=new Animation();
		animation.setAnimation(animationFormBean.getAnimation());
		animation.setAnimationPath(animationFormBean.getAnimationPath());
		animation.setSetName(animationFormBean.getSetName());
		
		AnimationInfo animationInfo=new AnimationInfo();
		animationInfo.setId(animationFormBean.getAnimationInfoId().getId());
		animation.setAnimationInfoId(animationInfo);
		
		boolean isOk=animationService.animationAdd(animation);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("AnimationSelectConroller.animationAdd.Fail", null, "保存失败，请稍后再试！", locale));
			return "animationAdd";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("AnimationSelectConroller.animationAdd.Success", null, "保存成功！", locale));
		return "redirect:animationSelect";
	}
	
	@GetMapping("animationDelete")
	public String animationDelete(@RequestParam Integer id) {
		boolean isOk=animationService.animationDelete(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk? 
							messageSource.getMessage("AnimationSelectConroller.animationDelete.Success", null, "修改成功！", locale) :
								messageSource.getMessage("AnimationSelectConroller.animationDelete.Fail", null, "修改失败，请稍后再试！", locale));
		return "redirect:animationSelect";
	}
	
	@GetMapping("animationUpdate")
	public String animationUpdate(@RequestParam Integer id,AnimationFormBean animationFormBean,Model model) {
		Integer animationInfoId=WebTools.memory(null, "animationSelect_animationInfoId",null);
		if(animationInfoId == null) {
			return "animationInfo";
		}
		model.addAttribute("animationFormBean", animationService.getAnimationById(id));
		return "animationUpdate";
	}
	
	@PostMapping("animationUpdate")
	public String animationUpdate(@Validated(AnimationFormBeanGroupSequence.class)AnimationFormBean animationFormBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "animationUpdate";
		}
		if(file.getSize() > 0) {
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"video/animation");
			String absPath = Common.UPLOAD_FILE+"video/animation";
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			file.transferTo(new File(absPath,pictureName+suffix));
			animationFormBean.setAnimation(pictureName+suffix);
		}
		Animation animation=new Animation();
		animation.setId(animationFormBean.getId());
		animation.setAnimation(animationFormBean.getAnimation());
		animation.setAnimationPath(animationFormBean.getAnimationPath());
		animation.setSetName(animationFormBean.getSetName());
		
		AnimationInfo animationInfo=new AnimationInfo();
		animationInfo.setId(animationFormBean.getAnimationInfoId().getId());
		animation.setAnimationInfoId(animationInfo);
		
		boolean isOk=animationService.animationUpdate(animation);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("AnimationSelectConroller.animationUpdate.Fail", null, "修改失败，请稍后再试！", locale));
			return "animationAdd";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("AnimationSelectConroller.animationUpdate.Success", null, "修改成功！", locale));
		return "redirect:animationSelect";
	}
	
}

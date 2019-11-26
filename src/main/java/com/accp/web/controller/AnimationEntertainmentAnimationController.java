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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.accp.pojo.AnimationEntertainmentAnimation;
import com.accp.pojo.AnimationEntertainmentType;
import com.accp.service.AnimationEntertainmentAnimationService;
import com.accp.service.AnimationEntertainmentTypeService;
import com.accp.web.formbean.AnimationEntertainmentAnimationFormBean;
import com.accp.web.formbean.AnimationEntertainmentAnimationFormBean.AnimationEntertainmentAnimationFormBeanGroupSequence;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class AnimationEntertainmentAnimationController {
	@Autowired
	private AnimationEntertainmentAnimationService animationEntertainmentAnimationService;
	private Integer size=8;
	private Integer num=1;
	@Autowired
	private AnimationEntertainmentTypeService animationEntertainmentTypeService;
	@Autowired
	private MessageSource messageSource;

	@GetMapping("animationEntertainmentAnimation")
	public String animationEntertainmentAnimation(Integer pageSize,Integer pageNum,String name,Integer entertainmentTypeId,Model model) {
		pageSize=WebTools.memory(pageSize, "animationEntertainmentAnimation_pageSize", size);
		pageNum=WebTools.memory(pageNum, "animationEntertainmentAnimation_pageNum", num);
		name=WebTools.memory(name, "animationEntertainmentAnimation_name", null);
		entertainmentTypeId=WebTools.memory(entertainmentTypeId, "animationEntertainmentAnimation_entertainmentTypeId", null);
		PageInfo<AnimationEntertainmentAnimation> animationEntertainmentAnimations=animationEntertainmentAnimationService.getEntertainmentAnimationByNameAndEntertainmentTypeId(pageNum,pageSize,name,entertainmentTypeId);
		model.addAttribute("pageInfo", animationEntertainmentAnimations);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(animationEntertainmentAnimations.getPageNum(),animationEntertainmentAnimations.getPages());
		model.addAttribute("animationEntertainmentAnimation_pagingButton", pagingButton);
		//类型
		model.addAttribute("animationEntertainmentAnimation_animationEntertainmentTypes", animationEntertainmentTypeService.getAll());
		
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession(Common.DATA_MANAGEMENT,"animationEntertainmentAnimation");
		WebTools.setSession("animationEntertainmentAnimation_pageSize", pageSize);
		WebTools.setSession("animationEntertainmentAnimation_pageNum", pageNum);
		WebTools.setSession("animationEntertainmentAnimation_name", name);
		WebTools.setSession("animationEntertainmentAnimation_entertainmentTypeId", entertainmentTypeId);
		return "animationEntertainmentAnimation";
	}
	
	@GetMapping("animationEntertainmentAnimationSeach")
	public String animationEntertainmentAnimationSeach(Model model) {
		model.addAttribute("animationEntertainmentTypes",animationEntertainmentTypeService.getAll());
		model.addAttribute("pageInfo",animationEntertainmentAnimationService.getEntertainmentAnimationByNameAndEntertainmentTypeId(1, 20, null, null));
		return "animationEntertainmentAnimationSeach";
	}
	
	@GetMapping("animationEntertainmentAnimationSeach02")
	@ResponseBody
	public PageInfo<AnimationEntertainmentAnimation> animationEntertainmentAnimationSeach02(Integer pageNum,String name,Integer entertainmentTypeName){
		return animationEntertainmentAnimationService.getEntertainmentAnimationByNameAndEntertainmentTypeId(pageNum, 20, name, entertainmentTypeName);
	}
	
	@GetMapping("animationEntertainmentAnimationVisit")
	public String animationEntertainmentAnimationVisit(@RequestParam Integer id,Model model) {
		model.addAttribute("animationEntertainmentAnimation", animationEntertainmentAnimationService.getAnimationEntertainmentAnimationById(id));
		return "animationEntertainmentAnimationVisit";
	}
	
	@GetMapping("animationEntertainmentAnimationAdd")
	public String animationEntertainmentAnimationAdd(AnimationEntertainmentAnimationFormBean animationEntertainmentAnimationFormBean) {
		//类型
		List<AnimationEntertainmentType> animationEntertainmentTypes=animationEntertainmentTypeService.getAll();
		AnimationEntertainmentType animationEntertainmentType = new AnimationEntertainmentType();
		animationEntertainmentType.setTypeName("请选择");
		animationEntertainmentType.setId(null);
		animationEntertainmentTypes.add(0,animationEntertainmentType);
		WebTools.setSession("animationEntertainmentTypes",animationEntertainmentTypes);
		return "animationEntertainmentAnimationAdd";
	}
	
	@PostMapping("animationEntertainmentAnimationAdd")
	public String animationEntertainmentAnimationAdd(@Validated(AnimationEntertainmentAnimationFormBeanGroupSequence.class)AnimationEntertainmentAnimationFormBean animationEntertainmentAnimationFormBean,Errors errors,MultipartFile file,MultipartFile file2,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "animationEntertainmentAnimationAdd";
		}
		if(animationEntertainmentAnimationFormBean.getEntertainmentTypeId().getId() == null) {
			errors.rejectValue("entertainmentTypeId","AnimationEntertainmentAnimationController.entertainmentTypeId.NotBlank","请选择类型！");
			return "animationEntertainmentAnimationAdd";
		}
		//娱乐动漫封面
		if(file.getSize() == 0) {
			errors.rejectValue("picture","AnimationEntertainmentAnimationController.picture.NotBlank","请选择封面！");
			return "animationEntertainmentAnimationAdd";
		}
		//娱乐动漫视频
		if(file2.getSize() > 0) {
			//String absPath2 = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"video/animationEntertainmentAnimation");
			String absPath2 = Common.UPLOAD_FILE+"video/animationEntertainmentAnimation";
			String suffix2=file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf("."));
			String pictureName2=UUID.randomUUID().toString();
			file2.transferTo(new File(absPath2,pictureName2+suffix2));
			animationEntertainmentAnimationFormBean.setEntertainmentAnimation(pictureName2+suffix2);
		}
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/animationEntertainmentAnimationImg");
		String absPath = Common.UPLOAD_FILE+"img/animationEntertainmentAnimationImg";
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		file.transferTo(new File(absPath,pictureName+suffix));
		animationEntertainmentAnimationFormBean.setPicture(pictureName+suffix);
		
		AnimationEntertainmentAnimation animationEntertainmentAnimation = new AnimationEntertainmentAnimation();
		animationEntertainmentAnimation.setName(animationEntertainmentAnimationFormBean.getName());
		animationEntertainmentAnimation.setEntertainmentAnimation(animationEntertainmentAnimationFormBean.getEntertainmentAnimation());
		animationEntertainmentAnimation.setEntertainmentAnimationPath(animationEntertainmentAnimationFormBean.getEntertainmentAnimationPath());
		animationEntertainmentAnimation.setPicture(animationEntertainmentAnimationFormBean.getPicture());
		
		AnimationEntertainmentType animationEntertainmentType=new AnimationEntertainmentType();
		animationEntertainmentType.setId(animationEntertainmentAnimationFormBean.getEntertainmentTypeId().getId());
		animationEntertainmentAnimation.setEntertainmentTypeId(animationEntertainmentType);

		boolean isOk=animationEntertainmentAnimationService.animationEntertainmentAnimationAdd(animationEntertainmentAnimation);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("AnimationEntertainmentAnimationController.animationEntertainmentAnimationAdd.Fail", null, "保存失败，请稍后再试！", locale));
			return "animationEntertainmentAnimationAdd";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("AnimationEntertainmentAnimationController.animationEntertainmentAnimationAdd.Success", null, "保存成功！", locale));
		WebTools.removeSession("animationEntertainmentTypes");
		return "redirect:animationEntertainmentAnimation";
	}
	
	@GetMapping("animationEntertainmentAnimationDelete")
	public String animationEntertainmentAnimationDelete(@RequestParam Integer id) {
		boolean isOk=animationEntertainmentAnimationService.animationEntertainmentAnimationDelete(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
							messageSource.getMessage("AnimationEntertainmentAnimationController.animationEntertainmentAnimationDelete.Success", null, "删除成功！", locale) :
								messageSource.getMessage("AnimationEntertainmentAnimationController.animationEntertainmentAnimationDelete.Fail", null, "删除失败，请稍后再试！", locale));
		return "redirect:animationEntertainmentAnimation";
	}
	
	@GetMapping("animationEntertainmentAnimationUpdate")
	public String animationEntertainmentAnimationUpdate(AnimationEntertainmentAnimationFormBean animationEntertainmentAnimationFormBean,@RequestParam Integer id,Model model) {
		//类型
		List<AnimationEntertainmentType> animationEntertainmentTypes=animationEntertainmentTypeService.getAll();
		AnimationEntertainmentType animationEntertainmentType = new AnimationEntertainmentType();
		animationEntertainmentType.setTypeName("请选择");
		animationEntertainmentType.setId(null);
		animationEntertainmentTypes.add(0,animationEntertainmentType);
		WebTools.setSession("animationEntertainmentTypes",animationEntertainmentTypes);
		
		AnimationEntertainmentAnimation aniimationEntertainmentAnimation=animationEntertainmentAnimationService.getAnimationEntertainmentAnimationById(id);
		model.addAttribute("animationEntertainmentAnimationFormBean",aniimationEntertainmentAnimation);
		WebTools.setSession("picture", aniimationEntertainmentAnimation.getPicture());
		return "animationEntertainmentAnimationUpdate";
	}
	
	@PostMapping("animationEntertainmentAnimationUpdate")
	public String animationEntertainmentAnimationUpdate(@Validated(AnimationEntertainmentAnimationFormBeanGroupSequence.class)AnimationEntertainmentAnimationFormBean animationEntertainmentAnimationFormBean,Errors errors,MultipartFile file,MultipartFile file2,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "animationEntertainmentAnimationUpdate";
		}
		if(animationEntertainmentAnimationFormBean.getEntertainmentTypeId().getId() == null) {
			errors.rejectValue("entertainmentTypeId","AnimationEntertainmentAnimationController.entertainmentTypeId.NotBlank","请选择类型！");
			return "animationEntertainmentAnimationUpdate";
		}
		//娱乐动漫封面
		if(file.getSize() > 0) {
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/animationEntertainmentAnimationImg");
			String absPath = Common.UPLOAD_FILE+"img/animationEntertainmentAnimationImg";
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			file.transferTo(new File(absPath,pictureName+suffix));
			animationEntertainmentAnimationFormBean.setPicture(pictureName+suffix);
		}
		//娱乐动漫视频
		if(file2.getSize() > 0) {
			//String absPath2 = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"video/animationEntertainmentAnimation");
			String absPath2 = Common.UPLOAD_FILE+"video/animationEntertainmentAnimation";
			String suffix2=file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf("."));
			String pictureName2=UUID.randomUUID().toString();
			file2.transferTo(new File(absPath2,pictureName2+suffix2));
			animationEntertainmentAnimationFormBean.setEntertainmentAnimation(pictureName2+suffix2);
		}
		AnimationEntertainmentAnimation animationEntertainmentAnimation = new AnimationEntertainmentAnimation();
		animationEntertainmentAnimation.setId(animationEntertainmentAnimationFormBean.getId());
		animationEntertainmentAnimation.setName(animationEntertainmentAnimationFormBean.getName());
		animationEntertainmentAnimation.setEntertainmentAnimation(animationEntertainmentAnimationFormBean.getEntertainmentAnimation());
		animationEntertainmentAnimation.setEntertainmentAnimationPath(animationEntertainmentAnimationFormBean.getEntertainmentAnimationPath());
		animationEntertainmentAnimation.setPicture(animationEntertainmentAnimationFormBean.getPicture());
		
		AnimationEntertainmentType animationEntertainmentType=new AnimationEntertainmentType();
		animationEntertainmentType.setId(animationEntertainmentAnimationFormBean.getEntertainmentTypeId().getId());
		animationEntertainmentAnimation.setEntertainmentTypeId(animationEntertainmentType);

		boolean isOk=animationEntertainmentAnimationService.animationEntertainmentAnimationUpdate(animationEntertainmentAnimation);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("AnimationEntertainmentAnimationController.animationEntertainmentAnimationUpdate.Fail", null, "修改失败，请稍后再试！", locale));
			return "animationEntertainmentAnimationUpdate";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("AnimationEntertainmentAnimationController.animationEntertainmentAnimationUpdate.Success", null, "修改成功！", locale));
		WebTools.removeSession("animationEntertainmentTypes");
		WebTools.removeSession("picture");
		return "redirect:animationEntertainmentAnimation";
	}
}

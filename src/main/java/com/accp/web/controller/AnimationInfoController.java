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

import com.accp.pojo.AnimationClassification;
import com.accp.pojo.AnimationInfo;
import com.accp.pojo.AnimationType;
import com.accp.service.AnimationClassificationService;
import com.accp.service.AnimationInfoService;
import com.accp.service.AnimationService;
import com.accp.service.AnimationTypeService;
import com.accp.web.formbean.AnimationInfoFormBean;
import com.accp.web.formbean.AnimationInfoFormBean.AnimatinInfoFormBeanGroupSequence;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class AnimationInfoController {
	@Autowired
	private AnimationInfoService animationInfoService; 
	private Integer size=9;
	private Integer num=1;
	@Autowired
	private AnimationTypeService animationTypeService;
	@Autowired
	private AnimationClassificationService animationClassificationService;
	@Autowired
	private AnimationService animationService;
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("animationInfo")
	public String animationInfo(Integer pageSize,Integer pageNum,String animationName,String animationType,Model model) {
		pageSize=WebTools.memory(pageSize, "animationInfo_pageSize", size);
		pageNum=WebTools.memory(pageNum, "animationInfo_pageNum", num);
		animationName=WebTools.memory(animationName, "animationInfo_animationName", null);
		animationType=WebTools.memory(animationType, "animationInfo_animationType", null);
		if(animationType !=null && animationType.equals("全部")) {
			animationType=null;
		}
		PageInfo<AnimationInfo> animationInfo=animationInfoService.getAnimationInfoByNameAndType(pageNum,pageSize,null,animationName,animationType,null);
		model.addAttribute("pageInfo", animationInfo);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(animationInfo.getPageNum(),animationInfo.getPages());
		model.addAttribute("animationInfo_pagingButton", pagingButton);
		//类型
		List<AnimationType> animationTypes=animationTypeService.getAll();
		model.addAttribute("animationInfo_animationTypes", animationTypes);
		
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession(Common.DATA_MANAGEMENT,"animationInfo");
		WebTools.setSession("animationInfo_pageSize", pageSize);
		WebTools.setSession("animationInfo_pageNum", pageNum);
		WebTools.setSession("animationInfo_animationName", animationName);
		WebTools.setSession("animationInfo_animationType", animationType);
		return "animationInfo";
	}
	
	@GetMapping("animationInfoFind")
	@ResponseBody
	public PageInfo<AnimationInfo> animationInfoFind(Integer pageNum,String year,String animationType,String animationQuarter){
		if(year != null && year.length() >0) {
			WebTools.setSession("animationInfoFind_animationType", null);
			WebTools.setSession("animationInfoFind_animationQuarter", null);
		}else if(animationType != null && animationType.length() >0) {
			WebTools.setSession("animationInfoFind_year", null);
			WebTools.setSession("animationInfoFind_animationQuarter", null);
		}else if(animationQuarter !=null && animationQuarter.length() >0) {
			WebTools.setSession("animationInfoFind_animationType", null);
			WebTools.setSession("animationInfoFind_year", null);
		}
		year=WebTools.memory(year, "animationInfoFind_year", null);
		animationType=WebTools.memory(animationType, "animationInfoFind_animationType", null);
		animationQuarter=WebTools.memory(animationQuarter, "animationInfoFind_animationQuarter", null);
		if(animationType !=null && animationType.equals("全部")) {
			animationType=null;
		}
		
		WebTools.setSession("animationInfoFind_year", year);
		WebTools.setSession("animationInfoFind_animationType", animationType);
		WebTools.setSession("animationInfoFind_animationQuarter", animationQuarter);
		return animationInfoService.getAnimationInfoByNameAndType(pageNum, 10, year, null, animationType,animationQuarter);
	}
	
	@GetMapping("animationInfoVisit")
	public String animationInfoVisit(@RequestParam Integer animationInfoId,Model model) {
		model.addAttribute("animationInfo", animationInfoService.getAnimationInfoById(animationInfoId));
		model.addAttribute("animations", animationService.getAnimationByAniamtionInfoId(animationInfoId));
		return "animationInfoVisit";
	}
	
	@GetMapping("animationInfoAdd")
	public String animationInfoAdd(AnimationInfoFormBean animationInfoFormBean,Model model) {
		List<AnimationClassification> animationClassifications=animationClassificationService.getAll();
		AnimationClassification animationClassification=new AnimationClassification();
		animationClassification.setId(null);
		animationClassification.setName("请选择");
		animationClassifications.add(0,animationClassification);
		
		WebTools.setSession("animationClassifications", animationClassifications);
		WebTools.setSession("animationTypes", animationTypeService.getAll());
		return "animationInfoAdd";
	}
	
	@PostMapping("animationInfoAdd")
	public String animationInfoAdd(@Validated(AnimatinInfoFormBeanGroupSequence.class)AnimationInfoFormBean animationInfoFormBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "animationInfoAdd";
		}
		if(file.getSize() == 0) {
			errors.rejectValue("animationPicture","AnimationInfoController.animationInfoAdd.NotBlank","请选择图片！");
			return "animationInfoAdd";
		}
		if(animationInfoFormBean.getClassificationId().getId() == null) {
			errors.rejectValue("classificationId","AnimationInfoController.classificationId.NotNull","请选择类型！");
			return "animationInfoAdd";
		}
		
		//存储位置
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/animationInfoImg");
		String absPath = Common.UPLOAD_FILE+"img/animationInfoImg";
		//随机生成文件名加原文件后缀
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		//上传文件
		file.transferTo(new File(absPath,pictureName+suffix));
		animationInfoFormBean.setAnimationPicture(pictureName+suffix);
		animationInfoFormBean.setAnimationType(animationInfoFormBean.getAnimationType().replace(",", " "));
		
		AnimationInfo animationInfo=new AnimationInfo();
		animationInfo.setAnimationContent(animationInfoFormBean.getAnimationContent());
		animationInfo.setAnimationName(animationInfoFormBean.getAnimationName());
		animationInfo.setAnimationType(animationInfoFormBean.getAnimationType());
		animationInfo.setClassificationId(animationInfoFormBean.getClassificationId());
		animationInfo.setAnimationPicture(animationInfoFormBean.getAnimationPicture());
		
		boolean isOk=animationInfoService.animationInfoAdd(animationInfo);
		Locale locale = LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG,messageSource.getMessage("AnimationInfoController.animationInfoAdd.Fail", null, "添加失败，请稍后再试！", locale));
			return "animationInfoAdd";
		}
		WebTools.setSession(Common.MSG,messageSource.getMessage("AnimationInfoController.animationInfoAdd.Success", null, "添加成功！", locale));
		WebTools.removeSession("animationClassifications");
		WebTools.removeSession("animationTypes");
		return "redirect:animationInfo";
	}
	
	@GetMapping("animationInfoDelete")
	public String animationInfoDelete(@RequestParam Integer id) {
		boolean isOk=animationInfoService.deleteAnimationInfoById(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG, isOk?
									messageSource.getMessage("AnimationINfoController.animationInfoDelete.Success", null, "删除成功！", locale) :
										messageSource.getMessage("AnimationINfoController.animationInfoDelete.Fail", null, "删除失败，请稍后再试！", locale));	
		return "redirect:animationInfo";
	}
	
	@GetMapping("animationInfoUpdate")
	public String animationInfoUpdate(@RequestParam Integer id,AnimationInfoFormBean animationInfoFormBean,Model model) {
		List<AnimationClassification> animationClassifications=animationClassificationService.getAll();
		AnimationClassification animationClassification=new AnimationClassification();
		animationClassification.setId(null);
		animationClassification.setName("请选择");
		animationClassifications.add(0,animationClassification);
		
		WebTools.setSession("animationClassifications", animationClassifications);
		WebTools.setSession("animationTypes", animationTypeService.getAll());
		
		//怎样让复选框都选中
		AnimationInfo animationInfo=animationInfoService.getAnimationInfoById(id);
		WebTools.setSession("animationInfoTypes", animationInfo.getAnimationType());
		WebTools.setSession("animationInfo_picture", animationInfo.getAnimationPicture());
		model.addAttribute("animationInfoFormBean",animationInfo);
		return "animationInfoUpdate";
	}
	
	@PostMapping("animationInfoUpdate")
	public String animationInfoUpdate(@Validated(AnimatinInfoFormBeanGroupSequence.class)AnimationInfoFormBean animationInfoFormBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "animationInfoUpdate";
		}
		if(animationInfoFormBean.getClassificationId().getId() == null) {
			errors.rejectValue("classificationId","AnimationInfoController.classificationId.NotNull","请选择类型！");
			return "animationInfoUpdate";
		}
		if(file.getSize() > 0) {
			//存储位置
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/animationInfoImg");
			String absPath = Common.UPLOAD_FILE+"img/animationInfoImg";
			//随机生成文件名加原文件后缀
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			//上传文件
			file.transferTo(new File(absPath,pictureName+suffix));
			animationInfoFormBean.setAnimationPicture(pictureName+suffix);
		}
		animationInfoFormBean.setAnimationType(animationInfoFormBean.getAnimationType().replace(",", " "));
		
		AnimationInfo animationInfo=new AnimationInfo();
		animationInfo.setId(animationInfoFormBean.getId());
		animationInfo.setAnimationContent(animationInfoFormBean.getAnimationContent());
		animationInfo.setAnimationName(animationInfoFormBean.getAnimationName());
		animationInfo.setAnimationType(animationInfoFormBean.getAnimationType());
		animationInfo.setClassificationId(animationInfoFormBean.getClassificationId());
		animationInfo.setAnimationPicture(animationInfoFormBean.getAnimationPicture());
		
		boolean isOk=animationInfoService.animationInfoUpdate(animationInfo);
		Locale locale = LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG,messageSource.getMessage("AnimationInfoController.animationInfoUpdate.Fail", null, "修改失败，请稍后再试！", locale));
			return "animationInfoUpdate";
		}
		
		WebTools.setSession(Common.MSG,messageSource.getMessage("AnimationInfoController.animationInfoUpdate.Success", null, "修改成功！", locale));
		WebTools.removeSession("animationClassifications");
		WebTools.removeSession("animationTypes");
		WebTools.removeSession("animationInfoTypes");
		return "redirect:animationInfo";
	}
}

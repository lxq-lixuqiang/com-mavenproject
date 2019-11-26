package com.accp.web.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.pojo.Animation;
import com.accp.pojo.AnimationInfo;
import com.accp.service.AnimationEntertainmentAnimationService;
import com.accp.service.AnimationInfoService;
import com.accp.service.AnimationNavigationBarService;
import com.accp.service.AnimationService;
import com.accp.service.AnimationTypeService;
import com.github.pagehelper.PageInfo;

/**
 * 动漫主页控制器
 * @author Y2项目:李旭强
 *
 */
@Controller
public class AnimationController {

	@Autowired
	private AnimationNavigationBarService animationNavigationBarService;
	private Integer animationNavigationBarId=1;
	@Autowired
	private AnimationInfoService animationInfoService;
	@Autowired
	private AnimationTypeService animationTypeService;
	@Autowired
	private AnimationService animationService;
	@Autowired
	private AnimationEntertainmentAnimationService animationEntertainmentAnimationService;
	
	@GetMapping("animation")
	public String comic(Model model) {
		//动漫导航栏
		model.addAttribute("animationNavigationBar",animationNavigationBarService.getAnimationNavigationBarMapperById(animationNavigationBarId));
		//轮播
		model.addAttribute("newAnimationEntertainmentAnimation1",animationEntertainmentAnimationService.getAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(null,0,5));
		//轮播右边动漫信息
		model.addAttribute("newAnimationEntertainmentAnimation2",animationEntertainmentAnimationService.getAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(null,5,6));
		//推广
		model.addAttribute("animationEntertainmentAnimation01",animationEntertainmentAnimationService.getAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(1,0,5));
		//动画
		model.addAttribute("animationEntertainmentAnimation02",animationEntertainmentAnimationService.getAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(2,0,8));
		//动画最新
		model.addAttribute("newAnimationEntertainmentAnimation02",animationEntertainmentAnimationService.getAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(2,8,7));
		//新番
		model.addAttribute("animationInfo7", animationInfoService.getAnimationInfoByClassificationId(16,1));
		model.addAttribute("animationInfo1", animationInfoService.getAnimationInfoByClassificationId(16,2));
		model.addAttribute("animationInfo2", animationInfoService.getAnimationInfoByClassificationId(16,3));
		model.addAttribute("animationInfo3", animationInfoService.getAnimationInfoByClassificationId(16,4));
		model.addAttribute("animationInfo4", animationInfoService.getAnimationInfoByClassificationId(16,5));
		model.addAttribute("animationInfo5", animationInfoService.getAnimationInfoByClassificationId(16,6));
		model.addAttribute("animationInfo6", animationInfoService.getAnimationInfoByClassificationId(16,7));
		//音乐
		model.addAttribute("animationEntertainmentAnimation03",animationEntertainmentAnimationService.getAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(4,0,8));
		//音乐最新
		model.addAttribute("newAnimationEntertainmentAnimation03",animationEntertainmentAnimationService.getAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(4,8,7));
		//娱乐
		model.addAttribute("animationEntertainmentAnimation04",animationEntertainmentAnimationService.getAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(3,0,8));
		//娱乐最新
		model.addAttribute("newAnimationEntertainmentAnimation04",animationEntertainmentAnimationService.getAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(3,8,7));
		//按年份
		List<String> year=new ArrayList<>();
		year.add("2018");
		model.addAttribute("years", year);
		//按季度
		model.addAttribute("quarters", animationInfoService.getQuarter());
		//按标签
		model.addAttribute("animationTypes", animationTypeService.getAll());
		//动漫
		model.addAttribute("pageInfo",animationInfoService.getAnimationInfoByNameAndType(1, 10, null, null, null,null));
		return "animation";
	}
	
	@GetMapping("animationSeach")
	public String animationSeach(Model model) {
		model.addAttribute("animationTypes",animationTypeService.getAll());
		model.addAttribute("pageInfo",animationInfoService.getAnimationInfoByNameAndType(1, 10, null, null, null, null));
		return "animationSeach";
	}
	
	@GetMapping("animationSeach02")
	@ResponseBody
	public PageInfo<AnimationInfo> animationSeach02(Integer pageNum,String animationName,String animationType){
		return animationInfoService.getAnimationInfoByNameAndType(pageNum, 10, null, animationName, animationType, null);
	}
	
	@GetMapping("animationVisit")
	public String animationVisit(@RequestParam Integer animationId,Model model) {
		Animation animation=animationService.getAnimationById(animationId);
		model.addAttribute("animation", animation);
		return "animationVisit";
	}
}

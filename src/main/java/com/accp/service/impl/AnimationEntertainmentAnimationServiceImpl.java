package com.accp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.AnimationEntertainmentAnimationMapper;
import com.accp.pojo.AnimationEntertainmentAnimation;
import com.accp.service.AnimationEntertainmentAnimationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AnimationEntertainmentAnimationServiceImpl implements AnimationEntertainmentAnimationService {
	@Autowired
	private AnimationEntertainmentAnimationMapper animationEntertainmentAnimationMapper;
	
	@Override
	public PageInfo<AnimationEntertainmentAnimation> getEntertainmentAnimationByNameAndEntertainmentTypeId(
			Integer pageNum, Integer pageSize, String name, Integer entertainmentTypeId) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(animationEntertainmentAnimationMapper.findEntertainmentAnimationByNameAndEntertainmentTypeId(name,entertainmentTypeId));
	}

	@Override
	public boolean animationEntertainmentAnimationAdd(AnimationEntertainmentAnimation animationEntertainmentAnimation) {
		animationEntertainmentAnimation.setDateTime(new Date());
		return animationEntertainmentAnimationMapper.add(animationEntertainmentAnimation) == 1;
	}

	@Override
	public boolean animationEntertainmentAnimationDelete(Integer id) {
		return animationEntertainmentAnimationMapper.delete(id) == 1;
	}

	@Override
	public AnimationEntertainmentAnimation getAnimationEntertainmentAnimationById(Integer id) {
		return animationEntertainmentAnimationMapper.findAnimationEntertainmentAnimationById(id);
	}

	@Override
	public boolean animationEntertainmentAnimationUpdate(
			AnimationEntertainmentAnimation animationEntertainmentAnimation) {
		animationEntertainmentAnimation.setDateTime(new Date());
		return animationEntertainmentAnimationMapper.update(animationEntertainmentAnimation) == 1;
	}

	@Override
	public List<AnimationEntertainmentAnimation> getAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(
			Integer entertainmentTypeId, Integer pageNum, Integer pageSize) {
		return animationEntertainmentAnimationMapper.findAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(entertainmentTypeId,pageNum,pageSize);
	}

	
}

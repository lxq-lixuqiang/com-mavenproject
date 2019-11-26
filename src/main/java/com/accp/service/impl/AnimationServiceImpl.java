package com.accp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.AnimationMapper;
import com.accp.pojo.Animation;
import com.accp.service.AnimationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AnimationServiceImpl implements AnimationService {
	@Autowired
	private AnimationMapper animationMapper;

	@Override
	public PageInfo<Animation> getAnimationBySetName(Integer pageNum, Integer pageSize,Integer animationInfoId, String setName) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(animationMapper.findAnimationBySetName(animationInfoId,setName));
	}

	@Override
	public boolean animationAdd(Animation animation) {
		animation.setDateTime(new Date());
		return animationMapper.add(animation) == 1;
	}

	@Override
	public List<Animation> getAnimationByAniamtionInfoId(Integer animationInfoId) {
		return animationMapper.findAnimationByAnimationInfoId(animationInfoId);
	}

	@Override
	public Animation getAnimationById(Integer id) {
		return animationMapper.findAnimationById(id);
	}

	@Override
	public boolean animationDelete(Integer id) {
		return animationMapper.delete(id) == 1;
	}

	@Override
	public boolean animationUpdate(Animation animation) {
		animation.setDateTime(new Date());
		return animationMapper.update(animation) == 1;
	}
}

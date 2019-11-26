package com.accp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.AnimationNavigationBarMapper;
import com.accp.pojo.AnimationNavigationBar;
import com.accp.service.AnimationNavigationBarService;

@Service
public class AnimationNavigationBarServiceImpl implements AnimationNavigationBarService {
	@Autowired
	private AnimationNavigationBarMapper animationNavigationBarMapper;
	
	@Override
	public AnimationNavigationBar getAnimationNavigationBarMapperById(Integer id) {
		return animationNavigationBarMapper.findAnimationNavigationBarById(id);
	}

	@Override
	public boolean updateAnimationNavigationBar(Integer id, String animationWallpaper) {
		return animationNavigationBarMapper.update(id, animationWallpaper) == 1;
	}

}

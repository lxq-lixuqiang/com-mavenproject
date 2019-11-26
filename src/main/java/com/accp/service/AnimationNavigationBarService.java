package com.accp.service;

import com.accp.pojo.AnimationNavigationBar;

public interface AnimationNavigationBarService {
	AnimationNavigationBar getAnimationNavigationBarMapperById(Integer id);

	boolean updateAnimationNavigationBar(Integer id, String animationWallpaper);
}

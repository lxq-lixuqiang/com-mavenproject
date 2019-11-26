package com.accp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.WallpaperNavigationBarMapper;
import com.accp.pojo.WallpaperNavigationBar;
import com.accp.service.WallpaperNavigationBarService;

@Service
public class WallpaperNavigationBarServiceImpl implements WallpaperNavigationBarService {
	@Autowired
	private WallpaperNavigationBarMapper wallpaperNavigationBarMapper;

	@Override
	public WallpaperNavigationBar getWallpaperNavigationBarMapperById(Integer id) {
		return wallpaperNavigationBarMapper.findWallpaperNavigationBarById(id);
	}

	@Override
	public boolean updateWallpaperNavigationBar(Integer id, String wallpaper) {
		return wallpaperNavigationBarMapper.update(id,wallpaper) == 1;
	}

}

package com.accp.service;

import com.accp.pojo.WallpaperNavigationBar;

public interface WallpaperNavigationBarService {
	WallpaperNavigationBar getWallpaperNavigationBarMapperById(Integer id);

	boolean updateWallpaperNavigationBar(Integer id, String wallpaper);
}

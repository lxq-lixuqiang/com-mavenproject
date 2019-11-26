package com.accp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.WallpaperMapper;
import com.accp.pojo.Wallpaper;
import com.accp.service.WallpaperService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class WallpaperServiceImpl implements WallpaperService {
	@Autowired
	private WallpaperMapper wallpaperMapper;

	@Override
	public PageInfo<Wallpaper> getWallpaperAll(String name,Integer pageSize,Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(wallpaperMapper.findAll(name));
	}

	@Override
	public boolean deleteWallpaperById(Integer id) {
		return wallpaperMapper.removeById(id) == 1;
	}

	@Override
	public boolean updateWallpaper(Wallpaper wallpaper) {
		return wallpaperMapper.update(wallpaper) == 1;
	}

	@Override
	public boolean addWallpaper(Wallpaper wallpaper) {
		return wallpaperMapper.add(wallpaper) == 1;
	}

	@Override
	public PageInfo<Wallpaper> getWallpaperByWallpaperTheme(Integer pageNum, Integer pageSize,
			Integer wallpaperThemeId) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(wallpaperMapper.findWallpaperByWallpaperThememId(wallpaperThemeId));
	}

}

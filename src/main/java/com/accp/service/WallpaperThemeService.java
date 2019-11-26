package com.accp.service;

import java.util.Date;
import java.util.List;

import com.accp.pojo.WallpaperTheme;
import com.github.pagehelper.PageInfo;

public interface WallpaperThemeService {
	List<WallpaperTheme> getTopWallpaperTheme(Integer topNum);

	List<Date> getWallpaperThemeTopDate(Integer topDate);

	List<WallpaperTheme> getNewWallpaperTheme(Integer topNum, Integer newNum);

	PageInfo<WallpaperTheme> getAllWallpaperTheme(Integer pageSize, Integer pageNum, String theme,Integer typeId,Date date);

	WallpaperTheme getWallpaperThemeById(Integer id);

	boolean updateWallpaperTheme(WallpaperTheme wallpaperTheme);

	boolean addWallpaperTheme(WallpaperTheme wallpaperTheme);

	boolean deleteWallpaperThemeById(Integer id);

	List<WallpaperTheme> getWallpaperThemeByType(Integer typeId,Integer pageSize);
}

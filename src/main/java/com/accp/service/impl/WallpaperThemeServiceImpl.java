package com.accp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.WallpaperMapper;
import com.accp.mapper.WallpaperThemeMapper;
import com.accp.pojo.Wallpaper;
import com.accp.pojo.WallpaperTheme;
import com.accp.service.WallpaperThemeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class WallpaperThemeServiceImpl implements WallpaperThemeService {
	@Autowired
	private WallpaperThemeMapper wallpaperThemeMapper;
	@Autowired
	private WallpaperMapper wallpaperMapper;

	@Override
	public List<WallpaperTheme> getTopWallpaperTheme(Integer topNum) {
		return wallpaperThemeMapper.findTopWallpaperTheme(topNum);
	}

	@Override
	public List<Date> getWallpaperThemeTopDate(Integer topDate) {
		return wallpaperThemeMapper.findWallpaperThemeTopDate(topDate);
	}

	@Override
	public List<WallpaperTheme> getNewWallpaperTheme(Integer topNum, Integer newNum) {
		return wallpaperThemeMapper.findNewWallpaperTheme(topNum,newNum);
	}

	@Override
	public PageInfo<WallpaperTheme> getAllWallpaperTheme(Integer pageSize, Integer pageNum, String theme,
			Integer typeId,Date date) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(wallpaperThemeMapper.findAllWallpaperTheme(theme,typeId,date));
	}

	@Override
	public WallpaperTheme getWallpaperThemeById(Integer id) {
		return wallpaperThemeMapper.findWallpaperThemeById(id);
	}

	@Override
	public boolean updateWallpaperTheme(WallpaperTheme wallpaperTheme) {
		wallpaperTheme.setDate(new Date());
		return wallpaperThemeMapper.update(wallpaperTheme) == 1;
	}

	@Override
	public boolean addWallpaperTheme(WallpaperTheme wallpaperTheme) {
		wallpaperTheme.setDate(new Date());
		wallpaperThemeMapper.add(wallpaperTheme);
		
		WallpaperTheme findWallpaperTheme=wallpaperThemeMapper.findWallpaperThemeByWallpaper(wallpaperTheme.getWallpaper());
		
		Wallpaper wallpaper=new Wallpaper();
		wallpaper.setWallpaper(findWallpaperTheme.getWallpaper());
		wallpaper.setWallpaperTheme(findWallpaperTheme);
		return wallpaperMapper.add(wallpaper) == 1;
	}

	@Override
	public boolean deleteWallpaperThemeById(Integer id) {
		wallpaperMapper.deleteWallpaperBywallpaperThemeId(id);
		return wallpaperThemeMapper.delete(id) == 1;
	}

	@Override
	public List<WallpaperTheme> getWallpaperThemeByType(Integer typeId, Integer pageSize) {
		return wallpaperThemeMapper.findWallpaperThemeByType(typeId,pageSize);
	}

}

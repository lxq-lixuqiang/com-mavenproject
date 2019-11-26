package com.accp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.GameWallpaperMapper;
import com.accp.pojo.GameWallpaper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class GameWallpaperService implements com.accp.service.GameWallpaperService {
	@Autowired
	private GameWallpaperMapper gameWallpaperMapper;

	@Override
	public PageInfo<GameWallpaper> getGameWallpaperByGameId(Integer pageSize, Integer pageNum, Integer gameId) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(gameWallpaperMapper.getGameWallpaperByGameId(gameId));
	}

	@Override
	public boolean addGameWallpaper(GameWallpaper gameWallpaper) {
		return gameWallpaperMapper.add(gameWallpaper) == 1;
	}

	@Override
	public boolean gameWallpaperDelete(Integer id) {
		return gameWallpaperMapper.delete(id) == 1;
	}

	@Override
	public boolean gameWallpaperUpdate(GameWallpaper gameWallpaper) {
		return gameWallpaperMapper.update(gameWallpaper) == 1;
	}

	@Override
	public List<GameWallpaper> getGameWallpaperByGameId(Integer gameId) {
		return gameWallpaperMapper.findGameWallpaperByGameId(gameId);
	}
}

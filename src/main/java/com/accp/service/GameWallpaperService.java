package com.accp.service;

import java.util.List;

import com.accp.pojo.GameWallpaper;
import com.github.pagehelper.PageInfo;

public interface GameWallpaperService {

	PageInfo<GameWallpaper> getGameWallpaperByGameId(Integer pageSize, Integer pageNum, Integer gameId);

	boolean addGameWallpaper(GameWallpaper gameWallpaper);

	boolean gameWallpaperDelete(Integer id);

	boolean gameWallpaperUpdate(GameWallpaper gameWallpaper);

	List<GameWallpaper> getGameWallpaperByGameId(Integer gameId);

}

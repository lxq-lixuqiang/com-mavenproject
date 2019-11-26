package com.accp.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.GameMapper;
import com.accp.mapper.GameWallpaperMapper;
import com.accp.pojo.Game;
import com.accp.service.GameService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class GameServiceImpl implements GameService {
	@Autowired
	private GameMapper gameMapper;
	@Autowired
	private GameWallpaperMapper gameWallpaperMapper;

	@Override
	public PageInfo<Game> getGameByNameAndType(Integer pageNum, Integer pageSize, String nameAndType) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(gameMapper.findGameByNameAndType(nameAndType));
	}

	@Override
	public boolean gameAdd(Game game) {
		game.setDate(new Date());
		return gameMapper.add(game) == 1;
	}

	@Override
	public boolean gameDelete(Integer id) {
		gameWallpaperMapper.deleteByGameId(id);
		return gameMapper.delete(id) == 1;
	}

	@Override
	public Game getGameById(Integer id) {
		return gameMapper.findGameById(id);
	}

	@Override
	public boolean gameUpdate(Game game) {
		game.setDate(new Date());
		return gameMapper.update(game) == 1;
	}

	@Override
	public List<Game> getGameByClassificationId(Integer pageSize, Integer classificationId) {
		return gameMapper.findGameByClassificationId(pageSize,classificationId);
	}

	@Override
	public List<Game> getGameByPlatformId(Integer pageSize, Integer platformId) {
		return gameMapper.findGameByPlatformId(pageSize,platformId);
	}

	@Override
	public PageInfo<Game> getGameByClassificationIdAndPlatformIdAndTypeIdAndLanguage(Integer pageNum, Integer pageSize,
			Integer classificationId, Integer platformId, Integer typeId, String language,String name) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(gameMapper.findGameByClassificationIdAndPlatformIdAndTypeIdAndLanguage(classificationId,platformId,typeId,language,name));
	}
}

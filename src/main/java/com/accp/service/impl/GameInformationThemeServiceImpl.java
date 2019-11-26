package com.accp.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.GameInformationMapper;
import com.accp.mapper.GameInformationThemeMapper;
import com.accp.pojo.GameInformationTheme;
import com.accp.service.GameInformationThemeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class GameInformationThemeServiceImpl implements GameInformationThemeService {
	@Autowired
	private GameInformationThemeMapper gameInformationThemeMapper;
	@Autowired
	private GameInformationMapper gameInformationMapper;

	@Override
	public PageInfo<GameInformationTheme> getGameInfomationThemeByTheme(Integer pageNum, Integer pageSize,
			String theme) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(gameInformationThemeMapper.findGameInfomationThemeByTheme(theme));
	}

	@Override
	public boolean gameInformationThemeAdd(GameInformationTheme gameInformationTheme) {
		gameInformationTheme.setDate(new Date());
		return gameInformationThemeMapper.Add(gameInformationTheme) == 1;
	}

	@Override
	public boolean gameInformationThemeDelete(Integer id) {
		gameInformationMapper.deleteByInformationThemeId(id);
		return gameInformationThemeMapper.delete(id) == 1;
	}

	@Override
	public GameInformationTheme getGameInformationThemeBy(Integer id) {
		return gameInformationThemeMapper.findGameInfomationThemeById(id);
	}

	@Override
	public boolean gameInformationThemeUpdate(GameInformationTheme gameInformationTheme) {
		gameInformationTheme.setDate(new Date());
		return gameInformationThemeMapper.update(gameInformationTheme) == 1;
	}
}

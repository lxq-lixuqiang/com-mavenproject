package com.accp.service;

import com.accp.pojo.GameInformationTheme;
import com.github.pagehelper.PageInfo;

public interface GameInformationThemeService {

	PageInfo<GameInformationTheme> getGameInfomationThemeByTheme(Integer pageNum, Integer pageSize, String theme);

	boolean gameInformationThemeAdd(GameInformationTheme gameInformationTheme);

	boolean gameInformationThemeDelete(Integer id);

	GameInformationTheme getGameInformationThemeBy(Integer id);

	boolean gameInformationThemeUpdate(GameInformationTheme gameInformationTheme);

}

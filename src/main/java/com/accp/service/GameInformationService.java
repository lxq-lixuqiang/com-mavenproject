package com.accp.service;

import com.accp.pojo.GameInformation;
import com.github.pagehelper.PageInfo;

public interface GameInformationService {

	PageInfo<GameInformation> getGameInformationByInformationThemeId(Integer pageSize, Integer pageNum,
			Integer informationThemeId);

	boolean gameInformationDelete(Integer id);

	Integer getSerialNumberByInformationThemeId(Integer informationThemeId);

	boolean gameInformationAdd(GameInformation gameInformation);

	boolean gameInformationUpdate(GameInformation gameInformation);

}

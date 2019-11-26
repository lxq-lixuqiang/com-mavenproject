package com.accp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.GameInformationMapper;
import com.accp.pojo.GameInformation;
import com.accp.service.GameInformationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class GameInformationServiceImpl implements GameInformationService {
	@Autowired
	private GameInformationMapper GameInformationMapper;

	@Override
	public PageInfo<GameInformation> getGameInformationByInformationThemeId(Integer pageSize, Integer pageNum,
			Integer informationThemeId) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(GameInformationMapper.findGameInformationByInformationThemeId(informationThemeId));
	}

	@Override
	public boolean gameInformationDelete(Integer id) {
		return GameInformationMapper.delete(id) == 1;
	}

	@Override
	public Integer getSerialNumberByInformationThemeId(Integer informationThemeId) {
		int num=GameInformationMapper.getSerialNumberNumByInformationThemeId(informationThemeId);
		if(num >0) {
			return GameInformationMapper.findSerialNumberByInformationThemeId(informationThemeId)+1;
		}else {
			return 1;
		}
	}

	@Override
	public boolean gameInformationAdd(GameInformation gameInformation) {
		return GameInformationMapper.add(gameInformation) == 1;
	}

	@Override
	public boolean gameInformationUpdate(GameInformation gameInformation) {
		return GameInformationMapper.update(gameInformation) == 1;
	}
	
	
}

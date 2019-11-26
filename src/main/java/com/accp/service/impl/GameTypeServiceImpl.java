package com.accp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.GameTypeMapper;
import com.accp.pojo.GameType;
import com.accp.service.GameTypeService;

@Service
public class GameTypeServiceImpl implements GameTypeService {
	@Autowired
	private GameTypeMapper gameTypeMapper;

	@Override
	public List<GameType> getAll() {
		return gameTypeMapper.findAll();
	}

}

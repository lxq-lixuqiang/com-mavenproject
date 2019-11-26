package com.accp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.GamePlatformMapper;
import com.accp.pojo.GamePlatform;
import com.accp.service.GamePlatformService;

@Service
public class GamePlatformServiceImpl implements GamePlatformService {
	@Autowired
	private GamePlatformMapper gamePlatformMapper;

	@Override
	public List<GamePlatform> getAll() {
		return gamePlatformMapper.findAll();
	}

}

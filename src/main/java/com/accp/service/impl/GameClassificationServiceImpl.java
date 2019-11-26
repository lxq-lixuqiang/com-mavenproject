package com.accp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.GameClassificationMapper;
import com.accp.pojo.GameClassification;
import com.accp.service.GameClassificationService;

@Service
public class GameClassificationServiceImpl implements GameClassificationService {
	@Autowired
	private GameClassificationMapper gameClassificationMapper;

	@Override
	public List<GameClassification> getAll() {
		return gameClassificationMapper.findAll();
	}

}

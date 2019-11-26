package com.accp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.AnimationEntertainmentTypeMapper;
import com.accp.pojo.AnimationEntertainmentType;
import com.accp.service.AnimationEntertainmentTypeService;

@Service
public class AnimationEntertainmentTypeServiceImpl implements AnimationEntertainmentTypeService {
	@Autowired
	private AnimationEntertainmentTypeMapper animationEntertainmentTypeMapper;
	
	@Override
	public List<AnimationEntertainmentType> getAll() {
		return animationEntertainmentTypeMapper.findAll();
	}

}

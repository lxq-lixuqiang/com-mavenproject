package com.accp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.AnimationTypeMapper;
import com.accp.pojo.AnimationType;
import com.accp.service.AnimationTypeService;

@Service
public class AnimationTypeServiceImpl implements AnimationTypeService {
	@Autowired
	private AnimationTypeMapper animationTypeMapper;

	@Override
	public List<AnimationType> getAll() {
		return animationTypeMapper.findAll();
	}

}

package com.accp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.AnimationClassificationMapper;
import com.accp.pojo.AnimationClassification;
import com.accp.service.AnimationClassificationService;

@Service
public class AnimationClassificationServiceImpl implements AnimationClassificationService {
	@Autowired
	private AnimationClassificationMapper animationClassificationMapper;

	@Override
	public List<AnimationClassification> getAll() {
		return animationClassificationMapper.findAll();
	}

}

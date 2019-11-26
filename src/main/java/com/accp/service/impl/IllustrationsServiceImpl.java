package com.accp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.IllustrationsMapper;
import com.accp.pojo.Illustrations;
import com.accp.service.IllustrationsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class IllustrationsServiceImpl implements IllustrationsService {
	@Autowired
	private IllustrationsMapper illustrationsMapper;

	@Override
	public List<Illustrations> getAllIllustrations() {
		return illustrationsMapper.findAllIllustrations();
	}

	@Override
	public PageInfo<Illustrations> getAllIllustrationsByPage(Integer pageSize,Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(illustrationsMapper.findAllIllustrations());
	}

	@Override
	public Illustrations getIllustrationsById(Integer id) {
		return illustrationsMapper.findIllustrationsById(id);
	}

	@Override
	public boolean updateIllustrations(Illustrations illustrations) {
		illustrations.setDatetime(new Date());
		return illustrationsMapper.update(illustrations) == 1;
	}


}

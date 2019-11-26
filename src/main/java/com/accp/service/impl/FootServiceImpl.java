package com.accp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.FootMapper;
import com.accp.pojo.Foot;
import com.accp.service.FootService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class FootServiceImpl implements FootService {
	@Autowired
	private FootMapper footMapper;

	@Override
	public List<Foot> getAllFoot() {
		return footMapper.findAllFoot();
	}

	@Override
	public PageInfo<Foot> getFootByName(Integer pageSize, Integer pageNum, String name) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(footMapper.findFootByName(name));
	}

	@Override
	public boolean addFoot(Foot foot) {
		return footMapper.add(foot) == 1;
	}

	@Override
	public boolean deleteFoot(Integer id) {
		return footMapper.delete(id) == 1;
	}

	@Override
	public boolean updateFoot(Foot foot) {
		return footMapper.update(foot) == 1;
	}

}

package com.accp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.HomeNavigationBarMapper;
import com.accp.pojo.HomeNavigationBar;
import com.accp.service.HomeNavigationBarService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class HomeNavigationBarServiceImpl implements HomeNavigationBarService {

	@Autowired
	private HomeNavigationBarMapper homeNavigationBarMapper;

	@Override
	public PageInfo<HomeNavigationBar> getHomeNavigationBarByTitle(Integer pageNum, Integer pageSize, String title) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(homeNavigationBarMapper.findHomeNavigationBarByTitle(title));
	}

	@Override
	public boolean homeNavigationBarAdd(HomeNavigationBar homeNavigationBar) {
		homeNavigationBar.setDate(new Date());
		return homeNavigationBarMapper.add(homeNavigationBar) == 1;
	}

	@Override
	public List<HomeNavigationBar> getAll() {
		return homeNavigationBarMapper.findAll();
	}

	@Override
	public HomeNavigationBar getHomeNavigationBarById(Integer id) {
		return homeNavigationBarMapper.findHomeNavigationBarById(id);
	}

	@Override
	public boolean homeNavigationBarDelete(Integer id) {
		return homeNavigationBarMapper.delete(id) == 1;
	}

	@Override
	public boolean homeNavigationBarUpdate(HomeNavigationBar homeNavigationBar) {
		homeNavigationBar.setDate(new Date());
		return homeNavigationBarMapper.update(homeNavigationBar);
	}
}

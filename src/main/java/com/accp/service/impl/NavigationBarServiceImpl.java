package com.accp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.NavigationBarMapper;
import com.accp.pojo.NavigationBar;
import com.accp.service.NavigationBarService;

@Service
public class NavigationBarServiceImpl implements NavigationBarService {
	
	@Autowired
	private NavigationBarMapper navigationBarMapper;

	@Override
	public List<NavigationBar> getAllNavigationBar() {
		return navigationBarMapper.fintAllNavigationBar();
	}

	@Override
	public boolean updateNavigationBar(NavigationBar navigationBar,Integer oldId) {
		return navigationBarMapper.update(navigationBar,oldId) == 1;
	}

	@Override
	public boolean isExistsId(Integer nowId,Integer findId) {
		return navigationBarMapper.isExistsId(nowId,findId) == 1;
	}

}

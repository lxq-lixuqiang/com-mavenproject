package com.accp.service;

import java.util.List;

import com.accp.pojo.NavigationBar;

public interface NavigationBarService {
	List<NavigationBar> getAllNavigationBar();

	boolean updateNavigationBar(NavigationBar navigationBar, Integer oldId);

	boolean isExistsId(Integer nowId,Integer findId);
}

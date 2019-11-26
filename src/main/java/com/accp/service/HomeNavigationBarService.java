package com.accp.service;

import java.util.List;

import com.accp.pojo.HomeNavigationBar;
import com.github.pagehelper.PageInfo;

public interface HomeNavigationBarService {

	PageInfo<HomeNavigationBar> getHomeNavigationBarByTitle(Integer pageNum, Integer pageSize, String title);

	boolean homeNavigationBarAdd(HomeNavigationBar homeNavigationBar);

	List<HomeNavigationBar> getAll();

	HomeNavigationBar getHomeNavigationBarById(Integer id);

	boolean homeNavigationBarDelete(Integer id);

	boolean homeNavigationBarUpdate(HomeNavigationBar homeNavigationBar);


}

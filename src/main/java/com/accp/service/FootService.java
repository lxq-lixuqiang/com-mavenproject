package com.accp.service;

import java.util.List;

import com.accp.pojo.Foot;
import com.github.pagehelper.PageInfo;

public interface FootService {
	List<Foot> getAllFoot();

	PageInfo<Foot> getFootByName(Integer pageSize, Integer pageNum, String name);

	boolean addFoot(Foot foot);

	boolean deleteFoot(Integer id);

	boolean updateFoot(Foot foot);
}

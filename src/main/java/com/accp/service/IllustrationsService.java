package com.accp.service;

import java.util.List;

import com.accp.pojo.Illustrations;
import com.github.pagehelper.PageInfo;

public interface IllustrationsService {
	List<Illustrations> getAllIllustrations();

	PageInfo<Illustrations> getAllIllustrationsByPage(Integer pageSize,Integer pageNum);

	Illustrations getIllustrationsById(Integer id);

	boolean updateIllustrations(Illustrations illustrations);
}

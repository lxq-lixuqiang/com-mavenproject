package com.accp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.WallpaperTypeMapper;
import com.accp.pojo.WallpaperType;
import com.accp.service.WallpaperTypeService;

@Service
public class WallpaperTypeServiceImpl implements WallpaperTypeService {
	@Autowired
	private WallpaperTypeMapper wallpaperTypeMapper;

	@Override
	public List<WallpaperType> getAllWallpaperType() {
		return wallpaperTypeMapper.findAllWallpaperType();
	}

}

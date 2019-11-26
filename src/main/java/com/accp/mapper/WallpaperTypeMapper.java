package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.accp.pojo.WallpaperType;

public interface WallpaperTypeMapper {
	@Select("select * from wallpaper_type")
	List<WallpaperType> findAllWallpaperType();
}

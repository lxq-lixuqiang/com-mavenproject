package com.accp.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.accp.pojo.WallpaperNavigationBar;

public interface WallpaperNavigationBarMapper {
	@Select("select * from wallpaper_navigationBar where id=#{id}")
	WallpaperNavigationBar findWallpaperNavigationBarById(Integer id);

	@Update("update wallpaper_navigationBar set wallpaper=#{wallpaper} where id=#{id}")
	int update(@Param("id")Integer id,@Param("wallpaper")String wallpaper);
}

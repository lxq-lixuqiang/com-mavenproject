package com.accp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.accp.pojo.WallpaperTheme;

public interface WallpaperThemeMapper {
	@Select("select * from wallpaper_theme order by date desc limit #{topNum}")
	List<WallpaperTheme> findTopWallpaperTheme(Integer topNum);

	@Select("SELECT DATE FROM wallpaper_theme GROUP BY DATE desc limit 12 ")
	List<Date> findWallpaperThemeTopDate(Integer topDate);

	@Select("select * from wallpaper_theme order by date desc limit #{topNum},#{newNum}")
	List<WallpaperTheme> findNewWallpaperTheme(@Param("topNum")Integer topNum,@Param("newNum")Integer newNum);

	List<WallpaperTheme> findAllWallpaperTheme(@Param("theme")String theme,@Param("typeId")Integer typeId,@Param("date")Date date);

	WallpaperTheme findWallpaperThemeById(Integer id);

	int update(WallpaperTheme wallpaperTheme);

	@Insert("insert into wallpaper_theme values(null,#{wallpaper},#{theme},#{date},#{wallpaperType.id})")
	int add(WallpaperTheme wallpaperTheme);

	@Select("select * from wallpaper_theme where wallpaper=#{wallpaper}")
	WallpaperTheme findWallpaperThemeByWallpaper(String wallpaper);

	@Delete("delete from wallpaper_theme where id=#{id}")
	int delete(Integer id);

	@Select("select * from wallpaper_theme where typeId=#{typeId} order by date desc limit #{pageSize}")
	List<WallpaperTheme> findWallpaperThemeByType(@Param("typeId")Integer typeId,@Param("pageSize")Integer pageSize);
}

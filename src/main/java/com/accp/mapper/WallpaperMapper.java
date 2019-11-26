package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.accp.pojo.Wallpaper;

/**
 * 壁纸数据访问层
 * @author Y2项目:李旭强
 *
 */
public interface WallpaperMapper {
	/**
	 * 按壁纸名称分页查询壁纸对象
	 * @param name 壁纸名称
	 * @return 返回壁纸对象集合
	 */
	List<Wallpaper> findAll(String name);

	/**
	 * 按壁纸id删除壁纸对象
	 * @param id 壁纸id
	 * @return 删除返回数据库受影响行数
	 */
	@Delete("DELETE FROM t_wallpaper where id=#{id}")
	int removeById(@Param("id")Integer id);

	/**
	 * 按壁纸id查询壁纸对象
	 * @param id 壁纸id
	 * @return 返回壁纸对象，没有:返回null
	 */
	@Select("select * from t_wallpaper where id=#{id}")
	Wallpaper findOneById(@Param("id")Integer id);

	/**
	 * 按壁纸对象id修改壁纸对象
	 * @param wallpaper 壁纸对象
	 * @return 修改返回数据库受影响行数
	 */
	@Update("update t_wallpaper set wallpaper=#{wallpaper} where id=#{id}")
	int update(Wallpaper wallpaper);

	/**
	 * 新增壁纸对象
	 * @param wallpaper 壁纸对象
	 * @return 新增返回数据库受影响行数
	 */
	@Insert("insert into t_wallpaper values(null,#{wallpaper},#{wallpaperTheme.id})")
	int add(Wallpaper wallpaper);

	@Delete("delete from t_wallpaper where themeId=#{id}")
	int deleteWallpaperBywallpaperThemeId(Integer id);

	List<Wallpaper> findWallpaperByWallpaperThememId(Integer wallpaperThemeId);

}

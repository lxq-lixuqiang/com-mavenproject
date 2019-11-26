package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.accp.pojo.GameWallpaper;

public interface GameWallpaperMapper {

	@Select("select * from game_wallpaper where gameId=#{gameId}")
	List<GameWallpaper> getGameWallpaperByGameId(Integer gameId);

	@Insert("insert into game_wallpaper values(null,#{wallpaper},#{gameId.id})")
	int add(GameWallpaper gameWallpaper);

	@Delete("delete from game_wallpaper where id=#{id}")
	int delete(Integer id);

	@Update("update game_wallpaper set wallpaper=#{wallpaper} where id=#{id}")
	int update(GameWallpaper gameWallpaper);

	@Select("select * from game_wallpaper where gameId=#{gameId}")
	List<GameWallpaper> findGameWallpaperByGameId(Integer gameId);

	@Delete("delete from game_wallpaper where gameId=#{gameId}")
	int deleteByGameId(Integer gameId);

}

package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.accp.pojo.GameInformationTheme;

public interface GameInformationThemeMapper {

	List<GameInformationTheme> findGameInfomationThemeByTheme(@Param("theme")String theme);

	@Insert("insert into game_informationTheme values(null,#{picture},#{theme},#{date})")
	int Add(GameInformationTheme gameInformationTheme);

	@Delete("delete from game_informationTheme where id=#{id}")
	int delete(Integer id);

	@Select("select * from game_informationTheme where id=#{id}")
	GameInformationTheme findGameInfomationThemeById(Integer id);

	int update(GameInformationTheme gameInformationTheme);

}

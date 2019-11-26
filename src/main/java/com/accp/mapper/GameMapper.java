package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.accp.pojo.Game;

/**
 *游戏数据访问层
 * @author Y2项目:李旭强
 *
 */
public interface GameMapper {

	List<Game> findGameByNameAndType(@Param("nameAndType")String nameAndType);

	@Insert("insert into t_game values(null,#{picture},#{name},#{platformId.id},#{language},#{issuer},#{typeId.id},#{classificationId.id},#{game},#{gamePath},#{content},#{gameExplain},#{date})")
	int add(Game game);

	@Delete("delete from t_game where id=#{id}")
	int delete(Integer id);

	Game findGameById(@Param("id")Integer id);

	int update(Game game);

	List<Game> findGameByClassificationId(@Param("pageSize")Integer pageSize,@Param("classificationId")Integer classificationId);

	List<Game> findGameByPlatformId(@Param("pageSize")Integer pageSize,@Param("platformId")Integer platformId);

	List<Game> findGameByClassificationIdAndPlatformIdAndTypeIdAndLanguage(@Param("classificationId")Integer classificationId,@Param("platformId")Integer platformId,
			@Param("typeId")Integer typeId,@Param("language")String language,@Param("name")String name);

}

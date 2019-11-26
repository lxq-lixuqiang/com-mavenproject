package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.accp.pojo.GameType;

public interface GameTypeMapper {
	@Select("select * from game_type")
	List<GameType> findAll();
}

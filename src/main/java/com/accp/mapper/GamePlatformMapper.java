package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.accp.pojo.GamePlatform;

public interface GamePlatformMapper {
	
	@Select("select * from game_platform")
	List<GamePlatform> findAll();
}

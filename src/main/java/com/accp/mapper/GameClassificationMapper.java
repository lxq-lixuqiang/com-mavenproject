package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.accp.pojo.GameClassification;

public interface GameClassificationMapper {
	@Select("select * from game_classification")
	List<GameClassification> findAll();
}

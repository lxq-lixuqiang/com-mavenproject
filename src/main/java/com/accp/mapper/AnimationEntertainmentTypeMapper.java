package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.accp.pojo.AnimationEntertainmentType;

public interface AnimationEntertainmentTypeMapper {
	@Select("select * from animation_entertainmentType")
	List<AnimationEntertainmentType> findAll();
}

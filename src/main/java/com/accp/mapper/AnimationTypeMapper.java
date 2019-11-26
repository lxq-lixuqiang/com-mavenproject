package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.accp.pojo.AnimationType;

public interface AnimationTypeMapper {
	@Select("select * from animation_type")
	List<AnimationType> findAll();
}

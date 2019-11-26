package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.accp.pojo.AnimationClassification;

public interface AnimationClassificationMapper {
	@Select("select * from animation_classification")
	List<AnimationClassification> findAll();
}

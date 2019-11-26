package com.accp.service;

import java.util.List;

import com.accp.pojo.AnimationInfo;
import com.github.pagehelper.PageInfo;

public interface AnimationInfoService {
	PageInfo<AnimationInfo> getAnimationInfoByNameAndType(Integer pageNum,Integer pageSize,String year,String animationName,String animationType,String animationQuarter);

	boolean animationInfoAdd(AnimationInfo animationInfo);

	boolean deleteAnimationInfoById(Integer id);

	AnimationInfo getAnimationInfoById(Integer id);

	boolean animationInfoUpdate(AnimationInfo animationInfo);

	List<AnimationInfo> getAnimationInfoByClassificationId(Integer pageSize, Integer classificationId);

	List<String> getQuarter();
	
	List<AnimationInfo> getAnimationInfoByNotClassificationId(Integer notClassificationId,Integer pageSize);
}

package com.accp.service;

import java.util.List;

import com.accp.pojo.Animation;
import com.github.pagehelper.PageInfo;

public interface AnimationService {

	PageInfo<Animation> getAnimationBySetName(Integer pageNum, Integer pageSize,Integer animationInfoId, String setName);

	boolean animationAdd(Animation animation);

	List<Animation> getAnimationByAniamtionInfoId(Integer animationInfoId);

	Animation getAnimationById(Integer id);

	boolean animationDelete(Integer id);

	boolean animationUpdate(Animation animation);

	

}

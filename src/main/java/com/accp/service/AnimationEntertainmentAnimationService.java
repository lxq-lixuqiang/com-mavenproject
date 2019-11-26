package com.accp.service;

import java.util.List;

import com.accp.pojo.AnimationEntertainmentAnimation;
import com.github.pagehelper.PageInfo;

public interface AnimationEntertainmentAnimationService {

	PageInfo<AnimationEntertainmentAnimation> getEntertainmentAnimationByNameAndEntertainmentTypeId(Integer pageNum,
			Integer pageSize, String name, Integer entertainmentTypeId);

	boolean animationEntertainmentAnimationAdd(AnimationEntertainmentAnimation animationEntertainmentAnimation);

	boolean animationEntertainmentAnimationDelete(Integer id);

	AnimationEntertainmentAnimation getAnimationEntertainmentAnimationById(Integer id);

	boolean animationEntertainmentAnimationUpdate(AnimationEntertainmentAnimation animationEntertainmentAnimation);

	List<AnimationEntertainmentAnimation> getAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(Integer entertainmentTypeId, Integer pageNum,Integer pageSize);

}

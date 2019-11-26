package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.accp.pojo.AnimationEntertainmentAnimation;

public interface AnimationEntertainmentAnimationMapper {

	List<AnimationEntertainmentAnimation> findEntertainmentAnimationByNameAndEntertainmentTypeId(@Param("name")String name,@Param("entertainmentTypeId")Integer entertainmentTypeId);

	@Insert("insert into animation_entertainmentAnimation values(null,#{picture},#{name},#{entertainmentAnimation},#{entertainmentAnimationPath},#{entertainmentTypeId.id},#{dateTime})")
	int add(AnimationEntertainmentAnimation animationEntertainmentAnimation);

	@Delete("delete from animation_entertainmentAnimation where id=#{id}")
	int delete(Integer id);

	AnimationEntertainmentAnimation findAnimationEntertainmentAnimationById(@Param("id")Integer id);

	int update(AnimationEntertainmentAnimation animationEntertainmentAnimation);

	List<AnimationEntertainmentAnimation> findAnimationEntertainmentAnimationByEntertainmentTypeAndPageNumAndPageSize(
			@Param("entertainmentTypeId")Integer entertainmentTypeId,@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);

}

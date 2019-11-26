package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.accp.pojo.Animation;

public interface AnimationMapper {
	
	@Delete("delete from t_animation where animationInfoId=#{animationInfoId}")
	int deleteAnimationByAnimationInfoId(Integer animationInfoId);

	List<Animation> findAnimationBySetName(@Param("animationInfoId")Integer animationInfoId,@Param("setName")String setName);

	@Insert("insert into t_animation values(null,#{setName},#{animationInfoId.id},#{dateTime},#{animation},#{animationPath})")
	int add(Animation animation);

	@Select("select * from t_animation where animationInfoId=#{animationInfoId} order by dateTime asc")
	List<Animation> findAnimationByAnimationInfoId(Integer animationInfoId);

	@Select("select * from t_animation where id=#{id}")
	Animation findAnimationById(Integer id);

	@Delete("delete from t_animation where id=#{id}")
	int delete(Integer id);

	int update(Animation animation);
}

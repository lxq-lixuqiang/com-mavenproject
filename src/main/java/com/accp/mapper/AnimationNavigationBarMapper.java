package com.accp.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.accp.pojo.AnimationNavigationBar;

public interface AnimationNavigationBarMapper {
	@Select("select * from animation_navigationBar where id=#{id}")
	AnimationNavigationBar findAnimationNavigationBarById(Integer id);

	@Update("update animation_navigationBar set animationWallpaper=#{animationWallpaper} where id=#{id}")
	int update(@Param("id")Integer id,@Param("animationWallpaper")String animationWallpaper);
}

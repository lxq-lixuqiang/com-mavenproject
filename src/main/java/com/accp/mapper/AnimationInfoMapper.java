package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.accp.pojo.AnimationInfo;

public interface AnimationInfoMapper {
	
	List<AnimationInfo> findAnimationInfoByNameAndType(@Param("year")String year,@Param("animationName")String animationName,@Param("animationType")String animationType,@Param("animationQuarter")String animationQuarter);

	@Insert("insert into animation_info values(null,#{animationPicture},#{animationName},#{animationQuarter},#{animationType},#{animationDate},#{classificationId.id},#{animationContent})")
	int add(AnimationInfo animationInfo);

	@Delete("delete from animation_info where id=#{id}")
	int delete(Integer id);

	AnimationInfo findAnimationInfoById(@Param("id")Integer id);

	int update(AnimationInfo animationInfo);

	@Select("select * from animation_info where classificationId=#{classificationId} limit #{pageSize}")
	List<AnimationInfo> findAnimationInfoByClassificationId(@Param("pageSize")Integer pageSize,@Param("classificationId")Integer classificationId);

	@Select("SELECT animationQuarter FROM animation_info GROUP BY animationQuarter")
	List<String> findQuarter();

	@Select("select * from animation_info where classificationId != #{notClassificationId} limit #{pageSize}")
	List<AnimationInfo> findAnimationInfoByNotClassificationId(@Param("notClassificationId")Integer notClassificationId,@Param("pageSize")Integer pageSize);
}

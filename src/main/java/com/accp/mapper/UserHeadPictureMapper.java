package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.accp.pojo.UserHeadPicture;

public interface UserHeadPictureMapper {
	@Select("select * from user_userHeadPicture")
	List<UserHeadPicture> findAllUserHeadPicture();

	@Insert("insert into user_userHeadPicture values(null,#{headPicture})")
	int add(UserHeadPicture userHeadPicture);

	@Delete("delete from user_userHeadPicture where id=#{id}")
	int delete(Integer id);

	@Update("update user_userHeadPicture set headPicture=#{pictureName} where id=#{id}")
	int update(@Param("id")Integer id,@Param("pictureName")String pictureName);

}

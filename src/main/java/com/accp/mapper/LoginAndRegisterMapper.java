package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.accp.pojo.LoginAndRegister;

public interface LoginAndRegisterMapper {
	@Select("select picture from common_loginAndRegister where type=#{type}")
	String findLoginAndRegisterByType(Integer type);

	@Select("select * from common_loginAndRegister ")
	List<LoginAndRegister> findAllLoginAndRegister();

	@Update("update common_loginAndRegister set picture=#{picture} where id=#{id}")
	int update(@Param("id")Integer id,@Param("picture")String picture);
}

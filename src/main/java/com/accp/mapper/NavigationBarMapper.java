package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.accp.pojo.NavigationBar;

public interface NavigationBarMapper {
	
	@Select("select * from common_navigationBar ORDER BY id")
	List<NavigationBar> fintAllNavigationBar();

	@Update("update common_navigationBar set id=#{navigationBar.id},name=#{navigationBar.name},path=#{navigationBar.path} where id=#{oldId}")
	int update(@Param("navigationBar")NavigationBar navigationBar,@Param("oldId")Integer oldId);

	@Select("select count(1) from common_navigationBar where id != #{nowId} AND id = #{findId}")
	int isExistsId(@Param("nowId")Integer nowId,@Param("findId")Integer findId);
}

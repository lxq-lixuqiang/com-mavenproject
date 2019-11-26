package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.accp.pojo.HomeNavigationBar;

public interface HomeNavigationBarMapper {

	List<HomeNavigationBar> findHomeNavigationBarByTitle(@Param("title")String title);

	@Insert("insert into home_navigationBar values(null,#{picture},#{title},#{backgroundColor},#{date},#{homeNavigationBar},#{homeNavigationBarPath})")
	int add(HomeNavigationBar homeNavigationBar);

	@Select("select * from home_navigationBar order by date desc")
	List<HomeNavigationBar> findAll();

	@Select("select * from home_navigationBar where id=#{id}")
	HomeNavigationBar findHomeNavigationBarById(Integer id);

	@Delete("delete from home_navigationBar where id=#{id}")
	int delete(Integer id);

	boolean update(HomeNavigationBar homeNavigationBar);

}

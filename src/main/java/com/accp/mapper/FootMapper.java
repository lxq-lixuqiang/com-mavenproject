package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.accp.pojo.Foot;

public interface FootMapper {
	@Select("select * from common_foot")
	List<Foot> findAllFoot();

	List<Foot> findFootByName(@Param("name")String name);

	@Insert("insert into common_foot values(null,#{name},#{path})")
	int add(Foot foot);

	@Delete("delete from common_foot where id=#{id}")
	int delete(Integer id);

	@Update("update common_foot set name=#{name},path=#{path} where id=#{id}")
	int update(Foot foot);
}

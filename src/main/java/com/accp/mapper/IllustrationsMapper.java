package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.accp.pojo.Illustrations;

public interface IllustrationsMapper {
	@Select("select * from common_illustrations")
	List<Illustrations> findAllIllustrations();

	@Select("select * from common_illustrations where id=#{id}")
	Illustrations findIllustrationsById(Integer id);

	int update(Illustrations illustrations);
}

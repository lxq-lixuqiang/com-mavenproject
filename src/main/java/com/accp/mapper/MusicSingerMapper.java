package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.accp.pojo.MusicSinger;

public interface MusicSingerMapper {

	List<MusicSinger> findAllMusicSinger(@Param("name")String name,@Param("sex")Integer sex);

	@Insert("insert into music_singer values(null,#{singerPicture},#{name},#{sex},#{synopsis})")
	int add(MusicSinger musicSinger);

	@Delete("delete from music_singer where id = #{id}")
	int delete(Integer id);

	@Select("select * from music_singer where id=#{id}")
	MusicSinger findMusicSingerById(Integer id);

	int update(MusicSinger musicSinger);

}

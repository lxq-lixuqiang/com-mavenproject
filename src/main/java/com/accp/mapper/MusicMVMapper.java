package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.accp.pojo.MusicMV;

public interface MusicMVMapper {

	@Delete("delete from music_mv where singerId=#{singerId} ")
	int deleteMusicBySingerId(Integer singerId);

	List<MusicMV> findAllMusicMV(@Param("themeName")String themeName,@Param("musicSingerId")Integer musicSingerId);

	@Delete("delete from music_mv where id=#{id}")
	int delete(Integer id);

	@Insert("insert into music_mv values(null,#{themePicture},#{themeName},#{mv},#{musicSinger.id},#{date})")
	int add(MusicMV musicMV);

	@Select("select * from music_mv where id=#{id}")
	MusicMV findMusicMVById(Integer id);

	int update(MusicMV musicMV);

	
	List<MusicMV> findTopMusicMV(@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);

	@Select("select * from music_mv where singerId=#{singerId} order by date desc ")
	List<MusicMV> findMusicMVBySingerId(Integer singerId);
}

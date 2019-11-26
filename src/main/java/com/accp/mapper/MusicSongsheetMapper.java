package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.accp.pojo.MusicSongsheet;

public interface MusicSongsheetMapper {

	List<MusicSongsheet> findMusicSongsheetByName(@Param("name")String name);

	@Delete("delete from music_songsheet where id=#{id}")
	int delete(Integer id);

	@Insert("insert into music_songsheet values(null,#{picture},#{name},#{date})")
	int add(MusicSongsheet musicSongsheet);

	@Select("select * from music_songsheet where id=#{id}")
	MusicSongsheet findMusicSongsheetById(Integer id);

	boolean update(MusicSongsheet musicSongsheet);

	@Select("select * from music_songsheet order by date desc limit #{topNum}")
	List<MusicSongsheet> findMusicSongsheetTop(int topNum);

}

package com.accp.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.accp.pojo.Music;


public interface MusicSongsheetInfoMapper {
	
	@Delete("delete from music_songsheetInfo where musicId=#{musicId}")
	int deleteMusicByMusicId(Integer musicId);

	List<Music> findMusicSongsheetInfoBySongsheetId(@Param("songsheetId")Integer songsheetId,@Param("name")String name);

	@Delete("delete from music_songsheetInfo where songsheetId=#{songsheetId}")
	boolean deleteMusicSongsheetInfoBySongsheetId(Integer songsheetId);

	@Select("select musicId from music_songsheetInfo where songsheetId=#{musicSongsheetId}")
	List<Integer> findMusicIdByMusicSongsheetId(Integer SongsheetId);

	@Delete("delete from music_songsheetInfo where songsheetId=#{songsheetId}")
	boolean removeMusicSongsheetInfoBySongsheetId(Integer songsheetId);

	@Insert("insert into music_songsheetInfo values (null,#{songsheetId},#{musicId})")
	int add(@Param("musicId")Integer musicId,@Param("songsheetId")Integer songsheetId);

}

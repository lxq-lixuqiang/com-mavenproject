package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.accp.pojo.Music;

/**
 * 音乐数据访问层
 * @author Y2项目:李旭强
 *
 */
public interface MusicMapper {

	@Delete("delete from t_music where singerId=#{musicSingerId}")
	int deleteMusicByMusicSingerId(Integer musicSingerId);

	@Select("select id from t_music where singerId=#{musicSingerId}")
	List<Integer> findMusicIdBySingerId(Integer musicSingerId);

	List<Music> findMusicBySingerId(@Param("singerId")Integer singerId,@Param("songName")String songName);

	@Delete("delete from t_music where id=#{id}")
	int delete(Integer id);

	@Insert("insert into t_music values(null,#{music},#{songName},#{date},#{musicSinger.id})")
	boolean add(Music music);

	@Select("select * from t_music where id=#{id}")
	Music findMusicById(Integer id);

	int update(Music music);
	
	List<Music> findMusicBySongNameAndSingerName(@Param("songName")String songName,@Param("singerName")String singerName);

}

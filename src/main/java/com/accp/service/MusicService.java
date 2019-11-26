package com.accp.service;

import com.accp.pojo.Music;
import com.github.pagehelper.PageInfo;

/**
 * 音乐业务逻辑层
 * @author Y2项目:李旭强
 *
 */
public interface MusicService {

	PageInfo<Music> getMusicBySingerId(Integer singerId, Integer pageNum,Integer pageSize,String songName);

	boolean deleteMusicById(Integer id);

	boolean musicAdd(Music music);

	Music getMusicById(Integer id);

	boolean musicUpdate(Music music);

	PageInfo<Music> getMusicBySongNameAndSingerName(Integer pageSize,Integer pageNum, String songName, String singerName);


}

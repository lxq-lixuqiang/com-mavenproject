package com.accp.service;

import com.accp.pojo.MusicSinger;
import com.github.pagehelper.PageInfo;

public interface MusicSingerService {
	PageInfo<MusicSinger> getAllMusicSinger(Integer pageNum,Integer pageSize,String name,Integer sex);

	boolean MusicSingerAdd(MusicSinger musicSinger);

	boolean MusicSingerDelete(Integer id);

	MusicSinger getMusicSingerById(Integer id);

	boolean updateMusicSinger(MusicSinger musicSinger);
}

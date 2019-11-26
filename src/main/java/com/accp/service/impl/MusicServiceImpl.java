package com.accp.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.MusicMapper;
import com.accp.mapper.MusicSongsheetInfoMapper;
import com.accp.pojo.Music;
import com.accp.service.MusicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 音乐业务逻辑层实现类
 * @author Y2项目:李旭强
 *
 */
@Service
public class MusicServiceImpl implements MusicService {
	@Autowired
	private MusicMapper musicMapper;
	@Autowired
	private MusicSongsheetInfoMapper musicSongsheetInfoMapper;

	@Override
	public PageInfo<Music> getMusicBySingerId(Integer singerId, Integer pageNum, Integer pageSize,String songName) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(musicMapper.findMusicBySingerId(singerId,songName));
	}

	@Override
	public boolean deleteMusicById(Integer id) {
		musicSongsheetInfoMapper.deleteMusicByMusicId(id);
		return musicMapper.delete(id) == 1;
	}

	@Override
	public boolean musicAdd(Music music) {
		music.setDate(new Date());
		return musicMapper.add(music);
	}

	@Override
	public Music getMusicById(Integer id) {
		return musicMapper.findMusicById(id);
	}

	@Override
	public boolean musicUpdate(Music music) {
		music.setDate(new Date());
		return musicMapper.update(music) == 1;
	}

	@Override
	public PageInfo<Music> getMusicBySongNameAndSingerName(Integer pageSize, Integer pageNum, String songName,
			String singerName) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(musicMapper.findMusicBySongNameAndSingerName(songName, singerName));
	}


}

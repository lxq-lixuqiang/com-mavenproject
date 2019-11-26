package com.accp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.MusicMVMapper;
import com.accp.mapper.MusicMapper;
import com.accp.mapper.MusicSingerMapper;
import com.accp.mapper.MusicSongsheetInfoMapper;
import com.accp.pojo.MusicSinger;
import com.accp.service.MusicSingerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MusicSingerServiceImpl implements MusicSingerService {
	@Autowired
	private MusicSingerMapper musicSingerMapper;
	@Autowired
	private MusicMVMapper musicMVMapper;
	@Autowired
	private MusicMapper musicMapper;
	@Autowired
	private MusicSongsheetInfoMapper musicSongsheetInfoMapper;
	
	@Override
	public PageInfo<MusicSinger> getAllMusicSinger(Integer pageNum, Integer pageSize, String name, Integer sex) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(musicSingerMapper.findAllMusicSinger(name, sex));
	}

	@Override
	public boolean MusicSingerAdd(MusicSinger musicSinger) {
		return musicSingerMapper.add(musicSinger) == 1;
	}

	@Override
	public boolean MusicSingerDelete(Integer id) {
		musicMVMapper.deleteMusicBySingerId(id);
		List<Integer> musicIds=musicMapper.findMusicIdBySingerId(id);
		for (Integer musicId : musicIds) {
			musicSongsheetInfoMapper.deleteMusicByMusicId(musicId);
		}
		musicMapper.deleteMusicByMusicSingerId(id);
		return  musicSingerMapper.delete(id)== 1;
	}

	@Override
	public MusicSinger getMusicSingerById(Integer id) {
		return musicSingerMapper.findMusicSingerById(id);
	}

	@Override
	public boolean updateMusicSinger(MusicSinger musicSinger) {
		return musicSingerMapper.update(musicSinger) == 1; 
	}

}

package com.accp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.MusicSongsheetInfoMapper;
import com.accp.pojo.Music;
import com.accp.service.MusicSongsheetInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MusicSongsheetInfoServiceImpl implements MusicSongsheetInfoService {
	@Autowired
	private MusicSongsheetInfoMapper musicSongsheetInfoMapper;

	@Override
	public PageInfo<Music> getMusicSongsheetInfoBySongsheetId(Integer pageNum,Integer pageSize,Integer songsheetId,String name) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(musicSongsheetInfoMapper.findMusicSongsheetInfoBySongsheetId(songsheetId,name));
	}

	@Override
	public List<Integer> getMusicIdByMusicSongsheetId(Integer SongsheetId){
		return musicSongsheetInfoMapper.findMusicIdByMusicSongsheetId(SongsheetId);
	}

	@Override
	public boolean musicSongsheetInfoAdd(Integer[] musicIds, Integer songsheetId) {
		int num=0;
		//删除之前
		musicSongsheetInfoMapper.removeMusicSongsheetInfoBySongsheetId(songsheetId);
		//添加新的
		for (Integer musicId : musicIds) {
			num+=musicSongsheetInfoMapper.add(musicId,songsheetId);
		}
		return num == musicIds.length;
	}
}

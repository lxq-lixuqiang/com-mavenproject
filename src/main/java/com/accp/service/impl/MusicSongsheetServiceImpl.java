package com.accp.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.MusicSongsheetInfoMapper;
import com.accp.mapper.MusicSongsheetMapper;
import com.accp.pojo.MusicSongsheet;
import com.accp.service.MusicSongsheetService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MusicSongsheetServiceImpl implements MusicSongsheetService {
	@Autowired
	private MusicSongsheetMapper musicSongsheetMapper;
	@Autowired 
	private MusicSongsheetInfoMapper musicSongsheetInfoMapper;
	
	@Override
	public PageInfo<MusicSongsheet> getMusicSongsheetByName(Integer pageSize, Integer pageNum, String name) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(musicSongsheetMapper.findMusicSongsheetByName(name));
	}

	@Override
	public boolean musicSongsheetDelete(Integer id) {
		musicSongsheetInfoMapper.deleteMusicSongsheetInfoBySongsheetId(id);
		return musicSongsheetMapper.delete(id) == 1;
	}

	@Override
	public boolean MusicSongsheetAdd(MusicSongsheet musicSongsheet) {
		musicSongsheet.setDate(new Date());
		return musicSongsheetMapper.add(musicSongsheet) == 1;
	}

	@Override
	public MusicSongsheet getMusicSongsheetById(Integer id) {
		return musicSongsheetMapper.findMusicSongsheetById(id);
	}

	@Override
	public boolean MusicSongsheetUpdate(MusicSongsheet musicSongsheet) {
		musicSongsheet.setDate(new Date());
		return musicSongsheetMapper.update(musicSongsheet);
	}

	@Override
	public List<MusicSongsheet> getMusicSongsheetTop(int topNum) {
		return musicSongsheetMapper.findMusicSongsheetTop(topNum);
	}

}

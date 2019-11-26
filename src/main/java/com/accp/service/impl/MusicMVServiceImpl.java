package com.accp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.MusicMVMapper;
import com.accp.pojo.MusicMV;
import com.accp.service.MusicMVService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MusicMVServiceImpl implements MusicMVService {
	@Autowired
	private MusicMVMapper musicMVMapper;
	
	@Override
	public PageInfo<MusicMV> getAllMusicMV(Integer pageSize, Integer pageNum, String themeName, Integer musicSingerId) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(musicMVMapper.findAllMusicMV(themeName,musicSingerId));
	}

	@Override
	public boolean musicMVDelete(Integer id) {
		return musicMVMapper.delete(id) == 1; 
	}

	@Override
	public boolean musicMVAdd(MusicMV musicMV) {
		musicMV.setDate(new Date());
		return musicMVMapper.add(musicMV) == 1;
	}

	@Override
	public MusicMV getMusicMVById(Integer id) {
		return musicMVMapper.findMusicMVById(id);
	}

	@Override
	public boolean musicMVUpdate(MusicMV musicMV) {
		musicMV.setDate(new Date());
		return musicMVMapper.update(musicMV) == 1;
	}

	@Override
	public List<MusicMV> getTopMusicMV(Integer pageNum,Integer pageSize) {
		return musicMVMapper.findTopMusicMV(pageNum,pageSize);
	}

	@Override
	public PageInfo<MusicMV> getMusicMVBySingerId(Integer singerId,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(musicMVMapper.findMusicMVBySingerId(singerId));
	}

}

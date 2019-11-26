package com.accp.service;

import java.util.List;

import com.accp.pojo.MusicMV;
import com.github.pagehelper.PageInfo;

public interface MusicMVService {
	PageInfo<MusicMV> getAllMusicMV(Integer pageSize,Integer pageNum,String themeName,Integer musicSingerId);

	boolean musicMVDelete(Integer id);

	boolean musicMVAdd(MusicMV musicMV);

	MusicMV getMusicMVById(Integer id);

	boolean musicMVUpdate(MusicMV musicMV);

	List<MusicMV> getTopMusicMV(Integer pageNum,Integer pageSize);
	
	PageInfo<MusicMV> getMusicMVBySingerId(Integer singerId,Integer pageNum,Integer pageSize);
}

package com.accp.service;

import java.util.List;

import com.accp.pojo.Music;
import com.github.pagehelper.PageInfo;

public interface MusicSongsheetInfoService {

	PageInfo<Music> getMusicSongsheetInfoBySongsheetId(Integer pageNum,Integer pageSize,Integer songsheetId,String name);

	List<Integer> getMusicIdByMusicSongsheetId(Integer SongsheetId);

	boolean musicSongsheetInfoAdd(Integer[] musicIds, Integer songsheetId);

}

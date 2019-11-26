package com.accp.service;

import java.util.List;

import com.accp.pojo.MusicSongsheet;
import com.github.pagehelper.PageInfo;

public interface MusicSongsheetService {
	PageInfo<MusicSongsheet> getMusicSongsheetByName(Integer pageSize,Integer pageNum,String name);

	boolean musicSongsheetDelete(Integer id);

	boolean MusicSongsheetAdd(MusicSongsheet musicSongsheet);

	MusicSongsheet getMusicSongsheetById(Integer id);

	boolean MusicSongsheetUpdate(MusicSongsheet musicSongsheet);

	List<MusicSongsheet> getMusicSongsheetTop(int topNum);
}

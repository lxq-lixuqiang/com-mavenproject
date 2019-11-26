package com.accp.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.pojo.Music;
import com.accp.pojo.MusicSinger;
import com.accp.service.MusicMVService;
import com.accp.service.MusicService;
import com.accp.service.MusicSingerService;
import com.accp.service.MusicSongsheetService;
import com.github.pagehelper.PageInfo;

/**
 * 音乐控制器
 * @author Y2项目:李旭强
 *
 */
@Controller
public class MusicController {
	@Autowired
	private MusicService musicService;
	@Autowired
	private MusicSingerService musicSingerService;
	@Autowired
	private MusicMVService musicMVService;
	private Integer topNum=5;
	@Autowired
	private MusicSongsheetService musicSongsheetService;

	@GetMapping("music")
	public String music(Model model) {
		//轮播栏
		model.addAttribute("topMusicMVs",musicMVService.getTopMusicMV(0,topNum));
		//精选歌单
		model.addAttribute("topMusicSongsheets",musicSongsheetService.getMusicSongsheetTop(5));
		//MV推荐
		model.addAttribute("musicMVs",musicMVService.getTopMusicMV(topNum,3));
		//热门歌手
		model.addAttribute("pageInfoMusicSinger", musicSingerService.getAllMusicSinger(1, 14, null, null));
		//新歌发布
		model.addAttribute("pageInfoMusics",musicService.getMusicBySongNameAndSingerName(10,1,null,null));
		return "music";
	}
	
	@GetMapping("musicSingerSelect")
	@ResponseBody
	public PageInfo<MusicSinger> musicSingerSelect(Integer pageNum,Integer pageSize,String name) {
		return musicSingerService.getAllMusicSinger(pageNum, pageSize, name, null);
	}
	
	@GetMapping("musicAndSonger")
	@ResponseBody
	public PageInfo<Music> musicAndSonger(Integer pageNum,Integer pageSize,String songName,String singerName) {
		return musicService.getMusicBySongNameAndSingerName(pageSize, pageNum, songName, singerName);
	}
}

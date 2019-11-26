package com.accp.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.accp.pojo.Music;
import com.accp.pojo.MusicMV;
import com.accp.pojo.MusicSinger;
import com.accp.service.MusicMVService;
import com.accp.service.MusicService;
import com.accp.service.MusicSingerService;
import com.accp.web.formbean.MusicSingerFormBean;
import com.accp.web.formbean.MusicSingerFormBean.MusicSingerBeanGroupSequence;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
import com.github.pagehelper.PageInfo;

@Controller
public class MusicSingerController {
	@Autowired
	private MusicSingerService musicSingerService;
	private Integer size=9;
	private Integer num=1;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private MusicMVService musicMVService;
	@Autowired
	private MusicService musicService;

	@GetMapping("musicSinger")
	public String musicSinger(Integer pageNum,Integer pageSize,String name,Integer sex,Model model) {
		name=WebTools.memory(name, "MusicSinger_name", null);
		sex=WebTools.memory(sex, "MusicSinger_sex", -1);
		pageSize=WebTools.memory(pageSize, "MusicSinger_pageSize", size);
		pageNum=WebTools.memory(pageNum, "MusicSinger_pageNum", num);
		PageInfo<MusicSinger> musicSingers=musicSingerService.getAllMusicSinger(pageNum, pageSize, name, sex);
		model.addAttribute("pageInfo", musicSingers);
		//生成分页按钮
		List<String> pagingButton=WebTools.pagingButton(musicSingers.getPageNum(),musicSingers.getPages());
		model.addAttribute("MusicSinger_pagingButton", pagingButton);
		
		WebTools.setSession(Common.DATA_MANAGEMENT,"musicSinger");
		WebTools.sessionToRequest(Common.MSG);
		WebTools.setSession("MusicSinger_name", name);
		WebTools.setSession("MusicSinger_sex", sex);
		WebTools.setSession("MusicSinger_pageSize", pageSize);
		WebTools.setSession("MusicSinger_pageNum", pageNum);
		WebTools.setSession("MusicSinger_pagingButton", pagingButton);
		return "musicSinger";
	}
	
	@GetMapping("musicSingerAdd")
	public String musicSingerAdd(MusicSingerFormBean musicSingerBean) {
		return "musicSingerAdd";
	}
	
	@PostMapping("musicSingerAdd")
	public String musicSingerAdd(@Validated(MusicSingerBeanGroupSequence.class)MusicSingerFormBean musicSingerBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "musicSingerAdd";
		}
		if(file.getSize() == 0) {
			errors.rejectValue("singerPicture","MusicSingerController.singerPicture.NotBlank","请选择图片！");
			return "musicSingerAdd";
		}
		
		//存储位置
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/musicSingerImg");
		String absPath = Common.UPLOAD_FILE+"img/musicSingerImg";
		//随机生成文件名加原文件后缀
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String pictureName=UUID.randomUUID().toString();
		//上传文件
		file.transferTo(new File(absPath,pictureName+suffix));
		musicSingerBean.setSingerPicture(pictureName+suffix);
		
		MusicSinger musicSinger = new MusicSinger();
		musicSinger.setSingerPicture(musicSingerBean.getSingerPicture());
		musicSinger.setSex(musicSingerBean.getSex());
		musicSinger.setName(musicSingerBean.getName());
		musicSinger.setSynopsis(musicSingerBean.getSynopsis());
		
		boolean isOk=musicSingerService.MusicSingerAdd(musicSinger);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG,messageSource.getMessage("MusicSingerController.musicSingerAdd.Fail", null, "保存失败，请稍后再试！", locale));
		}
		WebTools.setSession(Common.MSG,messageSource.getMessage("MusicSingerController.musicSingerAdd.Success", null, "保存成功！", locale));
		return "redirect:musicSinger";
	}
	
	@GetMapping("musicSingerDelete")
	public String musicSingerDelete(@RequestParam Integer id) {
		boolean isOk=musicSingerService.MusicSingerDelete(id);
		Locale locale=LocaleContextHolder.getLocale();
		WebTools.setSession(Common.MSG,isOk?
					messageSource.getMessage("MusicSingerController.musicSingerDelete.Success", null, "删除成功！", locale) :
					messageSource.getMessage("MusicSingerController.musicSingerDelete.Fail", null, "删除失败，请稍后再试！", locale));
		return "redirect:musicSinger";
	}
	
	@GetMapping("musicSingerUpdate")
	public String musicSingerUpdate(@RequestParam Integer id,MusicSingerFormBean musicSingerBean,Model model) {
		MusicSinger musicSinger=musicSingerService.getMusicSingerById(id);
		model.addAttribute("musicSingerFormBean",musicSinger);
		WebTools.setSession("singerPicture", musicSinger.getSingerPicture());
		return "musicSingerUpdate";
	}
	
	@PostMapping("musicSingerUpdate")
	public String musicSingerUpdate(@Validated(MusicSingerBeanGroupSequence.class)MusicSingerFormBean musicSingerBean,Errors errors,MultipartFile file,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			return "musicSingerUpdate";
		}
		if(file != null && file.getSize()>0) {
			//存储位置
			//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"img/musicSingerImg");
			String absPath = Common.UPLOAD_FILE+"img/musicSingerImg";
			//随机生成文件名加原文件后缀
			String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String pictureName=UUID.randomUUID().toString();
			//上传文件
			file.transferTo(new File(absPath,pictureName+suffix));
			musicSingerBean.setSingerPicture(pictureName+suffix);
		}
		MusicSinger musicSinger=new MusicSinger();
		musicSinger.setId(musicSingerBean.getId());
		musicSinger.setName(musicSingerBean.getName());
		musicSinger.setSex(musicSingerBean.getSex());
		musicSinger.setSingerPicture(musicSingerBean.getSingerPicture());
		musicSinger.setSynopsis(musicSingerBean.getSynopsis());
		
		boolean isOk=musicSingerService.updateMusicSinger(musicSinger);
		Locale locale=LocaleContextHolder.getLocale();
		if(!isOk) {
			model.addAttribute(Common.MSG, messageSource.getMessage("MusicSingerController.musicSingerUpdate.Fail", null,"修改失败，请稍后再试！", locale));
			return "musicSingerUpdate";
		}
		WebTools.setSession(Common.MSG, messageSource.getMessage("MusicSingerController.musicSingerUpdate.Success", null,"修改成功！", locale));
		return "redirect:musicSinger";
	}
	
	@GetMapping("musicSingerVisit")
	public String musicSingerVisit(@RequestParam Integer id,Model model) {
		model.addAttribute("musicSinger",musicSingerService.getMusicSingerById(id));
		model.addAttribute("pageInfoMusicMVs",musicMVService.getMusicMVBySingerId(id,1,10));
		model.addAttribute("pageInfoMusics",musicService.getMusicBySingerId(id,1,13,null));
		return "musicSingerVisit";
	}
	
	@GetMapping("musicAjax")
	@ResponseBody
	public PageInfo<Music> musicAjax(Integer pageNum,Integer pageSize,Integer singerId) {
		return musicService.getMusicBySingerId(singerId, pageNum, pageSize,null);
	}
	
	@GetMapping("musicMVAjax")
	@ResponseBody
	public PageInfo<MusicMV> musicMVAjax(Integer pageNum,Integer pageSize,Integer singerId) {
		return musicMVService.getMusicMVBySingerId(singerId, pageNum, pageSize);
	}
	
	@GetMapping("downloadMusic")
	public HttpEntity<byte[]> downloadMusic(String songName,HttpServletRequest request) throws IOException {
		//String absPath = request.getServletContext().getRealPath(Common.UPLOAD_FILE+"audio/music");
		String absPath = Common.UPLOAD_FILE+"audio/music";
		File file= new File(absPath, songName);
		byte[] body=FileUtils.readFileToByteArray(file);
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentDispositionFormData("attachment", songName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
	}
	
}

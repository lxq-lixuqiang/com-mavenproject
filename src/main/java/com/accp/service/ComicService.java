package com.accp.service;

import java.util.List;

import com.accp.pojo.Comic;
/**
 * 动漫业务逻辑层
 * @author Y2项目:李旭强
 *
 */
public interface ComicService {
	List<Comic> getComicByRegionAndRemark(String region,String remark,Integer top);

	List<Comic> getComicAll(String type, String name, Integer pageNum, Integer pageSize);

	long getComicAllCount(String type, String name);

	boolean deleteById(Integer id);

	Comic getComicById(Integer id);

	Boolean isNoHas(Integer id, String name, String type, String region);

	Boolean updateComic(Comic comic);

	Boolean addComic(Comic comic);

	List<Comic> getMaxDateComic(Integer size,String region);
}

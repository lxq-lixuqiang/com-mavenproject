package com.accp.service;


import java.util.List;

import com.accp.pojo.Game;
import com.github.pagehelper.PageInfo;

/**
 * 游戏业务逻辑层
 * @author Y2项目:李旭强
 *
 */
public interface GameService {

	PageInfo<Game> getGameByNameAndType(Integer pageNum, Integer pageSize, String nameAndType);

	boolean gameAdd(Game game);

	boolean gameDelete(Integer id);

	Game getGameById(Integer id);

	boolean gameUpdate(Game game);

	List<Game> getGameByClassificationId(Integer pageSize,Integer classificationId);

	List<Game> getGameByPlatformId(Integer pageSize,Integer platformId);
	
	PageInfo<Game> getGameByClassificationIdAndPlatformIdAndTypeIdAndLanguage(Integer pageNum, Integer pageSize, Integer classificationId,Integer platformId,Integer typeId,String language,String name);

}

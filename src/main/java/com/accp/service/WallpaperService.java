package com.accp.service;



import com.accp.pojo.Wallpaper;
import com.github.pagehelper.PageInfo;
/**
 * 壁纸业务逻辑层
 * @author Y2项目:李旭强
 *
 */
public interface WallpaperService {
	
	PageInfo<Wallpaper> getWallpaperAll(String name,Integer pageSize,Integer pageNum);

	boolean deleteWallpaperById(Integer id);

	boolean updateWallpaper(Wallpaper wallpaper);

	boolean addWallpaper(Wallpaper wallpaper);

	PageInfo<Wallpaper> getWallpaperByWallpaperTheme(Integer pageNum, Integer pageSize, Integer wallpaperThemeId);

}

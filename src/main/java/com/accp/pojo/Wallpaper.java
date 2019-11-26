package com.accp.pojo;


import com.accp.pojo.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 壁纸类
 * @author Y2项目:李旭强
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class Wallpaper extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 4143419217200151363L;
	private String wallpaper ;
	private WallpaperTheme wallpaperTheme;
}

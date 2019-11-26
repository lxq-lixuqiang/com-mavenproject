package com.accp.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class WallpaperTheme extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 1774754378837464519L;
	private String wallpaper ;
	private String theme ;
	private Date date ;
	private WallpaperType wallpaperType ;
}

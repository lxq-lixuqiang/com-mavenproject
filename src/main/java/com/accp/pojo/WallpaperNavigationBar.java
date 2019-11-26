package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class WallpaperNavigationBar extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 4264325797749264247L;
	private String wallpaper;
}

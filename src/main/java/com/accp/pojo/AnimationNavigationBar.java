package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class AnimationNavigationBar extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 1039372070849092374L;
	private String animationWallpaper;
}

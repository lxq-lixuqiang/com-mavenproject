package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class GameWallpaper extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 4648921727951332563L;
	private String wallpaper ;
	private Game gameId ;

}

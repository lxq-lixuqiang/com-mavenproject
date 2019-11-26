package com.accp.pojo;

import java.util.Date;

/**
 * 游戏类
 * @author Y2项目:李旭强
 *
 */
import com.accp.pojo.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class Game extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 1838349867806146910L;
	private String picture ;
	private String name ;
	private GamePlatform platformId ;
	private String language ;
	private String issuer ;
	private GameType typeId ;
	private GameClassification classificationId ;
	private String game  ;
	private String gamePath ;
	private String content ;
	private String gameExplain;
	private Date date ;
}

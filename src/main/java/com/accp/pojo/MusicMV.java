package com.accp.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class MusicMV extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 1417839821669222184L;
	private String themePicture ;
	private String themeName ;
	private String mv ;
	private MusicSinger musicSinger ;
	private Date date ;
}

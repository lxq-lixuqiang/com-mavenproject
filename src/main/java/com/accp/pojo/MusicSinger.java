package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class MusicSinger extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 4783530090112401698L;
	private String singerPicture ;
	private String name ;
	private Integer sex ;
	private String synopsis ;
}

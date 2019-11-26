package com.accp.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class MusicSongsheet extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -6626657395642134113L;
	private String picture ;
	private String name ;
	private Date date ;
}

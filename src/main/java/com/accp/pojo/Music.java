package com.accp.pojo;


import java.util.Date;

import com.accp.pojo.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
/**
 * 音乐类
 * @author Y2项目:李旭强
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class Music extends BaseEntity {
	 @Getter
	private static final long serialVersionUID = -5532443424534165971L;
	private String music ;
	private String songName ;
	private Date date ;
	private MusicSinger musicSinger ;
}

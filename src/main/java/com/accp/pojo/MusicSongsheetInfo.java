package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class MusicSongsheetInfo extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -6862494006398180936L;
	private MusicSongsheet musicSongsheet;
	private Music music;
}

package com.accp.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class AnimationEntertainmentAnimation extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -8851668380003736276L;
	private String picture ;
	private String name ;
	private String entertainmentAnimation ;
	private String entertainmentAnimationPath ;
	private AnimationEntertainmentType entertainmentTypeId ;
	private Date dateTime ;
}

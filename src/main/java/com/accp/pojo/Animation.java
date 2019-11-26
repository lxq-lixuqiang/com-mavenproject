package com.accp.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class Animation extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 3156034174146370624L;
	private String setName ;
	private AnimationInfo animationInfoId ;
	private Date dateTime;
	private String animation ;
	private String animationPath ;
}

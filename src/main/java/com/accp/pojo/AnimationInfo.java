package com.accp.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class AnimationInfo extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 7608813187696984027L;
	private String animationPicture ;
	private String animationName ;
	private String animationQuarter ;
	private String animationType ;
	private Date animationDate ;
	private AnimationClassification classificationId ;
	private String animationContent ;
}

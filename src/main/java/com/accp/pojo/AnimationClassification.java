package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class AnimationClassification extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -7439232973993134915L;
	private String name ;
}

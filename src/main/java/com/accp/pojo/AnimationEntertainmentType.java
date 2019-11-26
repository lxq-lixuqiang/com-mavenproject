package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class AnimationEntertainmentType extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 1278422255928742266L;
	private String typeName;
}

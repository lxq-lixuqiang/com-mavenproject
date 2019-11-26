package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class AnimationType extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 6122887603873994014L;
	private String type;
}

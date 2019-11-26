package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class GamePlatform extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 9058720461418259452L;
	private String platformName;
}

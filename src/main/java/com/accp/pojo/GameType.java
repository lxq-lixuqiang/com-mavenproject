package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class GameType extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -7152782643137169822L;
	private String typeName;
}

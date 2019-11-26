package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class GameClassification extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -8038782710463971743L;
	private String classificationName;
}

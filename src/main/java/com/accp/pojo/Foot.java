package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class Foot extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 8972007418861143981L;
	private String name;
	private String path;
}

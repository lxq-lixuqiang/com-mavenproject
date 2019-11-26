package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class LoginAndRegister extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -2602683462110598625L;
	private String picture ;
	private Integer type ;
}

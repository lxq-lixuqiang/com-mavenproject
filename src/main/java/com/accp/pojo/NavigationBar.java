package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class NavigationBar extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -2879295420284898909L;
	private String name ;
	private String path ;
}

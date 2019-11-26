package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class WallpaperType extends BaseEntity{
	@Getter
	private static final long serialVersionUID = 4643551109477465287L;
	private String name;
}

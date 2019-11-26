package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class UserHeadPicture extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -1573046639975517979L;
	private String headPicture;
}

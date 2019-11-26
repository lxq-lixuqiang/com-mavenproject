package com.accp.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class GameInformationTheme extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -9173545919434063631L;
	private String picture ;
	private String theme ;
	private Date date ;
}

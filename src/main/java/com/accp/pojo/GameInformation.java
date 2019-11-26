package com.accp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class GameInformation extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 2188887219626270313L;
	private GameInformationTheme informationThemeId;
	private Integer serialNumber ;
	private String infoOrImg ;
	private int infoOrImgType;
}

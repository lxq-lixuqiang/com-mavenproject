package com.accp.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class HomeNavigationBar extends BaseEntity{
	@Getter
	private static final long serialVersionUID = -3148616068823729360L;
	private String picture;
	private String title ;
	private String backgroundColor ;
	private Date date;
	private String homeNavigationBar ;
	private String homeNavigationBarPath ;
}

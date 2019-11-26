package com.accp.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class Illustrations extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 448485887313987479L;
	private String picture ;
	private String title ;
	private String theme ;
	private String author ;
	private Date datetime ;
	private String content ;
}

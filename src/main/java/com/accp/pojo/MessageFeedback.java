package com.accp.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class MessageFeedback extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 5230335762465133595L;
	private String title ;
	private String sender ;
	private Date dateTime ;
	private Integer type ;
	private String content ;
}

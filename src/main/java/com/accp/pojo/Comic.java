package com.accp.pojo;

import java.util.Date;

import com.accp.pojo.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 动漫类
 * @author Y2项目:李旭强
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class Comic extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 3417296209416271046L;
	private String imgpath ;
	private String name ;
	private Integer setnumber ;
	private String type ;
	private Date date ;
	private String region ;
	private String address ;
	private String remark ;
}

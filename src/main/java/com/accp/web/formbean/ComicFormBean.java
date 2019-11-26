package com.accp.web.formbean;

import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.accp.pojo.BaseEntity;
import com.accp.web.util.GroupA;
import com.accp.web.util.GroupB;

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
public class ComicFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 3417296209416271046L;
	private String imgpath ;
	
	@NotBlank(message="{Comic.name.NotBlank}",groups=GroupA.class)
	@Length(max=30,message="{Comic.name.Length}",groups=GroupB.class)
	private String name ;
	
	@NotNull(message="{Comic.setnumber.NotNull}",groups=GroupA.class)
	@Min(value=1,message="{Comic.setnumber.Min}",groups=GroupB.class)
	private Integer setnumber ;
	
	@NotBlank(message="{Comic.type.NotBlank}",groups=GroupA.class)
	@Length(max=30,message="{Comic.type.Length}",groups=GroupB.class)
	private String type ;
	
	@NotNull(message="{Comic.date.NotNull}",groups=GroupA.class)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date ;
	
	@NotBlank(message="{Comic.region.NotBlank}",groups=GroupA.class)
	@Length(max=10,message="{Comic.region.Length}",groups=GroupB.class)
	private String region ;
	
	@NotBlank(message="{Comic.address.NotBlank}",groups=GroupA.class)
	@Length(max=100,message="{Comic.address.Length}",groups=GroupB.class)
	private String address ;
	private String remark ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface comicGroupSequence{}
	
}

package com.accp.web.formbean;

import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.accp.pojo.BaseEntity;
import com.accp.web.util.GroupA;
import com.accp.web.util.GroupB;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class IllustrationsFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 448485887313987479L;
	private String picture ;
	
	@NotBlank(message="{IllustrationsBean.title.NotBlank}",groups=GroupA.class)
	@Length(max=20,message="{IllustrationsBean.title.Length}",groups=GroupB.class)
	private String title ;
	
	@NotBlank(message="{IllustrationsBean.theme.NotBlank}",groups=GroupA.class)
	@Length(max=30,message="{IllustrationsBean.theme.Length}",groups=GroupB.class)
	private String theme ;
	
	@NotBlank(message="{IllustrationsBean.author.NotBlank}",groups=GroupA.class)
	@Length(max=20,message="{IllustrationsBean.author.Length}",groups=GroupB.class)
	private String author ;
	private Date datetime ;
	
	@Length(max=200,message="{IllustrationsBean.content.Length}",groups=GroupA.class)
	private String content ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface updateIllustrationsBeanGroupSequence{}
}

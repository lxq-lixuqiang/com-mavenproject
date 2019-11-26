package com.accp.web.formbean;

import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class MessageFeedbackFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -5991868004702266730L;
	
	@NotBlank(message="{MessageFeedbackBean.title.NotBlank}",groups=GroupA.class)
	@Length(max=30,message="{MessageFeedbackBean.title.Length}",groups=GroupB.class)
	private String title ;
	
	private String sender ;
	private Date dateTime ;
	
	@NotNull(message="{MessageFeedbackBean.type.NotNull}",groups=GroupA.class)
	private Integer type ;
	
	@NotBlank(message="{MessageFeedbackBean.content.NotBlank}",groups=GroupA.class)
	@Length(max=500,message="{MessageFeedbackBean.content.Length}",groups=GroupB.class)
	private String content ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface MessageFeedbackBeanGroupSequence{}
}

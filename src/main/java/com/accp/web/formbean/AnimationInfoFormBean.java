package com.accp.web.formbean;

import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.accp.pojo.AnimationClassification;
import com.accp.pojo.BaseEntity;
import com.accp.web.util.GroupA;
import com.accp.web.util.GroupB;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class AnimationInfoFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -2732147925904283834L;
	private String animationPicture ;
	
	@NotBlank(message="{AnimationInfoFormBean.animationName.NotBlank}",groups=GroupA.class)
	@Length(max=40,message="{AnimationInfoFormBean.animationName.Length}",groups=GroupB.class)
	private String animationName ;
	private String animationQuarter ;
	
	@NotBlank(message="{AnimationInfoFormBean.animationType.NotBlank}",groups=GroupA.class)
	@Length(max=100,message="{AnimationInfoFormBean.animationType.Length}",groups=GroupB.class)
	private String animationType ;
	private Date animationDate ;
	private AnimationClassification classificationId ;
	
	@NotBlank(message="{AnimationInfoFormBean.animationContent.NotBlank}",groups=GroupA.class)
	@Length(max=200,message="{AnimationInfoFormBean.animationContent.Length}",groups=GroupB.class)
	private String animationContent ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface AnimatinInfoFormBeanGroupSequence{};
}

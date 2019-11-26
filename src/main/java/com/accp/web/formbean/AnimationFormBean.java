package com.accp.web.formbean;

import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.accp.pojo.AnimationInfo;
import com.accp.pojo.BaseEntity;
import com.accp.web.util.GroupA;
import com.accp.web.util.GroupB;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class AnimationFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 522221452259716577L;
	
	@NotBlank(message="{AnimationFormBean.setName.NotBlank}",groups=GroupA.class)
	@Length(max=20,message="{AnimationFormBean.setName.Length}",groups=GroupB.class)
	private String setName ;
	private AnimationInfo animationInfoId ;
	private Date dateTime;
	private String animation ;
	
	@Length(max=1000,message="{AnimationFormBean.animationPath.Length}",groups=GroupA.class)
	private String animationPath ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface AnimationFormBeanGroupSequence{}
}

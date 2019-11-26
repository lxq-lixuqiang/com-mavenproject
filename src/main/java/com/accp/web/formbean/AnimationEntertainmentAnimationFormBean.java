package com.accp.web.formbean;

import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.accp.pojo.AnimationEntertainmentType;
import com.accp.pojo.BaseEntity;
import com.accp.web.util.GroupA;
import com.accp.web.util.GroupB;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class AnimationEntertainmentAnimationFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -8851668380003736276L;
	private String picture ;
	
	@NotBlank(message="{AnimationEntertainmentAnimationFormBean.name.NotBlank}",groups=GroupA.class)
	@Length(max=30,message="{AnimationEntertainmentAnimationFormBean.name.Length}",groups=GroupB.class)
	private String name ;
	private String entertainmentAnimation ;
	
	@Length(max=1000,message="{AnimationEntertainmentAnimationFormBean.entertainmentAnimationPath.Length}",groups=GroupA.class)
	private String entertainmentAnimationPath ;
	private AnimationEntertainmentType entertainmentTypeId ;
	private Date dateTime ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface AnimationEntertainmentAnimationFormBeanGroupSequence{};
}

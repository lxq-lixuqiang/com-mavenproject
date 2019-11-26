package com.accp.web.formbean;

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
public class MusicSingerFormBean extends BaseEntity{
	@Getter
	private static final long serialVersionUID = -4490189721465270154L;
	private String singerPicture ;
	
	@NotBlank(message="{MusicSingerBean.name.NotBlank}",groups=GroupA.class)
	@Length(max=50,message="{MusicSingerBean.name.Length}",groups=GroupB.class)
	private String name ;
	
	@NotNull(message="{MusicSingerBean.sex.NotNull}",groups=GroupA.class)
	private Integer sex ;
	
	@NotBlank(message="{MusicSingerBean.synopsis.NotBlank}",groups=GroupA.class)
	@Length(max=1000,message="{MusicSingerBean.synopsis.Length}",groups=GroupB.class)
	private String synopsis ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface MusicSingerBeanGroupSequence{}
}

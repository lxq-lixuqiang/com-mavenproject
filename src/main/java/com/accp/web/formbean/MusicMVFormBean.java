package com.accp.web.formbean;

import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.accp.pojo.BaseEntity;
import com.accp.pojo.MusicSinger;
import com.accp.web.util.GroupA;
import com.accp.web.util.GroupB;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class MusicMVFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -1525934232080221101L;
	private String themePicture ;
	
	@NotBlank(message="{MusicMVFormBean.themeName.NotBlank}",groups=GroupA.class)
	@Length(max=50,message="{MusicMVFormBean.themeName.Length}",groups=GroupB.class)
	private String themeName ;
	private String mv ;
	private MusicSinger musicSinger ;
	private Date date ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface MusicMVFormBeanGroupSequence{}
}

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
public class MusicSongsheetFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -6626657395642134113L;
	private String picture ;
	
	@NotBlank(message="{MusicSongsheetFormBean.name.NotBlank}",groups=GroupA.class)
	@Length(max=50,message="{MusicSongsheetFormBean.name.Length}",groups=GroupB.class)
	private String name ;
	private Date date ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface MusicSongSheetFormBeanGroupSequence{}
}

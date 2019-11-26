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
/**
 * 音乐类
 * @author Y2项目:李旭强
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class MusicFormBean extends BaseEntity {
	 @Getter
	private static final long serialVersionUID = -5532443424534165971L;
	private String music ;
	
	@NotBlank(message="{MusicFormBean.songName.NotBlank}",groups=GroupA.class)
	@Length(max=50,message="{MusicFormBean.songName.Length}",groups=GroupB.class)
	private String songName ;
	private Date date ;
	private MusicSinger musicSinger ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface musicFormBeanGroupSequence{}
}

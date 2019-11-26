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
public class GameInformationThemeFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -9173545919434063631L;
	private String picture ;
	
	@NotBlank(message="{GameInformationThemeFormBean.theme.NotBlank}",groups=GroupA.class)
	@Length(max=50,message="{GameInformationThemeFormBean.theme.Length}",groups=GroupB.class)
	private String theme ;
	private Date date ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface GameInfomationThemeFormBeanGroupSequence{};
}

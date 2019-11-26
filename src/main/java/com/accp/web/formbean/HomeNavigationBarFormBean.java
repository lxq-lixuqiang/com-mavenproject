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
public class HomeNavigationBarFormBean extends BaseEntity{
	@Getter
	private static final long serialVersionUID = 7598442130170104011L;
	private String picture;
	
	@NotBlank(message="{HomeNavigationBarFormBean.title.NotBlank}",groups=GroupA.class)
	@Length(max=30,message="{HomeNavigationBarFormBean.title.Length}",groups=GroupB.class)
	private String title ;
	
	@NotBlank(message="{HomeNavigationBarFormBean.backgroundColor.NotBlank}",groups=GroupA.class)
	@Length(max=20,message="{HomeNavigationBarFormBean.backgroundColor.Length}",groups=GroupB.class)
	private String backgroundColor ;
	private Date date;
	private String homeNavigationBar ;
	
	@Length(max=1000,message="{HomeNavigationBarFormBean.homeNavigationBarPath.Length}",groups=GroupA.class)
	private String homeNavigationBarPath ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface HomeNavigationBarFormBeanGroupSequence{};
}

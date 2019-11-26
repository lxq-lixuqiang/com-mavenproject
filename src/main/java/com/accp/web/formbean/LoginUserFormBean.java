package com.accp.web.formbean;


import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;

import com.accp.pojo.BaseEntity;
import com.accp.web.util.GroupA;
import com.accp.web.util.GroupB;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 登录用户类
 * @author Y2项目:李旭强
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class LoginUserFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 252369091250051671L;

	@NotBlank(message="{user.username.NotBlank}",groups=GroupA.class)
	@Length(max=10,message="{user.username.Length}",groups=GroupB.class)
	private String username ;
	
	@NotBlank(message="{user.password.NotBlank}",groups=GroupA.class)
	@Length(min=6,max=16,message="{user.password.Length}",groups=GroupB.class)
	private String password ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface GroupSequenceLoginUser {}
}

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
 * 修改密码类
 * @author Y2项目:李旭强
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class UpdatePasswordFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 2139631354529531905L;
	
	@NotBlank(message="{updatePassword.oldpassword.NotBlank}",groups=GroupA.class)
	@Length(min=6,max=16,message="{updatePassword.oldpassword.Length}",groups=GroupB.class)
	private String oldPassword;
	
	@NotBlank(message="{forgetPassword.newpassword.NotBlank}",groups=GroupA.class)
	@Length(min=6,max=16,message="{forgetPassword.newpassword.Length}",groups=GroupB.class)
	private String newPassword;
	
	@NotBlank(message="{forgetPassword.newpassword02.NotBlank}",groups=GroupA.class)
	private String newPassword02;

	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface updatePasswordGroupSequence{}
}

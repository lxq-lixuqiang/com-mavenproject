package com.accp.web.formbean;


import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;

import com.accp.pojo.BaseEntity;
import com.accp.web.util.GroupA;
import com.accp.web.util.GroupB;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 忘记密码类
 * @author Y2项目:李旭强
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class ForgetPasswordFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 1184816411525693182L;
	
	@NotBlank(message="{forgetPassword.phone.NotBlank}",groups=GroupA.class)
	@Pattern(regexp="1[3,5,8]\\d{9}",message="{forgetPassword.phone.Pattern}",groups=GroupB.class)
	private String phone;
	
	@NotBlank(message="{forgetPassword.verification.NotBlank}",groups=GroupA.class)
	@Length(max=6,min=6,message="{forgetPassword.verification.Length}",groups=GroupB.class)
	private String verification;
	
	@NotBlank(message="{forgetPassword.newpassword.NotBlank}",groups=GroupA.class)
	@Length(min=6,max=16,message="{forgetPassword.newpassword.Length}",groups=GroupB.class)
	private String newpassword;
	
	@NotBlank(message="{forgetPassword.newpassword02.NotBlank}",groups=GroupA.class)
	private String newpassword02;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface groupSerquenceForgetPassword{}
}

package com.accp.web.formbean;

import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.accp.pojo.BaseEntity;
import com.accp.pojo.UserHeadPicture;
import com.accp.pojo.User.LoginIdentity;
import com.accp.pojo.User.Sex;
import com.accp.web.util.GroupA;
import com.accp.web.util.GroupB;
import com.accp.web.util.GroupC;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 用户类
 * @author Y2项目:李旭强
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class UserFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -4485845624665364736L;
	private String userImg ;
	
	@NotBlank(message="{user.username.NotBlank}",groups=GroupA.class)
	@Length(max=10,message="{user.username.Length}",groups=GroupB.class)
	private String username ;
	
	@NotBlank(message="{user.password.NotBlank}",groups=GroupA.class)
	@Length(max=16,min=6,message="{user.password.Length}",groups=GroupB.class)
	private String password ;
	
	@NotBlank(message="{user.password02.NotBlank}",groups=GroupA.class)
	private String password02 ;
	
	@NotBlank(message="{user.phone.NotBlank}",groups=GroupA.class)
	@Length(max=11,min=11,message="{user.phone.Length}",groups=GroupB.class)
	@Pattern(regexp="1[3,5,8]\\d{9}",message="{user.phone.Patter}",groups=GroupC.class)
	private String phone ;
	
	private LoginIdentity loginIdentity ;
	private Sex sex ;
	private String hobby ;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthData ;
	private String address ;
	private String email ;
	private UserHeadPicture userHeadPicture ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class,GroupC.class})
	public interface GroupSequenceRegisterUser{}
}

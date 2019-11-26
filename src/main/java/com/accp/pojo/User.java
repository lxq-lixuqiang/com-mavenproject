package com.accp.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.accp.pojo.BaseEntity;

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
public class User extends BaseEntity {
	@Getter
	private static final long serialVersionUID = -4485845624665364736L;
	private String userImg ;
	private String username ;
	private String password ;
	private String phone ;
	private LoginIdentity loginIdentity ;
	private Sex sex ;
	private String hobby ;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthData ;
	private String address ;
	private String email ;
	private UserHeadPicture userHeadPicture ;
	
	public enum Sex{
		/**保密*/
		Secrecyt,
		/**男*/
		Male,
		/**女*/
		Female;
		@Override
		public String toString() {
			return this == Male ? "男":this==Female?"女":"保密";
		}
	}
	public enum LoginIdentity{
		/**管理员*/
		Administrators,
		/**用户*/
		User;
		@Override
		public String toString() {
			return this == Administrators ? "管理员" : "用户";
		}
	}
}

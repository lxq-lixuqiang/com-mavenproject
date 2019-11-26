package com.accp.web.formbean;

import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.accp.pojo.BaseEntity;
import com.accp.pojo.WallpaperType;
import com.accp.web.util.GroupA;
import com.accp.web.util.GroupB;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class WallpaperThemeFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 1774754378837464519L;
	private String wallpaper ;
	
	@NotBlank(message="{WallpaperThemeBean.theme.NotBlank}",groups=GroupA.class)
	@Length(max=20,message="{WallpaperThemeBean.theme.Length}",groups=GroupB.class)
	private String theme ;
	private Date date ;
	private WallpaperType wallpaperType ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface WallpaperThemeBeanGroupSequence{}
}

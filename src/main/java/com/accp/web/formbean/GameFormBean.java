package com.accp.web.formbean;

import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

/**
 * 游戏类
 * @author Y2项目:李旭强
 *
 */
import com.accp.pojo.BaseEntity;
import com.accp.pojo.GameClassification;
import com.accp.pojo.GamePlatform;
import com.accp.pojo.GameType;
import com.accp.web.util.GroupA;
import com.accp.web.util.GroupB;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=true)
public class GameFormBean extends BaseEntity {
	@Getter
	private static final long serialVersionUID = 7174881843316147394L;
	private String picture ;
	
	@NotBlank(message="{GameFormBean.name.NotBlank}",groups=GroupA.class)
	@Length(max=30,message="{GameFormBean.name.Length}",groups=GroupB.class)
	private String name ;
	private GamePlatform platformId ;
	
	@NotBlank(message="{GameFormBean.language.NotBlank}",groups=GroupA.class)
	private String language ;
	
	@NotBlank(message="{GameFormBean.issuer.NotBlank}",groups=GroupA.class)
	@Length(max=50,message="{GameFormBean.issuer.Length}",groups=GroupB.class)
	private String issuer ;
	private GameType typeId ;
	private GameClassification classificationId ;
	private String game  ;
	
	@Length(max=1000,message="{GameFormBean.gamePath.Length}",groups=GroupB.class)
	private String gamePath ;
	
	@NotBlank(message="{GameFormBean.content.NotBlank}",groups=GroupA.class)
	@Length(max=300,message="{GameFormBean.content.Length}",groups=GroupB.class)
	private String content ;
	
	@NotBlank(message="{GameFormBean.gameExplain.NotBlank}",groups=GroupA.class)
	@Length(max=200,message="{GameFormBean.gameExplain.Length}",groups=GroupB.class)
	private String gameExplain;
	private Date date ;
	
	@GroupSequence({Default.class,GroupA.class,GroupB.class})
	public interface GameFormBeanGroupSequence{};
}

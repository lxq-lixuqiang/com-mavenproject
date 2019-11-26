package com.accp.pojo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode()
public abstract class BaseEntity implements Serializable {
	@Getter
	private static final long serialVersionUID = 1488049946819179926L;
	private Integer id;
}

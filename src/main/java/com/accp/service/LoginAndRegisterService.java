package com.accp.service;

import java.util.List;

import com.accp.pojo.LoginAndRegister;

public interface LoginAndRegisterService {
	String getLoginAndRegisterById(Integer type);

	List<LoginAndRegister> getAllLoginAndRegister();

	boolean updateLoginAndRegisterById(Integer id,String picture);
}

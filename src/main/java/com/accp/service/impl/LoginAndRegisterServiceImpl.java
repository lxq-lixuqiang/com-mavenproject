package com.accp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.LoginAndRegisterMapper;
import com.accp.pojo.LoginAndRegister;
import com.accp.service.LoginAndRegisterService;

@Service
public class LoginAndRegisterServiceImpl implements LoginAndRegisterService {
	@Autowired
	private LoginAndRegisterMapper loginAndRegisterMapper;

	@Override
	public String getLoginAndRegisterById(Integer type) {
		return loginAndRegisterMapper.findLoginAndRegisterByType(type);
	}

	@Override
	public List<LoginAndRegister> getAllLoginAndRegister() {
		return loginAndRegisterMapper.findAllLoginAndRegister();
	}

	@Override
	public boolean updateLoginAndRegisterById(Integer id,String picture) {
		return loginAndRegisterMapper.update(id,picture) == 1;
	}

}

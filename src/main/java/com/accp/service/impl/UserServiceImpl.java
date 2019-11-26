package com.accp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.UserMapper;
import com.accp.pojo.User;
import com.accp.pojo.User.LoginIdentity;
import com.accp.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 用户业务逻辑层实现类
 * @author Y2项目:李旭强
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserByname(String username) {
		return userMapper.bynamefind(username);
	}

	@Override
	public boolean addUser(User user) {
		return userMapper.addUser(user)==1;
	}

	@Override
	public boolean getUserNameIsNo(String username) {
		return userMapper.findUsername(username)==1;
	}
	
	@Override
	public boolean getPhoneIsNo(String phone) {
		return userMapper.findPhone(phone)==1;
	}

	@Override
	public String getUserImg(String username) {
		return userMapper.findUserImg(username);
	}

	@Override
	public boolean updatePassword(User forgetPassword) {
		return userMapper.updatePassword(forgetPassword) ==1;
	}

	@Override
	public PageInfo<User> getAllUser(String username,Integer pageSize,Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(userMapper.findAllUser(LoginIdentity.User.toString(),username)); 
	}

	@Override
	public boolean deleteById(int Id) {
		return userMapper.deleteUserById(Id) == 1;
	}

	@Override
	public User getUserById(int id) {
		return userMapper.findUserById(id);
	}

	@Override
	public boolean updateUserAllInfo(User user) {
		return userMapper.updateUserAllInfo(user)==1;
	}

	@Override
	public int isExistsUserHeadPicture(Integer userHeadPictureId) {
		return userMapper.isExistsUserHeadPicture(userHeadPictureId);
	}

	@Override
	public boolean updatePictureId(Integer userHeadPictureId, Integer userId) {
		return userMapper.updatePictureId(userHeadPictureId,userId) == 1;
	}
}

package com.accp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.UserHeadPictureMapper;
import com.accp.pojo.UserHeadPicture;
import com.accp.service.UserHeadPictureService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserHeadPictureServiceImpl implements UserHeadPictureService {
	@Autowired
	private UserHeadPictureMapper userHeadPictureMapper;

	@Override
	public PageInfo<UserHeadPicture> getAll(Integer pageSize,Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(userHeadPictureMapper.findAllUserHeadPicture());
	}

	@Override
	public boolean addUserHeadPicture(UserHeadPicture userHeadPicture) {
		return userHeadPictureMapper.add(userHeadPicture) ==1;
	}

	@Override
	public boolean deleteUserHeadPicture(Integer id) {
		return userHeadPictureMapper.delete(id) == 1;
	}

	@Override
	public boolean updateUserHeadPicture(Integer id, String pictureName) {
		return userHeadPictureMapper.update(id,pictureName) == 1;
	}

}

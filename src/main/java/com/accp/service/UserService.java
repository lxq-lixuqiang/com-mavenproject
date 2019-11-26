package com.accp.service;


import com.accp.pojo.User;
import com.github.pagehelper.PageInfo;


/**
 * 用户业务逻辑层
 * @author Y2项目:李旭强
 *
 */
public interface UserService {

	User getUserByname(String username);

	boolean addUser(User user);

	boolean getUserNameIsNo(String username);
	
	boolean getPhoneIsNo(String phone);

	String getUserImg(String username);

	boolean updatePassword(User forgetPassword);

	PageInfo<User> getAllUser(String username,Integer pageSize,Integer pageNum);

	boolean deleteById(int Id);

	User getUserById(int id);
	
	boolean updateUserAllInfo(User user);

	int isExistsUserHeadPicture(Integer userHeadPictureId);

	boolean updatePictureId(Integer userHeadPictureId, Integer userId);
}

package com.accp.service;

import com.accp.pojo.UserHeadPicture;
import com.github.pagehelper.PageInfo;

public interface UserHeadPictureService {
	PageInfo<UserHeadPicture> getAll(Integer pageSize,Integer pageNum);

	boolean addUserHeadPicture(UserHeadPicture userHeadPicture);

	boolean deleteUserHeadPicture(Integer id);

	boolean updateUserHeadPicture(Integer id, String pictureName);

}

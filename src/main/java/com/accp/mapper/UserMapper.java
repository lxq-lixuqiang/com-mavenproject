package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.accp.pojo.User;

/**
 * 用户数据访问层
 * @author Y2项目:李旭强
 *
 */
public interface UserMapper {
	/**
	 * 按用户名查询用户对象
	 * @param username 用户名
	 * @return 返回用户对象，没有：返回null
	 */
	User bynamefind(@Param("username")String username);

	/**
	 * 新增用户对象
	 * @param user 用户对象
	 * @return 新增影响数据库的受影响行数
	 */
	@Insert("insert into t_user values (null,#{userImg},#{username},#{password},#{phone},#{loginIdentity,typeHandler=org.apache.ibatis.type.EnumTypeHandler},#{sex,typeHandler=org.apache.ibatis.type.EnumTypeHandler},null,null,null,null,#{userHeadPicture.id})")
	int addUser(User user);

	/**
	 * 按用户名查询是否存在
	 * @param username 用户名
	 * @return 返回查询结果数
	 */
	@Select("SELECT count(1) FROM t_user WHERE username = #{username}")
	long findUsername(@Param("username")String username);

	/**
	 * 按手机号码查询是否存在
	 * @param phone 手机号码
	 * @return 返回查询结果数
	 */
	@Select("SELECT count(1) FROM t_user WHERE phone = #{phone}")
	long findPhone(@Param("phone")String phone);

	/**
	 * 按用户名查询用户头像图片名称
	 * @param username 用户名
	 * @return 返回用户头像图片名称，没有:返回null
	 */
	@Select("select userImg from t_user where username=#{username}")
	String findUserImg(@Param("username")String username);

	/**
	 * 忘记密码，按手机号更新密码
	 * @param forgetPassword 忘记密码
	 * @return 更新返回数据库受影响行数
	 */
	@Update("UPDATE t_user SET password = #{password} WHERE phone = #{phone}")
	int updatePassword(User User);

	/**
	 * 按用户名分页查询所有用户对象集合
	 * @param loginIdentity 身份:用户
	 * @param username 用户名-关键字
	 * @return 用户对象集合
	 */
	List<User> findAllUser(@Param("loginIdentity")String loginIdentity,@Param("username")String username);
	
	/**
	 * 通过Id删除用户对象
	 * @param id 用户Id
	 * @return 删除返回数据库受影响行数
	 */
	@Delete("DELETE FROM t_user WHERE id = #{id}")
	int deleteUserById(@Param("id")int id);

	/**
	 * 通过id查询用户对象
	 * @param id 用户id
	 * @return 返回用户对象，没有-返回null
	 */
	@Select("select id,userImg,username,phone from t_user where id= #{id}")
	User findUserById(@Param("id")int id);

	/**
	 * 更新用户对象的全部信息
	 * @param user 用户对象
	 * @return 更新返回数据库收影响行数
	 */
	int updateUserAllInfo(User user);

	@Select("select count(1) from t_user where userHeadPictureId=#{userHeadPictureId}")
	int isExistsUserHeadPicture(Integer userHeadPictureId);

	@Update("update t_user set userHeadPictureId=#{userHeadPictureId} where id=#{userId} ")
	int updatePictureId(@Param("userHeadPictureId")Integer userHeadPictureId,@Param("userId")Integer userId);
}

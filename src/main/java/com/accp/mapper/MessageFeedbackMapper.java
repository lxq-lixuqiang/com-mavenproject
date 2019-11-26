package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.accp.pojo.MessageFeedback;

public interface MessageFeedbackMapper {
	List<MessageFeedback> findMessageFeedbackByType(@Param("type")Integer type,@Param("title")String title);

	@Delete("delete from user_messageFeedback where id=#{id}")
	int delete(Integer id);

	@Insert("insert into user_messageFeedback values(null,#{title},#{sender},#{dateTime},#{type},#{content})")
	int add(MessageFeedback messageFeedback);
}

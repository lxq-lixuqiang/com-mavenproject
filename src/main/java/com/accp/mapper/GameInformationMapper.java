package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.accp.pojo.GameInformation;

public interface GameInformationMapper {

	List<GameInformation> findGameInformationByInformationThemeId(Integer informationThemeId);

	@Delete("delete from game_information where id=#{id}")
	int delete(Integer id);

	@Select("select serialNumber from game_information where informationThemeId=#{informationThemeId} order by serialNumber desc limit 1")
	int findSerialNumberByInformationThemeId(Integer informationThemeId);

	@Select("select count(1) from game_information where informationThemeId=#{informationThemeId}")
	int getSerialNumberNumByInformationThemeId(Integer informationThemeId);
	
	@Insert("insert into game_information values(null,#{informationThemeId.id},#{serialNumber},#{infoOrImg},#{infoOrImgType})")
	int add(GameInformation gameInformation);

	int update(GameInformation gameInformation);

	@Delete("delete from game_information where informationThemeId = #{informationThemeId}")
	int deleteByInformationThemeId(Integer informationThemeId);


}

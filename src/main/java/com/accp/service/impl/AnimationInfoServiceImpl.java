package com.accp.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.mapper.AnimationInfoMapper;
import com.accp.mapper.AnimationMapper;
import com.accp.pojo.AnimationInfo;
import com.accp.service.AnimationInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AnimationInfoServiceImpl implements AnimationInfoService {
	@Autowired
	private AnimationInfoMapper animationInfoMapper;
	@Autowired
	private AnimationMapper animationMapper;

	@Override
	public PageInfo<AnimationInfo> getAnimationInfoByNameAndType(Integer pageNum,Integer pageSize,String year,String animationName, String animationType,String animationQuarter) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(animationInfoMapper.findAnimationInfoByNameAndType(year, animationName, animationType,animationQuarter));
	}

	@Override
	public boolean animationInfoAdd(AnimationInfo animationInfo) {
		DateFormat df=new SimpleDateFormat("MM");
		Date date=new Date();
		Integer month=Integer.parseInt(df.format(date));
		String quarter="";
		if(month>=1 && month<=3) {
			quarter="冬";//冬季
		}else if(month >=4 && month <=6) {
			quarter="春";//春季
		}else if(month >=7 && month <=9) {
			quarter="夏";//夏季
		}else if(month >=10 && month <=12) {
			quarter="秋";//秋季
		}
		animationInfo.setAnimationQuarter(quarter);
		animationInfo.setAnimationDate(date);
		return animationInfoMapper.add(animationInfo) == 1;
	}

	@Override
	public boolean deleteAnimationInfoById(Integer id) {
		animationMapper.deleteAnimationByAnimationInfoId(id);
		return animationInfoMapper.delete(id) == 1;
	}

	@Override
	public AnimationInfo getAnimationInfoById(Integer id) {
		return animationInfoMapper.findAnimationInfoById(id);
	}

	@Override
	public boolean animationInfoUpdate(AnimationInfo animationInfo) {
		DateFormat df=new SimpleDateFormat("MM");
		Date date=new Date();
		Integer month=Integer.parseInt(df.format(date));
		String quarter="";
		if(month>=1 && month<=3) {
			quarter="冬";//冬季
		}else if(month >=4 && month <=6) {
			quarter="春";//春季
		}else if(month >=7 && month <=9) {
			quarter="夏";//夏季
		}else if(month >=10 && month <=12) {
			quarter="秋";//秋季
		}
		animationInfo.setAnimationQuarter(quarter);
		animationInfo.setAnimationDate(date);
		return animationInfoMapper.update(animationInfo) == 1;
	}

	@Override
	public List<AnimationInfo> getAnimationInfoByClassificationId(Integer pageSize, Integer classificationId) {
		return animationInfoMapper.findAnimationInfoByClassificationId(pageSize,classificationId);
	}

	@Override
	public List<String> getQuarter() {
		return animationInfoMapper.findQuarter();
	}

	@Override
	public List<AnimationInfo> getAnimationInfoByNotClassificationId(Integer notClassificationId, Integer pageSize) {
		return animationInfoMapper.findAnimationInfoByNotClassificationId(notClassificationId,pageSize);
	}

}

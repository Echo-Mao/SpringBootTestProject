package com.nbui.policy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbui.entity.UserInfo;
import com.nbui.policy.entity.UserInfoBrief;
import com.nbui.policy.service.MyImformationService;
import com.nbui.user.dao.*;
@Service
public class MyImformationServiceImpl implements MyImformationService {
@Autowired
private IUserDao IUserDao;
	/**
	 * 根据传入的信息修改用户表信息
	 */
	@Override
	public void UpdateUserInfo(UserInfo userInfo) {
		IUserDao.updateUserInfo(userInfo);		
	}
	/**
	 * 根据传入的用户id查询用户信息
	 */
	@Override
	public UserInfoBrief showUserByLoginId(Integer loginId) {
		UserInfoBrief userInfoBrief = IUserDao.findUserInfoBrief(loginId);
		return userInfoBrief;
	}
	


}

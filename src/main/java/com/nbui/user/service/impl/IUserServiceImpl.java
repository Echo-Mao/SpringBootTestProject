package com.nbui.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbui.entity.UserInfo;
import com.nbui.user.dao.*;
import com.nbui.user.service.IUserService;
@Service
public class IUserServiceImpl implements IUserService{
@Autowired
private IUserDao  IUserDao;

	@Override
	public Integer getUserId(Integer loginId) {
		System.out.println(loginId+".....");
		UserInfo UserInfo= IUserDao.getUserId(loginId);
		return UserInfo.getUserId();
	}

	@Override
	public Integer addUserInfo(Integer loginId) {
		Integer s=IUserDao.addUserInfo(loginId);
		return s;
	}
    
}

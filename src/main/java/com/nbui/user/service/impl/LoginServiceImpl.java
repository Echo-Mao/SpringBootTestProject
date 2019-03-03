package com.nbui.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbui.entity.LoginInfo;
import com.nbui.user.dao.ILoginDao;
import com.nbui.user.dao.IUserDao;
import com.nbui.user.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService{
	@Autowired
	ILoginDao loginDao;
	@Autowired
	IUserDao userDao;
	

	//用户名密码登陆
	@Override
	public LoginInfo loginByNamePwd(LoginInfo login) {
		LoginInfo login2=loginDao.findInfoByNameAndPwd(login);
		if(login2!=null) {
			return login2;
		}else {
			return null;
		}		
	}

	//短信验证登陆,已注册手机号验证通过直接登陆,未注册手机号自动注册，用户名为手机号，初始密码为动态验证码
	@Override
	public LoginInfo loginByPhone(String phone,String pwd) {
		LoginInfo login2=loginDao.findInfoByPhoneNum(phone);
		if(login2!=null) {
			return login2;
		}else{
			LoginInfo login=new LoginInfo();
			login.setPhoneNum(phone);
			login.setLoginName(phone);
			login.setLoginPwd(pwd);
			Integer s=loginDao.addLoginInfo(login);
			if(s>0) {
				LoginInfo login3=loginDao.findInfoByPhoneNum(phone);
				Integer id=login3.getLoginId();
				@SuppressWarnings("unused")
                Integer s2=userDao.addUserInfo(id);
				return login3;
			}else {
			    return null;
			}
		}
		
	}

	//注册登陆用户
	@Override
	public Integer addLoginInfo(LoginInfo login) {
		    Integer s=loginDao.addLoginInfo(login);	
		    return s;
	}

	//根据手机号查找用户
	@Override
	public LoginInfo findLoginByPhone(String phone) {
		LoginInfo login=loginDao.findInfoByPhoneNum(phone);
		if(login!=null) {
			return login;
		}else {
		return null;}
	}
	
   //修改用户登陆状态
	@Override
	public boolean updateLoginState(Integer loginId, String state) {
		Integer s=loginDao.updateLoginState(loginId, state);
		if(s>0) {
			return true;
		}else {
			return false;
		}	
	}
   //修改用户登陆信息
	@Override
	public Integer updateLoginInfo(LoginInfo login) {
		Integer s=loginDao.updateLoginInfo(login);
		return s;
	}
	
	
	

}

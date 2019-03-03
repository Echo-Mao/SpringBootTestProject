package com.nbui.user.service;

import com.nbui.entity.LoginInfo;

public interface ILoginService {
	//账号密码登陆
	LoginInfo loginByNamePwd(LoginInfo login);
	//短信登陆(已注册用户直接登陆，未注册用户手机号作为用户名和联系电话，动态验证码作为初次密码)
	LoginInfo loginByPhone(String phone,String pwd);
	//注册用户
	Integer addLoginInfo(LoginInfo login);
   //根据手机号查询用户是否已注册
	LoginInfo findLoginByPhone(String phone);
    //修改用户登陆状态
	boolean updateLoginState(Integer loginId,String state);
	//修改用户登陆信息
	Integer updateLoginInfo(LoginInfo login);
}

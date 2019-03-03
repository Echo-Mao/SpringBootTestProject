package com.nbui.user.dao;

import com.nbui.entity.LoginInfo;

import io.lettuce.core.dynamic.annotation.Param;

public interface ILoginDao {

    /**
     * >使用电话号码登录
     * 
     * @param phoneNum
     * @return 返回一个根据手机号查询到的登录对象,无记录则返回null
     */
    LoginInfo findInfoByPhoneNum(String phoneNum);

    /**
     * >使用账号密码登录
     * 
     * @param loginInfo
     * @return 返回一个根据账号和密码查询到的登录对象,无记录则返回null
     */
    LoginInfo findInfoByNameAndPwd(LoginInfo loginInfo);

    /**
     * >修改登录名密码
     * 
     * @param loginInfo
     *
     */
    Integer updateLoginInfo(LoginInfo loginInfo);
    
    /**
     * >登陆用户注册
     * 
     * @param loginInfo
     * 
     */
    Integer addLoginInfo(LoginInfo loginInfo);
    
    /**
     * >修改登陆状态
     * 
     * @param Integer
     * 
     * 
     */
    Integer updateLoginState(@Param("loginId")Integer loginId,@Param("state")String state);
    
    
    

}

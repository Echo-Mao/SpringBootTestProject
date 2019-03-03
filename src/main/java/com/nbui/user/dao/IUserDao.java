package com.nbui.user.dao;

import java.util.List;

import com.nbui.entity.UserInfo;
import com.nbui.policy.entity.UserInfoBrief;

/**
 * @author EchoMao
 * @time 2019年1月11日
 * 
 */
public interface IUserDao {

    /**
     * >修改方法调用这个
     * 
     * @param userInfo
     * @return
     */
    int updateUserInfo(UserInfo userInfo);

    /**
     * >删除方法,修改可查看状态为0
     * 
     * @param id
     * @return
     */
    int deleteUserInfo(int id);

    /**
     * >查询所有用户信息
     * 
     */
    List<UserInfo> findAllUserInfo();

    /**
     * >根据用户id查看用户信息
     * 
     */
    UserInfo findUserInfoById(int userId);

    /**
     * >根据保单id查询用户
     * 
     * @param policyId
     * @return
     */
    UserInfo findUserInfoByPolicyId(int policyId);

    /**
     * >注册新用户
     */
    int addUserInfo(Integer loginId);
    
    /**
     * 根据id 查询用户部分信息以及联表信息
     */
    UserInfoBrief findUserInfoBrief(Integer loginId);
    /**
     * 根据loginId获取UserId
     * @param loginId
     * @return
     */
    UserInfo getUserId(Integer loginId);
}

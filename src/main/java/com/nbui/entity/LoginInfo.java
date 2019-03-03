package com.nbui.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author EchoMao
 * @time 2019年1月11日
 * 
 */
@Component
@Scope(scopeName = "prototype")
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = -1314935833060658600L;

    private Integer loginId; // 登录表id
    private String loginName; // 登录名,客户使用登录名,员工使用工号
    private String loginPwd; // 登录密码
    private String phoneNum; // 用户电话号码
    private String loginStatus; // 登录状态
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loginTime; // 登录时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime; // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime; // 上次修改时间
    private Integer viewState; // 查看状态,1为可查看,0为不可查看

    public LoginInfo() {
        super();
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getViewState() {
        return viewState;
    }

    public void setViewState(Integer viewState) {
        this.viewState = viewState;
    }

    @Override
    public String toString() {
        return "LoginInfo [loginId=" + loginId + ", loginName=" + loginName + ", loginPwd=" + loginPwd + ", phoneNum="
                + phoneNum + ", loginStatus=" + loginStatus + ", loginTime=" + loginTime + ", createTime=" + createTime
                + ", modifyTime=" + modifyTime + ", viewState=" + viewState + "]";
    }

}

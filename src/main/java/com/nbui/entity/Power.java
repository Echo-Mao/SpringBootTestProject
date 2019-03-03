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
public class Power implements Serializable {

    private static final long serialVersionUID = -312300942155446179L;

    private Integer powerId; // 权限id
    private String powerName; // 权限名称
    private Integer roleId; // 权限所属的角色id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime; // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime; // 上次修改时间
    private Integer viewState; // 查看状态,1为可查询,0为不可查询

    public Power() {
        super();
    }

    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
        return "Power [powerId=" + powerId + ", powerName=" + powerName + ", roleId=" + roleId + ", createTime="
                + createTime + ", modifyTime=" + modifyTime + ", viewState=" + viewState + "]";
    }

}

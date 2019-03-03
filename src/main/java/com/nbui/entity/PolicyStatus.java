package com.nbui.entity;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author EchoMao
 * @time 2019年1月12日
 * 
 */
@Component
@Scope(scopeName = "prototype")
public class PolicyStatus implements Serializable {

    private static final long serialVersionUID = 7397562841948964536L;

    private Integer statusId; // 保单状态id
    private String statusName; // 保单状态名

    public PolicyStatus() {
        super();
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "PolicyStatus [statusId=" + statusId + ", statusName=" + statusName + "]";
    }

}

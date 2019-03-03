package com.nbui.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author EchoMao
 * @time 2019年1月12日
 * 
 */
@Component
@Scope(scopeName = "prototype")
public class OperationRecode implements Serializable {

    private static final long serialVersionUID = 5578626101621872978L;

    private Integer recordId; // 操作记录id
    private Integer policyId; // 保单id
    private Integer empId; // 操作人员id
    private EmpInfo operator; // 根据操作人员id查询到的操作人员对象
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime; // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime; // 上次修改时间
    private Integer viewState; // 查看状态,1为可查询,0为不可查询

    public OperationRecode() {
        super();
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public EmpInfo getOperator() {
        return operator;
    }

    public void setOperator(EmpInfo operator) {
        this.operator = operator;
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
        return "OperationRecode [recordId=" + recordId + ", policyId=" + policyId + ", empId=" + empId + ", operator="
                + operator + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", viewState=" + viewState
                + "]";
    }

}
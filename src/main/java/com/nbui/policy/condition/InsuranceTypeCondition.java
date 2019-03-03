package com.nbui.policy.condition;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author EchoMao
 * @date 2019年1月17日下午2:07:01
 * 
 */
@Component
@Scope(scopeName = "prototype")
public class InsuranceTypeCondition {

    private String qInsuranceName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date qStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date qEndDate;
    private Integer qStatus;
    private Integer qProgrammeId;

    public String getqInsuranceName() {
        return qInsuranceName;
    }

    public void setqInsuranceName(String qInsuranceName) {
        this.qInsuranceName = qInsuranceName;
    }

    public Date getqStartDate() {
        return qStartDate;
    }

    public void setqStartDate(Date qStartDate) {
        this.qStartDate = qStartDate;
    }

    public Date getqEndDate() {
        return qEndDate;
    }

    public void setqEndDate(Date qEndDate) {
        this.qEndDate = qEndDate;
    }

    public Integer getqStatus() {
        return qStatus;
    }

    public void setqStatus(Integer qStatus) {
        this.qStatus = qStatus;
    }

    public Integer getqProgrammeId() {
        return qProgrammeId;
    }

    public void setqProgrammeId(Integer qProgrammeId) {
        this.qProgrammeId = qProgrammeId;
    }

    @Override
    public String toString() {
        return "InsuranceTypeCondition [qInsuranceName=" + qInsuranceName + ", qStartDate=" + qStartDate + ", qEndDate="
                + qEndDate + ", qStatus=" + qStatus + ", qProgrammeId=" + qProgrammeId + "]";
    }

}

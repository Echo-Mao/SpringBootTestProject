package com.nbui.policy.condition;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author EchoMao
 * @date 2019年1月17日下午4:16:49
 * 
 */
@Component
@Scope(scopeName = "prototype")
public class ProgrammeCondition {

    private Integer qProgrammeId;
    private Double qPercent; // 百分比
    private Double qBasicsMoney; // 基础保费

    public Integer getqProgrammeId() {
        return qProgrammeId;
    }

    public void setqProgrammeId(Integer qProgrammeId) {
        this.qProgrammeId = qProgrammeId;
    }

    public Double getqPercent() {
        return qPercent;
    }

    public void setqPercent(Double qPercent) {
        this.qPercent = qPercent;
    }

    public Double getqBasicsMoney() {
        return qBasicsMoney;
    }

    public void setqBasicsMoney(Double qBasicsMoney) {
        this.qBasicsMoney = qBasicsMoney;
    }

    @Override
    public String toString() {
        return "ProgrammeCondition [qProgrammeId=" + qProgrammeId + ", qPercent=" + qPercent + ", qBasicsMoney="
                + qBasicsMoney + "]";
    }

}

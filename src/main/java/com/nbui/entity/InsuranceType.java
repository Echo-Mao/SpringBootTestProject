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
public class InsuranceType implements Serializable {

    private static final long serialVersionUID = 7421443966573709615L;

    private Integer insuranceId; // 险种id
    private String insuranceName; // 险种名称
    private String example; // 案例
    private String notice; // 赔款须知
    private String applicable; // 适用人群
    private String buyPercent; // 购买指数
    private Integer programmeId; // 报价方案id
    private ProgrammeInfo programme; // 根据报价方案查询到的报价方案对象
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime; // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime; // 上次修改时间
    private Integer viewState; // 查看状态,1为可查询,0为不可查询

    public InsuranceType() {
        super();
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getApplicable() {
        return applicable;
    }

    public void setApplicable(String applicable) {
        this.applicable = applicable;
    }

    public String getBuyPercent() {
        return buyPercent;
    }

    public void setBuyPercent(String buyPercent) {
        this.buyPercent = buyPercent;
    }

    public Integer getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(Integer programmeId) {
        this.programmeId = programmeId;
    }

    public ProgrammeInfo getProgramme() {
        return programme;
    }

    public void setProgramme(ProgrammeInfo programme) {
        this.programme = programme;
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
        return "InsuranceType [insuranceId=" + insuranceId + ", insuranceName=" + insuranceName + ", example=" + example
                + ", notice=" + notice + ", applicable=" + applicable + ", buyPercent=" + buyPercent + ", programmeId="
                + programmeId + ", programme=" + programme + ", createTime=" + createTime + ", modifyTime=" + modifyTime
                + ", viewState=" + viewState + "]";
    }

}
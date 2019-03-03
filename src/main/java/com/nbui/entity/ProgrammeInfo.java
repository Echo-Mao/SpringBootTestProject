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
public class ProgrammeInfo implements Serializable {

    private static final long serialVersionUID = 2917251037005160457L;

    private Integer programmeId; // 报价方案id
    private Integer percent; // 百分比
    private Double basicsMoney; // 基础保费
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime; // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime; // 上次修改时间

    public ProgrammeInfo() {
        super();
    }

    public Integer getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(Integer programmeId) {
        this.programmeId = programmeId;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Double getBasicsMoney() {
        return basicsMoney;
    }

    public void setBasicsMoney(Double basicsMoney) {
        this.basicsMoney = basicsMoney;
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

    @Override
    public String toString() {
        return "ProgrammeInfo [programmeId=" + programmeId + ", percent=" + percent + ", basicsMoney=" + basicsMoney
                + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
    }

}
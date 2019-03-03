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
public class RelatedPersonnel implements Serializable {

    private static final long serialVersionUID = -1444165312718539174L;

    private Integer personnelId; // 相关人员id
    private String personnelName; // 相关人员姓名
    private Integer paperTypeId; // 证件类型id
    private PaperInfo paperInfo; // 证件对象
    private String paperNum; // 证件号码
    private String phoneNum; // 电话号码
    private String email; // 电子邮箱
    private String address; // 地址
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime; // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime; // 上次修改时间
    private Integer viewState; // 查看状态,1为可查询,0为不可查询

    private String ipProvinceNum; // 省份id
    private Province province; // 根据省id查找到的省对象
    private String ipCity; // 城市id
    private City city; // 根据市id查找到的市对象

    public Integer getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public Integer getPaperTypeId() {
        return paperTypeId;
    }

    public void setPaperTypeId(Integer paperTypeId) {
        this.paperTypeId = paperTypeId;
    }

    public PaperInfo getPaperInfo() {
        return paperInfo;
    }

    public void setPaperInfo(PaperInfo paperInfo) {
        this.paperInfo = paperInfo;
    }

    public String getPaperNum() {
        return paperNum;
    }

    public void setPaperNum(String paperNum) {
        this.paperNum = paperNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getIpProvinceNum() {
        return ipProvinceNum;
    }

    public void setIpProvinceNum(String ipProvinceNum) {
        this.ipProvinceNum = ipProvinceNum;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getIpCity() {
        return ipCity;
    }

    public void setIpCity(String ipCity) {
        this.ipCity = ipCity;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "RelatedPersonnel [personnelId=" + personnelId + ", personnelName=" + personnelName + ", paperTypeId="
                + paperTypeId + ", paperInfo=" + paperInfo + ", paperNum=" + paperNum + ", phoneNum=" + phoneNum
                + ", email=" + email + ", address=" + address + ", createTime=" + createTime + ", modifyTime="
                + modifyTime + ", viewState=" + viewState + ", ipProvinceNum=" + ipProvinceNum + ", province="
                + province + ", ipCity=" + ipCity + ", city=" + city + "]";
    }

}

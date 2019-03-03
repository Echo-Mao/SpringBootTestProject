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
public class EmpInfo implements Serializable {

    private static final long serialVersionUID = 6832854933742611800L;

    private Integer empId; // 员工id
    private Integer loginId; // 员工工号
    private String loginPwd; // 员工登录密码
    private Integer roleId; // 员工角色id
    private Role role; // 根据员工角色id查询到的角色对象
    private String empName; // 员工姓名
    private String gender; // 员工性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday; // 员工生日
    private String email; // 员工电子邮箱
    private String phoneNum; // 员工手机号码
    private String paperNum; // 证件号
    private String provinceId; // 省id
    private Province province;
    private String cityId; // 市id
    private City city; // 根据员工的市id查询到的市对象
    private Integer incumbency; // 在职状态,0离职,1在职
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime; // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime; // 上次修改时间
    private Integer viewState; // 查看状态,1为可查询,0为不可查询

    public EmpInfo() {
        super();
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPaperNum() {
        return paperNum;
    }

    public void setPaperNum(String paperNum) {
        this.paperNum = paperNum;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getIncumbency() {
        return incumbency;
    }

    public void setIncumbency(Integer incumbency) {
        this.incumbency = incumbency;
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
        return "EmpInfo [empId=" + empId + ", loginId=" + loginId + ", loginPwd=" + loginPwd + ", roleId=" + roleId
                + ", role=" + role + ", empName=" + empName + ", gender=" + gender + ", birthday=" + birthday
                + ", email=" + email + ", phoneNum=" + phoneNum + ", paperNum=" + paperNum + ", provinceId="
                + provinceId + ", province=" + province + ", cityId=" + cityId + ", city=" + city + ", incumbency="
                + incumbency + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", viewState=" + viewState
                + "]";
    }

}
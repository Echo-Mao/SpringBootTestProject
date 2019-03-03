package com.nbui.employee.condition;


import java.io.Serializable;

public class EmpCondition implements Serializable{
	/**
	 * 员工信息查询模块
	 * @author 雷石秀
	 * 员工查询的条件类
	 */
	private static final long serialVersionUID = 1L;
	private String empName;//姓名
	private Integer loginId;//工号
	private Integer age;//年龄
	private Integer provinceId;//省
	private Integer cityId;//市
	private String phoneNum;//手机号码
	private Integer roleId;//角色 职位
	private Integer incumbency;//是否在职
	private Integer loginEmpId;//登录员工的Id
	public EmpCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmpCondition(String empName, Integer loginId, Integer age, Integer provinceId, Integer cityId,
			String phoneNum, Integer roleId, Integer incumbency, Integer loginEmpId) {
		super();
		this.empName = empName;
		this.loginId = loginId;
		this.age = age;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.phoneNum = phoneNum;
		this.roleId = roleId;
		this.incumbency = incumbency;
		this.loginEmpId = loginEmpId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getIncumbency() {
		return incumbency;
	}
	public void setIncumbency(Integer incumbency) {
		this.incumbency = incumbency;
	}
	public Integer getLoginEmpId() {
		return loginEmpId;
	}
	public void setLoginEmpId(Integer loginEmpId) {
		this.loginEmpId = loginEmpId;
	}
	@Override
	public String toString() {
		return "EmpCondition [empName=" + empName + ", loginId=" + loginId + ", age=" + age + ", provinceId="
				+ provinceId + ", cityId=" + cityId + ", phoneNum=" + phoneNum + ", roleId=" + roleId + ", incumbency="
				+ incumbency + ", loginEmpId=" + loginEmpId + "]";
	}
	

}
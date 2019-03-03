package com.nbui.user.condition;

public class CustomerCondition {
	
	private String personnelName;
	private String phoneNum;
	private String empName;
	private Integer provinceId;
	private Integer cityId;
	private Integer empId;
	private Integer roleId;
	
	
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getPersonnelName() {
		return personnelName;
	}
	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
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
	@Override
	public String toString() {
		return "CustomerCondition [personnelName=" + personnelName + ", phoneNum=" + phoneNum + ", empName=" + empName
				+ ", provinceId=" + provinceId + ", cityId=" + cityId + ", empId=" + empId + ", roleId=" + roleId + "]";
	}
	
	
}

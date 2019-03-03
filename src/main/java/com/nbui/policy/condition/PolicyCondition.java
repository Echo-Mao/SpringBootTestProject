package com.nbui.policy.condition;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 * @author EchoMao
 * @date 2019年1月15日上午11:32:30 >条件类
 */
@Component
@Scope(scopeName = "prototype")
public class PolicyCondition {

	private Integer policyId; // 保单id
	private String policyNum;// 表单编号
	private String carOwnerName; // 车主姓名
	private String brand; // 品牌名称
	private Integer empId; // 业务员id
	private String empName; // 业务员姓名
	private String provinceId; // 省id
	private String cityId; // 市id
	private Double insuranceAmount; // 保费(投保金额)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate; // 生效时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate; // 失效时间
	private Integer policyStatus; // 保单状态id
	private Integer highPrice;
	private Integer lowPrice;

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public String getCarOwnerName() {
		return carOwnerName;
	}

	public void setCarOwnerName(String carOwnerName) {
		this.carOwnerName = carOwnerName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public Double getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(Double insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(Integer policyStatus) {
		this.policyStatus = policyStatus;
	}

	public Integer getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(Integer highPrice) {
		this.highPrice = highPrice;
	}

	public Integer getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Integer lowPrice) {
		this.lowPrice = lowPrice;
	}

	public String getPolicyNum() {
		return policyNum;
	}

	public void setPolicyNum(String policyNum) {
		this.policyNum = policyNum;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "PolicyCondition [policyId=" + policyId + ", policyNum=" + policyNum + ", carOwnerName=" + carOwnerName
				+ ", brand=" + brand + ", empId=" + empId + ", empName=" + empName + ", provinceId=" + provinceId
				+ ", cityId=" + cityId + ", insuranceAmount=" + insuranceAmount + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", policyStatus=" + policyStatus + ", highPrice=" + highPrice + ", lowPrice="
				+ lowPrice + "]";
	}

}

package com.nbui.entity;

import java.io.Serializable;

public class PolicyInsurance implements Serializable {
	private static final long serialVersionUID = 6165247514461200001L;
	private Integer policyInsuranceId ;
	private Integer policyId ;
	private Integer Insurance ;
	private Double InsurancePrice ;
	
	public Integer getPolicyInsuranceId() {
		return policyInsuranceId;
	}
	public void setPolicyInsuranceId(Integer policyInsuranceId) {
		this.policyInsuranceId = policyInsuranceId;
	}
	public Integer getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}
	public Integer getInsurance() {
		return Insurance;
	}
	public void setInsurance(Integer insurance) {
		Insurance = insurance;
	}
	public Double getInsurancePrice() {
		return InsurancePrice;
	}
	public void setInsurancePrice(Double insurancePrice) {
		InsurancePrice = insurancePrice;
	}
	
	@Override
	public String toString() {
		return "PolicyInsurance [policyInsuranceId=" + policyInsuranceId + ", policyId=" + policyId + ", Insurance="
				+ Insurance + ", InsurancePrice=" + InsurancePrice + "]";
	}
}

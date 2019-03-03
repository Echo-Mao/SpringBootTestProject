package com.nbui.policy.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class InsuranceTypeBrief implements Serializable{
	private static final long serialVersionUID = -7379358587019556520L;
	private String  insuranceName;
	private double insurancePrice;
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public double getInsurancePrice() {
		return insurancePrice;
	}
	public void setInsurancePrice(double insurancePrice) {
		this.insurancePrice = insurancePrice;
	}
	@Override
	public String toString() {
		return "InsuranceTypeBrief [insuranceName=" + insuranceName + ", insurancePrice=" + insurancePrice + "]";
	}
	
}

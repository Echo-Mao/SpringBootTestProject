package com.nbui.policy.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 页面返回表单信息的简版
 * @author xinxin
 *
 */
@Component
public class PolicyBrief implements Serializable{

	private static final long serialVersionUID = 7172744339279083064L;
	private String policyNumber;//订单号(保单)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date policyCreateDate;//订单生效时间
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date billStartDate;//保单生效时间
	private String insuranceName;//保险名称
	private Integer insuranceAmount;//保险总金额
	private String  carplateNumber;//汽车名
	private String ipname;//被保人
	private String policystatus;//订单状态
	public String getPolicystatus() {
		return policystatus;
	}
	public void setPolicystatus(String policystatus) {
		this.policystatus = policystatus;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public Date getPolicyCreateDate() {
		return policyCreateDate;
	}
	public void setPolicyCreateDate(Date policyCreateDate) {
		this.policyCreateDate = policyCreateDate;
	}
	public Date getBillStartDate() {
		return billStartDate;
	}
	public void setBillStartDate(Date billStartDate) {
		this.billStartDate = billStartDate;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public Integer getInsuranceAmount() {
		return insuranceAmount;
	}
	public void setInsuranceAmount(Integer insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}
	public String getCarplateNumber() {
		return carplateNumber;
	}
	public void setCarplateNumber(String carplateNumber) {
		this.carplateNumber = carplateNumber;
	}
	public String getIpname() {
		return ipname;
	}
	public void setIpname(String ipname) {
		this.ipname = ipname;
	}
	@Override
	public String toString() {
		return "PolicyBrief [policyNumber=" + policyNumber + ", policyCreateDate=" + policyCreateDate
				+ ", billStartDate=" + billStartDate + ", insuranceName=" + insuranceName + ", insuranceAmount="
				+ insuranceAmount + ", carplateNumber=" + carplateNumber + ", ipname=" + ipname + "]";
	}
	
}

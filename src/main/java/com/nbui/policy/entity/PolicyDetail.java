package com.nbui.policy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
@Component
public class PolicyDetail implements Serializable{
	private static final long serialVersionUID = 189016103543673337L;
	//保单部分
	private String policyNumber;//保单号
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date billStartDate;//保单生效时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date billEndDate;//保单失效时间
	private Integer billLong;//保单期间
	
	//相关人员部分
	//姓名
	private String applicantName;//投保人姓名
	private String insuredPersonName;//被保人姓名
	private String carOwnerName;//车主姓名
	//手机
	private String applicantPhone;//投保人手机
	private String insuredPersonPhone;//被保人手机
	private String carOwnerPhone;//车主手机
	//证件类型
	private String applicantPaperType;//投保人证件类型
	private String insuredPersonPaperType;//被保人证件类型
	private String carOwnerPaperType;//车主证件类型
	//证件号码
	private String applicantPaperNum;//投保人证件号码
	private String insuredPersonPaperNum;//被保人证件号码
	private String carOwnerPaperNum;//车主证件号码
	
	//险种部分
	private List<InsuranceTypeBrief> insuranceList;
	private Integer insuranceAmount;//保险总价
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public Date getBillStartDate() {
		return billStartDate;
	}
	public void setBillStartDate(Date billStartDate) {
		this.billStartDate = billStartDate;
	}
	public Date getBillEndDate() {
		return billEndDate;
	}
	public void setBillEndDate(Date billEndDate) {
		this.billEndDate = billEndDate;
	}
	public Integer getBillLong() {
		return billLong;
	}
	public void setBillLong(Integer billLong) {
		this.billLong = billLong;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getInsuredPersonName() {
		return insuredPersonName;
	}
	public void setInsuredPersonName(String insuredPersonName) {
		this.insuredPersonName = insuredPersonName;
	}
	public String getCarOwnerName() {
		return carOwnerName;
	}
	public void setCarOwnerName(String carOwnerName) {
		this.carOwnerName = carOwnerName;
	}
	public String getApplicantPhone() {
		return applicantPhone;
	}
	public void setApplicantPhone(String applicantPhone) {
		this.applicantPhone = applicantPhone;
	}
	public String getInsuredPersonPhone() {
		return insuredPersonPhone;
	}
	public void setInsuredPersonPhone(String insuredPersonPhone) {
		this.insuredPersonPhone = insuredPersonPhone;
	}
	public String getCarOwnerPhone() {
		return carOwnerPhone;
	}
	public void setCarOwnerPhone(String carOwnerPhone) {
		this.carOwnerPhone = carOwnerPhone;
	}
	public String getApplicantPaperType() {
		return applicantPaperType;
	}
	public void setApplicantPaperType(String applicantPaperType) {
		this.applicantPaperType = applicantPaperType;
	}
	public String getInsuredPersonPaperType() {
		return insuredPersonPaperType;
	}
	public void setInsuredPersonPaperType(String insuredPersonPaperType) {
		this.insuredPersonPaperType = insuredPersonPaperType;
	}
	public String getCarOwnerPaperType() {
		return carOwnerPaperType;
	}
	public void setCarOwnerPaperType(String carOwnerPaperType) {
		this.carOwnerPaperType = carOwnerPaperType;
	}
	public String getApplicantPaperNum() {
		return applicantPaperNum;
	}
	public void setApplicantPaperNum(String applicantPaperNum) {
		this.applicantPaperNum = applicantPaperNum;
	}
	public String getInsuredPersonPaperNum() {
		return insuredPersonPaperNum;
	}
	public void setInsuredPersonPaperNum(String insuredPersonPaperNum) {
		this.insuredPersonPaperNum = insuredPersonPaperNum;
	}
	public String getCarOwnerPaperNum() {
		return carOwnerPaperNum;
	}
	public void setCarOwnerPaperNum(String carOwnerPaperNum) {
		this.carOwnerPaperNum = carOwnerPaperNum;
	}
	public List<InsuranceTypeBrief> getInsuranceList() {
		return insuranceList;
	}
	public void setInsuranceList(List<InsuranceTypeBrief> insuranceList) {
		this.insuranceList = insuranceList;
	}
	public Integer getInsuranceAmount() {
		return insuranceAmount;
	}
	public void setInsuranceAmount(Integer insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}
	@Override
	public String toString() {
		return "PolicyDetail [policyNumber=" + policyNumber + ", billStartDate=" + billStartDate + ", billEndDate="
				+ billEndDate + ", billLong=" + billLong + ", applicantName=" + applicantName + ", insuredPersonName="
				+ insuredPersonName + ", carOwnerName=" + carOwnerName + ", applicantPhone=" + applicantPhone
				+ ", insuredPersonPhone=" + insuredPersonPhone + ", carOwnerPhone=" + carOwnerPhone
				+ ", applicantPaperType=" + applicantPaperType + ", insuredPersonPaperType=" + insuredPersonPaperType
				+ ", carOwnerPaperType=" + carOwnerPaperType + ", applicantPaperNum=" + applicantPaperNum
				+ ", insuredPersonPaperNum=" + insuredPersonPaperNum + ", carOwnerPaperNum=" + carOwnerPaperNum
				+ ", insuranceList=" + insuranceList + ", insuranceAmount=" + insuranceAmount + "]";
	}
	
}

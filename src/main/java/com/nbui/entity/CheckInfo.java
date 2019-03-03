package com.nbui.entity;
//审核表

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ggz 审核表
 */
@Component
@Scope(scopeName = "prototype")
public class CheckInfo implements Serializable {
	private static final long serialVersionUID = 4147693368901472469L;
	private Integer resourceId;// 资料id
	private String idCard;// 身份证照片路径
	private String driverlicense;// 驾驶证照片路径
	private String drivingPermit;// 行驶证照片路径
	private String annualReport;// 年审报告照片路径
	private String vehicleinSpection;// 年检报告照片路径
	private String frame;// 车架号照片
	private String engine;// 发动机照片
	private String agreement;// 协议书照片
	private Integer policyId;// 保单id
	private PolicyInfo policy; // 保单实体

	private String paperType;// 证件类型
	private String paperNum;// 证件号
	private String engineNum;// 发动机编号
	private String frameNum;// 车架号

	private PolicyInfo policyInfo;// 保单映射类

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getDriverlicense() {
		return driverlicense;
	}

	public void setDriverlicense(String driverlicense) {
		this.driverlicense = driverlicense;
	}

	public String getDrivingPermit() {
		return drivingPermit;
	}

	public void setDrivingPermit(String drivingPermit) {
		this.drivingPermit = drivingPermit;
	}

	public String getAnnualReport() {
		return annualReport;
	}

	public void setAnnualReport(String annualReport) {
		this.annualReport = annualReport;
	}

	public String getVehicleinSpection() {
		return vehicleinSpection;
	}

	public void setVehicleinSpection(String vehicleinSpection) {
		this.vehicleinSpection = vehicleinSpection;
	}

	public String getFrame() {
		return frame;
	}

	public void setFrame(String frame) {
		this.frame = frame;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getPaperNum() {
		return paperNum;
	}

	public void setPaperNum(String paperNum) {
		this.paperNum = paperNum;
	}

	public String getEngineNum() {
		return engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}

	public String getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(String frameNum) {
		this.frameNum = frameNum;
	}

	public PolicyInfo getPolicyInfo() {
		return policyInfo;
	}

	public void setPolicyInfo(PolicyInfo policyInfo) {
		this.policyInfo = policyInfo;
	}

	public PolicyInfo getPolicy() {
		return policy;
	}

	public void setPolicy(PolicyInfo policy) {
		this.policy = policy;
	}

	@Override
	public String toString() {
		return "CheckInfo [resourceId=" + resourceId + ", idCard=" + idCard + ", driverlicense=" + driverlicense
				+ ", drivingPermit=" + drivingPermit + ", annualReport=" + annualReport + ", vehicleinSpection="
				+ vehicleinSpection + ", frame=" + frame + ", engine=" + engine + ", agreement=" + agreement
				+ ", policyId=" + policyId + ", policy=" + policy + ", paperType=" + paperType + ", paperNum="
				+ paperNum + ", engineNum=" + engineNum + ", frameNum=" + frameNum + ", policyInfo=" + policyInfo + "]";
	}

}

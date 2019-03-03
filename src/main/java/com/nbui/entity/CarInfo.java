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
public class CarInfo implements Serializable {

	private static final long serialVersionUID = 2419752353744857581L;

	private Integer carId; // 车辆信息id
	private Integer carOwnerId; // 车主id
	private RelatedPersonnel carOwner; // 根据车主id查询出的车主信息
	private Integer brandId; // 品牌id
	private BrandInfo brandInfo;// 品牌对象
	private String brandName;
	private String versionName; 
	private Integer versionId;// 型号id
	private VersionInfo versionInfo; // 型号对象
	private String plateNum; // 车牌号
	private String frameNum; // 车架号
	private String engineNum; // 发动机号
	private String carType; // 车辆类型
	private Integer busLoad; // 载员数
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date registerDate; // 车辆注册时间
	private String hasRefit; // 是否改装
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date annualReviewDate; // 年检时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime; // 创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date modifyTime; // 上次修改时间
	private Integer viewState; // 查看状态,1为可查询,0为不可查询

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getCarOwnerId() {
		return carOwnerId;
	}

	public void setCarOwnerId(Integer carOwnerId) {
		this.carOwnerId = carOwnerId;
	}

	public RelatedPersonnel getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(RelatedPersonnel carOwner) {
		this.carOwner = carOwner;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public BrandInfo getBrandInfo() {
		return brandInfo;
	}

	public void setBrandInfo(BrandInfo brandInfo) {
		this.brandInfo = brandInfo;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public VersionInfo getVersionInfo() {
		return versionInfo;
	}

	public void setVersionInfo(VersionInfo versionInfo) {
		this.versionInfo = versionInfo;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(String frameNum) {
		this.frameNum = frameNum;
	}

	public String getEngineNum() {
		return engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Integer getBusLoad() {
		return busLoad;
	}

	public void setBusLoad(Integer busLoad) {
		this.busLoad = busLoad;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getHasRefit() {
		return hasRefit;
	}

	public void setHasRefit(String hasRefit) {
		this.hasRefit = hasRefit;
	}

	public Date getAnnualReviewDate() {
		return annualReviewDate;
	}

	public void setAnnualReviewDate(Date annualReviewDate) {
		this.annualReviewDate = annualReviewDate;
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
		return "CarInfo [carId=" + carId + ", carOwnerId=" + carOwnerId + ", carOwner=" + carOwner + ", brandId="
				+ brandId + ", brandInfo=" + brandInfo + ", brandName=" + brandName + ", versionName=" + versionName
				+ ", versionId=" + versionId + ", versionInfo=" + versionInfo + ", plateNum=" + plateNum + ", frameNum="
				+ frameNum + ", engineNum=" + engineNum + ", carType=" + carType + ", busLoad=" + busLoad
				+ ", registerDate=" + registerDate + ", hasRefit=" + hasRefit + ", annualReviewDate=" + annualReviewDate
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", viewState=" + viewState + "]";
	}
}

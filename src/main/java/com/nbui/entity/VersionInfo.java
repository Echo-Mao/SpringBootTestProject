package com.nbui.entity;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author ggz
 * 车辆型号表
 */
@Component
@Scope(scopeName = "prototype")
public class VersionInfo implements Serializable{
	private static final long serialVersionUID = -4469922933681645277L;
	private Integer versionId;// 车辆型号id
	private String versionName;// 车辆型号名称
	private Integer brandId;// 车辆品牌id
	private Double carOffer;// 型号报价

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Double getCarOffer() {
		return carOffer;
	}

	public void setCarOffer(Double carOffer) {
		this.carOffer = carOffer;
	}

	@Override
	public String toString() {
		return "VersionInfo [versionId=" + versionId + ", versionName=" + versionName + ", brandId=" + brandId
				+ ", carOffer=" + carOffer + "]";
	}

}

package com.nbui.policy.entity;
import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class UserInfoBrief implements Serializable{
	private static final long serialVersionUID = 6301540670081871392L;
	private Integer loginId ;// 用户id
	    private String userName; // 用户名
	    private String gender; // 用户性别
	    private Integer paperTypeId; // 证件类型id
	    private String paperType;   //证件类型名称
	    private String paperNum; // 证件号码
	    private String provinceId; // 省id
	    private String province;
	    private String cityId; // 市id
	    private String city; // 城市名
	    private String address; // 用户详细地址
		public Integer getLoginId() {
			return loginId;
		}
		public void setLoginId(Integer loginId) {
			this.loginId = loginId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public Integer getPaperTypeId() {
			return paperTypeId;
		}
		public void setPaperTypeId(Integer paperTypeId) {
			this.paperTypeId = paperTypeId;
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
		public String getProvinceId() {
			return provinceId;
		}
		public void setProvinceId(String provinceId) {
			this.provinceId = provinceId;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public String getCityId() {
			return cityId;
		}
		public void setCityId(String cityId) {
			this.cityId = cityId;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		@Override
		public String toString() {
			return "UserInfoBrief [LoginId=" + loginId + ", userName=" + userName + ", gender=" + gender
					+ ", paperTypeId=" + paperTypeId + ", paperType=" + paperType + ", paperNum=" + paperNum
					+ ", provinceId=" + provinceId + ", province=" + province + ", cityId=" + cityId + ", city=" + city
					+ ", address=" + address + "]";
		}
	    

	   
}

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
public class PolicyInfo implements Serializable {

    private static final long serialVersionUID = 3486303042167352694L;

    private Integer policyId; // 保单id
    private String policyNum; // 保单号
    private Integer policyHolderId; // 投保人id
    private RelatedPersonnel policyHolder; // 根据投保人id查询到的投保人对象
    private Integer insurantId; // 被保人id
    private RelatedPersonnel insurant; // 根据被保人id查询到的被保人对象
    private Integer carOwnerId; // 车主id
    private RelatedPersonnel carOwner; // 根据车主id查询到的车主对象
    private Integer empId; // 跟单员id
    private EmpInfo operator; // 根据跟单员id查询到的员工对象
    private Integer userId; // 用户id(办理人id)
    private UserInfo transactor; // 根据被保人id查询到的办理人对象
    private Integer carId; // 车辆id
    private CarInfo carInfo;
    private Double insuranceAmount; // 投保金额
    private String insuranceTypes; // 所选险种
    private String insuranceCompany; // 所投保险公司
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date billStartDate; // 保单生效时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date billEndDate; // 保单失效时间
    private Integer policyStatus; // 保单状态id
    private PolicyStatus statusInfo; // 根据保单状态id查询到的保单状态对象
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime; // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime; // 上次修改时间
    private Integer viewState; // 查看状态,1为可查询,0为不可查询
    
    private PaperInfo paperInfo;
    private Province province;
    private City city;
    
	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public PaperInfo getPaperInfo() {
		return paperInfo;
	}

	public void setPaperInfo(PaperInfo paperInfo) {
		this.paperInfo = paperInfo;
	}

	public PolicyInfo() {
        super();
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public String getPolicyNum() {
        return policyNum;
    }

    public void setPolicyNum(String policyNum) {
        this.policyNum = policyNum;
    }

    public Integer getPolicyHolderId() {
        return policyHolderId;
    }

    public void setPolicyHolderId(Integer policyHolderId) {
        this.policyHolderId = policyHolderId;
    }

    public RelatedPersonnel getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(RelatedPersonnel policyHolder) {
        this.policyHolder = policyHolder;
    }

    public Integer getInsurantId() {
        return insurantId;
    }

    public void setInsurantId(Integer insurantId) {
        this.insurantId = insurantId;
    }

    public RelatedPersonnel getInsurant() {
        return insurant;
    }

    public void setInsurant(RelatedPersonnel insurant) {
        this.insurant = insurant;
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

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public EmpInfo getOperator() {
        return operator;
    }

    public void setOperator(EmpInfo operator) {
        this.operator = operator;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserInfo getTransactor() {
        return transactor;
    }

    public void setTransactor(UserInfo transactor) {
        this.transactor = transactor;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
    }

    public Double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(Double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public String getInsuranceTypes() {
        return insuranceTypes;
    }

    public void setInsuranceTypes(String insuranceTypes) {
        this.insuranceTypes = insuranceTypes;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
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

    public Integer getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(Integer policyStatus) {
        this.policyStatus = policyStatus;
    }

    public PolicyStatus getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(PolicyStatus statusInfo) {
        this.statusInfo = statusInfo;
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
		return "PolicyInfo [policyId=" + policyId + ", policyNum=" + policyNum + ", policyHolderId=" + policyHolderId
				+ ", policyHolder=" + policyHolder + ", insurantId=" + insurantId + ", insurant=" + insurant
				+ ", carOwnerId=" + carOwnerId + ", carOwner=" + carOwner + ", empId=" + empId + ", operator="
				+ operator + ", userId=" + userId + ", transactor=" + transactor + ", carId=" + carId + ", carInfo="
				+ carInfo + ", insuranceAmount=" + insuranceAmount + ", insuranceTypes=" + insuranceTypes
				+ ", insuranceCompany=" + insuranceCompany + ", billStartDate=" + billStartDate + ", billEndDate="
				+ billEndDate + ", policyStatus=" + policyStatus + ", statusInfo=" + statusInfo + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + ", viewState=" + viewState + ", paperInfo=" + paperInfo
				+ ", province=" + province + ", city=" + city + "]";
	}

    

}
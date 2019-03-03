package com.nbui.user.condition;

public class UserCondition {

    private Integer policyId; // 保单号
    private String productName; // 产品名称
    private String insurant; // 被保人姓名
    private String plateNum; // 车牌号

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getInsurant() {
        return insurant;
    }

    public void setInsurant(String insurant) {
        this.insurant = insurant;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    @Override
    public String toString() {
        return "UserCondition [policyId=" + policyId + ", productName=" + productName + ", insurant=" + insurant
                + ", plateNum=" + plateNum + "]";
    }

}

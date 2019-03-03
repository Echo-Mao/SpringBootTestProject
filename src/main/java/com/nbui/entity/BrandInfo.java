package com.nbui.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ggz 品牌表
 */
@Component
@Scope(scopeName = "prototype")
public class BrandInfo implements Serializable {

    private static final long serialVersionUID = -1198634759083684660L;

    private Integer brandId;// 品牌id
    private String brandname;// 品牌名称
    private List<VersionInfo> VersionInfos;// 所有型号

    public BrandInfo() {
        super();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public List<VersionInfo> getVersionInfos() {
        return VersionInfos;
    }

    public void setVersionInfos(List<VersionInfo> versionInfos) {
        VersionInfos = versionInfos;
    }

    @Override
    public String toString() {
        return "BrandInfo [brandId=" + brandId + ", brandname=" + brandname + ", VersionInfos=" + VersionInfos + "]";
    }

}

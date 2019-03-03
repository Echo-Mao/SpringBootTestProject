package com.nbui.entity;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author EchoMao
 * @time 2019年1月11日
 * 
 */
@Component
@Scope(scopeName = "prototype")
public class City implements Serializable {

    private static final long serialVersionUID = 4241578170506217790L;

    private Integer cid; // 城市表id
    private String cityId; // 城市id
    private String city; // 城市名
    private String provinceId; // 省id

    public City() {
        super();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public String toString() {
        return "City [cid=" + cid + ", cityId=" + cityId + ", city=" + city + ", provinceId=" + provinceId + "]";
    }

}

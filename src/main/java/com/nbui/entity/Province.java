package com.nbui.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author EchoMao
 * @time 2019年1月11日
 * 
 */
@Component
@Scope(scopeName = "prototype")
public class Province implements Serializable {

    private static final long serialVersionUID = -5303851531847983761L;

    private Integer pid; // 省份表id
    private String provinceId; // 省份id
    private String province; // 省份名称
    private List<City> citys; // 省中市

    public Province() {
        super();
    }

    // TODO 在此处添加需要的构造方法

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(List<City> citys) {
        this.citys = citys;
    }

    @Override
    public String toString() {
        return "Province [pid=" + pid + ", provinceId=" + provinceId + ", province=" + province + ", citys=" + citys
                + "]";
    }

}

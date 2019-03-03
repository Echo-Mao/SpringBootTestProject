package com.nbui.provinceCity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nbui.entity.Province;
import com.nbui.provinceCity.service.IProvinceService;

@RestController
@RequestMapping(value = "/privince")
public class ProvinceController {

    @Autowired
    IProvinceService provinceService;

    /**
     * >查询所有省
     * 
     * @return
     */
    @RequestMapping(value = "/findAllProvince.action")
    public Map<String, Object> findAllProvince() {
        Map<String, Object> provinceMap = new HashMap<>();
        List<Province> provinceList = provinceService.findAll();
        if (provinceList != null) {
            provinceMap.put("provinceList", provinceList);
        }
        return provinceMap;
    }

    /**
     * >按省id查询
     * 
     * @param provinceId
     * @return
     */
    @RequestMapping(value = "/findProvinceById.action")
    public Map<String, Object> findProvinceById(String provinceId) {
        Map<String, Object> provinceMap = new HashMap<>();
        Province province = provinceService.findById(provinceId);
        if (province != null) {
            provinceMap.put("province", province);
        }
        return provinceMap;
    }

}

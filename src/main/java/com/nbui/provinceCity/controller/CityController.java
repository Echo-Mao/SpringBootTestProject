package com.nbui.provinceCity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nbui.entity.City;
import com.nbui.provinceCity.service.ICityService;

@RestController
@RequestMapping(value="/city")
public class CityController {

    @Autowired
    ICityService cityService;
    
    /**
     * >根据省id查询
     * @param provinceId
     * @return
     */
    @RequestMapping(value="/findAllCityByProinceId.action")
    public Map<String, Object> findAllCityByProinceId(String provinceId) {
        Map<String, Object> cityMap = new HashMap<>();
        List<City> cityList = cityService.findAllByProviceId(provinceId);
        if (cityList != null) {
            cityMap.put("cityList", cityList);
        }
        System.out.println("cityList-"+cityList);
        return cityMap;
    }

}

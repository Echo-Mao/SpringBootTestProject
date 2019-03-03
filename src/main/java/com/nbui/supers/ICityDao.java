package com.nbui.supers;

import java.util.List;

import com.nbui.entity.City;

/**
 * @author EchoMao
 * @date 2019年1月12日上午11:44:29
 * 
 */
public interface ICityDao {

    /**
     * >按省id查询城市
     * 
     * @param provinceId
     * @return
     */
    List<City> findCityByProvinceId(String provinceId);

    /**
     * >按城市id查询城市
     * 
     * @return
     */
    City findCityById(String cityId);

}

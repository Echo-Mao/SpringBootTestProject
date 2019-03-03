package com.nbui.provinceCity.dao;

import java.util.List;

import com.nbui.entity.City;

import io.lettuce.core.dynamic.annotation.Param;

/**
 * @author EchoMao
 * @date 2019年1月12日上午11:44:29
 * 
 */
public interface ICityDao {

    /**
     * >查询所有城市
     * 
     * @return
     */
    List<City> findAllCity();

    /**
     * >按省id查询城市
     * 
     * @param provinceId
     * @return
     */
    List<City> findCityByProvinceId(String provinceId);

    /**
     * >按城市id查询城市
     * findCityById
     * @return
     */
    City findCityById(@Param("cityId")String cityId);

}

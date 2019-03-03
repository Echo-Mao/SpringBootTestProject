package com.nbui.provinceCity.dao;

import java.util.List;

import com.nbui.entity.Province;

import io.lettuce.core.dynamic.annotation.Param;

/**
 * @author EchoMao
 * @date 2019年1月12日下午1:34:50
 * 
 */
public interface IProvinceDao {

    /**
     * >查询所有省
     * 
     * @return 返回受到影响的行数,为0失败
     */
    List<Province> queryAllProvince();

    /**
     * >根据省id查询省对象
     * queryProvinceById
     * @param city
     * @return
     */
    Province queryProvinceById(@Param("provinceId")String provinceId);

}

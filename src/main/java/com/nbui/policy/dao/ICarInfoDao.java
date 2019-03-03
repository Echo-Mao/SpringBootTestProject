package com.nbui.policy.dao;

import com.nbui.entity.CarInfo;

/**
 * @author EchoMao
 * @date 2019年1月16日下午5:08:44
 * 
 */
public interface ICarInfoDao {
    
    /**
     * >添加车辆信息
     * @param carInfo
     * @return
     */
    int add(CarInfo carInfo);
    
    

}

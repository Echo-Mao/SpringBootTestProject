package com.nbui.policy.dao;

import java.util.List;

import com.nbui.entity.InsuranceType;
import com.nbui.policy.condition.InsuranceTypeCondition;

public interface IInsuranceDao {

    /**
     * >重载了add方法,添加险种,service层可改名便于理解
     * 
     * @param insurance 创建时将可查看状态设为0
     * @return
     */
    int addInsuranceType(InsuranceType insurance);

    /**
     * >查所有
     * 
     * @return
     */
    List<InsuranceType> findAllInsuranceType();

    /**
     * >根据条件查
     * 
     * @param conditionn
     * @return
     */
    List<InsuranceType> findInsuranceTypeByCondition(InsuranceTypeCondition conditionn);

    /**
     * >根据id查
     */
    InsuranceType findInsuranceTypeById(int insuranceId);

    /**
     * >修改险种的可查看状态为0
     * 
     * @param id
     * @return
     */
    int deleteInsuranceType(int id);

    /**
     * >修改险种信息
     * 
     * @param insurance
     * @return
     */
    int updateInsuranceType(InsuranceType insurance);

}

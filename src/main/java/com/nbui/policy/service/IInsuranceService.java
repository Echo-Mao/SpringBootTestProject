package com.nbui.policy.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.nbui.entity.InsuranceType;
import com.nbui.policy.condition.InsuranceTypeCondition;

public interface IInsuranceService {

    /**
     * >重载了add方法,添加险种
     * 
     * @param insurance 创建时将可查看状态设为0
     * @return
     * @throws Exception
     */
    int addInsuranceType(InsuranceType insurance) throws Exception;

    /**
     * >查所有
     */
    List<InsuranceType> findAllInsuranceType();

    /**
     * >根据条件查
     * 
     * @param conditionn
     * @return
     */
    PageInfo<InsuranceType> findInsuranceTypeByCondition(InsuranceTypeCondition condition, Integer page, Integer size);

    /**
     * >根据id查
     */
    InsuranceType findInsuranceTypeById(int insuranceId);

    /**
     * >修改险种的可查看状态为0
     * 
     * @param id
     * @return
     * @throws Exception
     */
    int deleteInsuranceType(String[] ids) throws Exception;

    /**
     * >修改险种信息
     * 
     * @param insurance
     * @return
     * @throws Exception
     */
    int updateInsuranceType(InsuranceType insurance) throws Exception;

    /**
     * >导出excel
     * 
     * @param response
     * @param condition
     */
    void exportExcel(HttpServletResponse response, InsuranceTypeCondition condition);

}

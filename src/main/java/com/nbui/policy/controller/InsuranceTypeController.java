package com.nbui.policy.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.nbui.entity.InsuranceType;
import com.nbui.policy.condition.InsuranceTypeCondition;
import com.nbui.policy.service.IInsuranceService;
import com.nbui.util.ret.RetResponse;
import com.nbui.util.ret.RetResult;

/**
 * @author EchoMao
 * @date 2019年1月17日下午5:09:00
 * 
 */
@RestController
@RequestMapping(value = "/insuranceType")
public class InsuranceTypeController {

    @Autowired
    IInsuranceService insuranceService;

    /**
     * >分页与多条件查询
     * 
     * @param conditionn 传入的条件参数
     * @param page       当前页数
     * @param size       当前页容量
     * @return RetResult<PageInfo<InsuranceType>> 统一返回的数据格式
     */
    @RequestMapping(value = "/insuranceTypeList.action")
    public RetResult<PageInfo<InsuranceType>> toInsuranceTypeList(InsuranceTypeCondition condition,
            @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) { // 将page及size的默认值设为0,以防空指针
        // 使用pagehelper帮助分页
        PageInfo<InsuranceType> pageInfo = insuranceService.findInsuranceTypeByCondition(condition, page, size);
        return RetResponse.makeOKRsp(pageInfo);
    }

    /**
     * >添加险种方法
     * 
     * @param insurance 新增的险种对象
     * @return 统一返回的数据格式
     */
    @RequestMapping(value = "/addInsuranceType.action")
    public RetResult<Integer> addInsuranceType(InsuranceType insurance) {
        Date now = new Date();
        insurance.setCreateTime(now); // 设置创建时间为当前
        insurance.setModifyTime(now); // 设置最后修改时间为当前
        int flag = 0;
        try {
            flag = insuranceService.addInsuranceType(insurance);
        } catch (Exception e) {
            System.err.println("错误-1445 : 添加方法出现错误,已执行操作将进行回滚.");
        }
        // 判断添加操作是否成功
        if (flag > 0) {
            return RetResponse.makeRsp(200, "Option - Add : SUCCESS", flag);
        }
        return RetResponse.makeRsp(500, "Option - Add : FAIL", flag);
    }

    /**
     * >根据删除险种
     * 
     * @param ids 前端传来的String类型数组
     * @return 统一返回的数据格式
     */
    @RequestMapping(value = "/deleteInsuranceType.action")
    public RetResult<Integer> deleteInsuranceType(String[] ids) {
        int flag = 0;
        try {
            flag = insuranceService.deleteInsuranceType(ids);
        } catch (Exception e) {
            System.err.println("错误-4595 : 删除方法出现错误,已执行操作将进行回滚.");
        }
        // 判断删除(修改)操作是否成功
        if (flag > 0) {
            return RetResponse.makeRsp(200, "Option - Del : SUCCESS", flag);
        }
        return RetResponse.makeRsp(500, "Option - Del : FAIL", flag);
    }

    /**
     * >修改险种
     * 
     * @param insurance 修改后的从前台获取到的险种对象
     * @return 统一返回的数据格式
     */
    @RequestMapping(value = "/updateInsuranceType.action")
    public RetResult<Integer> updateInsuranceType(InsuranceType insurance) {
        // 设置最后修改时间为当前
        insurance.setModifyTime(new Date());
        int flag = 0;
        try {
            flag = insuranceService.updateInsuranceType(insurance);
        } catch (Exception e) {
            System.err.println("错误-9941 : 修改方法出现错误,已执行操作将进行回滚.");
        }
        // 判断修改操作是否成功
        if (flag > 0) {
            return RetResponse.makeRsp(200, "Option - Update : SUCCESS", flag);
        }
        return RetResponse.makeRsp(500, "Option - Update : FAIL", flag);
    }

    /**
     * >根据id查找险种,是前往修改页面的中间控制器
     * 
     * @param id 从location获取到的id
     * @return 统一返回的数据格式
     */
    @RequestMapping(value = "/findInsuranceById.action")
    public RetResult<InsuranceType> findInsuranceById(Integer insuranceId) {
        if (insuranceId != null) {
            InsuranceType insuranceType = insuranceService.findInsuranceTypeById(insuranceId);
            if (insuranceType != null) {
                return RetResponse.makeOKRsp(insuranceType);
            }
            return RetResponse.makeRsp(500, "RESULT IS NULL!");
        } else {
            return RetResponse.makeRsp(500, "PARAM IS NULL!");
        }

    }

    /**
     * >导出Excel
     * 
     * @param response
     * @param condition
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcel.action")
    public void exportExcel(HttpServletResponse response, InsuranceTypeCondition condition) throws Exception {
        insuranceService.exportExcel(response, condition);
    }

}

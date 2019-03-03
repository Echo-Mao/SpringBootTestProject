package com.nbui.employee.dao;

import java.util.List;


import com.nbui.employee.condition.EmpCondition;
import com.nbui.entity.EmpInfo;

import io.lettuce.core.dynamic.annotation.Param;

/**
 * @author EchoMao
 * @time 2019年1月11日
 * 
 */
public interface IEmpDao {

    /**
     * >员工登录方法
     * 
     * @return 查找到的员工信息对象
     */
    EmpInfo findEmpByNameAndPwd(EmpInfo empInfo);

    /**
     * >修改员工密码
     * 
     * @param empInfo
     * @return
     */
    Integer updateEmpPassword(EmpInfo empInfo);

    /**
     * >删除方法,将指定记录的可查看状态修改为0
     * 
     * @param id 这是empId
     * @return 返回受到影响的行数
     */
    Integer deleteByEmpId(@Param("empId")Integer empId);

    /**
     * >查询所有
     */
    List<EmpInfo> findAllEmpInfo();

    

    /**
     * >职员注册
     * 
     * @param empInfo
     * @return
     */
    Integer addEmpInfo(EmpInfo empInfo);
    
    
 //添加部分
   /*  
    * 根据条件查询所有
    */
    List<EmpInfo> findAll(EmpCondition empCondition);

    /*
     * 根据名字和电话查询，用于添加  以防重复添加一个员工的信息
     */
	EmpInfo findByNameAndPhone(EmpInfo empInfo);
	/*
	 * 修改员工权限    
	 */
	Integer updateRoleId(EmpInfo empInfo);
	
	/**
     * >根据id查,service层可改名方便理解
     */
	EmpInfo findByEmpId(@Param("empId")Integer empId);

	Integer updateEmp(EmpInfo empInfo);

}
